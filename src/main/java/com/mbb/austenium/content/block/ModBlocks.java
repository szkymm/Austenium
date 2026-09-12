/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.ModBlocks
 * TYPE: Java Source
 * DESCRIPTION: Registry of all [MBB] Austenium blocks.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * ModBlocks CLASS IS CORE PART OF [MBB] AUSTENIUM ModBlocks.java.
 *
 * com.mbb.austenium.content.block.ModBlocks:
 *     DeferredRegister holder for all [MBB] Austenium blocks.
 */
public final class ModBlocks {

    private ModBlocks() {}

    public static final DeferredRegister<Block> BLOCKS
        = DeferredRegister.create(ForgeRegistries.BLOCKS, MbbAustenium.MOD_ID);

    public static final RegistryObject<Block> COPPER_FURNACE
        = BLOCKS.register("copper_furnace", CopperFurnaceBlock::new);

    public static final RegistryObject<Block> COPPER_BLAST_FURNACE
        = BLOCKS.register("copper_blast_furnace", CopperBlastFurnaceBlock::new);

    public static final RegistryObject<Block> COPPER_SMOKER
        = BLOCKS.register("copper_smoker", CopperSmokerBlock::new);

    public static final RegistryObject<Block> COPPER_BARREL
        = BLOCKS.register("copper_barrel", CopperBarrelBlock::new);

    public static final RegistryObject<Block> COPPER_CHEST
        = BLOCKS.register("copper_chest", CopperChestBlock::new);

