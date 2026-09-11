/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Tier shulker box content for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.recipe;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.ModRecipes;
import com.mbb.austenium.content.block.TierShulkerBoxBlock;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShulkerBoxUpgradeRecipe CLASS IS CORE PART OF [MBB] AUSTENIUM ShulkerBoxUpgradeRecipe.java.
 *
 * com.mbb.austenium.content.recipe.ShulkerBoxUpgradeRecipe:
 *     A shaped recipe that behaves exactly like the barrel recipe it mirrors, but
 *     copies the contents of the vanilla shulker box in the middle slot into the
 *     crafted tier shulker box.
 */
public class ShulkerBoxUpgradeRecipe extends ShapedRecipe {

    private static final TagKey<Item> UPGRADEABLE_SHULKER_BOXES =
        TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "upgradeable_shulker_boxes"));

    private static final String BLOCK_ENTITY_TAG = "BlockEntityTag";

    /**
     * Creates the ShulkerBoxUpgradeRecipe instance.
     *
     * @param id the container id assigned by the menu
     * @param group the group argument
     * @param category the category argument
     */
    public ShulkerBoxUpgradeRecipe(ResourceLocation id, String group, CraftingBookCategory category,
            int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
        super(id, group, category, width, height, ingredients, result);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack result = super.assemble(container, registryAccess);
        for (int slot = 0; slot < container.getContainerSize(); slot++) {
            ItemStack input = container.getItem(slot);
            // Accept tier boxes as well as vanilla boxes, or a chained upgrade would lose its contents.
            if (!isCarryableShulkerBox(input) || !input.hasTag()) {
                continue;
            }
            CompoundTag tag = input.getTag();
            if (tag != null && tag.contains(BLOCK_ENTITY_TAG, CompoundTag.TAG_COMPOUND)) {
                result.getOrCreateTag().put(BLOCK_ENTITY_TAG, tag.getCompound(BLOCK_ENTITY_TAG).copy());
                break;
            }
        }
        return result;
    }

    /**
     * Checks whether the stack is a shulker box whose contents are carried over to
     * the crafted box: a vanilla shulker box from the upgradeable tag, or a tier
     * shulker box of this mod, so chained upgrades never lose their contents.
     *
     * @param stack the crafting input stack to test
     * @return true when the stack is a shulker box that may carry contents over
     */
    private static boolean isCarryableShulkerBox(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.is(UPGRADEABLE_SHULKER_BOXES)) {
            return true;
        }
        Block block = Block.byItem(stack.getItem());
        return block instanceof TierShulkerBoxBlock;
    }

    /** {@inheritDoc} */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SHULKER_BOX_UPGRADE.get();
    }

    /**
     * Serializer CLASS IS CORE PART OF [MBB] AUSTENIUM ShulkerBoxUpgradeRecipe.java.
     *
     *     Parses the same pattern / key / result shape as minecraft:crafting_shaped.
     */
    public static class Serializer implements RecipeSerializer<ShulkerBoxUpgradeRecipe> {

        /** {@inheritDoc} */
        @Override
        public ShulkerBoxUpgradeRecipe fromJson(ResourceLocation id, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");
            CraftingBookCategory category = CraftingBookCategory.CODEC
                .byName(GsonHelper.getAsString(json, "category", null), CraftingBookCategory.MISC);
            Map<String, Ingredient> keys = keyFromJson(GsonHelper.getAsJsonObject(json, "key"));
            String[] pattern = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
            int width = pattern[0].length();
            int height = pattern.length;
            NonNullList<Ingredient> ingredients = dissolvePattern(pattern, keys, width, height);
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new ShulkerBoxUpgradeRecipe(id, group, category, width, height, ingredients, result);
        }

        /** {@inheritDoc} */
        @Override
        public ShulkerBoxUpgradeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);
            int width = buffer.readVarInt();
            int height = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }
            ItemStack result = buffer.readItem();
            return new ShulkerBoxUpgradeRecipe(id, group, category, width, height, ingredients, result);
        }

        /** {@inheritDoc} */
        @Override
        public void toNetwork(FriendlyByteBuf buffer, ShulkerBoxUpgradeRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            buffer.writeVarInt(recipe.getRecipeWidth());
            buffer.writeVarInt(recipe.getRecipeHeight());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
        }

        private static String[] patternFromJson(JsonArray array) {
            if (array.size() == 0) {
                throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
            }
            if (array.size() > 3) {
                throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
            }
            String[] pattern = new String[array.size()];
            for (int row = 0; row < pattern.length; row++) {
                String line = GsonHelper.convertToString(array.get(row), "pattern[" + row + "]");
                if (line.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
                }
                if (row > 0 && pattern[0].length() != line.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }
                pattern[row] = line;
            }
            return pattern;
        }

        private static Map<String, Ingredient> keyFromJson(JsonObject json) {
            Map<String, Ingredient> keys = new LinkedHashMap<>();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                if (entry.getKey().length() != 1) {
                    throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey()
                        + "' is an invalid symbol (must be 1 character only).");
                }
                if (" ".equals(entry.getKey())) {
                    throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
                }
                keys.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
            }
            keys.put(" ", Ingredient.EMPTY);
            return keys;
        }

        private static String[] shrink(String... pattern) {
            int firstColumn = Integer.MAX_VALUE;
            int lastColumn = 0;
            int firstRow = 0;
            int lastRow = 0;
            int emptyRows = 0;
            for (int row = 0; row < pattern.length; row++) {
                String line = pattern[row];
                int start = firstNonSpace(line);
                firstColumn = Math.min(firstColumn, start);
                int end = lastNonSpace(line);
                lastColumn = Math.max(lastColumn, end);
                if (end < 0) {
                    if (firstRow == row) {
                        firstRow++;
                    }
                    emptyRows++;
                } else {
                    lastRow = row;
                }
            }
            if (pattern.length == emptyRows) {
                return new String[0];
            }
            String[] shrunk = new String[lastRow - firstRow + 1];
            for (int row = 0; row < shrunk.length; row++) {
                shrunk[row] = pattern[row + firstRow].substring(firstColumn, lastColumn + 1);
            }
            return shrunk;
        }

        private static int firstNonSpace(String line) {
            int index = 0;
            while (index < line.length() && line.charAt(index) == ' ') {
                index++;
            }
            return index;
        }

        private static int lastNonSpace(String line) {
            int index = line.length() - 1;
            while (index >= 0 && line.charAt(index) == ' ') {
                index--;
            }
            return index;
        }

        private static NonNullList<Ingredient> dissolvePattern(String[] pattern, Map<String, Ingredient> keys,
                int width, int height) {
            NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
            Map<String, Ingredient> remaining = new LinkedHashMap<>(keys);
            remaining.remove(" ");
            for (int row = 0; row < pattern.length; row++) {
                for (int column = 0; column < pattern[row].length(); column++) {
                    String symbol = pattern[row].substring(column, column + 1);
                    Ingredient ingredient = keys.get(symbol);
                    if (ingredient == null) {
                        throw new JsonSyntaxException("Pattern references symbol '" + symbol
                            + "' but it's not defined in the key");
                    }
                    remaining.remove(symbol);
                    ingredients.set(column + width * row, ingredient);
                }
            }
            if (!remaining.isEmpty()) {
                throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + remaining.keySet());
            }
            return ingredients;
        }
    }
}
