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

/**
 * AdamantiteOreBlock CLASS IS CORE PART OF [MBB] AUSTENIUM AdamantiteOreBlock.java.
 *
 * com.mbb.austenium.content.block.AdamantiteOreBlock:
 *     Stone variant of the adamantite ore; drops the tier raw material and is gated to the tier pickaxe level.
 */
public class AdamantiteOreBlock extends Block {
    /**
     * Creates the AdamantiteOreBlock instance.
     */
    public AdamantiteOreBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.STONE).requiresCorrectToolForDrops()
            .lightLevel(blockState -> 8));
    }
}