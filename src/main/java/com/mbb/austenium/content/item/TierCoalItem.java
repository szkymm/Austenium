/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.TierCoalItem
 * TYPE: Java Source
 * DESCRIPTION: Coal item of one tier, carrying its own burn time.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * TierCoalItem CLASS IS CORE PART OF [MBB] AUSTENIUM TierCoalItem.java.
 *
 * com.mbb.austenium.content.item.TierCoalItem:
 *     Coal item of one tier. The vanilla fuel map is closed, so the burn time is
 *     carried by the item itself and read back through the Forge item hook.
 *
 * ATTRIBUTES:
 *     burnTicks (int): Burn duration in ticks this item reports to every furnace.
 *
 * PUBLIC METHODS:
 *     TierCoalItem(int burnTicks):
 *         Creates the coal item with the given burn duration.
 *     getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) -> int:
 *         Reports the tier burn duration to any machine that accepts this item.
 *
 * USAGE:
 *     Registered once per coal tier in ModItems; the value is the tier design
 *     burn time and never depends on the recipe type of the asking machine.
 *
 * WARNING:
 *     Private methods should not be called from outside the class.
 */
public class TierCoalItem extends Item {

    /**
     * Burn duration in ticks reported for one item of this tier.
     */
    private final int burnTicks;

    /**
     * Creates the TierCoalItem instance.
     *
     * @param burnTicks burn duration in ticks for one item of this tier
     */
    public TierCoalItem(int burnTicks) {
        super(new Item.Properties());
        this.burnTicks = burnTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        // Report the tier design value: the burn time never depends on the machine type.
        return this.burnTicks;
    }
}
