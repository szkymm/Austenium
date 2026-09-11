/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

/**
 * OrichalcumSwordItem CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumSwordItem.java.
 *
 * com.mbb.austenium.content.item.OrichalcumSwordItem:
 *     Sword of the orichalcum tier with the tier material and its innate enchantments.
 */
public class OrichalcumSwordItem extends SwordItem {
    /**
     * Creates the OrichalcumSwordItem instance.
     *
     * @param tier the tier this instance belongs to
     * @param attackDamage the attackDamage argument
     */
    public OrichalcumSwordItem(Tier tier, int attackDamage,
        float attackSpeed) { super(tier, attackDamage, attackSpeed, new net.minecraft.world.item.Item.Properties()); }
    @Override public boolean isFoil(ItemStack stack) { return false; }
}
