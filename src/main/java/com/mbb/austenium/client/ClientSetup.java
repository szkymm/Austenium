/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.client.ClientSetup
 * TYPE: Java Source
 * DESCRIPTION: Registers client-side renderers and screens for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.client;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.block.entity.CopperChestBlockEntity;
import com.mbb.austenium.content.block.entity.IronChestBlockEntity;
import com.mbb.austenium.content.block.entity.SilverChestBlockEntity;
import com.mbb.austenium.content.block.entity.GoldChestBlockEntity;
import com.mbb.austenium.content.block.entity.DiamondChestBlockEntity;
import com.mbb.austenium.content.block.entity.EmeraldChestBlockEntity;
import com.mbb.austenium.content.block.entity.OrichalcumChestBlockEntity;
import com.mbb.austenium.content.block.entity.MythrilChestBlockEntity;
import com.mbb.austenium.content.block.entity.AdamantiteChestBlockEntity;
import com.mbb.austenium.content.menu.GenericChestMenu;
import com.mbb.austenium.content.menu.GridMenu;
import com.mbb.austenium.content.menu.IronGridMenu;

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

        @SuppressWarnings("unchecked")
        BlockEntityType<IronChestBlockEntity> ironType = (BlockEntityType<IronChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.IRON_CHEST.get();
        event.registerBlockEntityRenderer(ironType, IronChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<SilverChestBlockEntity> silverType = (BlockEntityType<SilverChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.SILVER_CHEST.get();
        event.registerBlockEntityRenderer(silverType, SilverChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<GoldChestBlockEntity> goldType = (BlockEntityType<GoldChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.GOLD_CHEST.get();
        event.registerBlockEntityRenderer(goldType, GoldChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<DiamondChestBlockEntity> diamondType = (BlockEntityType<DiamondChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.DIAMOND_CHEST.get();
        event.registerBlockEntityRenderer(diamondType, DiamondChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<EmeraldChestBlockEntity> emeraldType = (BlockEntityType<EmeraldChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.EMERALD_CHEST.get();
        event.registerBlockEntityRenderer(emeraldType, EmeraldChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<OrichalcumChestBlockEntity> orichalcumType = (BlockEntityType<OrichalcumChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.ORICHALCUM_CHEST.get();
        event.registerBlockEntityRenderer(orichalcumType, OrichalcumChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<MythrilChestBlockEntity> mythrilType = (BlockEntityType<MythrilChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.MYTHRIL_CHEST.get();
        event.registerBlockEntityRenderer(mythrilType, MythrilChestRenderer::new);

        @SuppressWarnings("unchecked")
        BlockEntityType<AdamantiteChestBlockEntity> adamantiteType = (BlockEntityType<AdamantiteChestBlockEntity>) (BlockEntityType<?>) ModBlockEntities.ADAMANTITE_CHEST.get();
        event.registerBlockEntityRenderer(adamantiteType, AdamantiteChestRenderer::new);
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

            @SuppressWarnings("unchecked")
            MenuType<IronGridMenu> iron4 = (MenuType<IronGridMenu>) (MenuType<?>) ModMenuTypes.IRON_10X4.get();
            MenuScreens.register(iron4, IronGridScreen::new);
            @SuppressWarnings("unchecked")
            MenuType<IronGridMenu> iron8 = (MenuType<IronGridMenu>) (MenuType<?>) ModMenuTypes.IRON_10X8.get();
            MenuScreens.register(iron8, IronGridScreen::new);

            @SuppressWarnings("unchecked")
            MenuType<GridMenu> gold4 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.GOLD_12X4.get();
            MenuScreens.register(gold4, GridScreen::new);
            @SuppressWarnings("unchecked")
            MenuType<GridMenu> gold8 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.GOLD_12X8.get();
            MenuScreens.register(gold8, GridScreen::new);

            @SuppressWarnings("unchecked")
            MenuType<GridMenu> diamond5 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.DIAMOND_10X5.get();
            MenuScreens.register(diamond5, GridScreen::new);
            @SuppressWarnings("unchecked")
            MenuType<GridMenu> diamond10 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.DIAMOND_10X10.get();
            MenuScreens.register(diamond10, GridScreen::new);

            @SuppressWarnings("unchecked")
            MenuType<GridMenu> emerald5 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.EMERALD_12X5.get();
            MenuScreens.register(emerald5, GridScreen::new);
            @SuppressWarnings("unchecked")
            MenuType<GridMenu> emerald10 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.EMERALD_12X10.get();
            MenuScreens.register(emerald10, GridScreen::new);

            @SuppressWarnings("unchecked")
            MenuType<GridMenu> mythril5 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.MYTHRIL_14X5.get();
            MenuScreens.register(mythril5, GridScreen::new);
            @SuppressWarnings("unchecked")
            MenuType<GridMenu> mythril10 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.MYTHRIL_14X10.get();
            MenuScreens.register(mythril10, GridScreen::new);

            @SuppressWarnings("unchecked")
            MenuType<GridMenu> adamantite5 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.ADAMANTITE_15X5.get();
            MenuScreens.register(adamantite5, GridScreen::new);
            @SuppressWarnings("unchecked")
            MenuType<GridMenu> adamantite10 = (MenuType<GridMenu>) (MenuType<?>) ModMenuTypes.ADAMANTITE_15X10.get();
            MenuScreens.register(adamantite10, GridScreen::new);
        });
    }
}