/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.MythrilChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Mythril chest container with 70 slots per chest; 140 when paired.
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
 * MythrilChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM MythrilChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.MythrilChestBlockEntity:
 *     Mythril chest container with 70 slots per chest; 140 when paired.
 */
public class MythrilChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 70;

    public MythrilChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    public MythrilChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.MYTHRIL_CHEST.get(), pos, state);
    }

    @Override
    public void recheckOpen() {
        // GridMenu is not a ChestMenu; the vanilla recheck would reset the openers count to zero,
        // which makes the lid twitch. The menu lifecycle starts/stops openers instead.
    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    protected Component getDefaultName() {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return Component.translatable(large
            ? "container.mbb_austenium.large_mythril_chest"
            : "container.mbb_austenium.mythril_chest");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return large
            ? new GridMenu(ModMenuTypes.MYTHRIL_14X10.get(), containerId, inventory, this, 10, 14)
            : new GridMenu(ModMenuTypes.MYTHRIL_14X5.get(), containerId, inventory, this, 5, 14);
    }
}
