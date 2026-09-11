/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

/**
 * OrichalcumShovelItem CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumShovelItem.java.
 *
 * com.mbb.austenium.content.item.OrichalcumShovelItem:
 *     Shovel of the orichalcum tier with the tier material and its innate enchantments.
 */
public class OrichalcumShovelItem extends ShovelItem {
    /**
     * Creates the OrichalcumShovelItem instance.
     *
     * @param tier the tier this instance belongs to
     * @param attackDamage the attackDamage argument
     */
    public OrichalcumShovelItem(Tier tier, float attackDamage,
        float attackSpeed) { super(tier, attackDamage, attackSpeed, new net.minecraft.world.item.Item.Properties()); }
    @Override public boolean isFoil(ItemStack stack) { return false; }
}