    public static final RegistryObject<Block> COPPER_HOPPER
        = BLOCKS.register("copper_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.COPPER));

    public static final RegistryObject<Block> IRON_HOPPER
        = BLOCKS.register("iron_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.IRON));

    public static final RegistryObject<Block> SILVER_HOPPER
        = BLOCKS.register("silver_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.SILVER));

    public static final RegistryObject<Block> GOLD_HOPPER
        = BLOCKS.register("gold_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.GOLD));

    public static final RegistryObject<Block> DIAMOND_HOPPER
        = BLOCKS.register("diamond_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.DIAMOND));

    public static final RegistryObject<Block> EMERALD_HOPPER
        = BLOCKS.register("emerald_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.EMERALD));

    public static final RegistryObject<Block> ORICHALCUM_HOPPER
        = BLOCKS.register("orichalcum_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.ORICHALCUM));

    public static final RegistryObject<Block> MYTHRIL_HOPPER
        = BLOCKS.register("mythril_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.MYTHRIL));

    public static final RegistryObject<Block> ADAMANTITE_HOPPER
        = BLOCKS.register("adamantite_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.ADAMANTITE));

    public static final RegistryObject<Block> NETHERITE_HOPPER
        = BLOCKS.register("netherite_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.NETHERITE));

    public static final RegistryObject<Block> RADIANT_HOPPER
        = BLOCKS.register("radiant_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.RADIANT));

    public static final RegistryObject<Block> AURELIANIUM_HOPPER
        = BLOCKS.register("aurelianium_hopper", () -> new TierHopperBlock(TierHopperBlock.HopperTier.AURELIANIUM));


    public static final RegistryObject<Block> IRON_FURNACE
        = BLOCKS.register("iron_furnace", IronFurnaceBlock::new);

    public static final RegistryObject<Block> IRON_BLAST_FURNACE
        = BLOCKS.register("iron_blast_furnace", IronBlastFurnaceBlock::new);

    public static final RegistryObject<Block> IRON_SMOKER
        = BLOCKS.register("iron_smoker", IronSmokerBlock::new);

    public static final RegistryObject<Block> IRON_BARREL
        = BLOCKS.register("iron_barrel", IronBarrelBlock::new);

    public static final RegistryObject<Block> IRON_CHEST
        = BLOCKS.register("iron_chest", IronChestBlock::new);

    public static final RegistryObject<Block> SILVER_ORE
        = BLOCKS.register("silver_ore", SilverOreBlock::new);

    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE
        = BLOCKS.register("deepslate_silver_ore", DeepslateSilverOreBlock::new);

    public static final RegistryObject<Block> SILVER_FURNACE
        = BLOCKS.register("silver_furnace", SilverFurnaceBlock::new);

    public static final RegistryObject<Block> SILVER_BLAST_FURNACE
        = BLOCKS.register("silver_blast_furnace", SilverBlastFurnaceBlock::new);

    public static final RegistryObject<Block> SILVER_SMOKER
        = BLOCKS.register("silver_smoker", SilverSmokerBlock::new);

    public static final RegistryObject<Block> SILVER_BARREL
        = BLOCKS.register("silver_barrel", SilverBarrelBlock::new);

    public static final RegistryObject<Block> SILVER_CHEST
        = BLOCKS.register("silver_chest", SilverChestBlock::new);

    public static final RegistryObject<Block> SILVER_BLOCK
        =
            BLOCKS.register("silver_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL).strength(2.0f, 2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RAW_SILVER_BLOCK
        =
            BLOCKS.register("raw_silver_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.METAL).strength(2.0f, 2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COPPER_CHAIN
        = BLOCKS.register("copper_chain", CopperChainBlock::new);

    public static final RegistryObject<Block> SILVER_CHAIN
        = BLOCKS.register("silver_chain", SilverChainBlock::new);

    public static final RegistryObject<Block> GOLD_FURNACE
        = BLOCKS.register("gold_furnace", GoldFurnaceBlock::new);

    public static final RegistryObject<Block> GOLD_BLAST_FURNACE
        = BLOCKS.register("gold_blast_furnace", GoldBlastFurnaceBlock::new);

    public static final RegistryObject<Block> GOLD_SMOKER
        = BLOCKS.register("gold_smoker", GoldSmokerBlock::new);

    public static final RegistryObject<Block> GOLD_BARREL
        = BLOCKS.register("gold_barrel", GoldBarrelBlock::new);

    public static final RegistryObject<Block> GOLD_CHEST
        = BLOCKS.register("gold_chest", GoldChestBlock::new);

    public static final RegistryObject<Block> GOLD_CHAIN
        = BLOCKS.register("gold_chain", GoldChainBlock::new);

    public static final RegistryObject<Block> DIAMOND_FURNACE
        = BLOCKS.register("diamond_furnace", DiamondFurnaceBlock::new);
    public static final RegistryObject<Block> DIAMOND_BLAST_FURNACE
        = BLOCKS.register("diamond_blast_furnace", DiamondBlastFurnaceBlock::new);
    public static final RegistryObject<Block> DIAMOND_SMOKER
        = BLOCKS.register("diamond_smoker", DiamondSmokerBlock::new);
    public static final RegistryObject<Block> DIAMOND_BARREL
        = BLOCKS.register("diamond_barrel", DiamondBarrelBlock::new);
    public static final RegistryObject<Block> DIAMOND_CHEST
        = BLOCKS.register("diamond_chest", DiamondChestBlock::new);

    public static final RegistryObject<Block> EMERALD_FURNACE
        = BLOCKS.register("emerald_furnace", EmeraldFurnaceBlock::new);
    public static final RegistryObject<Block> EMERALD_BLAST_FURNACE
        = BLOCKS.register("emerald_blast_furnace", EmeraldBlastFurnaceBlock::new);
    public static final RegistryObject<Block> EMERALD_SMOKER
        = BLOCKS.register("emerald_smoker", EmeraldSmokerBlock::new);
    public static final RegistryObject<Block> EMERALD_BARREL
        = BLOCKS.register("emerald_barrel", EmeraldBarrelBlock::new);
    public static final RegistryObject<Block> EMERALD_CHEST
        = BLOCKS.register("emerald_chest", EmeraldChestBlock::new);

    public static final RegistryObject<Block> ORICHALCUM_ORE
        = BLOCKS.register("orichalcum_ore", OrichalcumOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_ORICHALCUM_ORE
        = BLOCKS.register("deepslate_orichalcum_ore", DeepslateOrichalcumOreBlock::new);

    public static final RegistryObject<Block> ORICHALCUM_BLOCK
        =
            BLOCKS.register("orichalcum_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_RED).strength(2.0f,
                2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel(blockState -> 9)));
    public static final RegistryObject<Block> RAW_ORICHALCUM_BLOCK
        =
            BLOCKS.register("raw_orichalcum_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_RED).strength(2.0f,
                2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel(blockState -> 9)));

    public static final RegistryObject<Block> ORICHALCUM_FURNACE
        = BLOCKS.register("orichalcum_furnace", OrichalcumFurnaceBlock::new);
    public static final RegistryObject<Block> ORICHALCUM_BLAST_FURNACE
        = BLOCKS.register("orichalcum_blast_furnace", OrichalcumBlastFurnaceBlock::new);
    public static final RegistryObject<Block> ORICHALCUM_SMOKER
        = BLOCKS.register("orichalcum_smoker", OrichalcumSmokerBlock::new);
    public static final RegistryObject<Block> ORICHALCUM_BARREL
        = BLOCKS.register("orichalcum_barrel", OrichalcumBarrelBlock::new);
    public static final RegistryObject<Block> ORICHALCUM_CHEST
        = BLOCKS.register("orichalcum_chest", OrichalcumChestBlock::new);
    public static final RegistryObject<Block> ORICHALCUM_CHAIN
        = BLOCKS.register("orichalcum_chain", OrichalcumChainBlock::new);

    public static final RegistryObject<Block> MYTHRIL_ORE
        = BLOCKS.register("mythril_ore", MythrilOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_MYTHRIL_ORE
        = BLOCKS.register("deepslate_mythril_ore", DeepslateMythrilOreBlock::new);

    public static final RegistryObject<Block> MYTHRIL_BLOCK
        =
            BLOCKS.register("mythril_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE).strength(2.0f,
                2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel(blockState -> 8)));
    public static final RegistryObject<Block> RAW_MYTHRIL_BLOCK
        =
            BLOCKS.register("raw_mythril_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE).strength(2.0f,
                2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel(blockState -> 8)));

    public static final RegistryObject<Block> MYTHRIL_FURNACE
        = BLOCKS.register("mythril_furnace", MythrilFurnaceBlock::new);
    public static final RegistryObject<Block> MYTHRIL_BLAST_FURNACE
        = BLOCKS.register("mythril_blast_furnace", MythrilBlastFurnaceBlock::new);
    public static final RegistryObject<Block> MYTHRIL_SMOKER
        = BLOCKS.register("mythril_smoker", MythrilSmokerBlock::new);
    public static final RegistryObject<Block> MYTHRIL_BARREL
        = BLOCKS.register("mythril_barrel", MythrilBarrelBlock::new);
    public static final RegistryObject<Block> MYTHRIL_CHEST
        = BLOCKS.register("mythril_chest", MythrilChestBlock::new);
    public static final RegistryObject<Block> MYTHRIL_CHAIN
        = BLOCKS.register("mythril_chain", MythrilChainBlock::new);

    public static final RegistryObject<Block> ADAMANTITE_ORE
        = BLOCKS.register("adamantite_ore", AdamantiteOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_ADAMANTITE_ORE
        = BLOCKS.register("deepslate_adamantite_ore", DeepslateAdamantiteOreBlock::new);

    public static final RegistryObject<Block> ADAMANTITE_BLOCK
        =
            BLOCKS.register("adamantite_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN).strength(2.0f,
                2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel(blockState -> 8)));
    public static final RegistryObject<Block> RAW_ADAMANTITE_BLOCK
        =
            BLOCKS.register("raw_adamantite_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN).strength(2.0f,
                2.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel(blockState -> 8)));

    public static final RegistryObject<Block> ADAMANTITE_FURNACE
        = BLOCKS.register("adamantite_furnace", AdamantiteFurnaceBlock::new);
    public static final RegistryObject<Block> ADAMANTITE_BLAST_FURNACE
        = BLOCKS.register("adamantite_blast_furnace", AdamantiteBlastFurnaceBlock::new);
    public static final RegistryObject<Block> ADAMANTITE_SMOKER
        = BLOCKS.register("adamantite_smoker", AdamantiteSmokerBlock::new);
    public static final RegistryObject<Block> ADAMANTITE_BARREL
        = BLOCKS.register("adamantite_barrel", AdamantiteBarrelBlock::new);
    public static final RegistryObject<Block> ADAMANTITE_CHEST
        = BLOCKS.register("adamantite_chest", AdamantiteChestBlock::new);
    public static final RegistryObject<Block> ADAMANTITE_CHAIN
        = BLOCKS.register("adamantite_chain", AdamantiteChainBlock::new);

    public static final RegistryObject<Block> NETHERITE_FURNACE
        = BLOCKS.register("netherite_furnace", NetheriteFurnaceBlock::new);
    public static final RegistryObject<Block> NETHERITE_BLAST_FURNACE
        = BLOCKS.register("netherite_blast_furnace", NetheriteBlastFurnaceBlock::new);
    public static final RegistryObject<Block> NETHERITE_SMOKER
        = BLOCKS.register("netherite_smoker", NetheriteSmokerBlock::new);
    public static final RegistryObject<Block> NETHERITE_BARREL
        = BLOCKS.register("netherite_barrel", NetheriteBarrelBlock::new);
    public static final RegistryObject<Block> NETHERITE_CHEST
        = BLOCKS.register("netherite_chest", NetheriteChestBlock::new);

    public static final RegistryObject<Block> RADIANT_DEBRIS
        = BLOCKS.register("radiant_debris", RadiantDebrisBlock::new);
    public static final RegistryObject<Block> RADIANT_SCRAP_BLOCK
        =
            BLOCKS.register("radiant_scrap_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PINK).strength(2.0f,
                3.0f).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel(blockState -> 12)));
    public static final RegistryObject<Block> RADIANT_BLOCK
        =
            BLOCKS.register("radiant_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PINK).strength(2.0f,
                3.0f).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel(blockState -> 12)));

    public static final RegistryObject<Block> RADIANT_FURNACE
        = BLOCKS.register("radiant_furnace", RadiantFurnaceBlock::new);
    public static final RegistryObject<Block> RADIANT_BLAST_FURNACE
        = BLOCKS.register("radiant_blast_furnace", RadiantBlastFurnaceBlock::new);
    public static final RegistryObject<Block> RADIANT_SMOKER
        = BLOCKS.register("radiant_smoker", RadiantSmokerBlock::new);
    public static final RegistryObject<Block> RADIANT_BARREL
        = BLOCKS.register("radiant_barrel", RadiantBarrelBlock::new);
    public static final RegistryObject<Block> RADIANT_CHEST
        = BLOCKS.register("radiant_chest", RadiantChestBlock::new);

    public static final RegistryObject<Block> AURELIANIUM_DEBRIS
        = BLOCKS.register("aurelianium_debris", AurelianiumDebrisBlock::new);
    public static final RegistryObject<Block> AURELIANIUM_SCRAP_BLOCK
        =
            BLOCKS.register("aurelianium_scrap_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK).strength(2.0f,
                6.0f).sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops().lightLevel(blockState -> 15)));
    public static final RegistryObject<Block> AURELIANIUM_BLOCK
        =
            BLOCKS.register("aurelianium_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK).strength(50.0f,
                1200.0f).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel(blockState -> 15)));

    public static final RegistryObject<Block> AUSTENIUMCRAFT_PORTAL
        = BLOCKS.register("austeniumcraft_portal",
            () -> new AusteniumcraftPortalBlock(AusteniumcraftPortalBlock.createProperties()));

    public static final RegistryObject<Block> AURELIANIUM_FURNACE
        = BLOCKS.register("aurelianium_furnace", AurelianiumFurnaceBlock::new);
    public static final RegistryObject<Block> AURELIANIUM_BLAST_FURNACE
        = BLOCKS.register("aurelianium_blast_furnace", AurelianiumBlastFurnaceBlock::new);
    public static final RegistryObject<Block> AURELIANIUM_SMOKER
        = BLOCKS.register("aurelianium_smoker", AurelianiumSmokerBlock::new);
    public static final RegistryObject<Block> AURELIANIUM_BARREL
        = BLOCKS.register("aurelianium_barrel", AurelianiumBarrelBlock::new);
    public static final RegistryObject<Block> AURELIANIUM_CHEST
        = BLOCKS.register("aurelianium_chest", AurelianiumChestBlock::new);

    public static final RegistryObject<Block> NETHERITE_SCRAP_BLOCK
        =
            BLOCKS.register("netherite_scrap_block",
            () -> new Block(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK).strength(50.0f,
                1200.0f).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COPPER_SHULKER_BOX
        = BLOCKS.register("copper_shulker_box", () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.COPPER));

    public static final RegistryObject<Block> IRON_SHULKER_BOX
        = BLOCKS.register("iron_shulker_box", () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.IRON));

    public static final RegistryObject<Block> SILVER_SHULKER_BOX
        = BLOCKS.register("silver_shulker_box", () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.SILVER));

