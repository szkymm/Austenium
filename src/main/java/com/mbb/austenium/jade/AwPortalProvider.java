/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.AwPortalProvider
 * TYPE: Java Source
 * DESCRIPTION: Jade provider that explains the Austeniumcraft World portal plane.
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

import java.util.Set;

/**
 * AwPortalProvider CLASS IS CORE PART OF [MBB] AUSTENIUM AwPortalProvider.java.
 *
 * com.mbb.austenium.jade.AwPortalProvider:
 *     Appends the frame rule and the activation rule of the Austeniumcraft World portal plane, plus the
 *     current band when the plane itself sits inside the mining dimension.
 *
 * ATTRIBUTES:
 *     blocks (Set<Block>): The portal blocks this provider answers for.
 *
 * PUBLIC METHODS:
 *     AwPortalProvider(Set<Block>) -> AwPortalProvider:
 *         Creates the provider for the portal family.
 *     getUid() -> ResourceLocation:
 *         Returns the Jade provider id.
 *     appendTooltip(ITooltip, BlockAccessor, IPluginConfig) -> void:
 *         Appends the portal rule line of the targeted plane block.
 *
 * PRIVATE METHODS:
 *     none.
 */
public class AwPortalProvider implements IBlockComponentProvider {

    private final Set<Block> blocks;

    /**
     * Creates the provider for the portal family.
     *
     * @param blocks the portal blocks this provider answers for
     */
    public AwPortalProvider(Set<Block> blocks) {
        this.blocks = blocks;
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "portal");
    }

    /** {@inheritDoc} */
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        // Only the portal plane carries the frame rule line, every other block is skipped.
        if (!this.blocks.contains(block)) {
            return;
        }
        tooltip.add(Component.translatable("jade.mbb_austenium.portal"));
        JadeSupport.appendAwBand(tooltip, accessor);
    }
}
