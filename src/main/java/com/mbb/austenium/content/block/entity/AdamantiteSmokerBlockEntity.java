/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AdamantiteSmokerBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Adamantite smoker entity; smokes vanilla recipes 15x faster.
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
 * AdamantiteSmokerBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM AdamantiteSmokerBlockEntity.java.
 */
public class AdamantiteSmokerBlockEntity extends AdamantiteFastFurnaceBlockEntity {

    /**
     * Creates the AdamantiteSmokerBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public AdamantiteSmokerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ADAMANTITE_SMOKER.get(), pos, state, RecipeType.SMOKING);
    }

    /**
     * Drives the vanilla furnace tick and then applies this tier speed factor.
     *
     * @param level the level holding the machine
     * @param pos the machine position
     * @param state the machine block state
     * @param entity the machine block entity
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, AdamantiteSmokerBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyAdamantiteSpeed();
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.adamantite_smoker");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new SmokerMenu(containerId, inventory, this, this.dataAccess);
    }
}