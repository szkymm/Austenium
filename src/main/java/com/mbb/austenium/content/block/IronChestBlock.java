/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.IronChestBlock
 * TYPE: Java Source
 * DESCRIPTION: Iron chest block; 40 slots single, 80 when two are paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.IronChestBlockEntity;
import com.mbb.austenium.content.menu.IronGridMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
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
 * IronChestBlock CLASS IS CORE PART OF [MBB] AUSTENIUM IronChestBlock.java.
 *
 * com.mbb.austenium.content.block.IronChestBlock:
 *     Iron chest; 40 slots single, 80 when two are paired.
 */
public class IronChestBlock extends ChestBlock {

    public static final BlockBehaviour.Properties PROPERTIES = BlockBehaviour.Properties.of()
        .mapColor(MapColor.METAL)
        .strength(3.5f, 3.5f)
        .sound(SoundType.METAL);

    public IronChestBlock() {
        super(PROPERTIES, ironChestEntityType());
    }

    @SuppressWarnings("unchecked")
    private static Supplier<BlockEntityType<? extends ChestBlockEntity>> ironChestEntityType() {
        return () -> (BlockEntityType<? extends ChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.IRON_CHEST.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IronChestBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? createTickerHelper(type, this.blockEntityType(), ChestBlockEntity::lidAnimateTick) : null;
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
            ? Component.translatable("container.mbb_austenium.large_iron_chest")
            : Component.translatable("container.mbb_austenium.iron_chest");
        int size = container.getContainerSize();
        int rows = Math.max(1, size / 10);
        return new SimpleMenuProvider((containerId, inventory, player) ->
            new IronGridMenu(rows == 8 ? ModMenuTypes.IRON_10X8.get() : ModMenuTypes.IRON_10X4.get(),
                containerId, inventory, container, rows, 10), title);
    }
}
