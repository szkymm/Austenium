/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AdamantiteFastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Adamantite furnace-family block entity with 15x speed factor.
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
 * AdamantiteFastFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM AdamantiteFastFurnaceBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.AdamantiteFastFurnaceBlockEntity:
 *     Base for adamantite machines; cooks and burns fuel 15x faster than vanilla.
 */
public abstract class AdamantiteFastFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private int lastScaledTotal;

    /**
     * Creates the AdamantiteFastFurnaceBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    protected AdamantiteFastFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                         RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, state, recipeType);
    }

    /**
     * Applies the 15x speed factor (scale 0.0667) after the vanilla furnace tick.
     *
     * <p>Fuel duration is scaled once at ignition; cooking total time is scaled
     * once whenever vanilla assigns a fresh value.</p>
     */
    protected void applyAdamantiteSpeed() {
        int duration = this.dataAccess.get(1);
        if (duration > 0 && this.dataAccess.get(0) == duration) {
            int scaledDuration = Math.max(1, (int) (duration * 0.0667f));
            this.dataAccess.set(1, scaledDuration);
            this.dataAccess.set(0, scaledDuration);
        }
        int totalTime = this.dataAccess.get(3);
        if (totalTime > 0 && totalTime != this.lastScaledTotal) {
            int scaledTotal = Math.max(1, (int) (totalTime * 0.0667f));
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