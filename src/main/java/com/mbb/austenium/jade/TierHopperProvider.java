/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.TierHopperProvider
 * TYPE: Java Source
 * DESCRIPTION: Jade provider that reports the tier and the measured transfer rate of a tiered hopper.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.TierHopperBlock;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.Locale;
import java.util.Set;

/**
 * TierHopperProvider CLASS IS CORE PART OF [MBB] AUSTENIUM TierHopperProvider.java.
 *
 * com.mbb.austenium.jade.TierHopperProvider:
 *     Appends the tier, the items per second, the items per activation and the cooldown of a tiered
 *     hopper. The numbers come from the HopperTier enum, so the tooltip cannot drift from the tick code.
 *
 * ATTRIBUTES:
 *     blocks (Set<Block>): The hopper blocks this provider answers for.
 *
 * PUBLIC METHODS:
 *     TierHopperProvider(Set<Block>) -> TierHopperProvider:
 *         Creates the provider for the hopper family.
 *     getUid() -> ResourceLocation:
 *         Returns the Jade provider id.
 *     appendTooltip(ITooltip, BlockAccessor, IPluginConfig) -> void:
 *         Appends the tier and the rate line of the targeted hopper.
 *
 * PRIVATE METHODS:
 *     none.
 */
public class TierHopperProvider implements IBlockComponentProvider {

    private static final int TICKS_PER_SECOND = 20;

    private final Set<Block> blocks;

    /**
     * Creates the provider for the hopper family.
     *
     * @param blocks the hopper blocks this provider answers for
     */
    public TierHopperProvider(Set<Block> blocks) {
        this.blocks = blocks;
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "hopper");
    }

    /** {@inheritDoc} */
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        // Only the twelve tier hoppers carry the rate line, every other block is skipped.
        if (!this.blocks.contains(block) || !(block instanceof TierHopperBlock hopperBlock)) {
            return;
        }
        TierHopperBlock.HopperTier tier = hopperBlock.getTier();
        // The measured rate follows the design formula of twenty ticks per second over the cooldown.
        double rate = (double) TICKS_PER_SECOND * tier.itemsPerActivation() / tier.cooldownTicks();
        String rateText = String.format(Locale.ROOT, "%.1f", rate);
        tooltip.add(Component.translatable("jade.mbb_austenium.hopper",
            JadeSupport.tierName(tier.id()), Component.literal(rateText),
            Component.literal(String.valueOf(tier.itemsPerActivation())),
            Component.literal(String.valueOf(tier.cooldownTicks()))));
    }
}
