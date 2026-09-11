/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AdamantiteChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Adamantite chest container with 75 slots per chest; 150 when paired.
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
 * AdamantiteChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM AdamantiteChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.AdamantiteChestBlockEntity:
 *     Adamantite chest container with 75 slots per chest; 150 when paired.
 */
public class AdamantiteChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 75;

    /**
     * Creates the AdamantiteChestBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public AdamantiteChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    /**
     * Creates the AdamantiteChestBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public AdamantiteChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.ADAMANTITE_CHEST.get(), pos, state);
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
            ? "container.mbb_austenium.large_adamantite_chest"
            : "container.mbb_austenium.adamantite_chest");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return large
            ? new GridMenu(ModMenuTypes.ADAMANTITE_15X10.get(), containerId, inventory, this, 10, 15)
            : new GridMenu(ModMenuTypes.ADAMANTITE_15X5.get(), containerId, inventory, this, 5, 15);
    }
}
