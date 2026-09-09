/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.item.AurelianiumAnvilHandler
 * TYPE: Java Source
 * DESCRIPTION: Anvil rule that swaps the innate Fortune for Silk Touch on aurelianium tools.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

/**
 * AURELIANIUMANVILHANDLER CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumAnvilHandler.java.
 *
 * com.mbb.austenium.content.item.AurelianiumAnvilHandler:
 *     An anvil holding an aurelianium tool and a Silk Touch book replaces the innate
 *     Fortune with Silk Touch, keeping every other enchantment and the durability.
 */
@Mod.EventBusSubscriber(modid = MbbAustenium.MOD_ID)
public final class AurelianiumAnvilHandler {

    private static final int EXPERIENCE_COST = 5;

    private AurelianiumAnvilHandler() {}

    /**
     * Builds the Silk Touch replacement result when the left slot is an aurelianium tool.
     *
     * @param event the anvil update event
     */
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack tool = event.getLeft();
        ItemStack book = event.getRight();
        if (!isAurelianiumTool(tool) || !isSilkTouchBook(book)) {
            return;
        }
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(tool);
        if (!enchantments.containsKey(Enchantments.BLOCK_FORTUNE)) {
            return;
        }
        // Fortune and Silk Touch are mutually exclusive in vanilla, so the swap is explicit here.
        enchantments.remove(Enchantments.BLOCK_FORTUNE);
        enchantments.put(Enchantments.SILK_TOUCH, 1);
        ItemStack result = tool.copy();
        EnchantmentHelper.setEnchantments(enchantments, result);
        event.setOutput(result);
        event.setCost(EXPERIENCE_COST);
        event.setMaterialCost(1);
    }

    private static boolean isAurelianiumTool(ItemStack stack) {
        return stack.getItem() instanceof AurelianiumSwordItem
            || stack.getItem() instanceof AurelianiumPickaxeItem
            || stack.getItem() instanceof AurelianiumAxeItem
            || stack.getItem() instanceof AurelianiumShovelItem
            || stack.getItem() instanceof AurelianiumHoeItem;
    }

    private static boolean isSilkTouchBook(ItemStack stack) {
        if (stack.getItem() != Items.ENCHANTED_BOOK) {
            return false;
        }
        ResourceLocation silkTouch = ForgeRegistries.ENCHANTMENTS.getKey(Enchantments.SILK_TOUCH);
        if (silkTouch == null) {
            return false;
        }
        ListTag stored = EnchantedBookItem.getEnchantments(stack);
        for (int index = 0; index < stored.size(); index++) {
            CompoundTag entry = stored.getCompound(index);
            if (silkTouch.toString().equals(entry.getString("id")) && entry.getInt("lvl") > 0) {
                return true;
            }
        }
        return false;
    }
}
