/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jei.AusteniumJeiPlugin
 * TYPE: Java Source
 * DESCRIPTION: JEI integration for every tier machine, container, ore, coal and the Austeniumcraft World.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jei;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.content.item.ModItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * AusteniumJeiPlugin CLASS IS CORE PART OF [MBB] AUSTENIUM AusteniumJeiPlugin.java.
 *
 * com.mbb.austenium.jei.AusteniumJeiPlugin:
 *     Registers copper machines as JEI catalysts for vanilla cooking categories.
 */
@JeiPlugin
public class AusteniumJeiPlugin implements IModPlugin {

    private static final int TICKS_PER_ITEM = 200;

    /** {@inheritDoc} */
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "jei");
    }

    /** {@inheritDoc} */
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addHopperInfo(registration, ModBlocks.COPPER_HOPPER.get(), "copper", "3.3", 6, 1);
        addHopperInfo(registration, ModBlocks.IRON_HOPPER.get(), "iron", "5.0", 4, 1);
        addHopperInfo(registration, ModBlocks.SILVER_HOPPER.get(), "silver", "6.7", 3, 1);
        addHopperInfo(registration, ModBlocks.GOLD_HOPPER.get(), "gold", "8.0", 5, 2);
        addHopperInfo(registration, ModBlocks.DIAMOND_HOPPER.get(), "diamond", "10", 2, 1);
        addHopperInfo(registration, ModBlocks.EMERALD_HOPPER.get(), "emerald", "12", 5, 3);
        addHopperInfo(registration, ModBlocks.ORICHALCUM_HOPPER.get(), "orichalcum", "15", 4, 3);
        addHopperInfo(registration, ModBlocks.MYTHRIL_HOPPER.get(), "mythril", "20", 1, 1);
        addHopperInfo(registration, ModBlocks.ADAMANTITE_HOPPER.get(), "adamantite", "30", 2, 3);
        addHopperInfo(registration, ModBlocks.NETHERITE_HOPPER.get(), "netherite", "40", 1, 2);
        addHopperInfo(registration, ModBlocks.RADIANT_HOPPER.get(), "radiant", "60", 1, 3);
        addHopperInfo(registration, ModBlocks.AURELIANIUM_HOPPER.get(), "aurelianium", "100", 1, 5);
        // ---- Austeniumcraft World (AW, 0.beta.6) ----
        registration.addIngredientInfo(ModItems.AUSTENIUMCRAFT_PORTAL.get(),
            Component.translatable("jei.mbb_austenium.austeniumcraft_portal"),
            Component.translatable("jei.mbb_austenium.austeniumcraft_portal.line2"),
            Component.translatable("jei.mbb_austenium.austeniumcraft_portal.line3"),
            Component.translatable("jei.mbb_austenium.austeniumcraft_portal.line4"),
            Component.translatable("jei.mbb_austenium.austeniumcraft_portal.line5"));
        // ---- 山铜 (Orichalcum) ----
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_ORE.get(),
            Component.translatable("jei.mbb_austenium.orichalcum_ore"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(),
            Component.translatable("jei.mbb_austenium.deepslate_orichalcum_ore"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.orichalcum_block"));
        registration.addIngredientInfo(ModBlocks.RAW_ORICHALCUM_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.raw_orichalcum_block"));
        registration.addIngredientInfo(ModItems.RAW_ORICHALCUM.get(), 
            Component.translatable("jei.mbb_austenium.raw_orichalcum"));
        registration.addIngredientInfo(ModItems.ORICHALCUM_INGOT.get(), 
            Component.translatable("jei.mbb_austenium.orichalcum_ingot"));
        registration.addIngredientInfo(ModItems.ORICHALCUM_NUGGET.get(), 
            Component.translatable("jei.mbb_austenium.orichalcum_nugget"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.orichalcum_furnace"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.orichalcum_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.orichalcum_smoker"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_CHEST.get(),
            Component.translatable("jei.mbb_austenium.orichalcum_chest"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.orichalcum_barrel"));
        registration.addIngredientInfo(ModBlocks.ORICHALCUM_CHAIN.get(), 
            Component.translatable("jei.mbb_austenium.orichalcum_chain"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ORICHALCUM_SHOVEL.get()), new ItemStack(ModItems.ORICHALCUM_PICKAXE.get()),
            new ItemStack(ModItems.ORICHALCUM_AXE.get()), new ItemStack(ModItems.ORICHALCUM_SWORD.get()),
            new ItemStack(ModItems.ORICHALCUM_HOE.get())),
            Component.translatable("jei.mbb_austenium.orichalcum_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ORICHALCUM_HELMET.get()), new ItemStack(ModItems.ORICHALCUM_CHESTPLATE.get()),
            new ItemStack(ModItems.ORICHALCUM_LEGGINGS.get()), new ItemStack(ModItems.ORICHALCUM_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.orichalcum_armor"));

        // ---- 秘银 (Mythril) ----
        registration.addIngredientInfo(ModBlocks.MYTHRIL_ORE.get(),
            Component.translatable("jei.mbb_austenium.mythril_ore"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_MYTHRIL_ORE.get(),
            Component.translatable("jei.mbb_austenium.deepslate_mythril_ore"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.mythril_block"));
        registration.addIngredientInfo(ModBlocks.RAW_MYTHRIL_BLOCK.get(),
            Component.translatable("jei.mbb_austenium.raw_mythril_block"));
        registration.addIngredientInfo(ModItems.RAW_MYTHRIL.get(), 
            Component.translatable("jei.mbb_austenium.raw_mythril"));
        registration.addIngredientInfo(ModItems.MYTHRIL_INGOT.get(), 
            Component.translatable("jei.mbb_austenium.mythril_ingot"));
        registration.addIngredientInfo(ModItems.MYTHRIL_NUGGET.get(), 
            Component.translatable("jei.mbb_austenium.mythril_nugget"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.mythril_furnace"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.mythril_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.mythril_smoker"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_CHEST.get(),
            Component.translatable("jei.mbb_austenium.mythril_chest"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.mythril_barrel"));
        registration.addIngredientInfo(ModBlocks.MYTHRIL_CHAIN.get(), 
            Component.translatable("jei.mbb_austenium.mythril_chain"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.MYTHRIL_SHOVEL.get()), new ItemStack(ModItems.MYTHRIL_PICKAXE.get()),
            new ItemStack(ModItems.MYTHRIL_AXE.get()), new ItemStack(ModItems.MYTHRIL_SWORD.get()),
            new ItemStack(ModItems.MYTHRIL_HOE.get())),
            Component.translatable("jei.mbb_austenium.mythril_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.MYTHRIL_HELMET.get()), new ItemStack(ModItems.MYTHRIL_CHESTPLATE.get()),
            new ItemStack(ModItems.MYTHRIL_LEGGINGS.get()), new ItemStack(ModItems.MYTHRIL_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.mythril_armor"));

        // ---- 精金 (Adamantite) ----
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_ORE.get(),
            Component.translatable("jei.mbb_austenium.adamantite_ore"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_ADAMANTITE_ORE.get(),
            Component.translatable("jei.mbb_austenium.deepslate_adamantite_ore"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.adamantite_block"));
        registration.addIngredientInfo(ModBlocks.RAW_ADAMANTITE_BLOCK.get(),
            Component.translatable("jei.mbb_austenium.raw_adamantite_block"));
        registration.addIngredientInfo(ModItems.RAW_ADAMANTITE.get(), 
            Component.translatable("jei.mbb_austenium.raw_adamantite"));
        registration.addIngredientInfo(ModItems.ADAMANTITE_INGOT.get(), 
            Component.translatable("jei.mbb_austenium.adamantite_ingot"));
        registration.addIngredientInfo(ModItems.ADAMANTITE_NUGGET.get(), 
            Component.translatable("jei.mbb_austenium.adamantite_nugget"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.adamantite_furnace"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.adamantite_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.adamantite_smoker"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_CHEST.get(),
            Component.translatable("jei.mbb_austenium.adamantite_chest"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.adamantite_barrel"));
        registration.addIngredientInfo(ModBlocks.ADAMANTITE_CHAIN.get(), 
            Component.translatable("jei.mbb_austenium.adamantite_chain"));

        registration.addIngredientInfo(ModBlocks.NETHERITE_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.netherite_furnace"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.netherite_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.netherite_smoker"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_CHEST.get(),
            Component.translatable("jei.mbb_austenium.netherite_chest"));
        registration.addIngredientInfo(ModBlocks.NETHERITE_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.netherite_barrel"));
        // ---- 耀金 (Radiant) ----
        registration.addIngredientInfo(ModBlocks.RADIANT_DEBRIS.get(),
            Component.translatable("jei.mbb_austenium.radiant_debris"));
        registration.addIngredientInfo(ModItems.RADIANT_SCRAP.get(),
            Component.translatable("jei.mbb_austenium.radiant_scrap"));
        registration.addIngredientInfo(ModItems.RADIANT_INGOT.get(), 
            Component.translatable("jei.mbb_austenium.radiant_ingot"));
        registration.addIngredientInfo(ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE.get(),
            Component.translatable("jei.mbb_austenium.radiant_upgrade_smithing_template"));
        registration.addIngredientInfo(ModBlocks.RADIANT_SCRAP_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.radiant_scrap_block"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.radiant_block"));
        registration.addIngredientInfo(ModBlocks.RADIANT_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.radiant_furnace"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.radiant_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.RADIANT_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.radiant_smoker"));
        registration.addIngredientInfo(ModBlocks.RADIANT_CHEST.get(),
            Component.translatable("jei.mbb_austenium.radiant_chest"));
        registration.addIngredientInfo(ModBlocks.RADIANT_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.radiant_barrel"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.RADIANT_SHOVEL.get()), new ItemStack(ModItems.RADIANT_PICKAXE.get()),
            new ItemStack(ModItems.RADIANT_AXE.get()), new ItemStack(ModItems.RADIANT_SWORD.get()),
            new ItemStack(ModItems.RADIANT_HOE.get())),
            Component.translatable("jei.mbb_austenium.radiant_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.RADIANT_HELMET.get()), new ItemStack(ModItems.RADIANT_CHESTPLATE.get()),
            new ItemStack(ModItems.RADIANT_LEGGINGS.get()), new ItemStack(ModItems.RADIANT_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.radiant_armor"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ADAMANTITE_SHOVEL.get()), new ItemStack(ModItems.ADAMANTITE_PICKAXE.get()),
            new ItemStack(ModItems.ADAMANTITE_AXE.get()), new ItemStack(ModItems.ADAMANTITE_SWORD.get()),
            new ItemStack(ModItems.ADAMANTITE_HOE.get())),
            Component.translatable("jei.mbb_austenium.adamantite_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.ADAMANTITE_HELMET.get()), new ItemStack(ModItems.ADAMANTITE_CHESTPLATE.get()),
            new ItemStack(ModItems.ADAMANTITE_LEGGINGS.get()), new ItemStack(ModItems.ADAMANTITE_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.adamantite_armor"));

        // ---- 银 (Silver) ----
        registration.addIngredientInfo(ModBlocks.SILVER_ORE.get(),
            Component.translatable("jei.mbb_austenium.silver_ore"));
        registration.addIngredientInfo(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
            Component.translatable("jei.mbb_austenium.deepslate_silver_ore"));
        registration.addIngredientInfo(ModItems.RAW_SILVER.get(), 
            Component.translatable("jei.mbb_austenium.raw_silver"));
        registration.addIngredientInfo(ModItems.SILVER_INGOT.get(), 
            Component.translatable("jei.mbb_austenium.silver_ingot"));
        registration.addIngredientInfo(ModItems.SILVER_NUGGET.get(), 
            Component.translatable("jei.mbb_austenium.silver_nugget"));
        registration.addIngredientInfo(ModBlocks.SILVER_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.silver_block"));
        registration.addIngredientInfo(ModBlocks.RAW_SILVER_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.raw_silver_block"));
        registration.addIngredientInfo(ModBlocks.SILVER_FURNACE.get(), 
            Component.translatable("jei.mbb_austenium.silver_furnace"));
        registration.addIngredientInfo(ModBlocks.SILVER_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.silver_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.SILVER_SMOKER.get(), 
            Component.translatable("jei.mbb_austenium.silver_smoker"));
        registration.addIngredientInfo(ModBlocks.SILVER_CHEST.get(), 
            Component.translatable("jei.mbb_austenium.silver_chest"));
        registration.addIngredientInfo(ModBlocks.SILVER_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.silver_barrel"));
        registration.addIngredientInfo(ModBlocks.SILVER_CHAIN.get(), 
            Component.translatable("jei.mbb_austenium.silver_chain"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.SILVER_SHOVEL.get()), new ItemStack(ModItems.SILVER_PICKAXE.get()),
            new ItemStack(ModItems.SILVER_AXE.get()), new ItemStack(ModItems.SILVER_SWORD.get()),
            new ItemStack(ModItems.SILVER_HOE.get())),
            Component.translatable("jei.mbb_austenium.silver_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.SILVER_HELMET.get()), new ItemStack(ModItems.SILVER_CHESTPLATE.get()),
            new ItemStack(ModItems.SILVER_LEGGINGS.get()), new ItemStack(ModItems.SILVER_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.silver_armor"));

        // ---- 铜档 ----
        registration.addIngredientInfo(ModBlocks.COPPER_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.copper_furnace"));
        registration.addIngredientInfo(ModBlocks.COPPER_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.copper_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.COPPER_SMOKER.get(), 
            Component.translatable("jei.mbb_austenium.copper_smoker"));
        registration.addIngredientInfo(ModBlocks.COPPER_CHEST.get(), 
            Component.translatable("jei.mbb_austenium.copper_chest"));
        registration.addIngredientInfo(ModBlocks.COPPER_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.copper_barrel"));
        registration.addIngredientInfo(ModItems.COPPER_NUGGET.get(), 
            Component.translatable("jei.mbb_austenium.copper_nugget"));
        registration.addIngredientInfo(ModBlocks.COPPER_CHAIN.get(), 
            Component.translatable("jei.mbb_austenium.copper_chain"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.COPPER_SWORD.get()), new ItemStack(ModItems.COPPER_PICKAXE.get()),
            new ItemStack(ModItems.COPPER_AXE.get()), new ItemStack(ModItems.COPPER_SHOVEL.get()),
            new ItemStack(ModItems.COPPER_HOE.get())),
            Component.translatable("jei.mbb_austenium.copper_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.COPPER_HELMET.get()), new ItemStack(ModItems.COPPER_CHESTPLATE.get()),
            new ItemStack(ModItems.COPPER_LEGGINGS.get()), new ItemStack(ModItems.COPPER_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.copper_armor"));

        // ---- 铁档 ----
        registration.addIngredientInfo(ModBlocks.IRON_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.iron_furnace"));
        registration.addIngredientInfo(ModBlocks.IRON_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.iron_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.IRON_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.iron_smoker"));
        registration.addIngredientInfo(ModBlocks.IRON_CHEST.get(), 
            Component.translatable("jei.mbb_austenium.iron_chest"));
        registration.addIngredientInfo(ModBlocks.IRON_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.iron_barrel"));

        // ---- 金档 ----
        registration.addIngredientInfo(ModBlocks.GOLD_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.gold_furnace"));
        registration.addIngredientInfo(ModBlocks.GOLD_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.gold_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.GOLD_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.gold_smoker"));
        registration.addIngredientInfo(ModBlocks.GOLD_CHEST.get(), 
            Component.translatable("jei.mbb_austenium.gold_chest"));
        registration.addIngredientInfo(ModBlocks.GOLD_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.gold_barrel"));
        registration.addIngredientInfo(ModBlocks.GOLD_CHAIN.get(), 
            Component.translatable("jei.mbb_austenium.gold_chain"));

        // ---- 钻石档 ----
        registration.addIngredientInfo(ModBlocks.DIAMOND_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.diamond_furnace"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.diamond_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.diamond_smoker"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_CHEST.get(), 
            Component.translatable("jei.mbb_austenium.diamond_chest"));
        registration.addIngredientInfo(ModBlocks.DIAMOND_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.diamond_barrel"));

        // ---- 绿宝石档 ----
        registration.addIngredientInfo(ModBlocks.EMERALD_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.emerald_furnace"));
        registration.addIngredientInfo(ModBlocks.EMERALD_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.emerald_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.EMERALD_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.emerald_smoker"));
        registration.addIngredientInfo(ModBlocks.EMERALD_CHEST.get(), 
            Component.translatable("jei.mbb_austenium.emerald_chest"));
        registration.addIngredientInfo(ModBlocks.EMERALD_BARREL.get(), 
            Component.translatable("jei.mbb_austenium.emerald_barrel"));

        // ---- 奥雷利亚尼姆（末地顶级档） ----
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_DEBRIS.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_debris"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_SCRAP_BLOCK.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_scrap_block"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_BLOCK.get(), 
            Component.translatable("jei.mbb_austenium.aurelianium_block"));
        registration.addIngredientInfo(ModItems.AURELIANIUM_SCRAP.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_scrap"));
        registration.addIngredientInfo(ModItems.AURELIANIUM_INGOT.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_ingot"));
        registration.addIngredientInfo(ModItems.AURELIANIUM_UPGRADE_SMITHING_TEMPLATE.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_upgrade_smithing_template"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_furnace"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_BLAST_FURNACE.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_blast_furnace"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_SMOKER.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_smoker"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_CHEST.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_chest"));
        registration.addIngredientInfo(ModBlocks.AURELIANIUM_BARREL.get(),
            Component.translatable("jei.mbb_austenium.aurelianium_barrel"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.AURELIANIUM_SWORD.get()), new ItemStack(ModItems.AURELIANIUM_PICKAXE.get()),
            new ItemStack(ModItems.AURELIANIUM_AXE.get()), new ItemStack(ModItems.AURELIANIUM_SHOVEL.get()),
            new ItemStack(ModItems.AURELIANIUM_HOE.get())),
            Component.translatable("jei.mbb_austenium.aurelianium_tools"));
        registration.addItemStackInfo(List.of(
            new ItemStack(ModItems.AURELIANIUM_HELMET.get()), new ItemStack(ModItems.AURELIANIUM_CHESTPLATE.get()),
            new ItemStack(ModItems.AURELIANIUM_LEGGINGS.get()), new ItemStack(ModItems.AURELIANIUM_BOOTS.get())),
            Component.translatable("jei.mbb_austenium.aurelianium_armor"));
        // ---- Coal family (0.rc.1) ----
        addCoalFamilyInfo(registration);
    }


    /**
     * Adds the shared tier hopper description to one hopper entry.
     *
     * @param registration the JEI registration handle
     * @param block the tier hopper block shown in JEI
     * @param tier the tier id whose name opens the text
     * @param rate the transfer rate in items per second, already formatted
     * @param cooldownTicks the cooldown between two activations, in ticks
     * @param batch the number of items moved per activation
     */
    private static void addHopperInfo(IRecipeRegistration registration, Block block, String tier,
        String rate, int cooldownTicks, int batch) {
        // The tier name family is shared with the Jade providers, so the key keeps its prefix.
        registration.addIngredientInfo(block, Component.translatable("jei.mbb_austenium.hopper",
            Component.translatable("jade.mbb_austenium.tier." + tier), rate, cooldownTicks, batch));
    }

    /**
     * Adds the sixteen coals, the sixteen coal blocks and the ten coal ores to JEI.
     *
     * @param registration the JEI registration handle
     */
    private static void addCoalFamilyInfo(IRecipeRegistration registration) {
        // The twelve tier coals are crafted along the ladder, the four dimension coals are mined.
        addCoalItemInfo(registration, ModItems.COPPER_COAL.get(), "copper_coal", 
            4000, "jei.mbb_austenium.coal.source.copper_coal");
        addCoalItemInfo(registration, ModItems.IRON_COAL.get(), "iron_coal", 
            8000, "jei.mbb_austenium.coal.source.iron_coal");
        addCoalItemInfo(registration, ModItems.SILVER_COAL.get(), "silver_coal", 
            9600, "jei.mbb_austenium.coal.source.silver_coal");
        addCoalItemInfo(registration, ModItems.GOLD_COAL.get(), "gold_coal", 
            16000, "jei.mbb_austenium.coal.source.gold_coal");
        addCoalItemInfo(registration, ModItems.DIAMOND_COAL.get(), "diamond_coal", 
            19200, "jei.mbb_austenium.coal.source.diamond_coal");
        addCoalItemInfo(registration, ModItems.EMERALD_COAL.get(), "emerald_coal", 
            25600, "jei.mbb_austenium.coal.source.emerald_coal");
        addCoalItemInfo(registration, ModItems.ORICHALCUM_COAL.get(), "orichalcum_coal", 
            32000, "jei.mbb_austenium.coal.source.orichalcum_coal");
        addCoalItemInfo(registration, ModItems.MYTHRIL_COAL.get(), "mythril_coal", 
            38400, "jei.mbb_austenium.coal.source.mythril_coal");
        addCoalItemInfo(registration, ModItems.ADAMANTITE_COAL.get(), "adamantite_coal", 
            48000, "jei.mbb_austenium.coal.source.adamantite_coal");
        addCoalItemInfo(registration, ModItems.NETHERITE_COAL.get(), "netherite_coal", 
            64000, "jei.mbb_austenium.coal.source.netherite_coal");
        addCoalItemInfo(registration, ModItems.RADIANT_COAL.get(), "radiant_coal", 
            80000, "jei.mbb_austenium.coal.source.radiant_coal");
        addCoalItemInfo(registration, ModItems.AURELIANIUM_COAL.get(), "aurelianium_coal", 
            160000, "jei.mbb_austenium.coal.source.aurelianium_coal");
        addCoalItemInfo(registration, ModItems.OVERWORLD_COAL.get(), "overworld_coal", 
            8000, "jei.mbb_austenium.coal.source.overworld_coal");
        addCoalItemInfo(registration, ModItems.NETHER_COAL.get(), "nether_coal", 
            16000, "jei.mbb_austenium.coal.source.nether_coal");
        addCoalItemInfo(registration, ModItems.END_COAL.get(), "end_coal", 
            24000, "jei.mbb_austenium.coal.source.end_coal");
        addCoalItemInfo(registration, ModItems.AUSTENIUMCRAFT_COAL.get(), "austeniumcraft_coal", 
            32000, "jei.mbb_austenium.coal.source.austeniumcraft_coal");
        // Every coal block burns exactly ten times its coal and crafts nine to one both ways.
        addCoalBlockInfo(registration, ModBlocks.COPPER_COAL_BLOCK.get(), "copper_coal", 40000);
        addCoalBlockInfo(registration, ModBlocks.IRON_COAL_BLOCK.get(), "iron_coal", 80000);
        addCoalBlockInfo(registration, ModBlocks.SILVER_COAL_BLOCK.get(), "silver_coal", 96000);
        addCoalBlockInfo(registration, ModBlocks.GOLD_COAL_BLOCK.get(), "gold_coal", 160000);
        addCoalBlockInfo(registration, ModBlocks.DIAMOND_COAL_BLOCK.get(), "diamond_coal", 192000);
        addCoalBlockInfo(registration, ModBlocks.EMERALD_COAL_BLOCK.get(), "emerald_coal", 256000);
        addCoalBlockInfo(registration, ModBlocks.ORICHALCUM_COAL_BLOCK.get(), "orichalcum_coal", 320000);
        addCoalBlockInfo(registration, ModBlocks.MYTHRIL_COAL_BLOCK.get(), "mythril_coal", 384000);
        addCoalBlockInfo(registration, ModBlocks.ADAMANTITE_COAL_BLOCK.get(), "adamantite_coal", 480000);
        addCoalBlockInfo(registration, ModBlocks.NETHERITE_COAL_BLOCK.get(), "netherite_coal", 640000);
        addCoalBlockInfo(registration, ModBlocks.RADIANT_COAL_BLOCK.get(), "radiant_coal", 800000);
        addCoalBlockInfo(registration, ModBlocks.AURELIANIUM_COAL_BLOCK.get(), "aurelianium_coal", 1600000);
        addCoalBlockInfo(registration, ModBlocks.OVERWORLD_COAL_BLOCK.get(), "overworld_coal", 80000);
        addCoalBlockInfo(registration, ModBlocks.NETHER_COAL_BLOCK.get(), "nether_coal", 160000);
        addCoalBlockInfo(registration, ModBlocks.END_COAL_BLOCK.get(), "end_coal", 240000);
        addCoalBlockInfo(registration, ModBlocks.AUSTENIUMCRAFT_COAL_BLOCK.get(), "austeniumcraft_coal", 320000);
        // The ten coal ores drop one coal each and stay gated by the wooden pickaxe.
        addCoalOreInfo(registration, ModBlocks.OVERWORLD_COAL_ORE.get(),
            "jei.mbb_austenium.overworld_coal_ore", "overworld_coal",
            "jei.mbb_austenium.band.overworld_stone");
        addCoalOreInfo(registration, ModBlocks.DEEPSLATE_OVERWORLD_COAL_ORE.get(),
            "jei.mbb_austenium.deepslate_overworld_coal_ore", "overworld_coal",
            "jei.mbb_austenium.band.overworld_deepslate");
        addCoalOreInfo(registration, ModBlocks.NETHER_COAL_ORE.get(),
            "jei.mbb_austenium.nether_coal_ore", "nether_coal",
            "jei.mbb_austenium.band.nether_netherrack");
        addCoalOreInfo(registration, ModBlocks.BLACKSTONE_NETHER_COAL_ORE.get(),
            "jei.mbb_austenium.blackstone_nether_coal_ore", "nether_coal",
            "jei.mbb_austenium.band.nether_blackstone");
        addCoalOreInfo(registration, ModBlocks.END_COAL_ORE.get(),
            "jei.mbb_austenium.end_coal_ore", "end_coal",
            "jei.mbb_austenium.band.end_stone");
        addCoalOreInfo(registration, ModBlocks.AUSTENIUMCRAFT_COAL_ORE.get(),
            "jei.mbb_austenium.austeniumcraft_coal_ore", "austeniumcraft_coal",
            "jei.mbb_austenium.band.aw_stone");
        addCoalOreInfo(registration, ModBlocks.DEEPSLATE_AUSTENIUMCRAFT_COAL_ORE.get(),
            "jei.mbb_austenium.deepslate_austeniumcraft_coal_ore", "austeniumcraft_coal",
            "jei.mbb_austenium.band.aw_deepslate");
        addCoalOreInfo(registration, ModBlocks.NETHERRACK_AUSTENIUMCRAFT_COAL_ORE.get(),
            "jei.mbb_austenium.netherrack_austeniumcraft_coal_ore", "austeniumcraft_coal",
            "jei.mbb_austenium.band.aw_netherrack");
        addCoalOreInfo(registration, ModBlocks.BLACKSTONE_AUSTENIUMCRAFT_COAL_ORE.get(),
            "jei.mbb_austenium.blackstone_austeniumcraft_coal_ore", "austeniumcraft_coal",
            "jei.mbb_austenium.band.aw_blackstone");
        addCoalOreInfo(registration, ModBlocks.END_STONE_AUSTENIUMCRAFT_COAL_ORE.get(),
            "jei.mbb_austenium.end_stone_austeniumcraft_coal_ore", "austeniumcraft_coal",
            "jei.mbb_austenium.band.aw_end_stone");
    }

    /**
     * Adds one coal item with its fuel budget and its recipe source.
     *
     * @param registration the JEI registration handle
     * @param coal the coal item
     * @param coalId the coal id used to build the name key
     * @param burnTicks the burn duration in ticks
     * @param sourceKey the translation key of the recipe or drop source line
     */
    private static void addCoalItemInfo(IRecipeRegistration registration, Item coal, String coalId,
        int burnTicks, String sourceKey) {
        registration.addIngredientInfo(coal, Component.translatable("jei.mbb_austenium.coal.item",
            Component.translatable("jei.mbb_austenium.coalname." + coalId), burnTicks,
            burnTicks / TICKS_PER_ITEM, Component.translatable(sourceKey)));
    }

    /**
     * Adds one coal block with its fuel budget.
     *
     * @param registration the JEI registration handle
     * @param block the coal block
     * @param coalId the coal id used to build the name key
     * @param burnTicks the burn duration in ticks
     */
    private static void addCoalBlockInfo(IRecipeRegistration registration, Block block, String coalId,
        int burnTicks) {
        registration.addIngredientInfo(block, Component.translatable("jei.mbb_austenium.coal.block",
            Component.translatable("jei.mbb_austenium.coalname." + coalId), burnTicks,
            burnTicks / TICKS_PER_ITEM));
    }

    /**
     * Adds one coal ore with its mining tier, its band and its drop rule.
     *
     * @param registration the JEI registration handle
     * @param ore the coal ore block
     * @param oreNameKey the translation key of the ore name
     * @param coalId the coal id the ore drops
     * @param bandKey the translation key of the dimension and host rock band
     */
    private static void addCoalOreInfo(IRecipeRegistration registration, Block ore, String oreNameKey,
        String coalId, String bandKey) {
        registration.addIngredientInfo(ore, Component.translatable("jei.mbb_austenium.coal.ore",
            Component.translatable(oreNameKey), Component.translatable(bandKey),
            Component.translatable("jei.mbb_austenium.coalname." + coalId)));
    }
    /** {@inheritDoc} */
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.COPPER_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.COPPER_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.COPPER_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.IRON_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.IRON_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.IRON_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.SILVER_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.SILVER_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.SILVER_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.GOLD_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.GOLD_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.GOLD_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.DIAMOND_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.DIAMOND_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.DIAMOND_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.EMERALD_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.EMERALD_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.EMERALD_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ORICHALCUM_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ORICHALCUM_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ORICHALCUM_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.MYTHRIL_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.MYTHRIL_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.MYTHRIL_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ADAMANTITE_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ADAMANTITE_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.ADAMANTITE_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.NETHERITE_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.NETHERITE_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.NETHERITE_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.RADIANT_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.RADIANT_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.RADIANT_SMOKER.get())));

        registration.addRecipeCatalysts(RecipeTypes.SMELTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.AURELIANIUM_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.BLASTING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.AURELIANIUM_BLAST_FURNACE.get())));
        registration.addRecipeCatalysts(RecipeTypes.SMOKING, VanillaTypes.ITEM_STACK,
            List.of(new ItemStack(ModBlocks.AURELIANIUM_SMOKER.get())));
    }
}