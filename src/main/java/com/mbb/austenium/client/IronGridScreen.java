/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.IronGridScreen
 * TYPE: Java Source
 * DESCRIPTION: Screen for the iron grid container menus (10 columns).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.content.menu.IronGridMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * IronGridScreen CLASS IS CORE PART OF [MBB] AUSTENIUM IronGridScreen.java.
 *
 * com.mbb.austenium.client.IronGridScreen:
 *     Draws the themed 10-column iron container background.
 */
@OnlyIn(Dist.CLIENT)
public class IronGridScreen extends AbstractContainerScreen<IronGridMenu> {

    private static final String GUI_DIR = "textures/gui/container/";
    private final int containerRows;
    private final int containerCols;
    private final ResourceLocation containerTexture;
    private final int containerTextureSize;

    /**
     * Creates the IronGridScreen instance.
     *
     * @param menu the container menu
     * @param inventory the player inventory
     * @param title the title argument
     */
    public IronGridScreen(IronGridMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.containerRows = menu.getRowCount();
        this.containerCols = menu.getColumnCount();
        this.imageWidth = 14 + 18 * this.containerCols;
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        String texture = switch (this.containerCols + "x" + this.containerRows) {
            case "10x4" -> "iron_single_10x4.png";
            case "10x8" -> "iron_double_10x8.png";
            default -> null;
        };
        int textureSize = switch (this.containerCols + "x" + this.containerRows) {
            case "10x4" -> 200;
            case "10x8" -> 300;
            default -> 0;
        };
        this.containerTexture = texture == null ? null :
            ResourceLocation.fromNamespaceAndPath("mbb_austenium", GUI_DIR + texture);
        this.containerTextureSize = textureSize;
    }

    /** {@inheritDoc} */
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        // Ensure hovered item tooltips always render (some container layouts lose them).
        if (this.hoveredSlot != null && this.hoveredSlot.hasItem() && this.menu.getCarried().isEmpty()) {
            graphics.renderTooltip(this.font, this.hoveredSlot.getItem(), mouseX, mouseY);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        if (this.containerTexture != null) {
            graphics.blit(this.containerTexture, this.leftPos, this.topPos, 0, 0,
                this.imageWidth, this.imageHeight, this.containerTextureSize, this.containerTextureSize);
            return;
        }
        graphics.fill(this.leftPos, this.topPos,
            this.leftPos + this.imageWidth, this.topPos + this.imageHeight, 0xFFC6C6C6);
    }
}