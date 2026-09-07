/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.DiamondChestRenderer
 * TYPE: Java Source
 * DESCRIPTION: Diamond chest 3D renderer with gold textures.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.DiamondChestBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * DiamondChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM DiamondChestRenderer.java.
 *
 * com.mbb.austenium.client.DiamondChestRenderer:
 *     Renders the diamond chest with the diamond entity texture set.
 */
@OnlyIn(Dist.CLIENT)
public class DiamondChestRenderer extends ChestRenderer<DiamondChestBlockEntity> {

    public DiamondChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(DiamondChestBlockEntity blockEntity, ChestType chestType) {
        String path = switch (chestType) {
            case LEFT -> "entity/chest/diamond_left";
            case RIGHT -> "entity/chest/diamond_right";
            default -> "entity/chest/diamond";
        };
        return new Material(net.minecraft.client.renderer.Sheets.CHEST_SHEET,
            new ResourceLocation(MbbAustenium.MOD_ID, path));
    }
}