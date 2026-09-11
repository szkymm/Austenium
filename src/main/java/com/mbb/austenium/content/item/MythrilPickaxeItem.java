/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

/**
 * MythrilPickaxeItem CLASS IS CORE PART OF [MBB] AUSTENIUM MythrilPickaxeItem.java.
 *
 * com.mbb.austenium.content.item.MythrilPickaxeItem:
 *     Pickaxe of the mythril tier with the tier material and its innate enchantments.
 */
public class MythrilPickaxeItem extends PickaxeItem {
    /**
     * Creates the MythrilPickaxeItem instance.
     *
     * @param tier the tier this instance belongs to
     * @param attackDamage the attackDamage argument
     */
    public MythrilPickaxeItem(Tier tier, int attackDamage,
        float attackSpeed) { super(tier, attackDamage, attackSpeed, new net.minecraft.world.item.Item.Properties()); }
    @Override public boolean isFoil(ItemStack stack) { return false; }
}