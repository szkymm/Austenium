/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.TierOreProvider
 * TYPE: Java Source
 * DESCRIPTION: Jade provider that reports the mining tier and the generation band of a mod ore.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.Set;

/**
 * TierOreProvider CLASS IS CORE PART OF [MBB] AUSTENIUM TierOreProvider.java.
 *
 * com.mbb.austenium.jade.TierOreProvider:
 *     Appends the mining tier and the generation band of an ore or debris block. The mining tier is read
 *     from the needs tags of the block itself, so it can never disagree with the drop gating, and the
 *     band line is derived from the registry path of the block.
 *
 * ATTRIBUTES:
 *     blocks (Set<Block>): The ore and debris blocks this provider answers for.
 *
 * PUBLIC METHODS:
 *     TierOreProvider(Set<Block>) -> TierOreProvider:
 *         Creates the provider for the ore family.
 *     getUid() -> ResourceLocation:
 *         Returns the Jade provider id.
 *     appendTooltip(ITooltip, BlockAccessor, IPluginConfig) -> void:
 *         Appends the mining tier and band lines of the targeted ore.
 *
 * PRIVATE METHODS:
 *     miningKey(BlockState) -> String:
 *         Maps the needs tags of one ore to its pickaxe tier key.
 *     appendBand(ITooltip, String) -> void:
 *         Appends the band line of one ore path.
 *     metalBandKey(String) -> String:
 *         Returns the band key of a metal ore or debris path, or null for coal ores.
 *     coalBand(String) -> Component:
 *         Composes the dimension and host rock line of one coal ore.
 */
public class TierOreProvider implements IBlockComponentProvider {

    private final Set<Block> blocks;

    /**
     * Creates the provider for the ore family.
     *
     * @param blocks the ore and debris blocks this provider answers for
     */
    public TierOreProvider(Set<Block> blocks) {
        this.blocks = blocks;
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "ore");
    }

    /** {@inheritDoc} */
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        // Blocks outside the ore family stay untouched, which guards shared block classes.
        if (!this.blocks.contains(block)) {
            return;
        }
        BlockState state = accessor.getBlockState();
        String miningKey = miningKey(state);
        tooltip.add(Component.translatable("jade.mbb_austenium.ore.mining",
            Component.translatable("jade.mbb_austenium.mining." + miningKey)));
        appendBand(tooltip, JadeSupport.pathOf(block));
        JadeSupport.appendAwBand(tooltip, accessor);
    }

    /**
     * Maps the needs tags of one ore to its pickaxe tier key.
     *
     * @param state the block state of the ore
     * @return the mining tier key, one of diamond, iron, stone or wood
     */
    private static String miningKey(BlockState state) {
        // The needs tags carry the real drop gating, so they are the single source for this line.
        if (state.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return "diamond";
        }
        if (state.is(BlockTags.NEEDS_IRON_TOOL)) {
            return "iron";
        }
        if (state.is(BlockTags.NEEDS_STONE_TOOL)) {
            return "stone";
        }
        return "wood";
    }

    /**
     * Appends the generation band line of one ore path.
     *
     * @param tooltip the tooltip under construction
     * @param path the block registry path
     */
    private static void appendBand(ITooltip tooltip, String path) {
        String bandKey = metalBandKey(path);
        // Metal ores and debris share one band line per material, coal ores compose theirs.
        if (bandKey != null) {
            tooltip.add(Component.translatable("jade.mbb_austenium.ore.band", Component.translatable(bandKey)));
            return;
        }
        tooltip.add(coalBand(path));
    }

    /**
     * Returns the band key of a metal ore or debris path.
     *
     * @param path the block registry path
     * @return the band translation key, or null for a coal ore path
     */
    private static String metalBandKey(String path) {
        if (path.equals("radiant_debris")) {
            return "jade.mbb_austenium.band.radiant";
        }
        if (path.equals("aurelianium_debris")) {
            return "jade.mbb_austenium.band.aurelianium";
        }
        // The four metal families share one band line between their stone and deepslate variants.
        for (String material : new String[] {"silver", "orichalcum", "mythril", "adamantite"}) {
            if (path.contains(material)) {
                return "jade.mbb_austenium.band." + material;
            }
        }
        return null;
    }

    /**
     * Composes the dimension and host rock line of one coal ore.
     *
     * @param path the block registry path of a coal ore
     * @return the translated coal band line
     */
    private static Component coalBand(String path) {
        // The registry path names both the dimension family and the host rock of every coal ore.
        String dimension = path.contains("austeniumcraft") ? "austeniumcraft"
            : path.contains("nether") ? "nether" : path.contains("end") ? "end" : "overworld";
        String rock = path.contains("deepslate") ? "deepslate"
            : path.contains("blackstone") ? "blackstone"
            : path.contains("netherrack") ? "netherrack"
            : path.contains("end_stone") ? "end_stone" : "stone";
        return Component.translatable("jade.mbb_austenium.band.coal",
            Component.translatable("jade.mbb_austenium.dim." + dimension),
            Component.translatable("jade.mbb_austenium.rock." + rock));
    }
}
