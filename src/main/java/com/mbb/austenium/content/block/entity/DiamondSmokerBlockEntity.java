/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.DiamondSmokerBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Diamond smoker entity; smokes vanilla recipes 5x faster.
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
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * DiamondSmokerBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM DiamondSmokerBlockEntity.java.
 */
public class DiamondSmokerBlockEntity extends DiamondFastFurnaceBlockEntity {

    /**
     * Creates the DiamondSmokerBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public DiamondSmokerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DIAMOND_SMOKER.get(), pos, state, RecipeType.SMOKING);
    }

    /**
     * Drives the vanilla furnace tick and then applies this tier speed factor.
     *
     * @param level the level holding the machine
     * @param pos the machine position
     * @param state the machine block state
     * @param entity the machine block entity
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, DiamondSmokerBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyDiamondSpeed();
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.diamond_smoker");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new SmokerMenu(containerId, inventory, this, this.dataAccess);
    }
}
