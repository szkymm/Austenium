/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Tier shulker box content for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.TierShulkerBoxBlock;
import com.mbb.austenium.content.block.entity.TierShulkerBoxBlockEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * TierShulkerBoxRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM TierShulkerBoxRenderer.java.
 *
 * com.mbb.austenium.client.TierShulkerBoxRenderer:
 *     Draws the vanilla shulker box geometry with the tier texture and the vanilla
 *     lid opening animation.
 */
@OnlyIn(Dist.CLIENT)
public class TierShulkerBoxRenderer implements BlockEntityRenderer<TierShulkerBoxBlockEntity> {

    private static final float SCALE = 0.9995f;


    private final ShulkerModel<?> model;

    /**
     * Creates the TierShulkerBoxRenderer instance.
     *
     * @param context the renderer context from the block entity renderer registry
     */
    public TierShulkerBoxRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new ShulkerModel<>(context.bakeLayer(ModelLayers.SHULKER));
    }

    /** {@inheritDoc} */
    @Override
    public void render(TierShulkerBoxBlockEntity blockEntity, float partialTick,
        PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        BlockState state = blockEntity.getBlockState();
        Direction facing = state.getBlock() instanceof TierShulkerBoxBlock
            ? state.getValue(TierShulkerBoxBlock.FACING)
            : Direction.UP;
        String tierId = state.getBlock() instanceof TierShulkerBoxBlock tierBlock
            ? tierBlock.getTier().id()
            : "copper";
        // Vanilla shulker boxes are stitched into their own atlas (Sheets.SHULKER_SHEET) under the
        // entity path and drawn with the matching shulker render type. Reading them from the block
        // atlas with entityCutoutNoCull made the model sample whatever sat at those atlas
        // coordinates, which showed up as a black box or as neighbouring sprites' colours.
        Material material = new Material(Sheets.SHULKER_SHEET,
            ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/shulker/shulker_" + tierId));

        float progress = blockEntity.getProgress(partialTick);

        pose.pushPose();
        pose.translate(0.5f, 0.5f, 0.5f);
        pose.scale(SCALE, SCALE, SCALE);
        pose.mulPose(facing.getRotation());
        pose.scale(1.0f, -1.0f, -1.0f);
        pose.translate(0.0f, -1.0f, 0.0f);

        ModelPart lid = this.model.getLid();
        lid.setPos(0.0f, 24.0f - progress * 0.5f * 16.0f, 0.0f);
        lid.yRot = 270.0f * progress * ((float) Math.PI / 180.0f);

        VertexConsumer consumer = material.buffer(buffers, location -> Sheets.shulkerBoxSheet());
        this.model.renderToBuffer(pose, consumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
        pose.popPose();
    }
}
