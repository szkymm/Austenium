/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Copper chest block for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.CopperChestBlockEntity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * CopperChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM CopperChestRenderer.java.
 *
 * com.mbb.austenium.client.CopperChestRenderer:
 *     Renders copper chests with copper textures registered to the vanilla chest atlas.
 */
@OnlyIn(Dist.CLIENT)
public class CopperChestRenderer extends ChestRenderer<CopperChestBlockEntity> {

    /**
     * Creates the CopperChestRenderer instance.
     *
     * @param context the renderer context from the block entity renderer registry
     */
    public CopperChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    protected Material getMaterial(CopperChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/copper_left"));
            case RIGHT -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/copper_right"));
            default -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/copper"));
        };
    }
}
