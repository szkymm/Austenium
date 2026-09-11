/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.block;

import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * GoldChainBlock CLASS IS CORE PART OF [MBB] AUSTENIUM GoldChainBlock.java.
 *
 * com.mbb.austenium.content.block.GoldChainBlock:
 *     Chain of the gold tier: decorative metal chain; the tier registers no chainmail armour.
 */
public class GoldChainBlock extends ChainBlock {

    /**
     * Creates the GoldChainBlock instance.
     */
    public GoldChainBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.GOLD)
            .strength(5.0f, 6.0f)
            .sound(SoundType.CHAIN));
    }
}
