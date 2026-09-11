/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.EmeraldChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Emerald chest container with 60 slots per chest; 120 when paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.GridMenu;

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
 * EmeraldChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM EmeraldChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.EmeraldChestBlockEntity:
 *     Emerald chest container with 60 slots per chest; 120 when paired.
 */
public class EmeraldChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 60;

    /**
     * Creates the EmeraldChestBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public EmeraldChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    /**
     * Creates the EmeraldChestBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public EmeraldChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.EMERALD_CHEST.get(), pos, state);
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
            ? "container.mbb_austenium.large_emerald_chest"
            : "container.mbb_austenium.emerald_chest");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new GridMenu(ModMenuTypes.EMERALD_12X5.get(), containerId, inventory, this, 5, 12);
    }
}