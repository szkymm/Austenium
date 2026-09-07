/*
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.SilverFastFurnaceBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Silver machines base, vanilla x3 speed.
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

public abstract class SilverFastFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private int lastScaledTotal;

    protected SilverFastFurnaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                           RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, state, recipeType);
    }

    protected void applySilverSpeed() {
        int duration = this.dataAccess.get(1);
        if (duration > 0 && this.dataAccess.get(0) == duration) {
            int scaled = Math.max(1, (int) (duration / 3.0f));
            this.dataAccess.set(1, scaled);
            this.dataAccess.set(0, scaled);
        }
        int totalTime = this.dataAccess.get(3);
        if (totalTime > 0 && totalTime != this.lastScaledTotal) {
            int scaled = Math.max(1, (int) (totalTime / 3.0f));
            this.dataAccess.set(3, scaled);
            this.lastScaledTotal = scaled;
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.lastScaledTotal = this.dataAccess.get(3);
    }
}
