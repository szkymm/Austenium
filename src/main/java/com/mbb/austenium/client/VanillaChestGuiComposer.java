/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.VanillaChestGuiComposer
 * TYPE: Java Source
 * DESCRIPTION: Builds N-row chest GUI backgrounds from vanilla generic_54 pixels.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mojang.blaze3d.platform.NativeImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * VanillaChestGuiComposer CLASS IS CORE PART OF [MBB] AUSTENIUM VanillaChestGuiComposer.java.
 *
 * com.mbb.austenium.client.VanillaChestGuiComposer:
 *     Copies vanilla generic_54 pixels 1:1 and tiles the container rows to build a
 *     background for any row count, so the grid always matches the vanilla look.
 */
@OnlyIn(Dist.CLIENT)
public final class VanillaChestGuiComposer {

    private static final int WIDTH = 176;
    private static final ResourceLocation TEXTURE_SOURCE =
        ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/generic_54.png");

    private static final Map<Integer, ResourceLocation> CACHE = new HashMap<>();

    private VanillaChestGuiComposer() {}

    /**
     * Returns the themed container texture for the given row count, composing it on first use.
     *
     * @param rows the container row count
     * @return the location of the composed texture
     */
    public static ResourceLocation ensure(int rows) {
        ResourceLocation cached = CACHE.get(rows);
        if (cached != null) {
            return cached;
        }
        int height = 114 + rows * 18;
        NativeImage source;
        try (InputStream stream = Minecraft.getInstance().getResourceManager().open(TEXTURE_SOURCE)) {
            source = NativeImage.read(stream);
        } catch (Throwable error) {
            return null;
        }

        NativeImage out = new NativeImage(WIDTH, height, false);
        // background color sampled from the vanilla panel (2,2).
        int background = source.getPixelRGBA(2, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < WIDTH; x++) {
                out.setPixelRGBA(x, y, background);
            }
        }

        // 1) rows 0-5: copy the vanilla container section verbatim (its baked grid).
        copyRegion(source, out, 0, 0, WIDTH, 132, 0, 0);

        // 2) rows 6+: tile the 18px row band copied from the last vanilla container row.
        //    vanilla rows: 0..5 at y=16..124 ; row band used = y=106..124 (row 5).
        if (rows > 6) {
            for (int row = 6; row < rows; row++) {
                int y = 16 + row * 18;
                copyRegion(source, out, 0, 106, WIDTH, 124, 0, y);
            }
            // container panel bottom border copied from vanilla (y=124..132).
            int panelBottomY = 16 + rows * 18;
            copyRegion(source, out, 0, 124, WIDTH, 132, 0, panelBottomY - 8);
            // black separator above the player panel (vanilla y=132..138).
            copyRegion(source, out, 0, 132, WIDTH, 138, 0, panelBottomY);
        }

        // 3) player panel: vanilla player cells start at source y~139; the menu puts
        //    them at 103+(rows-4)*18, so paste the panel so its cells land exactly there.
        int playerStartY = 103 + (rows - 4) * 18;
        int playerDest = Math.max(0, playerStartY - 1);
        copyRegion(source, out, 0, 138, WIDTH, 222, 0, playerDest);

        ResourceLocation location =
            ResourceLocation.fromNamespaceAndPath("mbb_austenium", "generated/gui/chest_" + rows);
        Minecraft.getInstance().getTextureManager().register(location, new DynamicTexture(out));
        CACHE.put(rows, location);
        return location;
    }

    private static void copyRegion(NativeImage source, NativeImage target,
                                   int srcX, int srcY, int srcW, int srcH,
                                   int dstX, int dstY) {
        for (int y = 0; y < srcH - srcY; y++) {
            int ty = dstY + y;
            if (ty < 0 || ty >= target.getHeight()) {
                continue;
            }
            for (int x = 0; x < srcW - srcX; x++) {
                int tx = dstX + x;
                if (tx < 0 || tx >= target.getWidth()) {
                    continue;
                }
                target.setPixelRGBA(tx, ty, source.getPixelRGBA(srcX + x, srcY + y));
            }
        }
    }
}