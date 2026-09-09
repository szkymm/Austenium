/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.recipe.AurelianiumSmithingRecipe
 * TYPE: Java Source
 * DESCRIPTION: Smithing transform recipe that bakes the innate aurelianium enchantments.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.recipe;

import com.google.gson.JsonObject;
import com.mbb.austenium.content.ModRecipes;
import com.mbb.austenium.content.item.AurelianiumEnchantments;

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
 * AURELIANIUMSMITHINGRECIPE CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumSmithingRecipe.java.
 *
 * com.mbb.austenium.content.recipe.AurelianiumSmithingRecipe:
 *     Aurelianium smithing transform; the innate enchantments are baked during
 *     assemble so the smithing preview already shows them, not only the
 *     stack taken out of the table.
 */
public class AurelianiumSmithingRecipe extends SmithingTransformRecipe {

    public AurelianiumSmithingRecipe(ResourceLocation id, Ingredient template, Ingredient base,
                                 Ingredient addition, ItemStack result) {
        super(id, template, base, addition, result);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        ItemStack stack = super.assemble(container, registryAccess);
        // Baked here so the result slot preview and the taken stack agree.
        AurelianiumEnchantments.applyCrafted(stack);
        return stack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.AURELIANIUM_SMITHING.get();
    }

    /**
     * Serializer that rebuilds AurelianiumSmithingRecipe instead of the vanilla type.
     */
    public static class Serializer extends SmithingTransformRecipe.Serializer {

        @Override
        public AurelianiumSmithingRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient template = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template"));
            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new AurelianiumSmithingRecipe(id, template, base, addition, result);
        }

        @Override
        public AurelianiumSmithingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient template = Ingredient.fromNetwork(buffer);
            Ingredient base = Ingredient.fromNetwork(buffer);
            Ingredient addition = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            return new AurelianiumSmithingRecipe(id, template, base, addition, result);
        }
    }
}
