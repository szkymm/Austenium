/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.IronChestRenderer
 * TYPE: Java Source
 * DESCRIPTION: Iron chest 3D renderer with iron textures.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.IronChestBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * IronChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM IronChestRenderer.java.
 *
 * com.mbb.austenium.client.IronChestRenderer:
 *     Renders the iron chest with the iron entity texture set.
 */
@OnlyIn(Dist.CLIENT)
public class IronChestRenderer extends ChestRenderer<IronChestBlockEntity> {

    public IronChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(IronChestBlockEntity blockEntity, ChestType chestType) {
        String path = switch (chestType) {
            case LEFT -> "entity/chest/iron_left";
            case RIGHT -> "entity/chest/iron_right";
            default -> "entity/chest/iron";
        };
        return new Material(net.minecraft.client.renderer.Sheets.CHEST_SHEET,
            new ResourceLocation(MbbAustenium.MOD_ID, path));
    }
}