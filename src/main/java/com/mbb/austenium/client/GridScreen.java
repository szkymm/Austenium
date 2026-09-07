/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.client;

import com.mbb.austenium.content.menu.GridMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GridScreen extends AbstractContainerScreen<GridMenu> {

    private static final String GUI_DIR = "textures/gui/container/";
    private final int containerRows;
    private final int containerCols;
    private final ResourceLocation containerTexture;
    private final int containerTextureSize;

    public GridScreen(GridMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.containerRows = menu.getRowCount();
        this.containerCols = menu.getColumnCount();
        this.imageWidth = 14 + 18 * this.containerCols;
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        String texture = switch (this.containerCols + "x" + this.containerRows) {
            case "10x4" -> "iron_single_10x4.png";
            case "10x8" -> "iron_double_10x8.png";
            case "12x4" -> "gold_single_12x4.png";
            case "12x8" -> "gold_double_12x8.png";
            default -> null;
        };
        int textureSize = switch (this.containerCols + "x" + this.containerRows) {
            case "10x4" -> 200;
            case "10x8" -> 300;
            case "12x4" -> 250;
            case "12x8" -> 300;
            default -> 0;
        };
        this.containerTexture = texture == null ? null : new ResourceLocation("mbb_austenium", GUI_DIR + texture);
        this.containerTextureSize = textureSize;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        if (this.hoveredSlot != null && this.hoveredSlot.hasItem() && this.menu.getCarried().isEmpty()) {
            graphics.renderTooltip(this.font, this.hoveredSlot.getItem(), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        if (this.containerTexture != null) {
            graphics.blit(this.containerTexture, this.leftPos, this.topPos, 0, 0,
                this.imageWidth, this.imageHeight, this.containerTextureSize, this.containerTextureSize);
            return;
        }
        graphics.fill(this.leftPos, this.topPos, this.leftPos + this.imageWidth, this.topPos + this.imageHeight, 0xFFC6C6C6);
    }
}
