/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class DeepslateMythrilOreBlock extends Block {
    public DeepslateMythrilOreBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.DEEPSLATE)
            .strength(2.5f, 2.0f)
            .sound(SoundType.STONE).requiresCorrectToolForDrops()
            .lightLevel(blockState -> 8));
    }
}