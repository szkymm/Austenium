/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.worldgen.AwPortalShape
 * TYPE: Java Source
 * DESCRIPTION: End-portal-shaped frame and portal plane of the Austeniumcraft World.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.worldgen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mbb.austenium.content.block.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * AWPORTALSHAPE CLASS IS CORE PART OF [MBB] AUSTENIUM AwPortalShape.java.
 *
 * com.mbb.austenium.worldgen.AwPortalShape:
 *     Owns the portal geometry: twelve frame cells laid out like the End portal ring
 *     (a 5x5 square without its corners) around a horizontal 3x3 portal plane. The ring
 *     only opens when every frame cell holds one of the twelve tier storage blocks and
 *     no tier is missing or doubled, so the order of placement never matters.
 *
 * ATTRIBUTES:
 *     FRAME_BLOCKS (List<Block>): The twelve tier storage blocks, one of each.
 *     FRAME_OFFSETS (List<BlockPos>): The twelve ring cells relative to the centre.
 *     PLANE_OFFSETS (List<BlockPos>): The nine portal cells relative to the centre.
 *
 * USAGE:
 *     AwPortalLinker builds and finds portals, AwDimensionEvents opens and breaks them.
 */
public final class AwPortalShape {

    public static final List<Block> FRAME_BLOCKS = List.of(
        Blocks.COPPER_BLOCK, Blocks.IRON_BLOCK, ModBlocks.SILVER_BLOCK.get(), Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK,
        Blocks.EMERALD_BLOCK, ModBlocks.ORICHALCUM_BLOCK.get(), ModBlocks.MYTHRIL_BLOCK.get(),
        ModBlocks.ADAMANTITE_BLOCK.get(), Blocks.NETHERITE_BLOCK, ModBlocks.RADIANT_BLOCK.get(),
        ModBlocks.AURELIANIUM_BLOCK.get());
    private static final int POCKET_RADIUS = 2;
    private static final int POCKET_DEPTH = 3;

    public static final List<BlockPos> FRAME_OFFSETS = buildFrameOffsets();
    public static final List<BlockPos> PLANE_OFFSETS = buildPlaneOffsets();

    private AwPortalShape() {}

    /**
     * Builds the twelve ring cells of the frame.
     *
     * @return the offsets of the ring cells, in ring order
     */
    private static List<BlockPos> buildFrameOffsets() {
        List<BlockPos> offsets = new ArrayList<>();
        for (int offsetX = -2; offsetX <= 2; offsetX++) {
            for (int offsetZ = -2; offsetZ <= 2; offsetZ++) {
                // The ring is the border of the 5x5 square without its four corners: twelve cells.
                boolean isCorner = Math.abs(offsetX) == 2 && Math.abs(offsetZ) == 2;
                boolean isRing = Math.max(Math.abs(offsetX), Math.abs(offsetZ)) == 2 && !isCorner;
                if (isRing) {
                    offsets.add(new BlockPos(offsetX, 0, offsetZ));
                }
            }
        }
        return List.copyOf(offsets);
    }

