/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.OrichalcumChestBlockEntity;
import com.mbb.austenium.content.menu.GenericChestMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * OrichalcumChestBlock CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumChestBlock.java.
 *
 * com.mbb.austenium.content.block.OrichalcumChestBlock:
 *     Chest of the orichalcum tier: paired container with that tier's capacity and GUI texture.
 */
public class OrichalcumChestBlock extends ChestBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_RED)
        .strength(1.5f, 1.5f)
        .sound(SoundType.METAL).requiresCorrectToolForDrops()
        .noOcclusion()
        .forceSolidOn();;

    /**
     * Creates the OrichalcumChestBlock instance.
     */
    public OrichalcumChestBlock() {
        super(PROPERTIES, orichalcumChestEntityType());
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BlockEntityType<? extends ChestBlockEntity>> orichalcumChestEntityType() {
        return () -> (BlockEntityType<?
            extends ChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.ORICHALCUM_CHEST.get();
    }

    /** {@inheritDoc} */
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OrichalcumChestBlockEntity(pos, state);
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
        BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? createTickerHelper(type,
            this.blockEntityType(), ChestBlockEntity::lidAnimateTick) : null;
    }

    /** {@inheritDoc} */
    @Override
    public net.minecraft.world.InteractionResult use(net.minecraft.world.level.block.state.BlockState state,
        Level level, net.minecraft.core.BlockPos pos,
        net.minecraft.world.entity.player.Player player,
        net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        if (level.isClientSide) {
            // Client-side prediction so the lid starts opening with the click, like the wider chests.
            if (level.getBlockEntity(pos) instanceof ChestBlockEntity chestEntity) {
                chestEntity.startOpen(player);
                com.mbb.austenium.client.OpenedChestTracker.open(player, chestEntity);
            }
            return net.minecraft.world.InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        Container container = getContainer(this, state, level, pos, false);
        if (container == null) {
            return null;
        }
        boolean large = state.getValue(TYPE) != ChestType.SINGLE;
        Component title = large
            ? Component.translatable("container.mbb_austenium.large_orichalcum_chest")
            : Component.translatable("container.mbb_austenium.orichalcum_chest");
        int rows = Math.max(1, (container.getContainerSize() + 8) / 9);
        return new SimpleMenuProvider((containerId, inventory, player) ->
            new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(rows).get(), containerId, inventory, container, rows),
                title);
    }
}
