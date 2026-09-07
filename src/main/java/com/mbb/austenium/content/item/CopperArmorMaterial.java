/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */

package com.mbb.austenium.content.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class CopperArmorMaterial implements ArmorMaterial {

    public static final ArmorMaterial INSTANCE = new CopperArmorMaterial();

    @Override public int getDurabilityForType(ArmorItem.Type type) { return 14; }
    @Override public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> 2; case CHESTPLATE -> 5; case LEGGINGS -> 4; case BOOTS -> 1;
        };
    }
    @Override public int getEnchantmentValue() { return 12; }
    @Override public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_IRON; }
    @Override public Ingredient getRepairIngredient() { return Ingredient.of(net.minecraft.world.item.Items.COPPER_INGOT); }
    @Override public String getName() { return "mbb_austenium:copper"; }
    @Override public float getToughness() { return 0.0F; }
    @Override public float getKnockbackResistance() { return 0.0F; }
}