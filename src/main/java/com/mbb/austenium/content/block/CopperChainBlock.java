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
 * CopperChainBlock CLASS IS CORE PART OF [MBB] AUSTENIUM CopperChainBlock.java.
 *
 * com.mbb.austenium.content.block.CopperChainBlock:
 *     Chain of the copper tier: decorative metal chain; the tier registers no chainmail armour.
 */
public class CopperChainBlock extends ChainBlock {

    /**
     * Creates the CopperChainBlock instance.
     */
    public CopperChainBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_ORANGE)
            .strength(5.0f, 6.0f)
            .sound(SoundType.CHAIN));
    }
}
