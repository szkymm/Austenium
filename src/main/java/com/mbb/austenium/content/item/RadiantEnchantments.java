/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.RadiantEnchantments
 * TYPE: Java Source
 * DESCRIPTION: Innate enchantment helper for radiant gear obtained from the smithing table.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * RADIANTENCHANTMENTS CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantEnchantments.java.
 *
 * com.mbb.austenium.content.item.RadiantEnchantments:
 *     Applies the innate radiant enchantments; smithing results never fire
 *     Forge ItemCraftedEvent, so the item classes call these helpers from
 *     Item#onCraftedBy instead.
 */
public final class RadiantEnchantments {

    private RadiantEnchantments() {}

    /**
     * Applies the innate enchantments for whichever radiant piece is given.
     *
     * @param stack the freshly crafted or smithed radiant item
     */
    public static void applyCrafted(ItemStack stack) {
        Item item = stack.getItem();
        // Armor and tools use different innate sets.
        if (item instanceof RadiantArmorItem armorItem) {
            applyArmor(stack, armorItem.getType() == ArmorItem.Type.BOOTS);
        } else if (item instanceof RadiantSwordItem || item instanceof RadiantPickaxeItem
                || item instanceof RadiantAxeItem || item instanceof RadiantShovelItem
                || item instanceof RadiantHoeItem) {
            applyTool(stack);
        }
    }

    /**
     * Applies the innate tool enchantments without downgrading inherited levels.
     *
     * @param stack the freshly smithed radiant tool
     */
    public static void applyTool(ItemStack stack) {
        // Efficiency, Fortune, Unbreaking and Mending define the radiant tool identity.
        enchantIfLower(stack, Enchantments.BLOCK_EFFICIENCY, 3);
        enchantIfLower(stack, Enchantments.BLOCK_FORTUNE, 3);
        enchantIfLower(stack, Enchantments.UNBREAKING, 3);
        enchantIfLower(stack, Enchantments.MENDING, 1);
    }

    /**
     * Applies the innate armor enchantments without downgrading inherited levels.
     *
     * @param stack the freshly smithed radiant armor piece
     * @param isBoots whether the piece is boots, which also gain Feather Falling
     */
    public static void applyArmor(ItemStack stack, boolean isBoots) {
        // Protection, Unbreaking and Mending apply to every radiant armor piece.
        enchantIfLower(stack, Enchantments.ALL_DAMAGE_PROTECTION, 3);
        enchantIfLower(stack, Enchantments.UNBREAKING, 3);
        enchantIfLower(stack, Enchantments.MENDING, 1);
        if (isBoots) {
            // Feather Falling only belongs on boots.
            enchantIfLower(stack, Enchantments.FALL_PROTECTION, 3);
        }
    }

    private static void enchantIfLower(ItemStack stack, Enchantment enchantment, int level) {
        int currentLevel = EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
        if (currentLevel < level) {
            stack.enchant(enchantment, level);
        }
    }
}
