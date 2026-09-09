/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.GenericChestScreen
 * TYPE: Java Source
 * DESCRIPTION: Procedurally drawn chest screen for any row count (no textures).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.content.menu.GenericChestMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * GenericChestScreen CLASS IS CORE PART OF [MBB] AUSTENIUM GenericChestScreen.java.
 *
 * com.mbb.austenium.client.GenericChestScreen:
 *     Draws the chest GUI background procedurally from the menu row count,
 *     matching the vanilla chest style for arbitrary container sizes.
 */
@OnlyIn(Dist.CLIENT)
public class GenericChestScreen extends AbstractContainerScreen<GenericChestMenu> {

    private static final String GUI_DIR = "textures/gui/container/";
    private final int containerRows;
    private final int containerCols;
    private final ResourceLocation containerTexture;
    private final int containerTextureSize;
    private final int labelColor;

    public GenericChestScreen(GenericChestMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.containerRows = menu.getRowCount();
        int size = menu.getContainer().getContainerSize();
        this.containerCols = Math.max(1, size / Math.max(1, this.containerRows));
        this.imageWidth = 14 + 18 * this.containerCols;
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        // Copper theme textures (single 9x4 / double 9x8); future materials extend this map.
        String texture = switch (this.containerCols + "x" + this.containerRows) {
            case "9x4" -> "copper_single_9x4.png";
            case "9x8" -> "copper_double_9x8.png";
            case "9x5" -> "silver_single_9x5.png";
            case "9x10" -> "silver_double_9x10.png";
            case "9x7" -> "orichalcum_single_9x7.png";
            case "9x14" -> "orichalcum_double_9x14.png";
            case "9x18" -> "aurelianium_single_9x18.png";
            default -> null;
        };
        // Padded canvas size (S = ceil(max(W,H)/50)*50) so blit can crop the transparent padding.
        int textureSize = switch (this.containerCols + "x" + this.containerRows) {
            case "9x4" -> 200;
            case "9x8" -> 300;
            case "9x5" -> 250;
            case "9x10" -> 300;
            case "9x7" -> 250;
            case "9x14" -> 400;
            case "9x18" -> 450;
            default -> 0;
        };
        this.containerTexture = texture == null ? null :
            new ResourceLocation("mbb_austenium", GUI_DIR + texture);
        this.containerTextureSize = textureSize;
        // The aurelianium panel is near black, so its labels switch to white to stay readable.
        this.labelColor = "9x18".equals(this.containerCols + "x" + this.containerRows) ? 0xFFFFFF : 0x404040;
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Vanilla draws both labels in dark grey, which disappears on a near-black panel.
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, this.labelColor, false);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY,
            this.labelColor, false);
    }

    private void drawCell(GuiGraphics graphics, int x, int y) {
        // 18x18 white outline + 16x16 grey cell, matching vanilla slot style.
        graphics.fill(x - 1, y - 1, x + 17, y + 17, 0xFFFFFFFF);
        graphics.fill(x, y, x + 16, y + 16, 0xFF8B8B8B);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        // Ensure hovered item tooltips always render (some container layouts lose them).
        if (this.hoveredSlot != null && this.hoveredSlot.hasItem() && this.menu.getCarried().isEmpty()) {
            graphics.renderTooltip(this.font, this.hoveredSlot.getItem(), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;
        int width = this.imageWidth;
        int height = this.imageHeight;

        // Packaged thematic background (exact window geometry, transparent padding cropped by blit).
        if (this.containerTexture != null) {
            // 9-arg blit with the real texture size: crops the transparent padding exactly.
            graphics.blit(this.containerTexture, x, y, 0, 0, width, height,
                this.containerTextureSize, this.containerTextureSize);
            return;
        }

        // Fallback: tile the vanilla slot band 1:1 (rows beyond 6).
        if (this.containerRows > 6) {
            ResourceLocation generated = VanillaChestGuiComposer.ensure(this.containerRows);
            if (generated != null) {
                graphics.blit(generated, x, y, 0, 0, width, height);
                return;
            }
        }

        // Light grey background and black outer frame.
        graphics.fill(x, y, x + width, y + height, 0xFFC6C6C6);
        graphics.fill(x, y, x + width, y + 1, 0xFF000000);
        graphics.fill(x, y + height - 1, x + width, y + height, 0xFF000000);
        graphics.fill(x, y, x + 1, y + height, 0xFF000000);
        graphics.fill(x + width - 1, y, x + width, y + height, 0xFF000000);

        // Container cells.
        for (int row = 0; row < this.containerRows; row++) {
            for (int column = 0; column < 9; column++) {
                this.drawCell(graphics, x + 8 + column * 18, y + 18 + row * 18);
            }
        }

        // No divider is drawn here: this fallback cannot tell a single chest from a paired one.

        // Player inventory (matches ChestMenu slot formula).
        int startY = y + 103 + (this.containerRows - 4) * 18;
        graphics.fill(x, startY - 8, x + width, startY - 6, 0xFF000000);
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.drawCell(graphics, x + 8 + column * 18, startY + row * 18);
            }
        }
        int hotbarY = y + 161 + (this.containerRows - 4) * 18;
        for (int column = 0; column < 9; column++) {
            this.drawCell(graphics, x + 8 + column * 18, hotbarY);
        }
    }
}