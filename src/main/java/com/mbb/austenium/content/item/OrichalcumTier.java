/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class OrichalcumTier implements Tier {
    public static final Tier INSTANCE = new OrichalcumTier();
    @Override public int getUses() { return 1200; }
    @Override public float getSpeed() { return 8.5F; }
    @Override public float getAttackDamageBonus() { return 3.5F; }
    @Override public int getLevel() { return 3; }
    @Override public int getEnchantmentValue() { return 16; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.ORICHALCUM_INGOT.get()); }
}
