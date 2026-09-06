/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.ModItems
 * TYPE: Java Source
 * DESCRIPTION: Registry of all [MBB] Austenium items.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.content.item;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * ModItems CLASS IS CORE PART OF [MBB] AUSTENIUM ModItems.java.
 *
 * com.mbb.austenium.content.item.ModItems:
 *     DeferredRegister holder for all [MBB] Austenium items.
 */
public final class ModItems {

    private ModItems() {}

    public static final DeferredRegister<Item> ITEMS
        = DeferredRegister.create(ForgeRegistries.ITEMS, MbbAustenium.MOD_ID);

    private static Item blockItem(RegistryObject<Block> block) {
        return new BlockItem(block.get(), new Item.Properties());
    }

    public static final RegistryObject<Item> COPPER_FURNACE
        = ITEMS.register("copper_furnace", () -> blockItem(ModBlocks.COPPER_FURNACE));

    public static final RegistryObject<Item> COPPER_BLAST_FURNACE
        = ITEMS.register("copper_blast_furnace", () -> blockItem(ModBlocks.COPPER_BLAST_FURNACE));

    public static final RegistryObject<Item> COPPER_SMOKER
        = ITEMS.register("copper_smoker", () -> blockItem(ModBlocks.COPPER_SMOKER));

    public static final RegistryObject<Item> COPPER_BARREL
        = ITEMS.register("copper_barrel", () -> blockItem(ModBlocks.COPPER_BARREL));

    public static final RegistryObject<Item> COPPER_CHEST
        = ITEMS.register("copper_chest", () -> blockItem(ModBlocks.COPPER_CHEST));
}