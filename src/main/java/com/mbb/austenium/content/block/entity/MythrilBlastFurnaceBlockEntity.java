/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.MythrilBlastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Mythril blast furnace entity; blasts vanilla recipes 12x faster.
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
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * MythrilBlastFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM MythrilBlastFurnaceBlockEntity.java.
 */
public class MythrilBlastFurnaceBlockEntity extends MythrilFastFurnaceBlockEntity {

    public MythrilBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MYTHRIL_BLAST_FURNACE.get(), pos, state, RecipeType.BLASTING);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MythrilBlastFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyMythrilSpeed();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.mythril_blast_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlastFurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}