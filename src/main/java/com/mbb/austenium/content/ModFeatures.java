/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModFeatures
 * TYPE: Java Source
 * DESCRIPTION: Feature type registry for [MBB] Austenium world generation.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.worldgen.MirroredScatteredOreFeature;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * MODFEATURES CLASS IS CORE PART OF [MBB] AUSTENIUM ModFeatures.java.
 *
 * com.mbb.austenium.content.ModFeatures:
 *     DeferredRegister holder for the custom feature types used by the mod.
 */
public final class ModFeatures {

    private ModFeatures() {}

    public static final DeferredRegister<Feature<?>> FEATURES
        = DeferredRegister.create(Registries.FEATURE, MbbAustenium.MOD_ID);

    public static final RegistryObject<Feature<OreConfiguration>> MIRRORED_SCATTERED_ORE
        = FEATURES.register("mirrored_scattered_ore",
            () -> new MirroredScatteredOreFeature(OreConfiguration.CODEC));
}
