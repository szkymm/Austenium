/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.AurelianiumChestBlock
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium chest block; 162 slots single, 324 when two are paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.AurelianiumChestBlockEntity;
import com.mbb.austenium.content.menu.GenericChestMenu;
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
 * AurelianiumChestBlock CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumChestBlock.java.
 *
 * com.mbb.austenium.content.block.AurelianiumChestBlock:
 *     Aurelianium chest; 162 slots single, 324 when two are paired.
 */
public class AurelianiumChestBlock extends ChestBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.COLOR_BLACK)
        .strength(2.0f, 3.0f)
        .sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel(blockState -> 15);

    public AurelianiumChestBlock() {
        super(PROPERTIES, aurelianiumChestEntityType());
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BlockEntityType<? extends ChestBlockEntity>> aurelianiumChestEntityType() {
        return () -> (BlockEntityType<? extends ChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.AURELIANIUM_CHEST.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AurelianiumChestBlockEntity(pos, state);
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
            ? Component.translatable("container.mbb_austenium.large_aurelianium_chest")
            : Component.translatable("container.mbb_austenium.aurelianium_chest");
        int size = container.getContainerSize();
        // A paired chest opens as an 18x18 grid; a single chest keeps the nine-column ChestMenu path.
        if (large) {
            return new SimpleMenuProvider((containerId, inventory, player) ->
                new GridMenu(ModMenuTypes.AURELIANIUM_18X18.get(),
                    containerId, inventory, container, 18, 18), title);
        }
        int rows = Math.max(1, (size + 8) / 9);
        return new SimpleMenuProvider((containerId, inventory, player) ->
            new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(rows).get(),
                containerId, inventory, container, rows), title);
    }
}
