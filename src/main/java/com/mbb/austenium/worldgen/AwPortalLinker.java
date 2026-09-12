/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.worldgen.AwPortalLinker
 * TYPE: Java Source
 * DESCRIPTION: Travel and counterpart portal handling of the Austeniumcraft World.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;

import net.minecraftforge.common.util.ITeleporter;

/**
 * AWPORTALLINKER CLASS IS CORE PART OF [MBB] AUSTENIUM AwPortalLinker.java.
 *
 * com.mbb.austenium.worldgen.AwPortalLinker:
 *     Carries an entity between the Overworld and the Austeniumcraft World. The link is
 *     one to one in x and z, the arrival portal is searched for in a small radius and
 *     built together with a carved pocket when none exists, and an AW portal above
 *     y 320 maps back to the Overworld at y 317 because the Overworld stops there.
 *
 * ATTRIBUTES:
 *     SEARCH_RADIUS (int): Radius used when looking for an existing counterpart portal.
 *     HIGH_LEVEL_REMAP_Y (int): Overworld arrival height for an AW portal above the Overworld ceiling.
 *     OVERWORLD_TOP_BLOCK_Y (int): Highest block the Overworld can hold (build height 320).
 *     PORTAL_COOLDOWN (int): Ticks an entity cannot use a portal again.
 *     POCKET_RADIUS (int): Half width of the pocket carved around a new portal.
 *     POCKET_DEPTH (int): Blocks carved below and above the new portal plane.
 */
public final class AwPortalLinker {

    private static final int SEARCH_RADIUS = 16;
    private static final int HIGH_LEVEL_REMAP_Y = 317;
    private static final int OVERWORLD_TOP_BLOCK_Y = 319;
    private static final int PORTAL_COOLDOWN = 300;
    private static final int POCKET_RADIUS = 2;
    /** Air kept above the plane for breathing room and three blocks of padding below it. */
    private static final int POCKET_DEPTH = 3;

    private AwPortalLinker() {}

    /**
     * Resolves the counterpart dimension of a portal trip.
     *
     * @param source the dimension the entity currently stands in
     * @return the counterpart dimension key, or null when this dimension has no AW link
     */
    public static ResourceKey<Level> counterpart(ResourceKey<Level> source) {
        if (AwDimensionRules.isAusteniumcraftWorld(source)) {
            return Level.OVERWORLD;
        }
        if (Level.OVERWORLD.equals(source)) {
            return AwDimensionRules.DIMENSION;
        }
        return null;
    }

    /**
     * Maps a portal position into the counterpart dimension.
     *
     * @param target the dimension the entity is about to enter
     * @param portalPos the portal position the entity used
     * @return the mapped target position
     */
    public static BlockPos mapPosition(ServerLevel target, BlockPos portalPos) {
        int wantedX = portalPos.getX();
        int wantedZ = portalPos.getZ();
        int wantedY;
        if (AwDimensionRules.isAusteniumcraftWorld(target.dimension())) {
            // The Austeniumcraft World accepts the Overworld height unchanged.
            wantedY = Math.max(AwDimensionRules.BEDROCK_Y + 2,
                Math.min(AwDimensionRules.TOP_Y - 2, portalPos.getY()));
        } else {
            // The Overworld holds blocks up to y 319, so anything above that starts at the shelf.
            wantedY = portalPos.getY() > OVERWORLD_TOP_BLOCK_Y ? HIGH_LEVEL_REMAP_Y : portalPos.getY();
            wantedY = Math.max(-60, wantedY);
        }
        return new BlockPos(wantedX, supportedY(target, wantedX, wantedY, wantedZ), wantedZ);
    }

    /**
     * Walks down from a wanted height until the cell below has a solid floor.
     *
     * @param target the level that will receive the portal
     * @param x the wanted x coordinate
     * @param wantedY the wanted y coordinate
     * @param z the wanted z coordinate
     * @return the lowest safe anchor at or below the wanted height, or the wanted height when the column has no floor
     */
    private static int supportedY(ServerLevel target, int x, int wantedY, int z) {
        for (int y = wantedY; y > target.getMinBuildHeight(); y--) {
            BlockPos below = new BlockPos(x, y - 1, z);
            if (!target.getBlockState(below).getCollisionShape(target, below).isEmpty()) {
                return y;
            }
        }
        return wantedY;
    }

