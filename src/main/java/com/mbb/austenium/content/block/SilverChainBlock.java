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
 * SilverChainBlock CLASS IS CORE PART OF [MBB] AUSTENIUM SilverChainBlock.java.
 *
 * com.mbb.austenium.content.block.SilverChainBlock:
 *     Chain of the silver tier: decorative metal chain; the tier registers no chainmail armour.
 */
public class SilverChainBlock extends ChainBlock {

    /**
     * Creates the SilverChainBlock instance.
     */
    public SilverChainBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .strength(5.0f, 6.0f)
            .sound(SoundType.CHAIN));
    }
}
