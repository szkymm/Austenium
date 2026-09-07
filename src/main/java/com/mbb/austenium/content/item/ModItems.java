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

    public static final RegistryObject<Item> IRON_FURNACE
        = ITEMS.register("iron_furnace", () -> blockItem(ModBlocks.IRON_FURNACE));

    public static final RegistryObject<Item> IRON_BLAST_FURNACE
        = ITEMS.register("iron_blast_furnace", () -> blockItem(ModBlocks.IRON_BLAST_FURNACE));

    public static final RegistryObject<Item> IRON_SMOKER
        = ITEMS.register("iron_smoker", () -> blockItem(ModBlocks.IRON_SMOKER));

    public static final RegistryObject<Item> IRON_BARREL
        = ITEMS.register("iron_barrel", () -> blockItem(ModBlocks.IRON_BARREL));

    public static final RegistryObject<Item> IRON_CHEST
        = ITEMS.register("iron_chest", () -> blockItem(ModBlocks.IRON_CHEST));

    public static final RegistryObject<Item> SILVER_ORE
        = ITEMS.register("silver_ore", () -> blockItem(ModBlocks.SILVER_ORE));

    public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE
        = ITEMS.register("deepslate_silver_ore", () -> blockItem(ModBlocks.DEEPSLATE_SILVER_ORE));

    public static final RegistryObject<Item> RAW_SILVER
        = ITEMS.register("raw_silver", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_INGOT
        = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_FURNACE
        = ITEMS.register("silver_furnace", () -> blockItem(ModBlocks.SILVER_FURNACE));
    public static final RegistryObject<Item> SILVER_BLAST_FURNACE
        = ITEMS.register("silver_blast_furnace", () -> blockItem(ModBlocks.SILVER_BLAST_FURNACE));
    public static final RegistryObject<Item> SILVER_SMOKER
        = ITEMS.register("silver_smoker", () -> blockItem(ModBlocks.SILVER_SMOKER));
    public static final RegistryObject<Item> SILVER_BARREL
        = ITEMS.register("silver_barrel", () -> blockItem(ModBlocks.SILVER_BARREL));
    public static final RegistryObject<Item> SILVER_CHEST
        = ITEMS.register("silver_chest", () -> blockItem(ModBlocks.SILVER_CHEST));
    public static final RegistryObject<Item> SILVER_BLOCK
        = ITEMS.register("silver_block", () -> blockItem(ModBlocks.SILVER_BLOCK));
    public static final RegistryObject<Item> RAW_SILVER_BLOCK
        = ITEMS.register("raw_silver_block", () -> blockItem(ModBlocks.RAW_SILVER_BLOCK));

    public static final RegistryObject<Item> SILVER_SHOVEL
        = ITEMS.register("silver_shovel", () -> new net.minecraft.world.item.ShovelItem(SilverTier.INSTANCE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_PICKAXE
        = ITEMS.register("silver_pickaxe", () -> new net.minecraft.world.item.PickaxeItem(SilverTier.INSTANCE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_AXE
        = ITEMS.register("silver_axe", () -> new net.minecraft.world.item.AxeItem(SilverTier.INSTANCE, 6.5F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SWORD
        = ITEMS.register("silver_sword", () -> new net.minecraft.world.item.SwordItem(SilverTier.INSTANCE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HOE
        = ITEMS.register("silver_hoe", () -> new net.minecraft.world.item.HoeItem(SilverTier.INSTANCE, 0, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HELMET
        = ITEMS.register("silver_helmet", () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_CHESTPLATE
        = ITEMS.register("silver_chestplate", () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_LEGGINGS
        = ITEMS.register("silver_leggings", () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_BOOTS
        = ITEMS.register("silver_boots", () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_NUGGET
        = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_NUGGET
        = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_SHOVEL
        = ITEMS.register("copper_shovel", () -> new net.minecraft.world.item.ShovelItem(CopperTier.INSTANCE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE
        = ITEMS.register("copper_pickaxe", () -> new net.minecraft.world.item.PickaxeItem(CopperTier.INSTANCE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE
        = ITEMS.register("copper_axe", () -> new net.minecraft.world.item.AxeItem(CopperTier.INSTANCE, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SWORD
        = ITEMS.register("copper_sword", () -> new net.minecraft.world.item.SwordItem(CopperTier.INSTANCE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE
        = ITEMS.register("copper_hoe", () -> new net.minecraft.world.item.HoeItem(CopperTier.INSTANCE, 0, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HELMET
        = ITEMS.register("copper_helmet", () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE
        = ITEMS.register("copper_chestplate", () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS
        = ITEMS.register("copper_leggings", () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS
        = ITEMS.register("copper_boots", () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> COPPER_CHAIN
        = ITEMS.register("copper_chain", () -> blockItem(ModBlocks.COPPER_CHAIN));

    public static final RegistryObject<Item> SILVER_CHAIN
        = ITEMS.register("silver_chain", () -> blockItem(ModBlocks.SILVER_CHAIN));

    public static final RegistryObject<Item> GOLD_FURNACE
        = ITEMS.register("gold_furnace", () -> blockItem(ModBlocks.GOLD_FURNACE));
    public static final RegistryObject<Item> GOLD_BLAST_FURNACE
        = ITEMS.register("gold_blast_furnace", () -> blockItem(ModBlocks.GOLD_BLAST_FURNACE));
    public static final RegistryObject<Item> GOLD_SMOKER
        = ITEMS.register("gold_smoker", () -> blockItem(ModBlocks.GOLD_SMOKER));
    public static final RegistryObject<Item> GOLD_BARREL
        = ITEMS.register("gold_barrel", () -> blockItem(ModBlocks.GOLD_BARREL));
    public static final RegistryObject<Item> GOLD_CHEST
        = ITEMS.register("gold_chest", () -> blockItem(ModBlocks.GOLD_CHEST));
    public static final RegistryObject<Item> GOLD_CHAIN
        = ITEMS.register("gold_chain", () -> blockItem(ModBlocks.GOLD_CHAIN));
}