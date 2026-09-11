/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.OrichalcumChestBlockEntity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * OrichalcumChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumChestRenderer.java.
 *
 * com.mbb.austenium.client.OrichalcumChestRenderer:
 *     Renders the orichalcum chest with the vanilla chest model and the orichalcum texture.
 */
@OnlyIn(Dist.CLIENT)
public class OrichalcumChestRenderer extends ChestRenderer<OrichalcumChestBlockEntity> {

    /**
     * Creates the OrichalcumChestRenderer instance.
     *
     * @param context the renderer context from the block entity renderer registry
     */
    public OrichalcumChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    protected Material getMaterial(OrichalcumChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/orichalcum_left"));
            case RIGHT -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/orichalcum_right"));
            default -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/orichalcum"));
        };
    }
}
