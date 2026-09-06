/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jei.AusteniumJeiPlugin
 * TYPE: Java Source
 * DESCRIPTION: JEI integration for copper furnace machines.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.jei;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
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

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MbbAustenium.MOD_ID, "jei");
    }

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
    }
}