/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.GoldChestRenderer
 * TYPE: Java Source
 * DESCRIPTION: Gold chest 3D renderer with gold textures.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.GoldChestBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * GoldChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM GoldChestRenderer.java.
 *
 * com.mbb.austenium.client.GoldChestRenderer:
 *     Renders the gold chest with the gold entity texture set.
 */
@OnlyIn(Dist.CLIENT)
public class GoldChestRenderer extends ChestRenderer<GoldChestBlockEntity> {

    public GoldChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(GoldChestBlockEntity blockEntity, ChestType chestType) {
        String path = switch (chestType) {
            case LEFT -> "entity/chest/gold_left";
            case RIGHT -> "entity/chest/gold_right";
            default -> "entity/chest/gold";
        };
        return new Material(net.minecraft.client.renderer.Sheets.CHEST_SHEET,
            new ResourceLocation(MbbAustenium.MOD_ID, path));
    }
}