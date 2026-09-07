/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */

package com.mbb.austenium.content.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class CopperTier implements Tier {

    public static final Tier INSTANCE = new CopperTier();

    @Override public int getUses() { return 210; }
    @Override public float getSpeed() { return 5.5F; }
    @Override public float getAttackDamageBonus() { return 1.5F; }
    @Override public int getLevel() { return 1; }
    @Override public int getEnchantmentValue() { return 12; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(net.minecraft.world.item.Items.COPPER_INGOT); }
}