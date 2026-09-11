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

/**
 * OrichalcumArmorItem CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumArmorItem.java.
 *
 * com.mbb.austenium.content.item.OrichalcumArmorItem:
 *     Armour piece of the orichalcum tier with its durability, defence and innate enchantments.
 */
public class OrichalcumArmorItem extends ArmorItem {
    /**
     * Creates the OrichalcumArmorItem instance.
     *
     * @param material the material argument
     * @param type the block entity type
     */
    public OrichalcumArmorItem(ArmorMaterial material, ArmorItem.Type type) {
        super(material, type, new net.minecraft.world.item.Item.Properties());
    }
    @Override public boolean isFoil(ItemStack stack) { return false; }
}
