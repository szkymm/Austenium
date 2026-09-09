/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumTier
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium tool tier; top of the [MBB] Austenium ladder.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * AURELIANIUMTIER CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumTier.java.
 *
 * com.mbb.austenium.content.item.AurelianiumTier:
 *     Aurelianium tool tier; durability 4000, speed 16.0, attack bonus 8.0, level 4.
 */
public class AurelianiumTier implements Tier {

    public static final Tier INSTANCE = new AurelianiumTier();

    @Override public int getUses() { return 4000; }
    @Override public float getSpeed() { return 16.0F; }
    @Override public float getAttackDamageBonus() { return 8.0F; }
    @Override public int getLevel() { return 4; }
    @Override public int getEnchantmentValue() { return 30; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.AURELIANIUM_INGOT.get()); }
}
