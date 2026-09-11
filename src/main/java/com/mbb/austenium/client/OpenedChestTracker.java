/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */

package com.mbb.austenium.client;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Tracks the chest opened by the current client player so stopOpen can be called
 * when the grid menu screen closes (client menu containers are plain containers).
 */
public final class OpenedChestTracker {

    private static final Map<UUID, ChestBlockEntity> OPEN = new HashMap<>();

    private OpenedChestTracker() {}

    /**
     * Remembers the chest this player opened so the lid can be closed later.
     *
     * @param player the player that opened the chest
     * @param chest the chest that was opened
     */
    public static void open(Player player, ChestBlockEntity chest) {
        OPEN.put(player.getUUID(), chest);
    }

    /**
     * Closes the chest remembered for this player, when one is remembered.
     *
     * @param player the player whose chest should be closed
     */
    public static void close(Player player) {
        ChestBlockEntity chest = OPEN.remove(player.getUUID());
        if (chest != null) {
            chest.stopOpen(player);
        }
    }
}
