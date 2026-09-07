/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import com.mbb.austenium.content.block.ModBlocks;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class SilverTier implements Tier {

    public static final Tier INSTANCE = new SilverTier();

    @Override
    public int getUses() {
        return 300;
    }

    @Override
    public float getSpeed() {
        return 6.5F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 2.5F;
    }

    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public int getEnchantmentValue() {
        return 14;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.SILVER_INGOT.get());
    }
}