    /**
     * Carries one entity through an Austeniumcraft portal.
     *
     * @param source the level the entity stands in
     * @param entity the entity that entered the portal plane
     * @param portalPos the portal cell the entity touched
     * @return the entity as it now exists in the destination level, or null when no trip happened
     */
    public static Entity travel(ServerLevel source, Entity entity, BlockPos portalPos) {
        ResourceKey<Level> targetKey = counterpart(source.dimension());
        if (targetKey == null || source.getServer() == null) {
            return null;
        }
        ServerLevel target = source.getServer().getLevel(targetKey);
        if (target == null) {
            return null;
        }
        BlockPos center = ensureCounterpart(target, mapPosition(target, portalPos));
        // A cooldown keeps the arrival from bouncing straight back through the new portal.
        entity.setPortalCooldown(PORTAL_COOLDOWN);
        // The arrival stands on the frame block beside the plane, never inside the new plane.
        double arrivalX = center.getX() + 2.5;
        double arrivalY = center.getY() + 1.0;
        double arrivalZ = center.getZ() + 0.5;
        if (entity instanceof ServerPlayer player) {
            player.teleportTo(target, arrivalX, arrivalY, arrivalZ, player.getYRot(), player.getXRot());
            return player;
        }
        // A plain changeDimension does not move a non player entity, so the teleporter places it.
        Entity moved = entity.changeDimension(target, new AwTeleporter(arrivalX, arrivalY, arrivalZ));
        if (moved == null || moved.isRemoved()) {
            // A dimension change that eats the traveller must never happen, so the copy is rebuilt.
            moved = moveCopy(entity, target, arrivalX, arrivalY, arrivalZ);
        }
        return moved;
    }

    /**
     * Rebuilds a traveller in the destination level when the dimension change lost it.
     *
     * @param entity the entity that entered the portal
     * @param target the level that must receive the copy
     * @param arrivalX the arrival x in the destination level
     * @param arrivalY the arrival y in the destination level
     * @param arrivalZ the arrival z in the destination level
     * @return the rebuilt entity, or null when the entity type refused to create one
     */
    private static Entity moveCopy(Entity entity, ServerLevel target, double arrivalX, double arrivalY,
            double arrivalZ) {
        Entity copy = entity.getType().create(target);
        if (copy == null) {
            return null;
        }
        copy.restoreFrom(entity);
        copy.moveTo(arrivalX, arrivalY, arrivalZ, entity.getYRot(), entity.getXRot());
        copy.setPortalCooldown(PORTAL_COOLDOWN);
        target.addFreshEntity(copy);
        return copy;
    }

    /**
     * Places a travelling entity on the arrival shelf of the counterpart portal.
     *
     * com.mbb.austenium.worldgen.AwPortalLinker.AwTeleporter:
     *     Forge teleporter that copies the entity into the destination level and moves the
     *     copy to the arrival position, which the default dimension change never does.
     */
    private static final class AwTeleporter implements ITeleporter {

        private final double arrivalX;
        private final double arrivalY;
        private final double arrivalZ;

        /**
         * Creates the teleporter for one arrival position.
         *
         * @param arrivalX the arrival x in the destination level
         * @param arrivalY the arrival y in the destination level
         * @param arrivalZ the arrival z in the destination level
         */
        private AwTeleporter(double arrivalX, double arrivalY, double arrivalZ) {
            this.arrivalX = arrivalX;
            this.arrivalY = arrivalY;
            this.arrivalZ = arrivalZ;
        }

        /** {@inheritDoc} */
        @Override
        public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw,
                Function<Boolean, Entity> repositionEntity) {
            Entity moved = repositionEntity.apply(false);
            if (moved != null) {
                // The copy must land on the shelf and keep the portal cooldown of the trip.
                moved.moveTo(arrivalX, arrivalY, arrivalZ, yaw, moved.getXRot());
                moved.setPortalCooldown(PORTAL_COOLDOWN);
            }
            return moved;
        }
    }

    /**
     * Finds the counterpart portal, or builds one with a carved pocket around it.
     *
     * @param target the level that must hold the counterpart portal
     * @param wanted the mapped position of the portal
     * @return the centre of the counterpart portal
     */
    public static BlockPos ensureCounterpart(ServerLevel target, BlockPos wanted) {
        // The pocket and the ring need a live chunk before any block can be written.
        target.getChunk(wanted.getX() >> 4, wanted.getZ() >> 4);
        BlockPos existing = AwPortalShape.findPortalCenter(target, wanted, SEARCH_RADIUS);
        if (existing != null) {
            return existing;
        }
        BlockPos center = wanted.immutable();
        AwPortalShape.carvePocket(target, center);
        AwPortalShape.buildFrame(target, center);
        AwPortalShape.activate(target, center);
        // The vanilla portal ticket keeps the arrival area live, so a traveller is never unloaded.
        target.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(center), 3, center);
        return center;
    }
}
