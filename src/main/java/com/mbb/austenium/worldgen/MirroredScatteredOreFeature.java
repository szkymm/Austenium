/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.worldgen.MirroredScatteredOreFeature
 * TYPE: Java Source
 * DESCRIPTION: Scattered ore feature that mirrors every placed block across the y=0 plane.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.worldgen;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

/**
 * MIRROREDSCATTEREDOREFEATURE CLASS IS CORE PART OF [MBB] AUSTENIUM MirroredScatteredOreFeature.java.
 *
 * com.mbb.austenium.worldgen.MirroredScatteredOreFeature:
 *     Replays the vanilla scattered ore loop and writes a mirrored copy of every
 *     block it places at (x, -y, z) in the same pass, so the radiant debris
 *     distribution stays exactly symmetric about the y=0 plane without scanning
 *     the surrounding blocks.
 *
 * ATTRIBUTES:
 *     MAX_DIST_FROM_ORIGIN (int): Largest offset the vanilla scatter loop may use.
 *
 * USAGE:
 *     Registered as the feature type mbb_austenium:mirrored_scattered_ore and used
 *     by the radiant debris configured features; configuration stays vanilla
 *     OreConfiguration (size, discard chance and targets).
 */
public class MirroredScatteredOreFeature extends Feature<OreConfiguration> {

    private static final int MAX_DIST_FROM_ORIGIN = 7;

    /**
     * Creates the MirroredScatteredOreFeature instance.
     *
     * @param codec the codec argument
     */
    public MirroredScatteredOreFeature(Codec<OreConfiguration> codec) {
        super(codec);
    }

    /** {@inheritDoc} */
    @Override
    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        OreConfiguration config = context.config();
        BlockPos origin = context.origin();
        int minimumY = level.getMinBuildHeight();
        int maximumY = level.getMaxBuildHeight();
        int blockCount = random.nextInt(config.size + 1);
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        // A private random keeps the mirrored checks from disturbing the vanilla random sequence.
        RandomSource mirrorRandom = RandomSource.create(0L);
        // Replay the vanilla scatter loop so every placed block can be mirrored immediately.
        for (int index = 0; index < blockCount; index++) {
            offsetTargetPos(cursor, random, origin, Math.min(index, MAX_DIST_FROM_ORIGIN));
            BlockState currentState = level.getBlockState(cursor);
            for (OreConfiguration.TargetBlockState targetState : config.targetStates) {
                if (OreFeature.canPlaceOre(currentState, level::getBlockState, random, config, targetState, cursor)) {
                    level.setBlock(cursor, targetState.state, Block.UPDATE_CLIENTS);
                    placeMirror(level, config, cursor.immutable(), targetState.state, mirrorRandom, minimumY, maximumY);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Picks one offset inside the given radius on each axis, exactly like the vanilla feature.
     *
     * @param cursor mutable position that receives the offset origin
     * @param random the feature random source
     * @param origin the vein origin
     * @param radius the largest offset allowed on each axis
     */
    private void offsetTargetPos(BlockPos.MutableBlockPos cursor, RandomSource random, BlockPos origin, int radius) {
        int offsetX = getRandomPlacementInOneAxisRelativeToOrigin(random, radius);
        int offsetY = getRandomPlacementInOneAxisRelativeToOrigin(random, radius);
        int offsetZ = getRandomPlacementInOneAxisRelativeToOrigin(random, radius);
        cursor.setWithOffset(origin, offsetX, offsetY, offsetZ);
    }

    /**
     * Draws a triangular offset in one axis, matching the vanilla distribution.
     *
     * @param random the feature random source
     * @param radius the largest offset allowed on this axis
     * @return the rounded offset, between minus radius and radius
     */
    private int getRandomPlacementInOneAxisRelativeToOrigin(RandomSource random, int radius) {
        return Math.round((random.nextFloat() - random.nextFloat()) * radius);
    }

    /**
     * Writes the mirrored copy at (x, -y, z) when that position is inside the world
     * and still holds a block the ore is allowed to replace.
     *
     * @param level the level being generated
     * @param config the ore configuration carrying the targets
     * @param position the position the vanilla pass just filled
     * @param state the state that was placed there
     * @param mirrorRandom private random used by the mirrored target check
     * @param minimumY lowest buildable y of the dimension
     * @param maximumY exclusive highest buildable y of the dimension
     */
    private static void placeMirror(WorldGenLevel level, OreConfiguration config, BlockPos position, BlockState state,
                                    RandomSource mirrorRandom, int minimumY, int maximumY) {
        BlockPos mirrorPosition = new BlockPos(position.getX(), -position.getY(), position.getZ());
        if (mirrorPosition.getY() < minimumY || mirrorPosition.getY() >= maximumY) {
            return;
        }
        if (mirrorPosition.equals(position)) {
            return;
        }
        BlockPos.MutableBlockPos mirrorCursor = new BlockPos.MutableBlockPos(
            mirrorPosition.getX(), mirrorPosition.getY(), mirrorPosition.getZ());
        BlockState mirrorState = level.getBlockState(mirrorCursor);
        for (OreConfiguration.TargetBlockState targetState : config.targetStates) {
            if (OreFeature.canPlaceOre(mirrorState,
                level::getBlockState, mirrorRandom, config, targetState, mirrorCursor)) {
                level.setBlock(mirrorCursor, state, Block.UPDATE_CLIENTS);
                return;
            }
        }
    }
}
