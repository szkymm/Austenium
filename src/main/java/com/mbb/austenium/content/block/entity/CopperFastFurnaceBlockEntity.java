/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.CopperFastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Copper furnace-family block entity with 1.25x speed factor.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
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
 * CopperFastFurnaceBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM CopperFastFurnaceBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.CopperFastFurnaceBlockEntity:
 *     Base for copper machines; cooks and burns fuel 1.25x faster than vanilla.
 *
 * ATTRIBUTES:
 *     lastScaledTotal (int): Last scaled cooking total time, prevents double scaling.
 *
 * PUBLIC METHODS:
 *     applyCopperSpeed(): Scales fuel duration and cooking time exactly once.
 */
public abstract class CopperFastFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private int lastScaledTotal;

    protected CopperFastFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                           RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, state, recipeType);
    }

    /**
     * Applies the 1.25x speed factor after the vanilla furnace tick.
     *
     * <p>Fuel duration is scaled once at ignition (lit time equals duration).
     * Cooking total time is scaled once whenever vanilla resets it (200 -> 160
     * for smelting, 100 -> 80 for blasting/smoking).</p>
     */
    protected void applyCopperSpeed() {
        // Scale fuel burn duration once: right after ignition litTime == litDuration.
        int duration = this.dataAccess.get(1);
        if (duration > 0 && this.dataAccess.get(0) == duration) {
            int scaledDuration = Math.max(1, (int) (duration * 0.8f));
            this.dataAccess.set(1, scaledDuration);
            this.dataAccess.set(0, scaledDuration);
        }

        // Scale cooking total time once whenever vanilla assigns a fresh value.
        int totalTime = this.dataAccess.get(3);
        if (totalTime > 0 && totalTime != this.lastScaledTotal) {
            int scaledTotal = Math.max(1, (int) (totalTime * 0.8f));
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
