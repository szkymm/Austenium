/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class AdamantiteArmorItem extends ArmorItem {
    public AdamantiteArmorItem(ArmorMaterial material, ArmorItem.Type type) {
        super(material, type, new net.minecraft.world.item.Item.Properties());
    }
    @Override public boolean isFoil(ItemStack stack) { return false; }
}