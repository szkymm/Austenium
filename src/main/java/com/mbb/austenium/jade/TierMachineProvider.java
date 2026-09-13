/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.TierMachineProvider
 * TYPE: Java Source
 * DESCRIPTION: Jade provider that reports the tier and the speed multiplier of a cooking machine.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.Map;
import java.util.Set;

/**
 * TierMachineProvider CLASS IS CORE PART OF [MBB] AUSTENIUM TierMachineProvider.java.
 *
 * com.mbb.austenium.jade.TierMachineProvider:
 *     Appends the tier and the design speed multiplier of a furnace, blast furnace or smoker. The live
 *     cooking progress and the remaining fuel stay with the built in Jade furnace provider.
 *
 * ATTRIBUTES:
 *     SPEED_MULTIPLIERS (Map<String, String>): Design multiplier of every machine tier.
 *     blocks (Set<Block>): The machine blocks this provider answers for.
 *
 * PUBLIC METHODS:
 *     TierMachineProvider(Set<Block>) -> TierMachineProvider:
 *         Creates the provider for one machine family.
 *     getUid() -> ResourceLocation:
 *         Returns the Jade provider id.
 *     appendTooltip(ITooltip, BlockAccessor, IPluginConfig) -> void:
 *         Appends the tier and the speed line of the targeted machine.
 *
 * PRIVATE METHODS:
 *     none.
 */
public class TierMachineProvider implements IBlockComponentProvider {

    private static final Map<String, String> SPEED_MULTIPLIERS = Map.ofEntries(
        Map.entry("copper", "1.25"),
        Map.entry("iron", "2.5"),
        Map.entry("silver", "3"),
        Map.entry("gold", "5"),
        Map.entry("diamond", "6"),
        Map.entry("emerald", "8"),
        Map.entry("orichalcum", "10"),
        Map.entry("mythril", "12"),
        Map.entry("adamantite", "15"),
        Map.entry("netherite", "20"),
        Map.entry("radiant", "25"),
        Map.entry("aurelianium", "50"));

    private final Set<Block> blocks;

    /**
     * Creates the provider for one machine family.
     *
     * @param blocks the machine blocks this provider answers for
     */
    public TierMachineProvider(Set<Block> blocks) {
        this.blocks = blocks;
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "machine");
    }

    /** {@inheritDoc} */
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        // Blocks outside the machine family stay untouched, which guards shared block classes.
        if (!this.blocks.contains(block)) {
            return;
        }
        String speed = SPEED_MULTIPLIERS.get(JadeSupport.tierOf(block));
        // A machine tier without a design multiplier is not part of the ladder, so it stays silent.
        if (speed == null) {
            return;
        }
        tooltip.add(Component.translatable("jade.mbb_austenium.machine",
            JadeSupport.tierName(JadeSupport.tierOf(block)), Component.literal(speed)));
    }
}
