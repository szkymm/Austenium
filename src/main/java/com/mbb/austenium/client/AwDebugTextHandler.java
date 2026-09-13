/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.AwDebugTextHandler
 * TYPE: Java Source
 * DESCRIPTION: Adds the Austeniumcraft World band line to the debug overlay.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.worldgen.AwDimensionRules;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * AwDebugTextHandler CLASS IS CORE PART OF [MBB] AUSTENIUM AwDebugTextHandler.java.
 *
 * com.mbb.austenium.client.AwDebugTextHandler:
 *     Appends one line to the vanilla debug overlay while the player stands inside the Austeniumcraft
 *     World, naming the band the player is in and the exact y level. Mods that replace the debug screen
 *     draw their own lines instead, so this handler only feeds the vanilla overlay.
 *
 * ATTRIBUTES:
 *     none.
 *
 * PUBLIC METHODS:
 *     onDebugText(CustomizeGuiOverlayEvent.DebugText) -> void:
 *         Adds the band line when the current dimension is the mining dimension.
 *
 * PRIVATE METHODS:
 *     none.
 */
@Mod.EventBusSubscriber(modid = MbbAustenium.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class AwDebugTextHandler {

    private AwDebugTextHandler() {}

    /**
     * Appends the Austeniumcraft World band line to the vanilla debug overlay.
     *
     * @param event the debug text event of the debug overlay
     */
    @SubscribeEvent
    public static void onDebugText(CustomizeGuiOverlayEvent.DebugText event) {
        Minecraft minecraft = Minecraft.getInstance();
        // The line only makes sense inside a level with a player, everywhere else it stays away.
        if (minecraft.level == null || minecraft.player == null) {
            return;
        }
        if (!AwDimensionRules.isAusteniumcraftWorld(minecraft.level.dimension())) {
            return;
        }
        int y = Mth.floor(minecraft.player.getY());
        String bandName = Component.translatable("aw.mbb_austenium.band."
            + AwDimensionRules.bandId(y)).getString();
        String messageDebug = Component.translatable("debug.mbb_austenium.aw_band", bandName,
            String.valueOf(y)).getString();
        event.getLeft().add(messageDebug);
    }
}
