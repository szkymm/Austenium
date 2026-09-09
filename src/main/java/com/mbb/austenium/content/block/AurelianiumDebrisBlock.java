/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.AurelianiumDebrisBlock
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium debris block; the overworld ore source of the aurelianium tier.
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
 * AURELIANIUMDEBRISBLOCK CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumDebrisBlock.java.
 *
 * com.mbb.austenium.content.block.AurelianiumDebrisBlock:
 *     Aurelianium debris block; mimics ancient debris but glows at light level 12.
 */
public class AurelianiumDebrisBlock extends Block {

    public AurelianiumDebrisBlock() {
        super(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .strength(30.0f, 1200.0f)
            .sound(SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops()
            .lightLevel(blockState -> 15));
    }
}
