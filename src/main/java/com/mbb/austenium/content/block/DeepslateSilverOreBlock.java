/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.DeepslateSilverOreBlock
 * TYPE: Java Source
 * DESCRIPTION: Deepslate silver ore block.
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
 * DeepslateSilverOreBlock CLASS IS CORE PART OF [MBB] AUSTENIUM DeepslateSilverOreBlock.java.
 */
public class DeepslateSilverOreBlock extends Block {

    /**
     * Creates the DeepslateSilverOreBlock instance.
     */
    public DeepslateSilverOreBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.DEEPSLATE)
            .strength(2.0f, 1.5f)
            .sound(SoundType.STONE).requiresCorrectToolForDrops()

            );
    }
}