    /**
     * Builds the nine cells of the portal plane.
     *
     * @return the offsets of the portal plane
     */
    private static List<BlockPos> buildPlaneOffsets() {
        List<BlockPos> offsets = new ArrayList<>();
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                offsets.add(new BlockPos(offsetX, 0, offsetZ));
            }
        }
        return List.copyOf(offsets);
    }

    /**
     * Reports whether the twelve ring cells hold exactly the twelve tier blocks.
     *
     * @param level the level that holds the frame
     * @param center the centre of the ring
     * @return true when every tier appears exactly once in the ring
     */
    public static boolean isCompleteFrame(Level level, BlockPos center) {
        Set<Block> found = new HashSet<>();
        for (BlockPos offset : FRAME_OFFSETS) {
            BlockState state = level.getBlockState(center.offset(offset));
            if (!FRAME_BLOCKS.contains(state.getBlock())) {
                return false;
            }
            found.add(state.getBlock());
        }
        return found.size() == FRAME_BLOCKS.size();
    }

    /**
     * Opens the portal plane when the ring is complete.
     *
     * @param level the server level that holds the frame
     * @param center the centre of the ring
     * @return true when the portal was opened
     */
    public static boolean activate(ServerLevel level, BlockPos center) {
        // An anchor in the last two layers of the dimension is dropped to the highest usable layer and
        // the ring follows it, the way a Nether portal consumes whatever stands in its way.
        BlockPos anchor = center;
        if (center.getY() > AwDimensionRules.TOP_Y - 2) {
            anchor = new BlockPos(center.getX(), AwDimensionRules.TOP_Y - 2, center.getZ());
            // The dropped anchor needs the same three clear layers above it as any other plane.
            carvePocket(level, anchor);
            buildFrame(level, anchor);
        }
        if (!isCompleteFrame(level, anchor)) {
            return false;
        }
        BlockState portalState = ModBlocks.AUSTENIUMCRAFT_PORTAL.get().defaultBlockState();
        for (BlockPos offset : PLANE_OFFSETS) {
            // The plane replaces whatever sat inside the ring, so the portal is always flush.
            level.setBlock(anchor.offset(offset), portalState, Block.UPDATE_CLIENTS);
        }
        return true;
    }

    /**
     * Clears the breathing space and the padding around a plane that is about to be opened.
     *
     * @param level the server level that receives the pocket
     * @param center the centre of the plane
     */
    public static void carvePocket(ServerLevel level, BlockPos center) {
        for (int offsetX = -POCKET_RADIUS; offsetX <= POCKET_RADIUS; offsetX++) {
            for (int offsetZ = -POCKET_RADIUS; offsetZ <= POCKET_RADIUS; offsetZ++) {
                for (int offsetY = 0; offsetY <= POCKET_DEPTH; offsetY++) {
                    BlockPos cell = center.offset(offsetX, offsetY, offsetZ);
                    // The bedrock floor of the dimension must survive every pocket.
                    if (cell.getY() <= AwDimensionRules.BEDROCK_Y) {
                        continue;
                    }
                    level.setBlock(cell, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                }
            }
        }
    }

    /**
     * Places the twelve tier blocks of the ring.
     *
     * @param level the server level that receives the frame
     * @param center the centre of the ring
     */
    public static void buildFrame(ServerLevel level, BlockPos center) {
        for (int index = 0; index < FRAME_OFFSETS.size(); index++) {
            // One tier per ring cell keeps the auto-built frame complete by construction.
            level.setBlock(center.offset(FRAME_OFFSETS.get(index)), FRAME_BLOCKS.get(index).defaultBlockState(),
                Block.UPDATE_CLIENTS);
        }
    }

    /**
     * Removes the portal plane around one centre.
     *
     * @param level the server level that holds the portal
     * @param center the centre of the ring
     */
    public static void removePortal(ServerLevel level, BlockPos center) {
        for (BlockPos offset : PLANE_OFFSETS) {
            BlockPos cell = center.offset(offset);
            if (level.getBlockState(cell).is(ModBlocks.AUSTENIUMCRAFT_PORTAL.get())) {
                level.setBlock(cell, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
            }
        }
    }

    /**
     * Removes every portal cell connected to one plane cell.
     *
     * @param level the server level that holds the plane
     * @param start the plane cell whose ring lost a frame block
     */
    public static void removeConnectedPlane(ServerLevel level, BlockPos start) {
        Set<BlockPos> visited = new HashSet<>();
        java.util.ArrayDeque<BlockPos> pending = new java.util.ArrayDeque<>();
        pending.add(start);
        while (!pending.isEmpty()) {
            BlockPos cell = pending.poll();
            if (!visited.add(cell)) {
                continue;
            }
            if (!level.getBlockState(cell).is(ModBlocks.AUSTENIUMCRAFT_PORTAL.get())) {
                continue;
            }
            level.setBlock(cell, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
            for (net.minecraft.core.Direction direction : net.minecraft.core.Direction.Plane.HORIZONTAL) {
                pending.add(cell.relative(direction));
            }
        }
    }

    /**
     * Searches for an existing portal centre near a position.
     *
     * @param level the server level to search
     * @param near the position to search around
     * @param radius the search radius in blocks, applied on all three axes
     * @return the centre of a live portal, or null when none is close enough
     */
    public static BlockPos findPortalCenter(ServerLevel level, BlockPos near, int radius) {
        for (BlockPos cursor : BlockPos.betweenClosed(near.offset(-radius, -radius, -radius),
                near.offset(radius, radius, radius))) {
            // Probing an unloaded chunk would generate terrain just to look for a portal.
            if (!level.hasChunkAt(cursor)) {
                continue;
            }
            if (isPortalCenter(level, cursor)) {
                return cursor.immutable();
            }
        }
        return null;
    }

    /**
     * Reports whether a position is the centre of a live portal plane.
     *
     * @param level the level to test
     * @param center the candidate centre, which does not need to be loaded
     * @return true when the whole 3x3 plane holds portal blocks
     */
    public static boolean isPortalCenter(Level level, BlockPos center) {
        for (BlockPos offset : PLANE_OFFSETS) {
            if (!level.getBlockState(center.offset(offset)).is(ModBlocks.AUSTENIUMCRAFT_PORTAL.get())) {
                return false;
            }
        }
        return true;
    }
}
