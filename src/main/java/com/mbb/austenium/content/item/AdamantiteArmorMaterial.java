/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
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

public class AdamantiteArmorMaterial implements ArmorMaterial {
    public static final ArmorMaterial INSTANCE = new AdamantiteArmorMaterial();
    @Override public int getDurabilityForType(ArmorItem.Type type) { return 40; }
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) { case HELMET -> 5; case CHESTPLATE -> 9; case LEGGINGS -> 8; case BOOTS -> 5; };
    }
    @Override public int getEnchantmentValue() { return 18; }
    @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_IRON; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.ADAMANTITE_INGOT.get()); }
    @Override public String getName() { return "mbb_austenium:adamantite"; }
    @Override public float getToughness() { return 2.5F; }
    @Override public float getKnockbackResistance() { return 0.05F; }
}
