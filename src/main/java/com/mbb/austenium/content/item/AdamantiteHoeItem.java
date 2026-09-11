/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

/**
 * AdamantiteHoeItem CLASS IS CORE PART OF [MBB] AUSTENIUM AdamantiteHoeItem.java.
 *
 * com.mbb.austenium.content.item.AdamantiteHoeItem:
 *     Hoe of the adamantite tier with the tier material and its innate enchantments.
 */
public class AdamantiteHoeItem extends HoeItem {
    /**
     * Creates the AdamantiteHoeItem instance.
     *
     * @param tier the tier this instance belongs to
     * @param attackDamage the attackDamage argument
     */
    public AdamantiteHoeItem(Tier tier, int attackDamage,
        float attackSpeed) { super(tier, attackDamage, attackSpeed, new net.minecraft.world.item.Item.Properties()); }
    @Override public boolean isFoil(ItemStack stack) { return false; }
}