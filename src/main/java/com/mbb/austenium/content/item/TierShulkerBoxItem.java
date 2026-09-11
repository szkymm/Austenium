/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Tier shulker box content for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import com.mbb.austenium.client.TierShulkerBoxItemRenderer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Consumer;

/**
 * TierShulkerBoxItem CLASS IS CORE PART OF [MBB] AUSTENIUM TierShulkerBoxItem.java.
 *
 * com.mbb.austenium.content.item.TierShulkerBoxItem:
 *     Block item for the tier shulker boxes; never stacks and is drawn as a real
 *     shulker box in the inventory instead of a flat sprite.
 */
public class TierShulkerBoxItem extends BlockItem {

    /**
     * Creates the TierShulkerBoxItem instance.
     *
     * @param block the block argument
     * @param properties the properties argument
     */
    public TierShulkerBoxItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    /**
     * Refuses to be stored inside another container item, which closes the vanilla
     * loophole where a plain shulker box would accept a tier box as nesting.
     *
     * @return always false, so containers that honour this rule reject the box
     */
    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    /**
     * Performs the initializeClient step.
     *
     * @param consumer the consumer argument
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BlockEntityWithoutLevelRenderer renderer;

            /** {@inheritDoc} */
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new TierShulkerBoxItemRenderer();
                }
                return this.renderer;
            }
        });
    }
}
