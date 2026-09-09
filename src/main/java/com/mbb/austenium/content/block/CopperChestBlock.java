/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Copper chest block for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.CopperChestBlockEntity;
import com.mbb.austenium.content.menu.GenericChestMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * CopperChestBlock CLASS IS CORE PART OF [MBB] AUSTENIUM CopperChestBlock.java.
 *
 * com.mbb.austenium.content.block.CopperChestBlock:
 *     Copper chest block; 36 slots single, 72 slots when two are paired.
 */
public class CopperChestBlock extends ChestBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.METAL)
        .strength(1.5f, 1.5f)
        .sound(SoundType.METAL);

    public CopperChestBlock() {
        super(PROPERTIES, copperChestEntityType());
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BlockEntityType<? extends ChestBlockEntity>> copperChestEntityType() {
        return () -> (BlockEntityType<? extends ChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.COPPER_CHEST.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CopperChestBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? createTickerHelper(type, this.blockEntityType(), ChestBlockEntity::lidAnimateTick) : null;
    }

    @Override
    public net.minecraft.world.InteractionResult use(net.minecraft.world.level.block.state.BlockState state, Level level, net.minecraft.core.BlockPos pos, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
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

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        Container container = getContainer(this, state, level, pos, false);
        if (container == null) {
            return null;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        boolean large = state.getValue(TYPE) != net.minecraft.world.level.block.state.properties.ChestType.SINGLE;
        Component title = large
            ? Component.translatable("container.mbb_austenium.large_copper_chest")
            : Component.translatable("container.mbb_austenium.copper_chest");
        int rows = Math.max(1, (container.getContainerSize() + 8) / 9);
        return new SimpleMenuProvider((containerId, inventory, player) ->
            new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(rows).get(), containerId, inventory, container, rows), title);
    }
}