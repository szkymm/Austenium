/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.RadiantArmorMaterial
 * TYPE: Java Source
 * DESCRIPTION: Radiant armor material; durability 50, defense 6-11-10-6, toughness 3.5.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * RADIANTARMORMATERIAL CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantArmorMaterial.java.
 *
 * com.mbb.austenium.content.item.RadiantArmorMaterial:
 *     Radiant armor material; knockback resistance 0.15 per piece.
 */
public class RadiantArmorMaterial implements ArmorMaterial {

    public static final ArmorMaterial INSTANCE = new RadiantArmorMaterial();

    @Override public int getDurabilityForType(ArmorItem.Type type) { return 50; }
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) { case HELMET -> 6; case CHESTPLATE -> 11; case LEGGINGS -> 10; case BOOTS -> 6; };
    }
    @Override public int getEnchantmentValue() { return 20; }
    @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_NETHERITE; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.RADIANT_INGOT.get()); }
    @Override public String getName() { return "mbb_austenium:radiant"; }
    @Override public float getToughness() { return 3.5F; }
    @Override public float getKnockbackResistance() { return 0.15F; }
}
