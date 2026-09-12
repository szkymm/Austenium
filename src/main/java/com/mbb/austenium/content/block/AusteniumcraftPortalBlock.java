/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.AusteniumcraftPortalBlock
 * TYPE: Java Source
 * DESCRIPTION: Portal plane block of the Austeniumcraft World.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.block;

import com.mbb.austenium.content.block.entity.AusteniumcraftPortalBlockEntity;
import com.mbb.austenium.worldgen.AwPortalLinker;
import com.mbb.austenium.worldgen.AwPortalShape;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

/**
 * AUSTENIUMCRAFTPORTALBLOCK CLASS IS CORE PART OF [MBB] AUSTENIUM AusteniumcraftPortalBlock.java.
 *
 * com.mbb.austenium.content.block.AusteniumcraftPortalBlock:
 *     The walk-through starfield plane of the Austeniumcraft portal. The block owns no
 *     ticking of its own: any entity whose bounding box touches the plane is handed to
 *     AwPortalLinker, and the renderer of the End portal block entity draws the surface.
 *
 * ATTRIBUTES:
 *     CODEC (MapCodec<AusteniumcraftPortalBlock>): Serializer of the block.
 *     LIGHT_LEVEL (int): Light emitted by the plane.
 */
public class AusteniumcraftPortalBlock extends BaseEntityBlock {

    private static final int LIGHT_LEVEL = 11;

    /**
     * Creates the portal plane block.
     *
     * @param properties the block properties built by createProperties
     */
    public AusteniumcraftPortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /** {@inheritDoc} */
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AusteniumcraftPortalBlockEntity(pos, state);
    }

    /** {@inheritDoc} */
    @Override
    public RenderShape getRenderShape(BlockState state) {
        // The plane is drawn by its own baked starfield model, so it renders without a block entity
        // renderer and therefore also under shader packs that replace the End portal shader.
        return RenderShape.MODEL;
    }

    /** {@inheritDoc} */
    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        // No mining progress in any game mode; the break event is refused as well.
        return 0.0F;
    }

    /** {@inheritDoc} */
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        if (!entity.canChangeDimensions() || entity.getPortalCooldown() > 0) {
            return;
        }
        AwPortalLinker.travel(serverLevel, entity, pos);
    }

    /** {@inheritDoc} */
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos,
            net.minecraft.world.level.block.Block fromBlock, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, fromBlock, fromPos, isMoving);
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        // The plane only survives while its own ring stays complete, whatever removed the block.
        for (int offsetX = -2; offsetX <= 2; offsetX++) {
            for (int offsetZ = -2; offsetZ <= 2; offsetZ++) {
                BlockPos center = pos.offset(offsetX, 0, offsetZ);
                if (!isPlaneCellOf(center, pos)) {
                    continue;
                }
                // Only a live plane whose own ring went incomplete breaks, never a neighbour ring.
                if (AwPortalShape.isPortalCenter(serverLevel, center)
                        && !AwPortalShape.isCompleteFrame(serverLevel, center)) {
                    AwPortalShape.removeConnectedPlane(serverLevel, pos);
                    return;
                }
            }
        }
    }

    /**
     * Reports whether one cell belongs to the plane of a ring centre.
     *
     * @param center the candidate ring centre
     * @param cell the cell to test
     * @return true when the cell is a plane cell of that centre
     */
    private static boolean isPlaneCellOf(BlockPos center, BlockPos cell) {
        int offsetX = cell.getX() - center.getX();
        int offsetZ = cell.getZ() - center.getZ();
        // Only the 3x3 interior of the ring is the plane of that centre.
        return cell.getY() == center.getY() && Math.abs(offsetX) <= 1 && Math.abs(offsetZ) <= 1;
    }

    /**
     * Builds the properties of the portal plane.
     *
     * @return the unbreakable, collision free, luminous properties of the plane
     */
    public static BlockBehaviour.Properties createProperties() {
        return BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .noCollission()
            .noOcclusion()
            .isViewBlocking((state, level, pos) -> false)
            .isSuffocating((state, level, pos) -> false)
            .strength(-1.0F, 3600000.0F)
            .lightLevel(state -> LIGHT_LEVEL)
            .noLootTable()
            .pushReaction(PushReaction.BLOCK);
    }
}
