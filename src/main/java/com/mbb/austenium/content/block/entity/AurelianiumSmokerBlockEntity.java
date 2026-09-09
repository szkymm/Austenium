/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AurelianiumSmokerBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium smoker entity; smokes vanilla recipes 25x faster.
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
 * AurelianiumSmokerBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumSmokerBlockEntity.java.
 */
public class AurelianiumSmokerBlockEntity extends AurelianiumFastFurnaceBlockEntity {

    public AurelianiumSmokerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AURELIANIUM_SMOKER.get(), pos, state, RecipeType.SMOKING);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, AurelianiumSmokerBlockEntity entity) {
        AbstractFurnaceBlockEntity.serverTick(level, pos, state, entity);
        entity.applyAurelianiumSpeed();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.aurelianium_smoker");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new SmokerMenu(containerId, inventory, this, this.dataAccess);
    }
}