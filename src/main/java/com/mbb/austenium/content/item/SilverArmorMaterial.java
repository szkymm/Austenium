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
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * SilverArmorMaterial CLASS IS CORE PART OF [MBB] AUSTENIUM SilverArmorMaterial.java.
 *
 * com.mbb.austenium.content.item.SilverArmorMaterial:
 *     Armour material of the silver tier: durability factor, defence points and equip sound.
 */
public class SilverArmorMaterial implements ArmorMaterial {

    public static final SilverArmorMaterial INSTANCE = new SilverArmorMaterial();

    /** {@inheritDoc} */
    @Override
    public int getDurabilityForType(net.minecraft.world.item.ArmorItem.Type type) {
        return 20;
    }

    /** {@inheritDoc} */
    @Override
    public int getDefenseForType(net.minecraft.world.item.ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> 3;
            case CHESTPLATE -> 6;
            case LEGGINGS -> 5;
            case BOOTS -> 2;
        };
    }

    /** {@inheritDoc} */
    @Override
    public int getEnchantmentValue() {
        return 14;
    }

    /** {@inheritDoc} */
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    /** {@inheritDoc} */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.SILVER_INGOT.get());
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "mbb_austenium:silver";
    }

    /** {@inheritDoc} */
    @Override
    public float getToughness() {
        return 0.0F;
    }

    /** {@inheritDoc} */
    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
