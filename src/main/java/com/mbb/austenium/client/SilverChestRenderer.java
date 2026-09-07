/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */

package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.SilverChestBlockEntity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SilverChestRenderer extends ChestRenderer<SilverChestBlockEntity> {

    public SilverChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Material getMaterial(SilverChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> new Material(Sheets.CHEST_SHEET,
                new ResourceLocation(MbbAustenium.MOD_ID, "entity/chest/silver_left"));
            case RIGHT -> new Material(Sheets.CHEST_SHEET,
                new ResourceLocation(MbbAustenium.MOD_ID, "entity/chest/silver_right"));
            default -> new Material(Sheets.CHEST_SHEET,
                new ResourceLocation(MbbAustenium.MOD_ID, "entity/chest/silver"));
        };
    }
}
