/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.RadiantTier
 * TYPE: Java Source
 * DESCRIPTION: Radiant tool tier; top of the [MBB] Austenium ladder.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * RADIANTTIER CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantTier.java.
 *
 * com.mbb.austenium.content.item.RadiantTier:
 *     Radiant tool tier; durability 2500, speed 11.5, attack bonus 5.5, level 4.
 */
public class RadiantTier implements Tier {

    public static final Tier INSTANCE = new RadiantTier();

    @Override public int getUses() { return 2500; }
    @Override public float getSpeed() { return 11.5F; }
    @Override public float getAttackDamageBonus() { return 5.5F; }
    @Override public int getLevel() { return 4; }
    @Override public int getEnchantmentValue() { return 20; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.RADIANT_INGOT.get()); }
}
