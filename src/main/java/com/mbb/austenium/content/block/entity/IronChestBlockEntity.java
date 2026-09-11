/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.IronChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Iron chest container with 40 slots per chest; 80 when paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.IronGridMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

/**
 * IronChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM IronChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.IronChestBlockEntity:
 *     Iron chest container with 40 slots per chest; 80 when paired.
 */
public class IronChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 40;

    /**
     * Creates the IronChestBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public IronChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    /**
     * Creates the IronChestBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public IronChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.IRON_CHEST.get(), pos, state);
    }

    /** {@inheritDoc} */
    @Override
    public void recheckOpen() {
        // GridMenu is not a ChestMenu; the vanilla recheck would reset the openers count to zero,
        // which makes the lid twitch. The menu lifecycle starts/stops openers instead.
    }

    /** {@inheritDoc} */
    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return Component.translatable(large
            ? "container.mbb_austenium.large_iron_chest"
            : "container.mbb_austenium.iron_chest");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        // A paired iron chest doubles the rows, so the menu must follow the live container size.
        int rows = Math.max(1, this.getContainerSize() / 10);
        return new IronGridMenu(rows == 8 ? ModMenuTypes.IRON_10X8.get() : ModMenuTypes.IRON_10X4.get(),
            containerId, inventory, this, rows, 10);
    }
}
