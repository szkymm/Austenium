/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.ClientSetup
 * TYPE: Java Source
 * DESCRIPTION: Registers client-side renderers and screens for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.CopperChestBlockEntity;
import com.mbb.austenium.content.menu.GenericChestMenu;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * ClientSetup CLASS IS CORE PART OF [MBB] AUSTENIUM ClientSetup.java.
 *
 * com.mbb.austenium.client.ClientSetup:
 *     Registers the generic chest screen and the copper chest renderer.
 */
@Mod.EventBusSubscriber(modid = MbbAustenium.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ClientSetup {

    private ClientSetup() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        @SuppressWarnings("unchecked")
        BlockEntityType<CopperChestBlockEntity> type = (BlockEntityType<CopperChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.COPPER_CHEST.get();
        event.registerBlockEntityRenderer(type, CopperChestRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (int rows = 1; rows <= ModMenuTypes.GENERIC_MAX_ROWS; rows++) {
                @SuppressWarnings("unchecked")
                MenuType<GenericChestMenu> menuType =
                    (MenuType<GenericChestMenu>) (MenuType<?>) ModMenuTypes.GENERIC_CHEST.get(rows).get();
                MenuScreens.register(menuType, GenericChestScreen::new);
            }
        });
    }
}
