/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.CopperFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Copper furnace-family block entity with 1.25x speed factor.
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
 * CopperFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM CopperFurnaceBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.CopperFurnaceBlockEntity:
 *     Copper furnace; smelts vanilla smelting recipes 1.25x faster.
 *
 * PUBLIC METHODS:
 *     serverTick(...): Runs vanilla furnace tick then applies copper speed.
 */
public class CopperFurnaceBlockEntity extends CopperFastFurnaceBlockEntity {

    public CopperFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPER_FURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    /**
     * Runs the vanilla furnace tick and then applies the 1.25x copper speed factor.
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, CopperFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyCopperSpeed();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.copper_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}