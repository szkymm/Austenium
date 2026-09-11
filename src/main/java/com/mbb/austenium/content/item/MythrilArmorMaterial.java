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

/**
 * MythrilArmorMaterial CLASS IS CORE PART OF [MBB] AUSTENIUM MythrilArmorMaterial.java.
 *
 * com.mbb.austenium.content.item.MythrilArmorMaterial:
 *     Armour material of the mythril tier: durability factor, defence points and equip sound.
 */
public class MythrilArmorMaterial implements ArmorMaterial {
    public static final ArmorMaterial INSTANCE = new MythrilArmorMaterial();
    @Override public int getDurabilityForType(ArmorItem.Type type) { return 36; }
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) { case HELMET -> 4; case CHESTPLATE -> 8; case LEGGINGS -> 7; case BOOTS -> 4; };
    }
    @Override public int getEnchantmentValue() { return 16; }
    @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_IRON; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(ModItems.MYTHRIL_INGOT.get()); }
    @Override public String getName() { return "mbb_austenium:mythril"; }
    @Override public float getToughness() { return 2.0F; }
    @Override public float getKnockbackResistance() { return 0.0F; }
}
