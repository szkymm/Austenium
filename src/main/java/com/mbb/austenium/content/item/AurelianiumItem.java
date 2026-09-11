/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumItem
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium material item that always renders the vanilla enchantment glint.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * AURELIANIUMITEM CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumItem.java.
 *
 * com.mbb.austenium.content.item.AurelianiumItem:
 *     Aurelianium material item; the tier uses the vanilla enchantment glint instead of
 *     the baked theme-colour halo the older tiers use.
 */
public class AurelianiumItem extends Item {

    /**
     * Creates the AurelianiumItem instance.
     *
     * @param properties the properties argument
     */
    public AurelianiumItem(Properties properties) {
        super(properties);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFoil(ItemStack stack) {
        // Always glint: the aurelianium tier is specified with the vanilla enchantment effect.
        return true;
    }
}
