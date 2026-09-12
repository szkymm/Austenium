/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.AwDimensionEvents
 * TYPE: Java Source
 * DESCRIPTION: Forge bus rules that keep the Austeniumcraft World a mining dimension.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.worldgen.AwDimensionRules;

import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.worldgen.AwPortalShape;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * AWDIMENSIONEVENTS CLASS IS CORE PART OF [MBB] AUSTENIUM AwDimensionEvents.java.
 *
 * com.mbb.austenium.content.AwDimensionEvents:
 *     Enforces the Austeniumcraft World rules that no data pack can express: the
 *     Nether portal frame stays inert inside AW, players cannot plant an End portal
 *     frame there either, and the natural spawn table is split by band so bats stay in
 *     the deepslate and stone layers while endermen stay in the end stone layer.
 */
@Mod.EventBusSubscriber(modid = MbbAustenium.MOD_ID)
public final class AwDimensionEvents {

    private AwDimensionEvents() {}

    /**
     * The portal plane is never minable, not even in creative mode.
     *
     * @param event the break event fired before a block is removed
     */
    @SubscribeEvent
    public static void onBreakEvent(BlockEvent.BreakEvent event) {
        if (event.getState().is(ModBlocks.AUSTENIUMCRAFT_PORTAL.get())) {
            event.setCanceled(true);
        }
    }

    /**
     * Cancels every Nether portal that a fire block would open inside AW.
     *
     * @param event the portal spawn event fired by the fire block
     */
    @SubscribeEvent
    public static void onPortalSpawn(BlockEvent.PortalSpawnEvent event) {
        // Only the Austeniumcraft World forbids the vanilla Nether route.
        if (isAusteniumcraftWorld(event.getLevel())) {
            event.setCanceled(true);
        }
    }

    /**
     * Cancels an End portal frame that a player tries to place inside AW.
     *
     * @param event the block placement event of the player
     */
    @SubscribeEvent
    public static void onEntityPlace(BlockEvent.EntityPlaceEvent event) {
        // The dimension holds no stronghold, so no End route may be started by hand either.
        if (!isAusteniumcraftWorld(event.getLevel())) {
            return;
        }
        if (event.getPlacedBlock().is(net.minecraft.world.level.block.Blocks.END_PORTAL_FRAME)) {
            event.setCanceled(true);
        }
    }

    /**
     * Opens an Austeniumcraft portal when the player strikes its aurelianium frame block.
     *
     * @param event the left click that a player performs on a block
     */
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getAction() != PlayerInteractEvent.LeftClickBlock.Action.START) {
            return;
        }
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (!isPortalDimension(serverLevel) || !event.getEntity().getMainHandItem().is(ItemTags.PICKAXES)) {
            return;
        }
        if (!serverLevel.getBlockState(event.getPos()).is(ModBlocks.AURELIANIUM_BLOCK.get())) {
            return;
        }
        if (openPortalNear(serverLevel, event.getPos())) {
            // A successful activation must not also mine the frame block away.
            event.setCanceled(true);
        }
    }

    /**
     * Tries to open a portal whose ring contains the struck block.
     *
     * @param level the server level that holds the ring
     * @param struck the frame block the player hit
     * @return true when a complete ring was found and its plane opened
     */
    private static boolean openPortalNear(ServerLevel level, BlockPos struck) {
        for (int offsetX = -2; offsetX <= 2; offsetX++) {
            for (int offsetZ = -2; offsetZ <= 2; offsetZ++) {
                BlockPos candidate = struck.offset(offsetX, 0, offsetZ);
                if (AwPortalShape.activate(level, candidate)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Reports whether a level can host an Austeniumcraft portal at all.
     *
     * @param level the level to test
     * @return true for the Overworld and the Austeniumcraft World
     */
    private static boolean isPortalDimension(Level level) {
        return Level.OVERWORLD.equals(level.dimension())
            || AwDimensionRules.isAusteniumcraftWorld(level.dimension());
    }

    /**
     * Keeps the natural spawn bands of the Austeniumcraft World apart.
     *
     * @param event the position check of a mob that is about to spawn
     */
    @SubscribeEvent
    public static void onPositionCheck(MobSpawnEvent.PositionCheck event) {
        Mob mob = event.getEntity();
        ResourceKey<Level> dimension = mob.level().dimension();
        if (!AwDimensionRules.isAusteniumcraftWorld(dimension)) {
            return;
        }
        int height = mob.blockPosition().getY();
        // Bats belong to the deepslate and stone layers, endermen to the end stone layer.
        if (mob.getType() == EntityType.BAT && !AwDimensionRules.isDeepslateOrStoneBand(height)) {
            event.setResult(net.minecraftforge.eventbus.api.Event.Result.DENY);
        }
        if (mob.getType() == EntityType.ENDERMAN && !AwDimensionRules.isEndStoneBand(height)) {
            event.setResult(net.minecraftforge.eventbus.api.Event.Result.DENY);
        }
    }

    /**
     * Reports whether a level accessor belongs to the Austeniumcraft World.
     *
     * @param levelAccessor the level accessor of the event, which may not be a server level
     * @return true when the accessor is the Austeniumcraft World
     */
    private static boolean isAusteniumcraftWorld(LevelAccessor levelAccessor) {
        // Only a server level owns a dimension key, so anything else is never AW.
        return levelAccessor instanceof ServerLevel serverLevel
            && AwDimensionRules.isAusteniumcraftWorld(serverLevel.dimension());
    }
}
