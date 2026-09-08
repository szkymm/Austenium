/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.item;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MbbAustenium.MOD_ID)
public final class MythrilEvents {

    private MythrilEvents() {}

    @SubscribeEvent
    public static void onCraft(PlayerEvent.ItemCraftedEvent event) {
        ItemStack stack = event.getCrafting();
        Item item = stack.getItem();
        if (item instanceof MythrilArmorItem) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
        } else if (isMythrilTool(item)) {
            stack.enchant(Enchantments.BLOCK_EFFICIENCY, 2);
        }
    }

    private static boolean isMythrilTool(Item item) {
        return item instanceof MythrilShovelItem || item instanceof MythrilPickaxeItem
            || item instanceof MythrilAxeItem || item instanceof MythrilSwordItem
            || item instanceof MythrilHoeItem;
    }
}
