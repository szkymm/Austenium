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
package com.mbb.austenium.content.tab;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.item.ModItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * ModCreativeTab CLASS IS CORE PART OF [MBB] AUSTENIUM ModCreativeTab.java.
 *
 * com.mbb.austenium.content.tab.ModCreativeTab:
 *     Defines the custom creative mode tab for [MBB] Austenium.
 *
 * ATTRIBUTES:
 *     TABS (DeferredRegister<CreativeModeTab>): Creative tab registry.
 *     AUSTENIUM_TAB (RegistryObject<CreativeModeTab>): The main creative tab.
 */
public final class ModCreativeTab {

    private ModCreativeTab() {}

    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MbbAustenium.MOD_ID);

    public static final RegistryObject<CreativeModeTab> AUSTENIUM_TAB = TABS.register("austenium_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mbb_austenium"))
            .icon(() -> new ItemStack(Blocks.CHEST))
            .displayItems((parameters, output) -> {
                // Enumerate the current item registry until real content is added.
                java.util.List<ItemStack> stacks = ModItems.ITEMS.getEntries().stream().map(reference -> {
                    net.minecraft.world.item.Item item = reference.get();
                    ItemStack stack = item.getDefaultInstance();
                    if (item instanceof com.mbb.austenium.content.item.OrichalcumArmorItem) {
                        stack.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1);
                    } else if (item instanceof com.mbb.austenium.content.item.OrichalcumShovelItem
                            || item instanceof com.mbb.austenium.content.item.OrichalcumPickaxeItem
                            || item instanceof com.mbb.austenium.content.item.OrichalcumAxeItem
                            || item instanceof com.mbb.austenium.content.item.OrichalcumSwordItem
                            || item instanceof com.mbb.austenium.content.item.OrichalcumHoeItem) {
                        stack.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2);
                    }
                    return stack;
                }).toList();
                output.acceptAll(stacks);
            })
            .build());
}