    public static final RegistryObject<Block> GOLD_SHULKER_BOX
        = BLOCKS.register("gold_shulker_box", () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.GOLD));

    public static final RegistryObject<Block> EMERALD_SHULKER_BOX
        =
            BLOCKS.register("emerald_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.EMERALD));

    public static final RegistryObject<Block> DIAMOND_SHULKER_BOX
        =
            BLOCKS.register("diamond_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.DIAMOND));

    public static final RegistryObject<Block> NETHERITE_SHULKER_BOX
        =
            BLOCKS.register("netherite_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.NETHERITE));

    public static final RegistryObject<Block> ADAMANTITE_SHULKER_BOX
        =
            BLOCKS.register("adamantite_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.ADAMANTITE));

    public static final RegistryObject<Block> MYTHRIL_SHULKER_BOX
        =
            BLOCKS.register("mythril_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.MYTHRIL));

    public static final RegistryObject<Block> ORICHALCUM_SHULKER_BOX
        =
            BLOCKS.register("orichalcum_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.ORICHALCUM));

    public static final RegistryObject<Block> RADIANT_SHULKER_BOX
        =
            BLOCKS.register("radiant_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.RADIANT));

    public static final RegistryObject<Block> AURELIANIUM_SHULKER_BOX
        =
            BLOCKS.register("aurelianium_shulker_box",
            () -> new TierShulkerBoxBlock(TierShulkerBoxBlock.ShulkerTier.AURELIANIUM));
}