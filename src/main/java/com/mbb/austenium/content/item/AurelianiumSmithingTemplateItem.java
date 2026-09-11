/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumSmithingTemplateItem
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium smithing template item that always renders the vanilla enchantment glint.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

/**
 * AURELIANIUMSMITHINGTEMPLATEITEM CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumSmithingTemplateItem.java.
 *
 * com.mbb.austenium.content.item.AurelianiumSmithingTemplateItem:
 *     Aurelianium upgrade template; inherits the vanilla template tooltips and adds the
 *     tier's constant enchantment glint.
 */
public class AurelianiumSmithingTemplateItem extends SmithingTemplateItem {

    /**
     * Creates the AurelianiumSmithingTemplateItem instance.
     *
     * @param appliesTo the appliesTo argument
     * @param ingredients the ingredients argument
     * @param upgradeDescription the upgradeDescription argument
     */
    public AurelianiumSmithingTemplateItem(Component appliesTo, Component ingredients, Component upgradeDescription,
                                           Component baseSlotDescription, Component additionsSlotDescription,
                                           List<ResourceLocation> baseIcons, List<ResourceLocation> additionIcons) {
        super(appliesTo, ingredients, upgradeDescription, baseSlotDescription, additionsSlotDescription,
            baseIcons, additionIcons);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFoil(ItemStack stack) {
        // Always glint, matching the rest of the aurelianium item set.
        return true;
    }
}
