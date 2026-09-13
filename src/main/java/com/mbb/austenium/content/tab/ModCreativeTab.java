/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.tab.ModCreativeTab
 * TYPE: Java Source
 * DESCRIPTION: Creative mode tab of [MBB] Austenium, ordered as eight content groups.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.tab;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.item.ModItems;
import com.mbb.austenium.content.item.OrichalcumArmorItem;
import com.mbb.austenium.content.item.OrichalcumAxeItem;
import com.mbb.austenium.content.item.OrichalcumHoeItem;
import com.mbb.austenium.content.item.OrichalcumPickaxeItem;
import com.mbb.austenium.content.item.OrichalcumShovelItem;
import com.mbb.austenium.content.item.OrichalcumSwordItem;
import com.mbb.austenium.content.item.MythrilArmorItem;
import com.mbb.austenium.content.item.MythrilAxeItem;
import com.mbb.austenium.content.item.MythrilHoeItem;
import com.mbb.austenium.content.item.MythrilPickaxeItem;
import com.mbb.austenium.content.item.MythrilShovelItem;
import com.mbb.austenium.content.item.MythrilSwordItem;
import com.mbb.austenium.content.item.AdamantiteArmorItem;
import com.mbb.austenium.content.item.AdamantiteAxeItem;
import com.mbb.austenium.content.item.AdamantiteHoeItem;
import com.mbb.austenium.content.item.AdamantitePickaxeItem;
import com.mbb.austenium.content.item.AdamantiteShovelItem;
import com.mbb.austenium.content.item.AdamantiteSwordItem;
import com.mbb.austenium.content.item.RadiantArmorItem;
import com.mbb.austenium.content.item.RadiantAxeItem;
import com.mbb.austenium.content.item.RadiantHoeItem;
import com.mbb.austenium.content.item.RadiantPickaxeItem;
import com.mbb.austenium.content.item.RadiantShovelItem;
import com.mbb.austenium.content.item.RadiantSwordItem;
import com.mbb.austenium.content.item.AurelianiumArmorItem;
import com.mbb.austenium.content.item.AurelianiumAxeItem;
import com.mbb.austenium.content.item.AurelianiumHoeItem;
import com.mbb.austenium.content.item.AurelianiumPickaxeItem;
import com.mbb.austenium.content.item.AurelianiumShovelItem;
import com.mbb.austenium.content.item.AurelianiumSwordItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ModCreativeTab CLASS IS CORE PART OF [MBB] AUSTENIUM ModCreativeTab.java.
 *
 * com.mbb.austenium.content.tab.ModCreativeTab:
 *     Defines the single creative mode tab of [MBB] Austenium and the eight content groups that
 *     order it, from the machine ladder down to the Austeniumcraft World portal plane.
 *
 * ATTRIBUTES:
 *     TABS (DeferredRegister<CreativeModeTab>): Creative tab registry.
 *     AUSTENIUM_TAB (RegistryObject<CreativeModeTab>): The single mod tab.
 *     MACHINES (List<RegistryObject<Item>>): every machine tier, four machine kinds per tier.
 *     CONTAINERS (List<RegistryObject<Item>>): every storage tier, chest, barrel and shulker box per tier.
 *     ORES (List<RegistryObject<Item>>): ten coal ores, eight metal ores and the two debris blocks.
 *     BLOCKS (List<RegistryObject<Item>>): metal blocks, raw blocks, scrap blocks and the netherite scrap pile.
 *     COALS (List<RegistryObject<Item>>): the sixteen coals and their sixteen fuel blocks.
 *     MATERIALS (List<RegistryObject<Item>>): ingots, nuggets, raw materials, scraps and chains.
 *     TEMPLATES (List<RegistryObject<Item>>): the two upgrade smithing templates.
 *     EQUIPMENT (List<RegistryObject<Item>>): tools and armor of the seven mod gear tiers.
 *     EXCLUDED_ENTRIES (Set<RegistryObject<Item>>): registered items kept out of the tab on purpose.
 *
 * PUBLIC METHODS:
 *     none, the tab is published through the static field.
 *
 * PRIVATE METHODS:
 *     displayOrder() -> List<RegistryObject<Item>>:
 *         Concatenates the eight groups into the display order of the tab.
 *     buildPreview(Item) -> ItemStack:
 *         Builds one entry stack and bakes the innate enchantment preview of its tier.
 *     verifyCoverage(List<RegistryObject<Item>>) -> void:
 *         Logs an error when the display order misses, repeats or invents an entry.
 */
