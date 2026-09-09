/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.MbbAustenium
 * TYPE: Java Source
 * DESCRIPTION: Main mod entry point of [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModFeatures;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.ModRecipes;
import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.content.item.ModItems;
import com.mbb.austenium.content.item.ModTiers;
import com.mbb.austenium.content.tab.ModCreativeTab;
import com.mbb.austenium.jei.JerWorldGenConfig;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * MbbAustenium CLASS IS CORE PART OF [MBB] AUSTENIUM MbbAustenium.java.
 *
 * com.mbb.austenium.MbbAustenium:
 *     Main Forge mod entry point; wires all content registries onto the mod event bus.
 */
@Mod(MbbAustenium.MOD_ID)
public final class MbbAustenium {

    public static final String MOD_ID = "mbb_austenium";
    public static final String VERSION = "0.beta.4";
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Registers all mod content registries onto the mod event bus.
     */
    public MbbAustenium() {
        ModTiers.register();
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModMenuTypes.MENU_TYPES.register(bus);
        ModFeatures.FEATURES.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModCreativeTab.TABS.register(bus);
        JerWorldGenConfig.ensure();
        LOGGER.info("{} v{} initialised.", MOD_ID, VERSION);
    }
}