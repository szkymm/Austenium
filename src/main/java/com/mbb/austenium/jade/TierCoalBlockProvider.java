/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.TierCoalBlockProvider
 * TYPE: Java Source
 * DESCRIPTION: Jade provider that reports the fuel value of a mod coal block.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.Set;

/**
 * TierCoalBlockProvider CLASS IS CORE PART OF [MBB] AUSTENIUM TierCoalBlockProvider.java.
 *
 * com.mbb.austenium.jade.TierCoalBlockProvider:
 *     Appends the burn budget of a mod coal block. The number is read from the item burn time the block
 *     item reports, so the tooltip always follows the fuel table the furnaces use.
 *
 * ATTRIBUTES:
 *     TICKS_PER_ITEM (int): Vanilla burn ticks of one smelting operation.
 *     blocks (Set<Block>): The coal blocks this provider answers for.
 *
 * PUBLIC METHODS:
 *     TierCoalBlockProvider(Set<Block>) -> TierCoalBlockProvider:
 *         Creates the provider for the fuel block family.
 *     getUid() -> ResourceLocation:
 *         Returns the Jade provider id.
 *     appendTooltip(ITooltip, BlockAccessor, IPluginConfig) -> void:
 *         Appends the burn budget line of the targeted coal block.
 *
 * PRIVATE METHODS:
 *     none.
 */
public class TierCoalBlockProvider implements IBlockComponentProvider {

    private static final int TICKS_PER_ITEM = 200;

    private final Set<Block> blocks;

    /**
     * Creates the provider for the fuel block family.
     *
     * @param blocks the coal blocks this provider answers for
     */
    public TierCoalBlockProvider(Set<Block> blocks) {
        this.blocks = blocks;
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "coal_block");
    }

    /** {@inheritDoc} */
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        // Only the sixteen coal blocks carry a fuel line, every other block is skipped.
        if (!this.blocks.contains(block)) {
            return;
        }
        int burnTicks = new ItemStack(block.asItem()).getBurnTime(null);
        // A block without a fuel value reports nothing instead of a misleading zero.
        if (burnTicks <= 0) {
            return;
        }
        tooltip.add(Component.translatable("jade.mbb_austenium.coal_block",
            Component.literal(String.valueOf(burnTicks)),
            Component.literal(String.valueOf(burnTicks / TICKS_PER_ITEM))));
    }
}
