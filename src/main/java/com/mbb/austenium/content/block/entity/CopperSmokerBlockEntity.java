/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.CopperSmokerBlockEntity
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
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * CopperSmokerBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM CopperSmokerBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.CopperSmokerBlockEntity:
 *     Copper smoker; smokes vanilla recipes 1.25x faster.
 *
 * PUBLIC METHODS:
 *     serverTick(...): Runs vanilla smoker tick then applies copper speed.
 */
public class CopperSmokerBlockEntity extends CopperFastFurnaceBlockEntity {

    /**
     * Creates the CopperSmokerBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public CopperSmokerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPER_SMOKER.get(), pos, state, RecipeType.SMOKING);
    }

    /**
     * Runs the vanilla smoker tick and then applies the 1.25x copper speed factor.
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, CopperSmokerBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyCopperSpeed();
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.copper_smoker");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new SmokerMenu(containerId, inventory, this, this.dataAccess);
    }
}
