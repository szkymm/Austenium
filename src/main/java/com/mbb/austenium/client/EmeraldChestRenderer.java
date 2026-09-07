/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.EmeraldChestRenderer
 * TYPE: Java Source
 * DESCRIPTION: Emerald chest 3D renderer with emerald textures.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.EmeraldChestBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * EmeraldChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM EmeraldChestRenderer.java.
 *
 * com.mbb.austenium.client.EmeraldChestRenderer:
 *     Renders the emerald chest with the emerald entity texture set.
 */
@OnlyIn(Dist.CLIENT)
public class EmeraldChestRenderer extends ChestRenderer<EmeraldChestBlockEntity> {

    public EmeraldChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(EmeraldChestBlockEntity blockEntity, ChestType chestType) {
        String path = switch (chestType) {
            case LEFT -> "entity/chest/emerald_left";
            case RIGHT -> "entity/chest/emerald_right";
            default -> "entity/chest/emerald";
        };
        return new Material(net.minecraft.client.renderer.Sheets.CHEST_SHEET,
            new ResourceLocation(MbbAustenium.MOD_ID, path));
    }
}