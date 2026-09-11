/*
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.SilverBlastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Silver blast furnace entity
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
 * SilverBlastFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM SilverBlastFurnaceBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.SilverBlastFurnaceBlockEntity:
 *     Cooking block entity of the silver tier: shared furnace logic at the tier speed.
 */
public class SilverBlastFurnaceBlockEntity extends SilverFastFurnaceBlockEntity {

    /**
     * Creates the SilverBlastFurnaceBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public SilverBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SILVER_BLAST_FURNACE.get(), pos, state, RecipeType.BLASTING);
    }

    /**
     * Drives the vanilla furnace tick and then applies this tier speed factor.
     *
     * @param level the level holding the machine
     * @param pos the machine position
     * @param state the machine block state
     * @param entity the machine block entity
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, SilverBlastFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applySilverSpeed();
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.silver_blast_furnace");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlastFurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}
