/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.TierContainerProvider
 * TYPE: Java Source
 * DESCRIPTION: Jade provider that reports the tier and the live capacity of a mod container.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.TierShulkerBoxBlock;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.Block;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.Set;

/**
 * TierContainerProvider CLASS IS CORE PART OF [MBB] AUSTENIUM TierContainerProvider.java.
 *
 * com.mbb.austenium.jade.TierContainerProvider:
 *     Appends the tier and the slot count of a chest, barrel or shulker box. The count is read from the
 *     live block entity, so the line always matches the menu the block opens.
 *
 * ATTRIBUTES:
 *     blocks (Set<Block>): The container blocks this provider answers for.
 *
 * PUBLIC METHODS:
 *     TierContainerProvider(Set<Block>) -> TierContainerProvider:
 *         Creates the provider for the container family.
 *     getUid() -> ResourceLocation:
 *         Returns the Jade provider id.
 *     appendTooltip(ITooltip, BlockAccessor, IPluginConfig) -> void:
 *         Appends the tier and the capacity line of the targeted container.
 *
 * PRIVATE METHODS:
 *     tierOf(Block) -> String:
 *         Returns the tier id of a container, shulker boxes answer through their own tier enum.
 */
public class TierContainerProvider implements IBlockComponentProvider {

    private final Set<Block> blocks;

    /**
     * Creates the provider for the container family.
     *
     * @param blocks the container blocks this provider answers for
     */
    public TierContainerProvider(Set<Block> blocks) {
        this.blocks = blocks;
    }

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "container");
    }

    /** {@inheritDoc} */
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        Block block = accessor.getBlock();
        // Blocks outside the container family stay untouched, the guard covers shared classes.
        if (!this.blocks.contains(block)) {
            return;
        }
        // A container without a block entity carries no slot count, so nothing is appended.
        if (!(accessor.getBlockEntity() instanceof Container container)) {
            return;
        }
        tooltip.add(Component.translatable("jade.mbb_austenium.container",
            JadeSupport.tierName(tierOf(block)), Component.literal(String.valueOf(container.getContainerSize()))));
    }

    /**
     * Returns the tier id of one container block.
     *
     * @param block the container block
     * @return the tier id of the container
     */
    private static String tierOf(Block block) {
        // The shulker boxes share one class, so their tier comes from the block tier enum.
        if (block instanceof TierShulkerBoxBlock shulkerBox) {
            return shulkerBox.getTier().id();
        }
        return JadeSupport.tierOf(block);
    }
}
