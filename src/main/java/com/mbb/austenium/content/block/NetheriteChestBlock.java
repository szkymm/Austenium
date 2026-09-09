/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.NetheriteChestBlock
 * TYPE: Java Source
 * DESCRIPTION: Netherite chest block; 105 slots single, 210 when two are paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.NetheriteChestBlockEntity;
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
 * NetheriteChestBlock CLASS IS CORE PART OF [MBB] AUSTENIUM NetheriteChestBlock.java.
 *
 * com.mbb.austenium.content.block.NetheriteChestBlock:
 *     Netherite chest; 105 slots single, 210 when two are paired.
 */
public class NetheriteChestBlock extends ChestBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_BROWN)
        .strength(2.0f, 3.0f)
        .sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops();

    public NetheriteChestBlock() {
        super(PROPERTIES, netheriteChestEntityType());
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BlockEntityType<? extends ChestBlockEntity>> netheriteChestEntityType() {
        return () -> (BlockEntityType<? extends ChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.NETHERITE_CHEST.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NetheriteChestBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? createTickerHelper(type, this.blockEntityType(), ChestBlockEntity::lidAnimateTick) : null;
    }

    @Override
    public net.minecraft.world.InteractionResult use(net.minecraft.world.level.block.state.BlockState state, Level level, net.minecraft.core.BlockPos pos, Player player, net.minecraft.world.InteractionHand hand, net.minecraft.world.phys.BlockHitResult hit) {
        if (level.isClientSide) {
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
        boolean large = state.getValue(TYPE) != ChestType.SINGLE;
        Component title = large
            ? Component.translatable("container.mbb_austenium.large_netherite_chest")
            : Component.translatable("container.mbb_austenium.netherite_chest");
        int size = container.getContainerSize();
        int rows = Math.max(1, size / 15);
        return new SimpleMenuProvider((containerId, inventory, player) ->
            new GridMenu(rows == 14 ? ModMenuTypes.NETHERITE_15X14.get() : ModMenuTypes.NETHERITE_15X7.get(),
                containerId, inventory, container, rows, 15), title);
    }
}
