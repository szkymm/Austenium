/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModMenuTypes
 * TYPE: Java Source
 * DESCRIPTION: Generic row-count menu type registry for [MBB] Austenium chests.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.menu.GenericChestMenu;

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
}
