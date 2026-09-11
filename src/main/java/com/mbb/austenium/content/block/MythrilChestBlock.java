/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.MythrilChestBlock
 * TYPE: Java Source
 * DESCRIPTION: Mythril chest block; 70 slots single, 140 when two are paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.MythrilChestBlockEntity;
import com.mbb.austenium.content.menu.GridMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.InteractionHand;
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
 * MythrilChestBlock CLASS IS CORE PART OF [MBB] AUSTENIUM MythrilChestBlock.java.
 *
 * com.mbb.austenium.content.block.MythrilChestBlock:
 *     Mythril chest; 70 slots single, 140 when two are paired.
 */
public class MythrilChestBlock extends ChestBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_PURPLE)
        .strength(1.5f, 1.5f)
        .sound(SoundType.METAL).requiresCorrectToolForDrops()
        .noOcclusion()
        .forceSolidOn();;

    /**
     * Creates the MythrilChestBlock instance.
     */
    public MythrilChestBlock() {
        super(PROPERTIES, mythrilChestEntityType());
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BlockEntityType<? extends ChestBlockEntity>> mythrilChestEntityType() {
        return () -> (BlockEntityType<?
            extends ChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.MYTHRIL_CHEST.get();
    }

    /** {@inheritDoc} */
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MythrilChestBlockEntity(pos, state);
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
        Level level,
        net.minecraft.core.BlockPos pos, Player player,
        net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        if (level.isClientSide) {
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
            ? Component.translatable("container.mbb_austenium.large_mythril_chest")
            : Component.translatable("container.mbb_austenium.mythril_chest");
        int size = container.getContainerSize();
        int rows = Math.max(1, size / 14);
        return new SimpleMenuProvider((containerId, inventory, player) ->
            new GridMenu(rows == 10 ? ModMenuTypes.MYTHRIL_14X10.get() : ModMenuTypes.MYTHRIL_14X5.get(),
                containerId, inventory, container, rows, 14), title);
    }
}