public final class ModCreativeTab {

    private ModCreativeTab() {}

    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MbbAustenium.MOD_ID);

    // Group 1: every machine tier, four machine kinds per tier.
    private static final List<RegistryObject<Item>> MACHINES = List.of(
        // Copper row.
        ModItems.COPPER_FURNACE, ModItems.COPPER_BLAST_FURNACE, ModItems.COPPER_SMOKER, ModItems.COPPER_HOPPER,
        // Iron row.
        ModItems.IRON_FURNACE, ModItems.IRON_BLAST_FURNACE, ModItems.IRON_SMOKER, ModItems.IRON_HOPPER,
        // Silver row.
        ModItems.SILVER_FURNACE, ModItems.SILVER_BLAST_FURNACE, ModItems.SILVER_SMOKER, ModItems.SILVER_HOPPER,
        // Gold row.
        ModItems.GOLD_FURNACE, ModItems.GOLD_BLAST_FURNACE, ModItems.GOLD_SMOKER, ModItems.GOLD_HOPPER,
        // Diamond row.
        ModItems.DIAMOND_FURNACE, ModItems.DIAMOND_BLAST_FURNACE, ModItems.DIAMOND_SMOKER, ModItems.DIAMOND_HOPPER,
        // Emerald row.
        ModItems.EMERALD_FURNACE, ModItems.EMERALD_BLAST_FURNACE, ModItems.EMERALD_SMOKER, ModItems.EMERALD_HOPPER,
        // Orichalcum row.
        ModItems.ORICHALCUM_FURNACE, ModItems.ORICHALCUM_BLAST_FURNACE, ModItems.ORICHALCUM_SMOKER,
        ModItems.ORICHALCUM_HOPPER,
        // Mythril row.
        ModItems.MYTHRIL_FURNACE, ModItems.MYTHRIL_BLAST_FURNACE, ModItems.MYTHRIL_SMOKER, ModItems.MYTHRIL_HOPPER,
        // Adamantite row.
        ModItems.ADAMANTITE_FURNACE, ModItems.ADAMANTITE_BLAST_FURNACE, ModItems.ADAMANTITE_SMOKER,
        ModItems.ADAMANTITE_HOPPER,
        // Netherite row.
        ModItems.NETHERITE_FURNACE, ModItems.NETHERITE_BLAST_FURNACE, ModItems.NETHERITE_SMOKER,
        ModItems.NETHERITE_HOPPER,
        // Radiant row.
        ModItems.RADIANT_FURNACE, ModItems.RADIANT_BLAST_FURNACE, ModItems.RADIANT_SMOKER, ModItems.RADIANT_HOPPER,
        // Aurelianium row.
        ModItems.AURELIANIUM_FURNACE, ModItems.AURELIANIUM_BLAST_FURNACE, ModItems.AURELIANIUM_SMOKER,
        ModItems.AURELIANIUM_HOPPER
    );

    // Group 2: every storage tier, chest, barrel and shulker box per tier.
    private static final List<RegistryObject<Item>> CONTAINERS = List.of(
        // Copper row.
        ModItems.COPPER_CHEST, ModItems.COPPER_BARREL, ModItems.COPPER_SHULKER_BOX,
        // Iron row.
        ModItems.IRON_CHEST, ModItems.IRON_BARREL, ModItems.IRON_SHULKER_BOX,
        // Silver row.
        ModItems.SILVER_CHEST, ModItems.SILVER_BARREL, ModItems.SILVER_SHULKER_BOX,
        // Gold row.
        ModItems.GOLD_CHEST, ModItems.GOLD_BARREL, ModItems.GOLD_SHULKER_BOX,
        // Diamond row.
        ModItems.DIAMOND_CHEST, ModItems.DIAMOND_BARREL, ModItems.DIAMOND_SHULKER_BOX,
        // Emerald row.
        ModItems.EMERALD_CHEST, ModItems.EMERALD_BARREL, ModItems.EMERALD_SHULKER_BOX,
        // Orichalcum row.
        ModItems.ORICHALCUM_CHEST, ModItems.ORICHALCUM_BARREL, ModItems.ORICHALCUM_SHULKER_BOX,
        // Mythril row.
        ModItems.MYTHRIL_CHEST, ModItems.MYTHRIL_BARREL, ModItems.MYTHRIL_SHULKER_BOX,
        // Adamantite row.
        ModItems.ADAMANTITE_CHEST, ModItems.ADAMANTITE_BARREL, ModItems.ADAMANTITE_SHULKER_BOX,
        // Netherite row.
        ModItems.NETHERITE_CHEST, ModItems.NETHERITE_BARREL, ModItems.NETHERITE_SHULKER_BOX,
        // Radiant row.
        ModItems.RADIANT_CHEST, ModItems.RADIANT_BARREL, ModItems.RADIANT_SHULKER_BOX,
        // Aurelianium row.
        ModItems.AURELIANIUM_CHEST, ModItems.AURELIANIUM_BARREL, ModItems.AURELIANIUM_SHULKER_BOX
    );

    // Group 3: ten coal ores, eight metal ores and the two debris blocks.
    private static final List<RegistryObject<Item>> ORES = List.of(
        // Coal family row.
        ModItems.OVERWORLD_COAL_ORE, ModItems.DEEPSLATE_OVERWORLD_COAL_ORE, ModItems.NETHER_COAL_ORE,
        ModItems.BLACKSTONE_NETHER_COAL_ORE, ModItems.END_COAL_ORE, ModItems.AUSTENIUMCRAFT_COAL_ORE,
        ModItems.DEEPSLATE_AUSTENIUMCRAFT_COAL_ORE, ModItems.NETHERRACK_AUSTENIUMCRAFT_COAL_ORE,
        ModItems.BLACKSTONE_AUSTENIUMCRAFT_COAL_ORE, ModItems.END_STONE_AUSTENIUMCRAFT_COAL_ORE,
        // Metal family row.
        ModItems.SILVER_ORE, ModItems.DEEPSLATE_SILVER_ORE, ModItems.ORICHALCUM_ORE,
        ModItems.DEEPSLATE_ORICHALCUM_ORE, ModItems.MYTHRIL_ORE, ModItems.DEEPSLATE_MYTHRIL_ORE,
        ModItems.ADAMANTITE_ORE, ModItems.DEEPSLATE_ADAMANTITE_ORE,
        // Debris row.
        ModItems.RADIANT_DEBRIS, ModItems.AURELIANIUM_DEBRIS
    );

    // Group 4: metal blocks, raw blocks, scrap blocks and the netherite scrap pile.
    private static final List<RegistryObject<Item>> BLOCKS = List.of(
        // Metal blocks row.
        ModItems.SILVER_BLOCK, ModItems.ORICHALCUM_BLOCK, ModItems.MYTHRIL_BLOCK, ModItems.ADAMANTITE_BLOCK,
        ModItems.RADIANT_BLOCK, ModItems.AURELIANIUM_BLOCK,
        // Raw blocks row.
        ModItems.RAW_SILVER_BLOCK, ModItems.RAW_ORICHALCUM_BLOCK, ModItems.RAW_MYTHRIL_BLOCK,
        ModItems.RAW_ADAMANTITE_BLOCK,
        // Scrap blocks row.
        ModItems.RADIANT_SCRAP_BLOCK, ModItems.AURELIANIUM_SCRAP_BLOCK,
        // Netherite scrap row.
        ModItems.NETHERITE_SCRAP_BLOCK
    );

    // Group 5: the sixteen coals and their sixteen fuel blocks.
    private static final List<RegistryObject<Item>> COALS = List.of(
        // Coals row.
        ModItems.COPPER_COAL, ModItems.IRON_COAL, ModItems.SILVER_COAL, ModItems.GOLD_COAL, ModItems.DIAMOND_COAL,
        ModItems.EMERALD_COAL, ModItems.ORICHALCUM_COAL, ModItems.MYTHRIL_COAL, ModItems.ADAMANTITE_COAL,
        ModItems.NETHERITE_COAL, ModItems.RADIANT_COAL, ModItems.AURELIANIUM_COAL, ModItems.OVERWORLD_COAL,
        ModItems.NETHER_COAL, ModItems.END_COAL, ModItems.AUSTENIUMCRAFT_COAL,
        // Coal blocks row.
        ModItems.COPPER_COAL_BLOCK, ModItems.IRON_COAL_BLOCK, ModItems.SILVER_COAL_BLOCK, ModItems.GOLD_COAL_BLOCK,
        ModItems.DIAMOND_COAL_BLOCK, ModItems.EMERALD_COAL_BLOCK, ModItems.ORICHALCUM_COAL_BLOCK,
        ModItems.MYTHRIL_COAL_BLOCK, ModItems.ADAMANTITE_COAL_BLOCK, ModItems.NETHERITE_COAL_BLOCK,
        ModItems.RADIANT_COAL_BLOCK, ModItems.AURELIANIUM_COAL_BLOCK, ModItems.OVERWORLD_COAL_BLOCK,
        ModItems.NETHER_COAL_BLOCK, ModItems.END_COAL_BLOCK, ModItems.AUSTENIUMCRAFT_COAL_BLOCK
    );

    // Group 6: ingots, nuggets, raw materials, scraps and chains.
    private static final List<RegistryObject<Item>> MATERIALS = List.of(
        // Ingots row.
        ModItems.SILVER_INGOT, ModItems.ORICHALCUM_INGOT, ModItems.MYTHRIL_INGOT, ModItems.ADAMANTITE_INGOT,
        ModItems.RADIANT_INGOT, ModItems.AURELIANIUM_INGOT,
        // Nuggets row.
        ModItems.COPPER_NUGGET, ModItems.SILVER_NUGGET, ModItems.ORICHALCUM_NUGGET, ModItems.MYTHRIL_NUGGET,
        ModItems.ADAMANTITE_NUGGET,
        // Raw materials row.
        ModItems.RAW_SILVER, ModItems.RAW_ORICHALCUM, ModItems.RAW_MYTHRIL, ModItems.RAW_ADAMANTITE,
        // Scraps row.
        ModItems.RADIANT_SCRAP, ModItems.AURELIANIUM_SCRAP,
        // Chains row.
        ModItems.COPPER_CHAIN, ModItems.SILVER_CHAIN, ModItems.GOLD_CHAIN, ModItems.ORICHALCUM_CHAIN,
        ModItems.MYTHRIL_CHAIN, ModItems.ADAMANTITE_CHAIN
    );

    // Group 7: the two upgrade smithing templates.
    private static final List<RegistryObject<Item>> TEMPLATES = List.of(
        // Upgrade templates row.
        ModItems.RADIANT_UPGRADE_SMITHING_TEMPLATE, ModItems.AURELIANIUM_UPGRADE_SMITHING_TEMPLATE
    );

    // Group 8: tools and armor of the seven mod gear tiers.
    private static final List<RegistryObject<Item>> EQUIPMENT = List.of(
        // Copper gear row.
        ModItems.COPPER_SWORD, ModItems.COPPER_PICKAXE, ModItems.COPPER_AXE, ModItems.COPPER_SHOVEL,
        ModItems.COPPER_HOE, ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_LEGGINGS,
        ModItems.COPPER_BOOTS,
        // Silver gear row.
        ModItems.SILVER_SWORD, ModItems.SILVER_PICKAXE, ModItems.SILVER_AXE, ModItems.SILVER_SHOVEL,
        ModItems.SILVER_HOE, ModItems.SILVER_HELMET, ModItems.SILVER_CHESTPLATE, ModItems.SILVER_LEGGINGS,
        ModItems.SILVER_BOOTS,
        // Orichalcum gear row.
        ModItems.ORICHALCUM_SWORD, ModItems.ORICHALCUM_PICKAXE, ModItems.ORICHALCUM_AXE, ModItems.ORICHALCUM_SHOVEL,
        ModItems.ORICHALCUM_HOE, ModItems.ORICHALCUM_HELMET, ModItems.ORICHALCUM_CHESTPLATE,
        ModItems.ORICHALCUM_LEGGINGS, ModItems.ORICHALCUM_BOOTS,
        // Mythril gear row.
        ModItems.MYTHRIL_SWORD, ModItems.MYTHRIL_PICKAXE, ModItems.MYTHRIL_AXE, ModItems.MYTHRIL_SHOVEL,
        ModItems.MYTHRIL_HOE, ModItems.MYTHRIL_HELMET, ModItems.MYTHRIL_CHESTPLATE, ModItems.MYTHRIL_LEGGINGS,
        ModItems.MYTHRIL_BOOTS,
        // Adamantite gear row.
        ModItems.ADAMANTITE_SWORD, ModItems.ADAMANTITE_PICKAXE, ModItems.ADAMANTITE_AXE, ModItems.ADAMANTITE_SHOVEL,
        ModItems.ADAMANTITE_HOE, ModItems.ADAMANTITE_HELMET, ModItems.ADAMANTITE_CHESTPLATE,
        ModItems.ADAMANTITE_LEGGINGS, ModItems.ADAMANTITE_BOOTS,
        // Radiant gear row.
        ModItems.RADIANT_SWORD, ModItems.RADIANT_PICKAXE, ModItems.RADIANT_AXE, ModItems.RADIANT_SHOVEL,
        ModItems.RADIANT_HOE, ModItems.RADIANT_HELMET, ModItems.RADIANT_CHESTPLATE, ModItems.RADIANT_LEGGINGS,
        ModItems.RADIANT_BOOTS,
        // Aurelianium gear row.
        ModItems.AURELIANIUM_SWORD, ModItems.AURELIANIUM_PICKAXE, ModItems.AURELIANIUM_AXE,
        ModItems.AURELIANIUM_SHOVEL, ModItems.AURELIANIUM_HOE, ModItems.AURELIANIUM_HELMET,
        ModItems.AURELIANIUM_CHESTPLATE, ModItems.AURELIANIUM_LEGGINGS, ModItems.AURELIANIUM_BOOTS
    );

    // The portal plane stays registered for the dimension link but is deliberately kept out of the tab.
    private static final Set<RegistryObject<Item>> EXCLUDED_ENTRIES = Set.of(ModItems.AUSTENIUMCRAFT_PORTAL);

    public static final RegistryObject<CreativeModeTab> AUSTENIUM_TAB = TABS.register("austenium_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mbb_austenium"))
            .icon(() -> new ItemStack(ModItems.AURELIANIUM_BLOCK.get()))
            .displayItems((parameters, output) -> {
                // Walk the eight group tables once per tab build and decorate every gear entry.
                List<RegistryObject<Item>> ordered = displayOrder();
                // A size or duplicate mismatch means the table drifted away from the registry.
                verifyCoverage(ordered);
                output.acceptAll(ordered.stream().map(reference -> buildPreview(reference.get())).toList());
            })
            .build());

    /**
     * Concatenates the eight content groups into the display order of the tab.
     *
     * @return every tab entry in its display order
     */
    private static List<RegistryObject<Item>> displayOrder() {
        List<RegistryObject<Item>> ordered = new ArrayList<>();
        // Machines open the tab, the tier ladder is the core of the mod.
        ordered.addAll(MACHINES);
        // Storage keeps every container of a tier next to its machine.
        ordered.addAll(CONTAINERS);
        // Ores and debris follow as the source of every material.
        ordered.addAll(ORES);
        // Blocks gather the nine to one storage shapes of those materials.
        ordered.addAll(BLOCKS);
        // The coal family sits between blocks and materials because it is a fuel line of its own.
        ordered.addAll(COALS);
        // Loose materials close the crafting chain before the templates.
        ordered.addAll(MATERIALS);
        // The two upgrade templates sit directly above the gear they unlock.
        ordered.addAll(TEMPLATES);
        // Equipment ends the crafting progression with the seven gear tiers.
        ordered.addAll(EQUIPMENT);
        return ordered;
    }

    /**
     * Builds one creative tab entry and bakes the innate enchantment preview of its tier.
     *
     * @param item the registered item the entry shows
     * @return the preview stack placed into the tab
     */
    private static ItemStack buildPreview(Item item) {
        ItemStack stack = item.getDefaultInstance();
        // The smithing and crafting tiers preview the enchantments their recipe bakes in.
        if (item instanceof OrichalcumArmorItem) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1);
        } else if (item instanceof OrichalcumPickaxeItem || item instanceof OrichalcumAxeItem
                || item instanceof OrichalcumShovelItem || item instanceof OrichalcumSwordItem
                || item instanceof OrichalcumHoeItem) {
            stack.enchant(Enchantments.UNBREAKING, 2);
        } else if (item instanceof MythrilArmorItem) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
        } else if (item instanceof MythrilPickaxeItem || item instanceof MythrilAxeItem
                || item instanceof MythrilShovelItem || item instanceof MythrilSwordItem
                || item instanceof MythrilHoeItem) {
            stack.enchant(Enchantments.BLOCK_EFFICIENCY, 2);
        } else if (item instanceof AdamantiteArmorItem) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 3);
        } else if (item instanceof AdamantitePickaxeItem || item instanceof AdamantiteAxeItem
                || item instanceof AdamantiteShovelItem || item instanceof AdamantiteSwordItem
                || item instanceof AdamantiteHoeItem) {
            stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
        } else if (item instanceof RadiantArmorItem radiantArmor) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 3);
            stack.enchant(Enchantments.UNBREAKING, 3);
            stack.enchant(Enchantments.MENDING, 1);
            // Radiant boots add feather falling on top of the standard armor set.
            if (radiantArmor.getType() == ArmorItem.Type.BOOTS) {
                stack.enchant(Enchantments.FALL_PROTECTION, 3);
            }
        } else if (item instanceof RadiantPickaxeItem || item instanceof RadiantAxeItem
                || item instanceof RadiantShovelItem || item instanceof RadiantSwordItem
                || item instanceof RadiantHoeItem) {
            stack.enchant(Enchantments.BLOCK_EFFICIENCY, 3);
            stack.enchant(Enchantments.BLOCK_FORTUNE, 3);
            stack.enchant(Enchantments.UNBREAKING, 3);
            stack.enchant(Enchantments.MENDING, 1);
        } else if (item instanceof AurelianiumArmorItem aurelianiumArmor) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 6);
            stack.enchant(Enchantments.UNBREAKING, 6);
            stack.enchant(Enchantments.MENDING, 1);
            // Aurelianium boots add feather falling on top of the standard armor set.
            if (aurelianiumArmor.getType() == ArmorItem.Type.BOOTS) {
                stack.enchant(Enchantments.FALL_PROTECTION, 6);
            }
        } else if (item instanceof AurelianiumPickaxeItem || item instanceof AurelianiumAxeItem
                || item instanceof AurelianiumShovelItem || item instanceof AurelianiumSwordItem
                || item instanceof AurelianiumHoeItem) {
            stack.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            stack.enchant(Enchantments.BLOCK_FORTUNE, 5);
            stack.enchant(Enchantments.UNBREAKING, 5);
            stack.enchant(Enchantments.MENDING, 1);
        }
        return stack;
    }

    /**
     * Logs an error when the display order drifts away from the item registry.
     *
     * @param ordered the display order built for this tab
     */
    private static void verifyCoverage(List<RegistryObject<Item>> ordered) {
        int expected = ModItems.ITEMS.getEntries().size() - EXCLUDED_ENTRIES.size();
        // A missing, repeated or wrongly excluded entry would silently hide an item from the tab.
        boolean isSizeWrong = ordered.size() != expected;
        boolean hasDuplicate = new HashSet<>(ordered).size() != ordered.size();
        boolean hasExcluded = ordered.stream().anyMatch(EXCLUDED_ENTRIES::contains);
        if (isSizeWrong || hasDuplicate || hasExcluded) {
            String messageError = "创造模式物品栏条目与注册不一致: " + ordered.size() + " / " + expected;
            MbbAustenium.LOGGER.error(messageError);
        }
    }
}
