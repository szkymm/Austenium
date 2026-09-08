/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.NetheriteFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Netherite furnace entity; smelts vanilla recipes 20x faster.
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
 * NetheriteFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM NetheriteFurnaceBlockEntity.java.
 */
public class NetheriteFurnaceBlockEntity extends NetheriteFastFurnaceBlockEntity {

    public NetheriteFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NETHERITE_FURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, NetheriteFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyNetheriteSpeed();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.netherite_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new FurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}