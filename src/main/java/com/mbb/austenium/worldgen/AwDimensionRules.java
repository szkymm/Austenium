/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.worldgen.AwDimensionRules
 * TYPE: Java Source
 * DESCRIPTION: Shared constants and band predicates of the Austeniumcraft World.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.worldgen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

/**
 * AWDIMENSIONRULES CLASS IS CORE PART OF [MBB] AUSTENIUM AwDimensionRules.java.
 *
 * com.mbb.austenium.worldgen.AwDimensionRules:
 *     One place for the Austeniumcraft World dimension key and its band limits, so the
 *     generator, the portal logic and the event rules cannot drift apart.
 *
 * ATTRIBUTES:
 *     DIMENSION (ResourceKey<Level>): Key of the Austeniumcraft World.
 *     BEDROCK_Y (int): Height of the single bedrock floor layer.
 *     DEEPSLATE_TOP_Y (int): Highest deepslate layer.
 *     STONE_TOP_Y (int): Highest stone layer.
 *     NETHERRACK_TOP_Y (int): Highest netherrack layer.
 *     TOP_Y (int): Highest block layer of the dimension.
 */
public final class AwDimensionRules {

    public static final ResourceKey<Level> DIMENSION = ResourceKey.create(
        net.minecraft.core.registries.Registries.DIMENSION,
        ResourceLocation.fromNamespaceAndPath("mbb_austenium", "austeniumcraft_world"));
    public static final int BEDROCK_Y = -64;
    public static final int DEEPSLATE_TOP_Y = -1;
    public static final int STONE_TOP_Y = 256;
    public static final int NETHERRACK_TOP_Y = 354;
    public static final int TOP_Y = 383;

    private AwDimensionRules() {}

    /**
     * Reports whether a dimension key is the Austeniumcraft World.
     *
     * @param dimension the dimension key to test
     * @return true when the key is the Austeniumcraft World
     */
    public static boolean isAusteniumcraftWorld(ResourceKey<Level> dimension) {
        return DIMENSION.equals(dimension);
    }

    /**
     * Reports whether a height belongs to the deepslate or stone band.
     *
     * @param height the world y to test
     * @return true when the height is inside the deepslate or stone band
     */
    public static boolean isDeepslateOrStoneBand(int height) {
        return height > BEDROCK_Y && height <= STONE_TOP_Y;
    }

    /**
     * Reports whether a height belongs to the end stone band.
     *
     * @param height the world y to test
     * @return true when the height is inside the end stone band
     */
    public static boolean isEndStoneBand(int height) {
        return height > NETHERRACK_TOP_Y && height <= TOP_Y;
    }
}
