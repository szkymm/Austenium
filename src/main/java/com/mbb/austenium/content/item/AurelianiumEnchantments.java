/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumEnchantments
 * TYPE: Java Source
 * DESCRIPTION: Innate enchantment helper for aurelianium gear obtained from the smithing table.
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
 * AURELIANIUMENCHANTMENTS CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumEnchantments.java.
 *
 * com.mbb.austenium.content.item.AurelianiumEnchantments:
 *     Applies the innate aurelianium enchantments; smithing never fires
 *     Forge ItemCraftedEvent, so the custom recipe type bakes them at assemble.
 */
public final class AurelianiumEnchantments {

    private AurelianiumEnchantments() {}

    /**
     * Applies the innate enchantments for whichever aurelianium piece is given.
     *
     * @param stack the freshly smithed aurelianium item
     */
    public static void applyCrafted(ItemStack stack) {
        Item item = stack.getItem();
        // Armor and tools use different innate sets.
        if (item instanceof AurelianiumArmorItem armorItem) {
            applyArmor(stack, armorItem.getType() == ArmorItem.Type.BOOTS);
        } else if (item instanceof AurelianiumSwordItem || item instanceof AurelianiumPickaxeItem
                || item instanceof AurelianiumAxeItem || item instanceof AurelianiumShovelItem
                || item instanceof AurelianiumHoeItem) {
            applyTool(stack);
        }
    }

    /**
     * Applies the innate tool enchantments without downgrading inherited levels.
     *
     * @param stack the freshly smithed aurelianium tool
     */
    public static void applyTool(ItemStack stack) {
        // Efficiency, Fortune, Unbreaking and Mending define the aurelianium tool identity.
        enchantIfLower(stack, Enchantments.BLOCK_EFFICIENCY, 5);
        enchantIfLower(stack, Enchantments.BLOCK_FORTUNE, 5);
        enchantIfLower(stack, Enchantments.UNBREAKING, 5);
        enchantIfLower(stack, Enchantments.MENDING, 1);
    }

    /**
     * Applies the innate armor enchantments without downgrading inherited levels.
     *
     * @param stack the freshly smithed aurelianium armor piece
     * @param isBoots whether the piece is boots, which also gain Feather Falling
     */
    public static void applyArmor(ItemStack stack, boolean isBoots) {
        // Protection, Unbreaking and Mending apply to every aurelianium armor piece.
        enchantIfLower(stack, Enchantments.ALL_DAMAGE_PROTECTION, 6);
        enchantIfLower(stack, Enchantments.UNBREAKING, 6);
        enchantIfLower(stack, Enchantments.MENDING, 1);
        if (isBoots) {
            // Feather Falling only belongs on boots.
            enchantIfLower(stack, Enchantments.FALL_PROTECTION, 6);
        }
    }

    private static void enchantIfLower(ItemStack stack, Enchantment enchantment, int level) {
        int currentLevel = EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
        if (currentLevel < level) {
            stack.enchant(enchantment, level);
        }
    }
}
