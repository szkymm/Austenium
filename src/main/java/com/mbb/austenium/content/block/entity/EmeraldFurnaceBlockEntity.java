/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.EmeraldFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Emerald furnace entity; smelts vanilla recipes 8x faster.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * EmeraldFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM EmeraldFurnaceBlockEntity.java.
 */
public class EmeraldFurnaceBlockEntity extends EmeraldFastFurnaceBlockEntity {

    public EmeraldFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EMERALD_FURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, EmeraldFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyEmeraldSpeed();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.emerald_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}