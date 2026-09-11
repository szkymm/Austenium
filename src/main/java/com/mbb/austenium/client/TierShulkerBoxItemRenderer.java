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

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * TierShulkerBoxItemRenderer CLASS IS CORE PART OF [MBB] AUSTENIUM TierShulkerBoxItemRenderer.java.
 *
 * com.mbb.austenium.client.TierShulkerBoxItemRenderer:
 *     Renders the tier shulker box model for the item form, closed lid, using the
 *     same geometry and texture as the placed block.
 */
@OnlyIn(Dist.CLIENT)
public class TierShulkerBoxItemRenderer extends BlockEntityWithoutLevelRenderer {

    private static final float SCALE = 0.9995f;

    private final ShulkerModel<?> model;

    /**
     * Creates the TierShulkerBoxItemRenderer instance.
     */
    public TierShulkerBoxItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.model = new ShulkerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SHULKER));
    }

    /** {@inheritDoc} */
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack pose,
            MultiBufferSource buffers, int light, int overlay) {
        Block block = Block.byItem(stack.getItem());
        String tierId = block instanceof TierShulkerBoxBlock tierBlock
            ? tierBlock.getTier().id()
            : "copper";
        Material material = new Material(Sheets.SHULKER_SHEET,
            ResourceLocation.fromNamespaceAndPath(MbbAustenium.MOD_ID, "entity/shulker/shulker_" + tierId));

        pose.pushPose();
        pose.translate(0.5f, 0.5f, 0.5f);
        pose.scale(SCALE, SCALE, SCALE);
        pose.mulPose(Direction.UP.getRotation());
        pose.scale(1.0f, -1.0f, -1.0f);
        pose.translate(0.0f, -1.0f, 0.0f);

        this.model.getLid().setPos(0.0f, 24.0f, 0.0f);
        this.model.getLid().yRot = 0.0f;

        VertexConsumer consumer = material.buffer(buffers, location -> Sheets.shulkerBoxSheet());
        this.model.renderToBuffer(pose, consumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
        pose.popPose();
    }
}
