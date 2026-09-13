/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.JadeSupport
 * TYPE: Java Source
 * DESCRIPTION: Shared helpers for the Jade providers: tier names, registry paths and the AW bands.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.worldgen.AwDimensionRules;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;

import net.minecraftforge.registries.ForgeRegistries;

/**
 * JadeSupport CLASS IS CORE PART OF [MBB] AUSTENIUM JadeSupport.java.
 *
 * com.mbb.austenium.jade.JadeSupport:
 *     Turns a block into its tier id, its registry path and its translated tier name, and appends the
 *     Austeniumcraft World band line when a block sits inside that dimension.
 *
 * ATTRIBUTES:
 *     none.
 *
 * PUBLIC METHODS:
 *     pathOf(Block) -> String:
 *         Returns the registry path of one mod block.
 *     tierOf(Block) -> String:
 *         Returns the tier id prefix of one mod block, or an empty string without a prefix.
 *     tierName(String) -> Component:
 *         Returns the translated tier name of one tier id.
 *     appendAwBand(ITooltip, BlockAccessor) -> void:
 *         Appends the Austeniumcraft World band line when the target block is inside that dimension.
 *
 * PRIVATE METHODS:
 *     none.
 */
public final class JadeSupport {

    private JadeSupport() {}

    /**
     * Returns the registry path of a block.
     *
     * @param block the block to look up
     * @return the registry path, or an empty string when the block is not registered
     */
    public static String pathOf(Block block) {
        ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
        // Unregistered blocks never carry a registry path, so the empty string stands for them.
        return key == null ? "" : key.getPath();
    }

    /**
     * Returns the tier id prefix of a mod block.
     *
     * @param block the block to inspect
     * @return the tier id, or an empty string when the path has no prefix
     */
    public static String tierOf(Block block) {
        String path = pathOf(block);
        int separator = path.indexOf('_');
        // A path without a separator has no tier prefix at all.
        return separator <= 0 ? "" : path.substring(0, separator);
    }

    /**
     * Returns the translated name of one tier.
     *
     * @param tier the tier id, for example copper
     * @return the translated tier name
     */
    public static Component tierName(String tier) {
        return Component.translatable("jade.mbb_austenium.tier." + tier);
    }

    /**
     * Appends the Austeniumcraft World band line when the target sits inside that dimension.
     *
     * @param tooltip the tooltip under construction
     * @param accessor the Jade block accessor of the target
     */
    public static void appendAwBand(ITooltip tooltip, BlockAccessor accessor) {
        Level level = accessor.getLevel();
        // Only the mining dimension carries the five band cake, every other dimension is skipped.
        if (level == null || !AwDimensionRules.isAusteniumcraftWorld(level.dimension())) {
            return;
        }
        int y = accessor.getPosition().getY();
        tooltip.add(Component.translatable("jade.mbb_austenium.aw_band",
            Component.translatable("aw.mbb_austenium.band." + AwDimensionRules.bandId(y)),
            Component.literal(String.valueOf(y))));
    }
}
