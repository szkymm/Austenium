/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.OrichalcumFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Orichalcum furnace entity; smelts vanilla recipes 10x faster.
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
 * OrichalcumFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumFurnaceBlockEntity.java.
 */
public class OrichalcumFurnaceBlockEntity extends OrichalcumFastFurnaceBlockEntity {

    public OrichalcumFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORICHALCUM_FURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, OrichalcumFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyOrichalcumSpeed();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.orichalcum_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}