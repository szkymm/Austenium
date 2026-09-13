/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.CoalOreBlock
 * TYPE: Java Source
 * DESCRIPTION: Base class of every coal ore block, dropping the vanilla coal experience range.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * CoalOreBlock CLASS IS CORE PART OF [MBB] AUSTENIUM CoalOreBlock.java.
 *
 * com.mbb.austenium.content.block.CoalOreBlock:
 *     Base class of the ten coal ore variants. Every coal ore yields the vanilla
 *     coal experience range, which the other ores of this mod do not grant.
 *
 * ATTRIBUTES:
 *     COAL_EXPERIENCE (UniformInt): Experience range shared by all coal ores.
 *
 * PUBLIC METHODS:
 *     CoalOreBlock(BlockBehaviour.Properties properties):
 *         Creates the coal ore block from the host rock properties.
 *
 * USAGE:
 *     Instantiated once per coal ore variant in ModBlocks; the variant only
 *     differs in map colour, hardness, resistance and sound.
 *
 * WARNING:
 *     Private methods should not be called from outside the class.
 */
public class CoalOreBlock extends DropExperienceBlock {

    /**
     * Experience range of every coal ore, matching the vanilla coal ore.
     */
    private static final UniformInt COAL_EXPERIENCE = UniformInt.of(0, 2);

    /**
     * Creates the CoalOreBlock instance.
     *
     * @param properties host rock properties of this coal ore variant
     */
    public CoalOreBlock(BlockBehaviour.Properties properties) {
        // Every coal ore drops the vanilla coal experience range.
        super(properties, COAL_EXPERIENCE);
    }
}
