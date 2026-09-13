/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.TierCoalBlockItem
 * TYPE: Java Source
 * DESCRIPTION: Coal block item of one tier, burning ten times its coal.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

/**
 * TierCoalBlockItem CLASS IS CORE PART OF [MBB] AUSTENIUM TierCoalBlockItem.java.
 *
 * com.mbb.austenium.content.item.TierCoalBlockItem:
 *     Coal block item of one tier. Mirrors the vanilla coal block ratio, where
 *     the block burns ten times the burn time of one coal of the same tier.
 *
 * ATTRIBUTES:
 *     burnTicks (int): Burn duration in ticks this block item reports.
 *
 * PUBLIC METHODS:
 *     TierCoalBlockItem(Block block, int burnTicks):
 *         Creates the block item with the given burn duration.
 *     getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) -> int:
 *         Reports the block burn duration to any machine that accepts this item.
 *
 * USAGE:
 *     Registered once per coal tier in ModItems next to the matching block.
 *
 * WARNING:
 *     Private methods should not be called from outside the class.
 */
public class TierCoalBlockItem extends BlockItem {

    /**
     * Burn duration in ticks reported for one block of this tier.
     */
    private final int burnTicks;

    /**
     * Creates the TierCoalBlockItem instance.
     *
     * @param block the coal block this item places
     * @param burnTicks burn duration in ticks for one block of this tier
     */
    public TierCoalBlockItem(Block block, int burnTicks) {
        super(block, new Properties());
        this.burnTicks = burnTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        // Report the block design value: it is ten times the matching coal burn time.
        return this.burnTicks;
    }
}
