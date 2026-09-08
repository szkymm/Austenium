/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModMenuTypes
 * TYPE: Java Source
 * DESCRIPTION: Generic row-count menu type registry for [MBB] Austenium chests.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.menu.GenericChestMenu;
import com.mbb.austenium.content.menu.GridMenu;
import com.mbb.austenium.content.menu.IronGridMenu;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ModMenuTypes CLASS IS CORE PART OF [MBB] AUSTENIUM ModMenuTypes.java.
 *
 * com.mbb.austenium.content.ModMenuTypes:
 *     Registers one MenuType per supported row count for generic chests.
 */
public final class ModMenuTypes {

    public static final int GENERIC_MAX_ROWS = 32;

    private ModMenuTypes() {}

    public static final DeferredRegister<MenuType<?>> MENU_TYPES
        = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MbbAustenium.MOD_ID);

    public static final Map<Integer, RegistryObject<MenuType<GenericChestMenu>>> GENERIC_CHEST = new LinkedHashMap<>();

    static {
        for (int rows = 1; rows <= GENERIC_MAX_ROWS; rows++) {
            final int rowCount = rows;
            GENERIC_CHEST.put(rowCount, MENU_TYPES.register("generic_chest_" + rowCount,
                () -> new MenuType<>((containerId, inventory) ->
                    new GenericChestMenu(GENERIC_CHEST.get(rowCount).get(), containerId, inventory, rowCount),
                    FeatureFlags.DEFAULT_FLAGS)));
        }
    }

    @SuppressWarnings("unchecked")
    private static final RegistryObject<MenuType<IronGridMenu>>[] IRON_HOLDERS = new RegistryObject[2];

    static {
        IRON_HOLDERS[0] = (RegistryObject<MenuType<IronGridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_10x4",
                () -> new MenuType<>((containerId, inventory) ->
                    new IronGridMenu(IRON_HOLDERS[0].get(), containerId, inventory, 4, 10), FeatureFlags.DEFAULT_FLAGS));
        IRON_HOLDERS[1] = (RegistryObject<MenuType<IronGridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_10x8",
                () -> new MenuType<>((containerId, inventory) ->
                    new IronGridMenu(IRON_HOLDERS[1].get(), containerId, inventory, 8, 10), FeatureFlags.DEFAULT_FLAGS));
    }

    public static final RegistryObject<MenuType<IronGridMenu>> IRON_10X4 = IRON_HOLDERS[0];
    public static final RegistryObject<MenuType<IronGridMenu>> IRON_10X8 = IRON_HOLDERS[1];

    @SuppressWarnings("unchecked")
    private static final RegistryObject<MenuType<GridMenu>>[] GOLD_HOLDERS = new RegistryObject[2];

    static {
        GOLD_HOLDERS[0] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_12x4",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(GOLD_HOLDERS[0].get(), containerId, inventory, 4, 12), FeatureFlags.DEFAULT_FLAGS));
        GOLD_HOLDERS[1] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_12x8",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(GOLD_HOLDERS[1].get(), containerId, inventory, 8, 12), FeatureFlags.DEFAULT_FLAGS));
    }

    public static final RegistryObject<MenuType<GridMenu>> GOLD_12X4 = GOLD_HOLDERS[0];
    public static final RegistryObject<MenuType<GridMenu>> GOLD_12X8 = GOLD_HOLDERS[1];

    @SuppressWarnings("unchecked")
    private static final RegistryObject<MenuType<GridMenu>>[] DIAMOND_HOLDERS = new RegistryObject[2];

    static {
        DIAMOND_HOLDERS[0] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_10x5",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(DIAMOND_HOLDERS[0].get(), containerId, inventory, 5, 10), FeatureFlags.DEFAULT_FLAGS));
        DIAMOND_HOLDERS[1] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_10x10",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(DIAMOND_HOLDERS[1].get(), containerId, inventory, 10, 10), FeatureFlags.DEFAULT_FLAGS));
    }

    public static final RegistryObject<MenuType<GridMenu>> DIAMOND_10X5 = DIAMOND_HOLDERS[0];
    public static final RegistryObject<MenuType<GridMenu>> DIAMOND_10X10 = DIAMOND_HOLDERS[1];

    @SuppressWarnings("unchecked")
    private static final RegistryObject<MenuType<GridMenu>>[] EMERALD_HOLDERS = new RegistryObject[2];

    static {
        EMERALD_HOLDERS[0] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_12x5",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(EMERALD_HOLDERS[0].get(), containerId, inventory, 5, 12), FeatureFlags.DEFAULT_FLAGS));
        EMERALD_HOLDERS[1] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_12x10",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(EMERALD_HOLDERS[1].get(), containerId, inventory, 10, 12), FeatureFlags.DEFAULT_FLAGS));
    }

    public static final RegistryObject<MenuType<GridMenu>> EMERALD_12X5 = EMERALD_HOLDERS[0];
    public static final RegistryObject<MenuType<GridMenu>> EMERALD_12X10 = EMERALD_HOLDERS[1];

    @SuppressWarnings("unchecked")
    private static final RegistryObject<MenuType<GridMenu>>[] MYTHRIL_HOLDERS = new RegistryObject[2];

    static {
        MYTHRIL_HOLDERS[0] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_14x5",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(MYTHRIL_HOLDERS[0].get(), containerId, inventory, 5, 14), FeatureFlags.DEFAULT_FLAGS));
        MYTHRIL_HOLDERS[1] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_14x10",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(MYTHRIL_HOLDERS[1].get(), containerId, inventory, 10, 14), FeatureFlags.DEFAULT_FLAGS));
    }

    public static final RegistryObject<MenuType<GridMenu>> MYTHRIL_14X5 = MYTHRIL_HOLDERS[0];
    public static final RegistryObject<MenuType<GridMenu>> MYTHRIL_14X10 = MYTHRIL_HOLDERS[1];

    @SuppressWarnings("unchecked")
    private static final RegistryObject<MenuType<GridMenu>>[] ADAMANTITE_HOLDERS = new RegistryObject[2];

    static {
        ADAMANTITE_HOLDERS[0] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_15x5",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(ADAMANTITE_HOLDERS[0].get(), containerId, inventory, 5, 15), FeatureFlags.DEFAULT_FLAGS));
        ADAMANTITE_HOLDERS[1] = (RegistryObject<MenuType<GridMenu>>) (RegistryObject<?>)
            MENU_TYPES.register("generic_chest_15x10",
                () -> new MenuType<>((containerId, inventory) ->
                    new GridMenu(ADAMANTITE_HOLDERS[1].get(), containerId, inventory, 10, 15), FeatureFlags.DEFAULT_FLAGS));
    }

    public static final RegistryObject<MenuType<GridMenu>> ADAMANTITE_15X5 = ADAMANTITE_HOLDERS[0];
    public static final RegistryObject<MenuType<GridMenu>> ADAMANTITE_15X10 = ADAMANTITE_HOLDERS[1];
}