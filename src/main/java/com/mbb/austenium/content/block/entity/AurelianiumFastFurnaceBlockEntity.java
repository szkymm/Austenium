/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AurelianiumFastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium furnace-family block entity with 50x speed factor.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * AurelianiumFastFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumFastFurnaceBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.AurelianiumFastFurnaceBlockEntity:
 *     Base for aurelianium machines; cooks and burns fuel 50x faster than vanilla.
 */
public abstract class AurelianiumFastFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private int lastScaledTotal;

    protected AurelianiumFastFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                         RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, state, recipeType);
    }

    /**
     * Applies the 50x speed factor (scale 0.02) after the vanilla furnace tick.
     *
     * <p>Fuel duration is scaled once at ignition; cooking total time is scaled
     * once whenever vanilla assigns a fresh value.</p>
     */
    protected void applyAurelianiumSpeed() {
        int duration = this.dataAccess.get(1);
        if (duration > 0 && this.dataAccess.get(0) == duration) {
            int scaledDuration = Math.max(1, (int) (duration * 0.02f));
            this.dataAccess.set(1, scaledDuration);
            this.dataAccess.set(0, scaledDuration);
        }
        int totalTime = this.dataAccess.get(3);
        if (totalTime > 0 && totalTime != this.lastScaledTotal) {
            int scaledTotal = Math.max(1, (int) (totalTime * 0.02f));
            this.dataAccess.set(3, scaledTotal);
            this.lastScaledTotal = scaledTotal;
        }
    }

    /**
     * Restores the scaled-total guard after loading persisted furnace state.
     */
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.lastScaledTotal = this.dataAccess.get(3);
    }
}