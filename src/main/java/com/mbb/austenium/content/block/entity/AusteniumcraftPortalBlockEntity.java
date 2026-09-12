/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AusteniumcraftPortalBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Block entity that lets the End portal renderer draw the AW portal plane.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * AUSTENIUMCRAFTPORTALBLOCKENTITY CLASS IS CORE PART OF [MBB] AUSTENIUM AusteniumcraftPortalBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.AusteniumcraftPortalBlockEntity:
 *     Extends the End portal block entity so the vanilla starfield renderer can draw the
 *     Austeniumcraft portal plane without a second renderer implementation.
 */
public class AusteniumcraftPortalBlockEntity extends TheEndPortalBlockEntity {

    /**
     * Creates the portal plane block entity.
     *
     * @param pos the position of the plane cell
     * @param state the block state of the plane cell
     */
    public AusteniumcraftPortalBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
}
