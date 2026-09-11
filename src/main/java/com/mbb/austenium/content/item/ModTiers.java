/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.ModTiers
 * TYPE: Java Source
 * DESCRIPTION: Registers the mod tool tiers with Forge so other mods can compare them.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

/**
 * MODTIERS CLASS IS CORE PART OF [MBB] AUSTENIUM ModTiers.java.
 *
 * com.mbb.austenium.content.item.ModTiers:
 *     Registers the mod tiers into Forge TierSortingRegistry so cross-mod tools
 *     and blocks compare tiers through the shared sorting order instead of the
 *     vanilla fallback. Sorted order:
 *     stone < copper < iron < silver < diamond < orichalcum < netherite
 *     < mythril < adamantite < radiant.
 */
public final class ModTiers {

    private ModTiers() {}

    /**
     * Registers every mod tier; must run during mod construction.
     */
    public static void register() {
        // Copper sits between stone and iron, silver between iron and diamond.
        TierSortingRegistry.registerTier(CopperTier.INSTANCE, id("copper"), List.of(Tiers.STONE), List.of(Tiers.IRON));
        TierSortingRegistry.registerTier(SilverTier.INSTANCE,
            id("silver"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
        TierSortingRegistry.registerTier(OrichalcumTier.INSTANCE,
            id("orichalcum"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
        // The top tiers extend the vanilla chain beyond netherite.
        TierSortingRegistry.registerTier(MythrilTier.INSTANCE, id("mythril"), List.of(Tiers.NETHERITE), List.of());
        TierSortingRegistry.registerTier(AdamantiteTier.INSTANCE,
            id("adamantite"), List.of(MythrilTier.INSTANCE), List.of());
        TierSortingRegistry.registerTier(RadiantTier.INSTANCE,
            id("radiant"), List.of(AdamantiteTier.INSTANCE), List.of());
        TierSortingRegistry.registerTier(AurelianiumTier.INSTANCE,
            id("aurelianium"), List.of(RadiantTier.INSTANCE), List.of());
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, path);
    }
}
