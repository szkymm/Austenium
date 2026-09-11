/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.ModItems
 * TYPE: Java Source
 * DESCRIPTION: Registry of all [MBB] Austenium items.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

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

    // Radiant blocks survive lava because the whole tier is fire resistant.
    private static Item radiantBlockItem(RegistryObject<Block> block) {
        return new BlockItem(block.get(), new Item.Properties().fireResistant());
    }

    // Shulker boxes never stack and render as a real box through their own item renderer.
    private static Item shulkerBoxItem(RegistryObject<Block> block) {
        return new TierShulkerBoxItem(block.get(), new Item.Properties().stacksTo(1));
    }

    // Empty-slot icons mirror the vanilla smithing template layout.
    private static final List<ResourceLocation> TEMPLATE_BASE_ICONS = List.of(
        ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet"),
        ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate"),
        ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings"),
        ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots"),
        ResourceLocation.withDefaultNamespace("item/empty_slot_hoe"),
        ResourceLocation.withDefaultNamespace("item/empty_slot_axe"),
        ResourceLocation.withDefaultNamespace("item/empty_slot_sword"),
        ResourceLocation.withDefaultNamespace("item/empty_slot_shovel"),
        ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe"));

    // The radiant template consumes one radiant ingot per smithing upgrade.
    private static final List<ResourceLocation> TEMPLATE_ADDITION_ICONS = List.of(
        ResourceLocation.withDefaultNamespace("item/empty_slot_ingot"));


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

    public static final RegistryObject<Item> COPPER_HOPPER
        = ITEMS.register("copper_hopper", () -> blockItem(ModBlocks.COPPER_HOPPER));

    public static final RegistryObject<Item> IRON_HOPPER
        = ITEMS.register("iron_hopper", () -> blockItem(ModBlocks.IRON_HOPPER));

    public static final RegistryObject<Item> SILVER_HOPPER
        = ITEMS.register("silver_hopper", () -> blockItem(ModBlocks.SILVER_HOPPER));

    public static final RegistryObject<Item> GOLD_HOPPER
        = ITEMS.register("gold_hopper", () -> blockItem(ModBlocks.GOLD_HOPPER));

    public static final RegistryObject<Item> DIAMOND_HOPPER
        = ITEMS.register("diamond_hopper", () -> blockItem(ModBlocks.DIAMOND_HOPPER));

    public static final RegistryObject<Item> EMERALD_HOPPER
        = ITEMS.register("emerald_hopper", () -> blockItem(ModBlocks.EMERALD_HOPPER));

    public static final RegistryObject<Item> ORICHALCUM_HOPPER
        = ITEMS.register("orichalcum_hopper", () -> blockItem(ModBlocks.ORICHALCUM_HOPPER));

    public static final RegistryObject<Item> MYTHRIL_HOPPER
        = ITEMS.register("mythril_hopper", () -> blockItem(ModBlocks.MYTHRIL_HOPPER));

    public static final RegistryObject<Item> ADAMANTITE_HOPPER
        = ITEMS.register("adamantite_hopper", () -> blockItem(ModBlocks.ADAMANTITE_HOPPER));

    public static final RegistryObject<Item> NETHERITE_HOPPER
        = ITEMS.register("netherite_hopper", () -> blockItem(ModBlocks.NETHERITE_HOPPER));

    public static final RegistryObject<Item> RADIANT_HOPPER
        = ITEMS.register("radiant_hopper", () -> blockItem(ModBlocks.RADIANT_HOPPER));

    public static final RegistryObject<Item> AURELIANIUM_HOPPER
        = ITEMS.register("aurelianium_hopper", () -> blockItem(ModBlocks.AURELIANIUM_HOPPER));


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
        =
            ITEMS.register("silver_shovel",
            () -> new net.minecraft.world.item.ShovelItem(SilverTier.INSTANCE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_PICKAXE
        =
            ITEMS.register("silver_pickaxe",
            () -> new net.minecraft.world.item.PickaxeItem(SilverTier.INSTANCE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_AXE
        =
            ITEMS.register("silver_axe",
            () -> new net.minecraft.world.item.AxeItem(SilverTier.INSTANCE, 6.5F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_SWORD
        =
            ITEMS.register("silver_sword",
            () -> new net.minecraft.world.item.SwordItem(SilverTier.INSTANCE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HOE
        =
            ITEMS.register("silver_hoe",
            () -> new net.minecraft.world.item.HoeItem(SilverTier.INSTANCE, 0, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_HELMET
        =
            ITEMS.register("silver_helmet",
            () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_CHESTPLATE
        =
            ITEMS.register("silver_chestplate",
            () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_LEGGINGS
        =
            ITEMS.register("silver_leggings",
            () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SILVER_BOOTS
        =
            ITEMS.register("silver_boots",
            () -> new net.minecraft.world.item.ArmorItem(SilverArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_NUGGET
        = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_NUGGET
        = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_SHOVEL
        =
            ITEMS.register("copper_shovel",
            () -> new net.minecraft.world.item.ShovelItem(CopperTier.INSTANCE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PICKAXE
        =
            ITEMS.register("copper_pickaxe",
            () -> new net.minecraft.world.item.PickaxeItem(CopperTier.INSTANCE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_AXE
        =
            ITEMS.register("copper_axe",
            () -> new net.minecraft.world.item.AxeItem(CopperTier.INSTANCE, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_SWORD
        =
            ITEMS.register("copper_sword",
            () -> new net.minecraft.world.item.SwordItem(CopperTier.INSTANCE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HOE
        =
            ITEMS.register("copper_hoe",
            () -> new net.minecraft.world.item.HoeItem(CopperTier.INSTANCE, 0, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HELMET
        =
            ITEMS.register("copper_helmet",
            () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHESTPLATE
        =
            ITEMS.register("copper_chestplate",
            () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_LEGGINGS
        =
            ITEMS.register("copper_leggings",
            () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BOOTS
        =
            ITEMS.register("copper_boots",
            () -> new net.minecraft.world.item.ArmorItem(CopperArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.BOOTS, new Item.Properties()));

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

    public static final RegistryObject<Item> DIAMOND_FURNACE
        = ITEMS.register("diamond_furnace", () -> blockItem(ModBlocks.DIAMOND_FURNACE));
    public static final RegistryObject<Item> DIAMOND_BLAST_FURNACE
        = ITEMS.register("diamond_blast_furnace", () -> blockItem(ModBlocks.DIAMOND_BLAST_FURNACE));
    public static final RegistryObject<Item> DIAMOND_SMOKER
        = ITEMS.register("diamond_smoker", () -> blockItem(ModBlocks.DIAMOND_SMOKER));
    public static final RegistryObject<Item> DIAMOND_BARREL
        = ITEMS.register("diamond_barrel", () -> blockItem(ModBlocks.DIAMOND_BARREL));
    public static final RegistryObject<Item> DIAMOND_CHEST
        = ITEMS.register("diamond_chest", () -> blockItem(ModBlocks.DIAMOND_CHEST));

    public static final RegistryObject<Item> EMERALD_FURNACE
        = ITEMS.register("emerald_furnace", () -> blockItem(ModBlocks.EMERALD_FURNACE));
    public static final RegistryObject<Item> EMERALD_BLAST_FURNACE
        = ITEMS.register("emerald_blast_furnace", () -> blockItem(ModBlocks.EMERALD_BLAST_FURNACE));
    public static final RegistryObject<Item> EMERALD_SMOKER
        = ITEMS.register("emerald_smoker", () -> blockItem(ModBlocks.EMERALD_SMOKER));
    public static final RegistryObject<Item> EMERALD_BARREL
        = ITEMS.register("emerald_barrel", () -> blockItem(ModBlocks.EMERALD_BARREL));
    public static final RegistryObject<Item> EMERALD_CHEST
        = ITEMS.register("emerald_chest", () -> blockItem(ModBlocks.EMERALD_CHEST));

    public static final RegistryObject<Item> ORICHALCUM_ORE
        = ITEMS.register("orichalcum_ore", () -> blockItem(ModBlocks.ORICHALCUM_ORE));
    public static final RegistryObject<Item> DEEPSLATE_ORICHALCUM_ORE
        = ITEMS.register("deepslate_orichalcum_ore", () -> blockItem(ModBlocks.DEEPSLATE_ORICHALCUM_ORE));
    public static final RegistryObject<Item> RAW_ORICHALCUM
        = ITEMS.register("raw_orichalcum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_INGOT
        = ITEMS.register("orichalcum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_BLOCK
        = ITEMS.register("orichalcum_block", () -> blockItem(ModBlocks.ORICHALCUM_BLOCK));
    public static final RegistryObject<Item> RAW_ORICHALCUM_BLOCK
        = ITEMS.register("raw_orichalcum_block", () -> blockItem(ModBlocks.RAW_ORICHALCUM_BLOCK));
    public static final RegistryObject<Item> ORICHALCUM_NUGGET
        = ITEMS.register("orichalcum_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORICHALCUM_FURNACE
        = ITEMS.register("orichalcum_furnace", () -> blockItem(ModBlocks.ORICHALCUM_FURNACE));
    public static final RegistryObject<Item> ORICHALCUM_BLAST_FURNACE
        = ITEMS.register("orichalcum_blast_furnace", () -> blockItem(ModBlocks.ORICHALCUM_BLAST_FURNACE));
    public static final RegistryObject<Item> ORICHALCUM_SMOKER
        = ITEMS.register("orichalcum_smoker", () -> blockItem(ModBlocks.ORICHALCUM_SMOKER));
    public static final RegistryObject<Item> ORICHALCUM_BARREL
        = ITEMS.register("orichalcum_barrel", () -> blockItem(ModBlocks.ORICHALCUM_BARREL));
    public static final RegistryObject<Item> ORICHALCUM_CHEST
        = ITEMS.register("orichalcum_chest", () -> blockItem(ModBlocks.ORICHALCUM_CHEST));
    public static final RegistryObject<Item> ORICHALCUM_CHAIN
        = ITEMS.register("orichalcum_chain", () -> blockItem(ModBlocks.ORICHALCUM_CHAIN));

    public static final RegistryObject<Item> ORICHALCUM_SHOVEL
        = ITEMS.register("orichalcum_shovel", () -> new OrichalcumShovelItem(OrichalcumTier.INSTANCE, 1.5F, -3.0F));
    public static final RegistryObject<Item> ORICHALCUM_PICKAXE
        = ITEMS.register("orichalcum_pickaxe", () -> new OrichalcumPickaxeItem(OrichalcumTier.INSTANCE, 1, -2.8F));
    public static final RegistryObject<Item> ORICHALCUM_AXE
        = ITEMS.register("orichalcum_axe", () -> new OrichalcumAxeItem(OrichalcumTier.INSTANCE, 6.5F, -3.1F));
    public static final RegistryObject<Item> ORICHALCUM_SWORD
        = ITEMS.register("orichalcum_sword", () -> new OrichalcumSwordItem(OrichalcumTier.INSTANCE, 3, -2.4F));
    public static final RegistryObject<Item> ORICHALCUM_HOE
        = ITEMS.register("orichalcum_hoe", () -> new OrichalcumHoeItem(OrichalcumTier.INSTANCE, 0, -3.0F));

    public static final RegistryObject<Item> ORICHALCUM_HELMET
        =
            ITEMS.register("orichalcum_helmet",
            () -> new OrichalcumArmorItem(OrichalcumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ORICHALCUM_CHESTPLATE
        =
            ITEMS.register("orichalcum_chestplate",
            () -> new OrichalcumArmorItem(OrichalcumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ORICHALCUM_LEGGINGS
        =
            ITEMS.register("orichalcum_leggings",
            () -> new OrichalcumArmorItem(OrichalcumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ORICHALCUM_BOOTS
        =
            ITEMS.register("orichalcum_boots",
            () -> new OrichalcumArmorItem(OrichalcumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> MYTHRIL_ORE
        = ITEMS.register("mythril_ore", () -> blockItem(ModBlocks.MYTHRIL_ORE));
    public static final RegistryObject<Item> DEEPSLATE_MYTHRIL_ORE
        = ITEMS.register("deepslate_mythril_ore", () -> blockItem(ModBlocks.DEEPSLATE_MYTHRIL_ORE));
    public static final RegistryObject<Item> RAW_MYTHRIL
        = ITEMS.register("raw_mythril", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_INGOT
        = ITEMS.register("mythril_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_BLOCK
        = ITEMS.register("mythril_block", () -> blockItem(ModBlocks.MYTHRIL_BLOCK));
    public static final RegistryObject<Item> RAW_MYTHRIL_BLOCK
        = ITEMS.register("raw_mythril_block", () -> blockItem(ModBlocks.RAW_MYTHRIL_BLOCK));
    public static final RegistryObject<Item> MYTHRIL_NUGGET
        = ITEMS.register("mythril_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MYTHRIL_FURNACE
        = ITEMS.register("mythril_furnace", () -> blockItem(ModBlocks.MYTHRIL_FURNACE));
    public static final RegistryObject<Item> MYTHRIL_BLAST_FURNACE
        = ITEMS.register("mythril_blast_furnace", () -> blockItem(ModBlocks.MYTHRIL_BLAST_FURNACE));
    public static final RegistryObject<Item> MYTHRIL_SMOKER
        = ITEMS.register("mythril_smoker", () -> blockItem(ModBlocks.MYTHRIL_SMOKER));
    public static final RegistryObject<Item> MYTHRIL_BARREL
        = ITEMS.register("mythril_barrel", () -> blockItem(ModBlocks.MYTHRIL_BARREL));
    public static final RegistryObject<Item> MYTHRIL_CHEST
        = ITEMS.register("mythril_chest", () -> blockItem(ModBlocks.MYTHRIL_CHEST));
    public static final RegistryObject<Item> MYTHRIL_CHAIN
        = ITEMS.register("mythril_chain", () -> blockItem(ModBlocks.MYTHRIL_CHAIN));

    public static final RegistryObject<Item> MYTHRIL_SHOVEL
        = ITEMS.register("mythril_shovel", () -> new MythrilShovelItem(MythrilTier.INSTANCE, 1.5F, -3.0F));
    public static final RegistryObject<Item> MYTHRIL_PICKAXE
        = ITEMS.register("mythril_pickaxe", () -> new MythrilPickaxeItem(MythrilTier.INSTANCE, 1, -2.8F));
    public static final RegistryObject<Item> MYTHRIL_AXE
        = ITEMS.register("mythril_axe", () -> new MythrilAxeItem(MythrilTier.INSTANCE, 6.5F, -3.1F));
    public static final RegistryObject<Item> MYTHRIL_SWORD
        = ITEMS.register("mythril_sword", () -> new MythrilSwordItem(MythrilTier.INSTANCE, 3, -2.4F));
    public static final RegistryObject<Item> MYTHRIL_HOE
        = ITEMS.register("mythril_hoe", () -> new MythrilHoeItem(MythrilTier.INSTANCE, 0, -3.0F));

    public static final RegistryObject<Item> MYTHRIL_HELMET
        =
            ITEMS.register("mythril_helmet",
            () -> new MythrilArmorItem(MythrilArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> MYTHRIL_CHESTPLATE
        =
            ITEMS.register("mythril_chestplate",
            () -> new MythrilArmorItem(MythrilArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> MYTHRIL_LEGGINGS
        =
            ITEMS.register("mythril_leggings",
            () -> new MythrilArmorItem(MythrilArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> MYTHRIL_BOOTS
        =
            ITEMS.register("mythril_boots",
            () -> new MythrilArmorItem(MythrilArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> ADAMANTITE_ORE
        = ITEMS.register("adamantite_ore", () -> blockItem(ModBlocks.ADAMANTITE_ORE));
    public static final RegistryObject<Item> DEEPSLATE_ADAMANTITE_ORE
        = ITEMS.register("deepslate_adamantite_ore", () -> blockItem(ModBlocks.DEEPSLATE_ADAMANTITE_ORE));
    public static final RegistryObject<Item> RAW_ADAMANTITE
        = ITEMS.register("raw_adamantite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_INGOT
        = ITEMS.register("adamantite_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTITE_BLOCK
        = ITEMS.register("adamantite_block", () -> blockItem(ModBlocks.ADAMANTITE_BLOCK));
    public static final RegistryObject<Item> RAW_ADAMANTITE_BLOCK
        = ITEMS.register("raw_adamantite_block", () -> blockItem(ModBlocks.RAW_ADAMANTITE_BLOCK));
    public static final RegistryObject<Item> ADAMANTITE_NUGGET
        = ITEMS.register("adamantite_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ADAMANTITE_FURNACE
        = ITEMS.register("adamantite_furnace", () -> blockItem(ModBlocks.ADAMANTITE_FURNACE));
    public static final RegistryObject<Item> ADAMANTITE_BLAST_FURNACE
        = ITEMS.register("adamantite_blast_furnace", () -> blockItem(ModBlocks.ADAMANTITE_BLAST_FURNACE));
    public static final RegistryObject<Item> ADAMANTITE_SMOKER
        = ITEMS.register("adamantite_smoker", () -> blockItem(ModBlocks.ADAMANTITE_SMOKER));
    public static final RegistryObject<Item> ADAMANTITE_BARREL
        = ITEMS.register("adamantite_barrel", () -> blockItem(ModBlocks.ADAMANTITE_BARREL));
    public static final RegistryObject<Item> ADAMANTITE_CHEST
        = ITEMS.register("adamantite_chest", () -> blockItem(ModBlocks.ADAMANTITE_CHEST));
    public static final RegistryObject<Item> ADAMANTITE_CHAIN
        = ITEMS.register("adamantite_chain", () -> blockItem(ModBlocks.ADAMANTITE_CHAIN));

    public static final RegistryObject<Item> ADAMANTITE_SHOVEL
        = ITEMS.register("adamantite_shovel", () -> new AdamantiteShovelItem(AdamantiteTier.INSTANCE, 2.0F, -3.0F));
    public static final RegistryObject<Item> ADAMANTITE_PICKAXE
        = ITEMS.register("adamantite_pickaxe", () -> new AdamantitePickaxeItem(AdamantiteTier.INSTANCE, 1, -2.8F));
    public static final RegistryObject<Item> ADAMANTITE_AXE
        = ITEMS.register("adamantite_axe", () -> new AdamantiteAxeItem(AdamantiteTier.INSTANCE, 7.0F, -3.1F));
    public static final RegistryObject<Item> ADAMANTITE_SWORD
        = ITEMS.register("adamantite_sword", () -> new AdamantiteSwordItem(AdamantiteTier.INSTANCE, 3, -2.4F));
    public static final RegistryObject<Item> ADAMANTITE_HOE
        = ITEMS.register("adamantite_hoe", () -> new AdamantiteHoeItem(AdamantiteTier.INSTANCE, 0, -2.9F));

    public static final RegistryObject<Item> ADAMANTITE_HELMET
        =
            ITEMS.register("adamantite_helmet",
            () -> new AdamantiteArmorItem(AdamantiteArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> ADAMANTITE_CHESTPLATE
        =
            ITEMS.register("adamantite_chestplate",
            () -> new AdamantiteArmorItem(AdamantiteArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> ADAMANTITE_LEGGINGS
        =
            ITEMS.register("adamantite_leggings",
            () -> new AdamantiteArmorItem(AdamantiteArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> ADAMANTITE_BOOTS
        =
            ITEMS.register("adamantite_boots",
            () -> new AdamantiteArmorItem(AdamantiteArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> NETHERITE_FURNACE
        = ITEMS.register("netherite_furnace", () -> blockItem(ModBlocks.NETHERITE_FURNACE));
    public static final RegistryObject<Item> NETHERITE_BLAST_FURNACE
        = ITEMS.register("netherite_blast_furnace", () -> blockItem(ModBlocks.NETHERITE_BLAST_FURNACE));
    public static final RegistryObject<Item> NETHERITE_SMOKER
        = ITEMS.register("netherite_smoker", () -> blockItem(ModBlocks.NETHERITE_SMOKER));
    public static final RegistryObject<Item> NETHERITE_BARREL
        = ITEMS.register("netherite_barrel", () -> blockItem(ModBlocks.NETHERITE_BARREL));
    public static final RegistryObject<Item> NETHERITE_CHEST
        = ITEMS.register("netherite_chest", () -> blockItem(ModBlocks.NETHERITE_CHEST));

    public static final RegistryObject<Item> RADIANT_DEBRIS
        = ITEMS.register("radiant_debris", () -> radiantBlockItem(ModBlocks.RADIANT_DEBRIS));
    public static final RegistryObject<Item> RADIANT_SCRAP
        = ITEMS.register("radiant_scrap", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RADIANT_SCRAP_BLOCK
        = ITEMS.register("radiant_scrap_block", () -> radiantBlockItem(ModBlocks.RADIANT_SCRAP_BLOCK));
    public static final RegistryObject<Item> RADIANT_INGOT
        = ITEMS.register("radiant_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RADIANT_BLOCK
        = ITEMS.register("radiant_block", () -> radiantBlockItem(ModBlocks.RADIANT_BLOCK));
    public static final RegistryObject<Item> RADIANT_UPGRADE_SMITHING_TEMPLATE
        = ITEMS.register("radiant_upgrade_smithing_template", () -> new SmithingTemplateItem(
            Component.translatable("item.mbb_austenium.smithing_template.radiant_upgrade.applies_to"),
            Component.translatable("item.mbb_austenium.smithing_template.radiant_upgrade.ingredients"),
            Component.translatable("upgrade.mbb_austenium.radiant_upgrade"),
            Component.translatable("item.mbb_austenium.smithing_template.radiant_upgrade.base_slot_description"),
            Component.translatable("item.mbb_austenium.smithing_template.radiant_upgrade.additions_slot_description"),
            TEMPLATE_BASE_ICONS, TEMPLATE_ADDITION_ICONS));

    public static final RegistryObject<Item> RADIANT_FURNACE
        = ITEMS.register("radiant_furnace", () -> radiantBlockItem(ModBlocks.RADIANT_FURNACE));
    public static final RegistryObject<Item> RADIANT_BLAST_FURNACE
        = ITEMS.register("radiant_blast_furnace", () -> radiantBlockItem(ModBlocks.RADIANT_BLAST_FURNACE));
    public static final RegistryObject<Item> RADIANT_SMOKER
        = ITEMS.register("radiant_smoker", () -> radiantBlockItem(ModBlocks.RADIANT_SMOKER));
    public static final RegistryObject<Item> RADIANT_BARREL
        = ITEMS.register("radiant_barrel", () -> radiantBlockItem(ModBlocks.RADIANT_BARREL));
    public static final RegistryObject<Item> RADIANT_CHEST
        = ITEMS.register("radiant_chest", () -> radiantBlockItem(ModBlocks.RADIANT_CHEST));

    public static final RegistryObject<Item> RADIANT_SHOVEL
        = ITEMS.register("radiant_shovel", () -> new RadiantShovelItem(RadiantTier.INSTANCE, 2.0F, -3.0F));
    public static final RegistryObject<Item> RADIANT_PICKAXE
        = ITEMS.register("radiant_pickaxe", () -> new RadiantPickaxeItem(RadiantTier.INSTANCE, 1, -2.8F));
    public static final RegistryObject<Item> RADIANT_AXE
        = ITEMS.register("radiant_axe", () -> new RadiantAxeItem(RadiantTier.INSTANCE, 7.0F, -3.1F));
    public static final RegistryObject<Item> RADIANT_SWORD
        = ITEMS.register("radiant_sword", () -> new RadiantSwordItem(RadiantTier.INSTANCE, 3, -2.4F));
    public static final RegistryObject<Item> RADIANT_HOE
        = ITEMS.register("radiant_hoe", () -> new RadiantHoeItem(RadiantTier.INSTANCE, 0, -2.9F));

    public static final RegistryObject<Item> RADIANT_HELMET
        =
            ITEMS.register("radiant_helmet",
            () -> new RadiantArmorItem(RadiantArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> RADIANT_CHESTPLATE
        =
            ITEMS.register("radiant_chestplate",
            () -> new RadiantArmorItem(RadiantArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> RADIANT_LEGGINGS
        =
            ITEMS.register("radiant_leggings",
            () -> new RadiantArmorItem(RadiantArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> RADIANT_BOOTS
        =
            ITEMS.register("radiant_boots",
            () -> new RadiantArmorItem(RadiantArmorMaterial.INSTANCE, net.minecraft.world.item.ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> AURELIANIUM_DEBRIS
        = ITEMS.register("aurelianium_debris", () -> radiantBlockItem(ModBlocks.AURELIANIUM_DEBRIS));
    public static final RegistryObject<Item> AURELIANIUM_SCRAP
        = ITEMS.register("aurelianium_scrap", () -> new AurelianiumItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> AURELIANIUM_SCRAP_BLOCK
        = ITEMS.register("aurelianium_scrap_block", () -> radiantBlockItem(ModBlocks.AURELIANIUM_SCRAP_BLOCK));
    public static final RegistryObject<Item> AURELIANIUM_INGOT
        = ITEMS.register("aurelianium_ingot", () -> new AurelianiumItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> AURELIANIUM_BLOCK
        = ITEMS.register("aurelianium_block", () -> radiantBlockItem(ModBlocks.AURELIANIUM_BLOCK));
    public static final RegistryObject<Item> AURELIANIUM_UPGRADE_SMITHING_TEMPLATE
        = ITEMS.register("aurelianium_upgrade_smithing_template", () -> new AurelianiumSmithingTemplateItem(
            Component.translatable("item.mbb_austenium.smithing_template.aurelianium_upgrade.applies_to"),
            Component.translatable("item.mbb_austenium.smithing_template.aurelianium_upgrade.ingredients"),
            Component.translatable("upgrade.mbb_austenium.aurelianium_upgrade"),
            Component.translatable("item.mbb_austenium.smithing_template.aurelianium_upgrade.base_slot_description"),
            Component.translatable(
                "item.mbb_austenium.smithing_template.aurelianium_upgrade.additions_slot_description"),
            TEMPLATE_BASE_ICONS, TEMPLATE_ADDITION_ICONS));

    public static final RegistryObject<Item> AURELIANIUM_FURNACE
        = ITEMS.register("aurelianium_furnace", () -> radiantBlockItem(ModBlocks.AURELIANIUM_FURNACE));
    public static final RegistryObject<Item> AURELIANIUM_BLAST_FURNACE
        = ITEMS.register("aurelianium_blast_furnace", () -> radiantBlockItem(ModBlocks.AURELIANIUM_BLAST_FURNACE));
    public static final RegistryObject<Item> AURELIANIUM_SMOKER
        = ITEMS.register("aurelianium_smoker", () -> radiantBlockItem(ModBlocks.AURELIANIUM_SMOKER));
    public static final RegistryObject<Item> AURELIANIUM_BARREL
        = ITEMS.register("aurelianium_barrel", () -> radiantBlockItem(ModBlocks.AURELIANIUM_BARREL));
    public static final RegistryObject<Item> AURELIANIUM_CHEST
        = ITEMS.register("aurelianium_chest", () -> radiantBlockItem(ModBlocks.AURELIANIUM_CHEST));

    public static final RegistryObject<Item> AURELIANIUM_SHOVEL
        = ITEMS.register("aurelianium_shovel", () -> new AurelianiumShovelItem(AurelianiumTier.INSTANCE, 2.0F, -3.0F));
    public static final RegistryObject<Item> AURELIANIUM_PICKAXE
        = ITEMS.register("aurelianium_pickaxe", () -> new AurelianiumPickaxeItem(AurelianiumTier.INSTANCE, 1, -2.8F));
    public static final RegistryObject<Item> AURELIANIUM_AXE
        = ITEMS.register("aurelianium_axe", () -> new AurelianiumAxeItem(AurelianiumTier.INSTANCE, 7.0F, -3.1F));
    public static final RegistryObject<Item> AURELIANIUM_SWORD
        = ITEMS.register("aurelianium_sword", () -> new AurelianiumSwordItem(AurelianiumTier.INSTANCE, 3, -2.4F));
    public static final RegistryObject<Item> AURELIANIUM_HOE
        = ITEMS.register("aurelianium_hoe", () -> new AurelianiumHoeItem(AurelianiumTier.INSTANCE, 0, -2.9F));

    public static final RegistryObject<Item> AURELIANIUM_HELMET
        =
            ITEMS.register("aurelianium_helmet",
            () -> new AurelianiumArmorItem(AurelianiumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> AURELIANIUM_CHESTPLATE
        =
            ITEMS.register("aurelianium_chestplate",
            () -> new AurelianiumArmorItem(AurelianiumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item> AURELIANIUM_LEGGINGS
        =
            ITEMS.register("aurelianium_leggings",
            () -> new AurelianiumArmorItem(AurelianiumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item> AURELIANIUM_BOOTS
        =
            ITEMS.register("aurelianium_boots",
            () -> new AurelianiumArmorItem(AurelianiumArmorMaterial.INSTANCE,
            net.minecraft.world.item.ArmorItem.Type.BOOTS));

    public static final RegistryObject<Item> NETHERITE_SCRAP_BLOCK
        =
            ITEMS.register("netherite_scrap_block",
            () -> new BlockItem(ModBlocks.NETHERITE_SCRAP_BLOCK.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> COPPER_SHULKER_BOX
        = ITEMS.register("copper_shulker_box", () -> shulkerBoxItem(ModBlocks.COPPER_SHULKER_BOX));

    public static final RegistryObject<Item> IRON_SHULKER_BOX
        = ITEMS.register("iron_shulker_box", () -> shulkerBoxItem(ModBlocks.IRON_SHULKER_BOX));

    public static final RegistryObject<Item> SILVER_SHULKER_BOX
        = ITEMS.register("silver_shulker_box", () -> shulkerBoxItem(ModBlocks.SILVER_SHULKER_BOX));

    public static final RegistryObject<Item> GOLD_SHULKER_BOX
        = ITEMS.register("gold_shulker_box", () -> shulkerBoxItem(ModBlocks.GOLD_SHULKER_BOX));

    public static final RegistryObject<Item> EMERALD_SHULKER_BOX
        = ITEMS.register("emerald_shulker_box", () -> shulkerBoxItem(ModBlocks.EMERALD_SHULKER_BOX));

    public static final RegistryObject<Item> DIAMOND_SHULKER_BOX
        = ITEMS.register("diamond_shulker_box", () -> shulkerBoxItem(ModBlocks.DIAMOND_SHULKER_BOX));

    public static final RegistryObject<Item> NETHERITE_SHULKER_BOX
        = ITEMS.register("netherite_shulker_box", () -> shulkerBoxItem(ModBlocks.NETHERITE_SHULKER_BOX));

    public static final RegistryObject<Item> ADAMANTITE_SHULKER_BOX
        = ITEMS.register("adamantite_shulker_box", () -> shulkerBoxItem(ModBlocks.ADAMANTITE_SHULKER_BOX));

    public static final RegistryObject<Item> MYTHRIL_SHULKER_BOX
        = ITEMS.register("mythril_shulker_box", () -> shulkerBoxItem(ModBlocks.MYTHRIL_SHULKER_BOX));

    public static final RegistryObject<Item> ORICHALCUM_SHULKER_BOX
        = ITEMS.register("orichalcum_shulker_box", () -> shulkerBoxItem(ModBlocks.ORICHALCUM_SHULKER_BOX));

    public static final RegistryObject<Item> RADIANT_SHULKER_BOX
        = ITEMS.register("radiant_shulker_box", () -> shulkerBoxItem(ModBlocks.RADIANT_SHULKER_BOX));

    public static final RegistryObject<Item> AURELIANIUM_SHULKER_BOX
        = ITEMS.register("aurelianium_shulker_box", () -> shulkerBoxItem(ModBlocks.AURELIANIUM_SHULKER_BOX));
}