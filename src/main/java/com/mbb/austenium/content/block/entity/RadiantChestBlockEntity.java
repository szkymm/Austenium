/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.RadiantChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Radiant chest container with 135 slots per chest; 270 when paired.
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
 * RadiantChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.RadiantChestBlockEntity:
 *     Radiant chest container with 135 slots per chest; 270 when paired.
 */
public class RadiantChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 135;

    /**
     * Creates the RadiantChestBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public RadiantChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    /**
     * Creates the RadiantChestBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public RadiantChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.RADIANT_CHEST.get(), pos, state);
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
            ? "container.mbb_austenium.large_radiant_chest"
            : "container.mbb_austenium.radiant_chest");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return large
            ? new GridMenu(ModMenuTypes.RADIANT_15X18.get(), containerId, inventory, this, 18, 15)
            : new GridMenu(ModMenuTypes.RADIANT_15X9.get(), containerId, inventory, this, 9, 15);
    }
}
