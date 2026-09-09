/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumArmorMaterial
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium armor material; durability 75, defense 9-15-14-9, toughness 6.0.
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
 * AURELIANIUMARMORMATERIAL CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumArmorMaterial.java.
 *
 * com.mbb.austenium.content.item.AurelianiumArmorMaterial:
 *     Aurelianium armor material; knockback resistance 0.2 per piece.
 */
public class AurelianiumArmorMaterial implements ArmorMaterial {

    public static final ArmorMaterial INSTANCE = new AurelianiumArmorMaterial();

    @Override public int getDurabilityForType(ArmorItem.Type type) { return 75; }
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) { case HELMET -> 9; case CHESTPLATE -> 15; case LEGGINGS -> 14; case BOOTS -> 9; };
    }
    @Override public int getEnchantmentValue() { return 30; }
    @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_NETHERITE; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.AURELIANIUM_INGOT.get()); }
    @Override public String getName() { return "mbb_austenium:aurelianium"; }
    @Override public float getToughness() { return 6.0F; }
    @Override public float getKnockbackResistance() { return 0.2F; }
}
