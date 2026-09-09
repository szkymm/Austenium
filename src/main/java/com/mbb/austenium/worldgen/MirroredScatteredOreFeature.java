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
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * MIRROREDSCATTEREDOREFEATURE CLASS IS CORE PART OF [MBB] AUSTENIUM MirroredScatteredOreFeature.java.
 *
 * com.mbb.austenium.worldgen.MirroredScatteredOreFeature:
 *     Runs the vanilla scattered ore feature, then writes a mirrored copy of every
 *     block it placed at (x, -y, z), so the radiant debris distribution is exactly
 *     symmetric about the y=0 plane.
 *
 * USAGE:
 *     Registered as the feature type mbb_austenium:mirrored_scattered_ore and used
 *     by the radiant debris configured features; configuration stays vanilla
 *     OreConfiguration (size, discard chance and targets).
 */
public class MirroredScatteredOreFeature extends Feature<OreConfiguration> {

    private static final int SCAN_RADIUS = 8;

    public MirroredScatteredOreFeature(Codec<OreConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        OreConfiguration config = context.config();
        int minimumY = level.getMinBuildHeight();
        int maximumY = level.getMaxBuildHeight();
        // Snapshot the scatter range so the blocks written by the vanilla pass can be found.
        Map<BlockPos, BlockState> snapshot = takeSnapshot(level, origin, minimumY, maximumY);
        boolean placed = Feature.SCATTERED_ORE.place(context);
        if (!placed) {
            return false;
        }
        Set<BlockState> placedStates = collectPlacedStates(config);
        // Mirror only the states this feature is allowed to write.
        for (Map.Entry<BlockPos, BlockState> entry : snapshot.entrySet()) {
            BlockPos position = entry.getKey();
            BlockState currentState = level.getBlockState(position);
            if (currentState == entry.getValue() || !placedStates.contains(currentState)) {
                continue;
            }
            placeMirror(level, config, position, currentState, minimumY, maximumY);
        }
        return true;
    }

    private static Map<BlockPos, BlockState> takeSnapshot(WorldGenLevel level, BlockPos origin,
                                                          int minimumY, int maximumY) {
        Map<BlockPos, BlockState> snapshot = new HashMap<>();
        // Walk the vanilla scatter range (MAX_DIST_FROM_ORIGIN = 7) plus one block of margin.
        for (int offsetY = -SCAN_RADIUS; offsetY <= SCAN_RADIUS; offsetY++) {
            int positionY = origin.getY() + offsetY;
            if (positionY < minimumY || positionY >= maximumY) {
                continue;
            }
            for (int offsetX = -SCAN_RADIUS; offsetX <= SCAN_RADIUS; offsetX++) {
                for (int offsetZ = -SCAN_RADIUS; offsetZ <= SCAN_RADIUS; offsetZ++) {
                    BlockPos position = origin.offset(offsetX, offsetY, offsetZ);
                    snapshot.put(position, level.getBlockState(position));
                }
            }
        }
        return snapshot;
    }

    private static Set<BlockState> collectPlacedStates(OreConfiguration config) {
        Set<BlockState> states = new HashSet<>();
        // The vanilla feature can only write the configured target states.
        for (OreConfiguration.TargetBlockState targetState : config.targetStates) {
            states.add(targetState.state);
        }
        return states;
    }

    private static void placeMirror(WorldGenLevel level, OreConfiguration config, BlockPos position,
                                    BlockState state, int minimumY, int maximumY) {
        BlockPos mirrorPosition = new BlockPos(position.getX(), -position.getY(), position.getZ());
        if (mirrorPosition.getY() < minimumY || mirrorPosition.getY() >= maximumY) {
            return;
        }
        if (mirrorPosition.equals(position)) {
            return;
        }
        BlockState mirrorState = level.getBlockState(mirrorPosition);
        if (!matchesTarget(config, mirrorState)) {
            return;
        }
        level.setBlock(mirrorPosition, state, Block.UPDATE_ALL);
    }

    private static boolean matchesTarget(OreConfiguration config, BlockState state) {
        RandomSource random = RandomSource.create(0L);
        // Reuse the configured replaceable predicates so mirrors never carve into air or structures.
        for (OreConfiguration.TargetBlockState targetState : config.targetStates) {
            if (targetState.target.test(state, random)) {
                return true;
            }
        }
        return false;
    }
}
