/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.IronBlastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Iron blast furnace entity; blasts vanilla recipes 2.5x faster.
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
 * IronBlastFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM IronBlastFurnaceBlockEntity.java.
 */
public class IronBlastFurnaceBlockEntity extends IronFastFurnaceBlockEntity {

    /**
     * Creates the IronBlastFurnaceBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public IronBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IRON_BLAST_FURNACE.get(), pos, state, RecipeType.BLASTING);
    }

    /**
     * Drives the vanilla furnace tick and then applies this tier speed factor.
     *
     * @param level the level holding the machine
     * @param pos the machine position
     * @param state the machine block state
     * @param entity the machine block entity
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, IronBlastFurnaceBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyIronSpeed();
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.iron_blast_furnace");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlastFurnaceMenu(containerId, inventory, this, this.dataAccess);
    }
}
