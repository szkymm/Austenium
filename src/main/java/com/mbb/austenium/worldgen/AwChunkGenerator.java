/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.worldgen.AwChunkGenerator
 * TYPE: Java Source
 * DESCRIPTION: Chunk generator of the Austeniumcraft World layered mining dimension.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.worldgen;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

/**
 * AWCHUNKGENERATOR CLASS IS CORE PART OF [MBB] AUSTENIUM AwChunkGenerator.java.
 *
 * com.mbb.austenium.worldgen.AwChunkGenerator:
 *     Builds the Austeniumcraft World as one solid layered mass. The vanilla noise
 *     pipeline still decides solid rock versus cave void, because the noise router of
 *     mbb_austenium:aw keeps the vanilla cave fields; this generator then rewrites the
 *     stone of every solid cell into the band material of its height, so the same
 *     dimension holds a deepslate, stone, netherrack and end stone layer without a
 *     fluid anywhere. The band boundaries drift by three to eight blocks per column so
 *     the transitions read as geology rather than as flat planes.
 *
 * ATTRIBUTES:
 *     CODEC (Codec<AwChunkGenerator>): Serializer referenced by the dimension JSON.
 *     BEDROCK_Y (int): Height of the single bedrock floor layer.
 *     DEEPSLATE_TOP_Y (int): Nominal highest deepslate layer.
 *     STONE_TOP_Y (int): Nominal highest stone layer.
 *     NETHERRACK_TOP_Y (int): Nominal highest netherrack layer.
 *     BLEND_MIN (int): Smallest boundary drift in blocks.
 *     BLEND_MAX (int): Largest boundary drift in blocks.
 *
 * USAGE:
 *     Referenced as the generator type mbb_austenium:aw from
 *     data/mbb_austenium/dimension/austeniumcraft_world.json.
 *
 * WARNING:
 *     The band limits must stay in sync with the dimension type height; the audit in
 *     tools/audit_tree.py checks the pair.
 */
