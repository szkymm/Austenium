/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */

package com.mbb.austenium.content.block;

import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class GoldChainBlock extends ChainBlock {

    public GoldChainBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.GOLD)
            .strength(5.0f, 6.0f)
            .sound(SoundType.CHAIN));
    }
}
