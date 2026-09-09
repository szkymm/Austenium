/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.RadiantPickaxeItem
 * TYPE: Java Source
 * DESCRIPTION: Radiant pickaxe item; no vanilla glint, innate enchantments on smithing.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

/**
 * RadiantPickaxeItem CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantPickaxeItem.java.
 *
 * com.mbb.austenium.content.item.RadiantPickaxeItem:
 *     Radiant pickaxe; no vanilla glint, innate enchantments on smithing.
 */
public class RadiantPickaxeItem extends PickaxeItem {

    public RadiantPickaxeItem(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Item.Properties().fireResistant());
    }

    @Override public boolean isFoil(ItemStack stack) { return false; }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) {
            RadiantEnchantments.applyTool(stack);
        }
    }
}
