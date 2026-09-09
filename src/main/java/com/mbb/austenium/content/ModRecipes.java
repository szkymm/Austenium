/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModRecipes
 * TYPE: Java Source
 * DESCRIPTION: Recipe serializer registry for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.recipe.AurelianiumSmithingRecipe;
import com.mbb.austenium.content.recipe.ShulkerBoxUpgradeRecipe;
import com.mbb.austenium.content.recipe.RadiantSmithingRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * MODRECIPES CLASS IS CORE PART OF [MBB] AUSTENIUM ModRecipes.java.
 *
 * com.mbb.austenium.content.ModRecipes:
 *     DeferredRegister holder for the mod recipe serializers.
 */
public final class ModRecipes {

    private ModRecipes() {}

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS
        = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MbbAustenium.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> RADIANT_SMITHING
        = RECIPE_SERIALIZERS.register("radiant_smithing", RadiantSmithingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> AURELIANIUM_SMITHING
        = RECIPE_SERIALIZERS.register("aurelianium_smithing", AurelianiumSmithingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> SHULKER_BOX_UPGRADE
        = RECIPE_SERIALIZERS.register("shaped_shulker_box_upgrade", ShulkerBoxUpgradeRecipe.Serializer::new);
}
