/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumArmorItem
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium armor item; no vanilla glint, innate enchantments on smithing.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * AURELIANIUMARMORITEM CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumArmorItem.java.
 *
 * com.mbb.austenium.content.item.AurelianiumArmorItem:
 *     Aurelianium armor piece; innate protection, unbreaking, mending and boot feathers.
 */
public class AurelianiumArmorItem extends ArmorItem {

    public AurelianiumArmorItem(ArmorMaterial material, ArmorItem.Type type) {
        super(material, type, new Item.Properties().fireResistant());
    }

    @Override public boolean isFoil(ItemStack stack) { return true; }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) {
            AurelianiumEnchantments.applyArmor(stack, this.getType() == ArmorItem.Type.BOOTS);
        }
    }
}
