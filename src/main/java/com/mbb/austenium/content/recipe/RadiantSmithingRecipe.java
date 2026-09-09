/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.recipe.RadiantSmithingRecipe
 * TYPE: Java Source
 * DESCRIPTION: Smithing transform recipe that bakes the innate radiant enchantments.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.recipe;

import com.google.gson.JsonObject;
import com.mbb.austenium.content.ModRecipes;
import com.mbb.austenium.content.item.RadiantEnchantments;

import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;

/**
 * RADIANTSMITHINGRECIPE CLASS IS CORE PART OF [MBB] AUSTENIUM RadiantSmithingRecipe.java.
 *
 * com.mbb.austenium.content.recipe.RadiantSmithingRecipe:
 *     Radiant smithing transform; the innate enchantments are baked during
 *     assemble so the smithing preview already shows them, not only the
 *     stack taken out of the table.
 */
public class RadiantSmithingRecipe extends SmithingTransformRecipe {

    public RadiantSmithingRecipe(ResourceLocation id, Ingredient template, Ingredient base,
                                 Ingredient addition, ItemStack result) {
        super(id, template, base, addition, result);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        ItemStack stack = super.assemble(container, registryAccess);
        // Baked here so the result slot preview and the taken stack agree.
        RadiantEnchantments.applyCrafted(stack);
        return stack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.RADIANT_SMITHING.get();
    }

    /**
     * Serializer that rebuilds RadiantSmithingRecipe instead of the vanilla type.
     */
    public static class Serializer extends SmithingTransformRecipe.Serializer {

        @Override
        public RadiantSmithingRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient template = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template"));
            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new RadiantSmithingRecipe(id, template, base, addition, result);
        }

        @Override
        public RadiantSmithingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient template = Ingredient.fromNetwork(buffer);
            Ingredient base = Ingredient.fromNetwork(buffer);
            Ingredient addition = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new RadiantSmithingRecipe(id, template, base, addition, result);
        }
    }
}
