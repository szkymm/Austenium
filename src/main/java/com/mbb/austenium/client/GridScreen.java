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

/**
 * GridScreen CLASS IS CORE PART OF [MBB] AUSTENIUM GridScreen.java.
 *
 * com.mbb.austenium.client.GridScreen:
 *     Screen for the wide tier chests (more than nine columns); centres the player slots.
 */
@OnlyIn(Dist.CLIENT)
public class GridScreen extends AbstractContainerScreen<GridMenu> {

    private static final String GUI_DIR = "textures/gui/container/";
    private final int containerRows;
    private final int containerCols;
    private final ResourceLocation containerTexture;
    private final int containerTextureSize;
    private final int labelColor;

    /**
     * Creates the GridScreen instance.
     *
     * @param menu the container menu
     * @param inventory the player inventory
     * @param title the title argument
     */
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
            case "10x5" -> "diamond_single_10x5.png";
            case "10x10" -> "diamond_double_10x10.png";
            case "12x5" -> "emerald_single_12x5.png";
            case "12x10" -> "emerald_double_12x10.png";
            case "14x5" -> "mythril_single_14x5.png";
            case "14x10" -> "mythril_double_14x10.png";
            case "15x5" -> "adamantite_single_15x5.png";
            case "15x10" -> "adamantite_double_15x10.png";
            case "15x7" -> "netherite_single_15x7.png";
            case "15x14" -> "netherite_double_15x14.png";
            case "15x9" -> "radiant_single_15x9.png";
            case "15x18" -> "radiant_double_15x18.png";
            case "18x18" -> "aurelianium_double_18x18.png";
            default -> null;
        };
        int textureSize = switch (this.containerCols + "x" + this.containerRows) {
            case "10x4" -> 200;
            case "10x8" -> 300;
            case "12x4" -> 250;
            case "12x8" -> 300;
            case "10x5" -> 250;
            case "10x10" -> 300;
            case "12x5" -> 250;
            case "12x10" -> 300;
            case "14x5" -> 300;
            case "14x10" -> 300;
            case "15x5" -> 300;
            case "15x10" -> 300;
            case "15x7" -> 300;
            case "15x14" -> 400;
            case "15x9" -> 300;
            case "15x18" -> 450;
            case "18x18" -> 500;
            default -> 0;
        };
        this.containerTexture =
            texture == null ? null : ResourceLocation.fromNamespaceAndPath("mbb_austenium", GUI_DIR + texture);
        this.containerTextureSize = textureSize;
        // The aurelianium panel is near black, so its labels switch to white to stay readable.
        this.labelColor = "18x18".equals(this.containerCols + "x" + this.containerRows) ? 0xFFFFFF : 0x404040;
    }

    /** {@inheritDoc} */
    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        // Vanilla draws both labels in dark grey, which disappears on a near-black panel.
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, this.labelColor, false);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY,
            this.labelColor, false);
    }

    /** {@inheritDoc} */
    @Override
    public void onClose() {
        OpenedChestTracker.close(this.minecraft.player);
        super.onClose();
    }

    /** {@inheritDoc} */
    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
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