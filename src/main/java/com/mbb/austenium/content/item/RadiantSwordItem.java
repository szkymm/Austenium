/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.RadiantSwordItem
 * TYPE: Java Source
 * DESCRIPTION: Radiant sword item; no vanilla glint, innate enchantments on smithing.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

/**
 * RadiantSwordItem CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantSwordItem.java.
 *
 * com.mbb.austenium.content.item.RadiantSwordItem:
 *     Radiant sword; no vanilla glint, innate enchantments on smithing.
 */
public class RadiantSwordItem extends SwordItem {

    /**
     * Creates the RadiantSwordItem instance.
     *
     * @param tier the tier this instance belongs to
     * @param attackDamage the attackDamage argument
     * @param attackSpeed the attackSpeed argument
     */
    public RadiantSwordItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Item.Properties().fireResistant());
    }

    @Override public boolean isFoil(ItemStack stack) { return false; }

    /** {@inheritDoc} */
    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) {
            RadiantEnchantments.applyTool(stack);
        }
    }
}
