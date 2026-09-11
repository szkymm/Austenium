/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Copper chest content for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.GenericChestMenu;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * CopperChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM CopperChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.CopperChestBlockEntity:
 *     Copper chest container with 36 slots per chest; doubles to 72 when paired.
 */
public class CopperChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 36;

    /**
     * Creates the CopperChestBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public CopperChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    /**
     * Creates the CopperChestBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public CopperChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.COPPER_CHEST.get(), pos, state);
    }

    /** {@inheritDoc} */
    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        boolean large = this.getBlockState().getValue(net.minecraft.world.level.block.ChestBlock.TYPE)
            != net.minecraft.world.level.block.state.properties.ChestType.SINGLE;
        return Component.translatable(large
            ? "container.mbb_austenium.large_copper_chest"
            : "container.mbb_austenium.copper_chest");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(4).get(), containerId, inventory, this, 4);
    }
}