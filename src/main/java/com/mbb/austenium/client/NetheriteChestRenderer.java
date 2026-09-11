/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.entity.NetheriteChestBlockEntity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * NetheriteChestRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM NetheriteChestRenderer.java.
 *
 * com.mbb.austenium.client.NetheriteChestRenderer:
 *     Renders the netherite chest with the vanilla chest model and the netherite texture.
 */
@OnlyIn(Dist.CLIENT)
public class NetheriteChestRenderer extends ChestRenderer<NetheriteChestBlockEntity> {

    /**
     * Creates the NetheriteChestRenderer instance.
     *
     * @param context the renderer context from the block entity renderer registry
     */
    public NetheriteChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /** {@inheritDoc} */
    @Override
    protected Material getMaterial(NetheriteChestBlockEntity blockEntity, ChestType chestType) {
        return switch (chestType) {
            case LEFT -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/netherite_left"));
            case RIGHT -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/netherite_right"));
            default -> new Material(Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/chest/netherite"));
        };
    }
}