public class AwChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<AwChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
        NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(NoiseBasedChunkGenerator::generatorSettings))
        .apply(instance, instance.stable(AwChunkGenerator::new)));

    private static final int BEDROCK_Y = AwDimensionRules.BEDROCK_Y;
    private static final int DEEPSLATE_TOP_Y = AwDimensionRules.DEEPSLATE_TOP_Y;
    private static final int STONE_TOP_Y = AwDimensionRules.STONE_TOP_Y;
    private static final int NETHERRACK_TOP_Y = AwDimensionRules.NETHERRACK_TOP_Y;
    private static final int BLEND_MIN = 3;
    private static final int BLEND_MAX = 8;
    private static final int BLEND_SALT_DEEPSLATE = 11;
    private static final int BLEND_SALT_STONE = 23;
    private static final int BLEND_SALT_NETHERRACK = 37;
    private static final ResourceLocation STRONGHOLD_ID =
        ResourceLocation.fromNamespaceAndPath("minecraft", "stronghold");

    /**
     * Creates the Austeniumcraft World generator.
     *
     * @param biomeSource the fixed biome source of the dimension
     * @param settings the noise settings holder whose router defines the cave field
     */
    public AwChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        // The vanilla noise generator owns the cave field, so only the materials differ.
        super(biomeSource, settings);
    }

    /** {@inheritDoc} */
    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    /** {@inheritDoc} */
    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState,
            StructureManager structureManager, ChunkAccess chunkAccess) {
        // The vanilla pass fills the solid mass and carves every cave void first.
        return super.fillFromNoise(executor, blender, randomState, structureManager, chunkAccess)
            .thenApply(this::applyBandMaterials);
    }

    /** {@inheritDoc} */
    @Override
    public ChunkGeneratorStructureState createState(HolderLookup<StructureSet> structureSetLookup,
            RandomState randomState, long seed) {
        // Hiding the stronghold set keeps every End portal room out of the Austeniumcraft World.
        HolderLookup<StructureSet> allowedSets = structureSetLookup.filterElements(set -> set.structures().stream()
            .noneMatch(entry -> entry.structure().unwrapKey()
                .map(key -> key.location().equals(STRONGHOLD_ID)).orElse(false)));
        return ChunkGeneratorStructureState.createForNormal(randomState, seed, this.getBiomeSource(), allowedSets);
    }

    /** {@inheritDoc} */
    @Override
    public void buildSurface(WorldGenRegion region, StructureManager structureManager, RandomState randomState,
            ChunkAccess chunkAccess) {
        // Skipping the surface pass keeps the band cake free of soil, grass and fluid.
    }

    /**
     * Rewrites the stone of every solid cell into the band material of its height.
     *
     * @param chunkAccess the freshly noise-filled chunk
     * @return the same chunk, with the band materials in place
     */
    private ChunkAccess applyBandMaterials(ChunkAccess chunkAccess) {
        LevelChunkSection[] sections = chunkAccess.getSections();
        int minBuildHeight = chunkAccess.getMinBuildHeight();
        ChunkPos chunkPos = chunkAccess.getPos();
        for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
            LevelChunkSection section = sections[sectionIndex];
            int sectionBottomY = minBuildHeight + (sectionIndex << 4);
            // The bedrock layer must be written even where the whole section was carved to air.
            boolean holdsBedrock = sectionBottomY <= BEDROCK_Y && BEDROCK_Y <= sectionBottomY + 15;
            if ((section == null || section.hasOnlyAir()) && !holdsBedrock) {
                continue;
            }
            // Sections that sit far inside the stone band can never hold another material.
            if (sectionBottomY >= BLEND_MAX && sectionBottomY + 15 <= STONE_TOP_Y - BLEND_MAX) {
                continue;
            }
            rewriteSection(section, sectionBottomY, chunkPos);
        }
        return chunkAccess;
    }

    /**
     * Replaces the noise default block of one section by the band material of each cell.
     *
     * @param section the section to rewrite
     * @param sectionBottomY the world y of the section's lowest layer
     * @param chunkPos the chunk position that anchors the column coordinates
     */
    private void rewriteSection(LevelChunkSection section, int sectionBottomY, ChunkPos chunkPos) {
        for (int localX = 0; localX < 16; localX++) {
            int worldX = chunkPos.getMinBlockX() + localX;
            for (int localZ = 0; localZ < 16; localZ++) {
                int worldZ = chunkPos.getMinBlockZ() + localZ;
                // Each column drifts its own boundaries, which keeps the layer seams uneven.
                int deepslateTop = DEEPSLATE_TOP_Y + boundaryDither(worldX, worldZ, BLEND_SALT_DEEPSLATE);
                int stoneTop = STONE_TOP_Y + boundaryDither(worldX, worldZ, BLEND_SALT_STONE);
                int netherrackTop = NETHERRACK_TOP_Y + boundaryDither(worldX, worldZ, BLEND_SALT_NETHERRACK);
                rewriteColumn(section, sectionBottomY, localX, localZ, deepslateTop, stoneTop, netherrackTop);
            }
        }
    }

    /**
     * Replaces the noise default block of one column inside a section.
     *
     * @param section the section to rewrite
     * @param sectionBottomY the world y of the section's lowest layer
     * @param localX the section local x of the column
     * @param localZ the section local z of the column
     * @param deepslateTop the drifted top of the deepslate band
     * @param stoneTop the drifted top of the stone band
     * @param netherrackTop the drifted top of the netherrack band
     */
    private void rewriteColumn(LevelChunkSection section, int sectionBottomY, int localX, int localZ,
            int deepslateTop, int stoneTop, int netherrackTop) {
        for (int localY = 0; localY < 16; localY++) {
            int worldY = sectionBottomY + localY;
            // The floor layer stays bedrock even where a cave void reached it.
            if (worldY <= BEDROCK_Y) {
                section.setBlockState(localX, localY, localZ, Blocks.BEDROCK.defaultBlockState(), false);
                continue;
            }
            BlockState bandState = bandMaterial(worldY, deepslateTop, stoneTop, netherrackTop);
            if (bandState == null) {
                continue;
            }
            // Air and the ore blocks of the vein pass must survive the rewrite untouched.
            if (section.getBlockState(localX, localY, localZ).is(Blocks.STONE)) {
                section.setBlockState(localX, localY, localZ, bandState, false);
            }
        }
    }

    /**
     * Resolves the band material of one height for one column.
     *
     * @param worldY the world y to resolve
     * @param deepslateTop the drifted top of the deepslate band
     * @param stoneTop the drifted top of the stone band
     * @param netherrackTop the drifted top of the netherrack band
     * @return the block state of that band, or null when the height keeps the stone default
     */
    private BlockState bandMaterial(int worldY, int deepslateTop, int stoneTop, int netherrackTop) {
        if (worldY <= deepslateTop) {
            return Blocks.DEEPSLATE.defaultBlockState();
        }
        if (worldY <= stoneTop) {
            return null;
        }
        if (worldY <= netherrackTop) {
            return Blocks.NETHERRACK.defaultBlockState();
        }
        return Blocks.END_STONE.defaultBlockState();
    }

    /**
     * Returns the deterministic signed drift of one boundary at one column.
     *
     * @param worldX the world x of the column
     * @param worldZ the world z of the column
     * @param boundarySalt the salt that keeps the three boundaries independent
     * @return a drift between -BLEND_MAX and -BLEND_MIN or between BLEND_MIN and BLEND_MAX
     */
    private static int boundaryDither(int worldX, int worldZ, int boundarySalt) {
        long seed = Mth.getSeed(worldX, boundarySalt, worldZ);
        int magnitude = BLEND_MIN + (int) Math.floorMod(seed, BLEND_MAX - BLEND_MIN + 1);
        return (seed & 1L) == 0L ? magnitude : -magnitude;
    }
}
