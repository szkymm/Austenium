/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumHoeItem
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium hoe item; no vanilla glint, innate enchantments on smithing.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

/**
 * AurelianiumHoeItem CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumHoeItem.java.
 *
 * com.mbb.austenium.content.item.AurelianiumHoeItem:
 *     Aurelianium hoe; no vanilla glint, innate enchantments on smithing.
 */
public class AurelianiumHoeItem extends HoeItem {

    /**
     * Creates the AurelianiumHoeItem instance.
     *
     * @param tier the tier this instance belongs to
     * @param attackDamage the attackDamage argument
     * @param attackSpeed the attackSpeed argument
     */
    public AurelianiumHoeItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Item.Properties().fireResistant());
    }

    @Override public boolean isFoil(ItemStack stack) { return true; }

    /** {@inheritDoc} */
    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) {
            AurelianiumEnchantments.applyTool(stack);
        }
    }
}
