/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModBlockEntities
 * TYPE: Java Source
 * DESCRIPTION: Registry of all [MBB] Austenium block entity types.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.content.block.entity.CopperBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.CopperBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.CopperChestBlockEntity;
import com.mbb.austenium.content.block.entity.CopperFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.CopperSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.IronBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.IronBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.IronChestBlockEntity;
import com.mbb.austenium.content.block.entity.IronFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.IronSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.SilverBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.SilverBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.SilverChestBlockEntity;
import com.mbb.austenium.content.block.entity.SilverFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.SilverSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.GoldBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.GoldBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.GoldChestBlockEntity;
import com.mbb.austenium.content.block.entity.GoldFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.GoldSmokerBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * ModBlockEntities CLASS IS CORE PART OF [MBB] AUSTENIUM ModBlockEntities.java.
 *
 * com.mbb.austenium.content.ModBlockEntities:
 *     DeferredRegister holder for all [MBB] Austenium block entity types.
 */
public final class ModBlockEntities {

    private ModBlockEntities() {}

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES
        = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MbbAustenium.MOD_ID);

    public static final RegistryObject<BlockEntityType<?>> COPPER_FURNACE
        = BLOCK_ENTITIES.register("copper_furnace",
            () -> BlockEntityType.Builder.of(CopperFurnaceBlockEntity::new, ModBlocks.COPPER_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> COPPER_BLAST_FURNACE
        = BLOCK_ENTITIES.register("copper_blast_furnace",
            () -> BlockEntityType.Builder.of(CopperBlastFurnaceBlockEntity::new, ModBlocks.COPPER_BLAST_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> COPPER_SMOKER
        = BLOCK_ENTITIES.register("copper_smoker",
            () -> BlockEntityType.Builder.of(CopperSmokerBlockEntity::new, ModBlocks.COPPER_SMOKER.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> COPPER_BARREL
        = BLOCK_ENTITIES.register("copper_barrel",
            () -> BlockEntityType.Builder.of(CopperBarrelBlockEntity::new, ModBlocks.COPPER_BARREL.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> COPPER_CHEST
        = BLOCK_ENTITIES.register("copper_chest",
            () -> BlockEntityType.Builder.of(CopperChestBlockEntity::new, ModBlocks.COPPER_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> IRON_FURNACE
        = BLOCK_ENTITIES.register("iron_furnace",
            () -> BlockEntityType.Builder.of(IronFurnaceBlockEntity::new, ModBlocks.IRON_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> IRON_BLAST_FURNACE
        = BLOCK_ENTITIES.register("iron_blast_furnace",
            () -> BlockEntityType.Builder.of(IronBlastFurnaceBlockEntity::new, ModBlocks.IRON_BLAST_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> IRON_SMOKER
        = BLOCK_ENTITIES.register("iron_smoker",
            () -> BlockEntityType.Builder.of(IronSmokerBlockEntity::new, ModBlocks.IRON_SMOKER.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> IRON_BARREL
        = BLOCK_ENTITIES.register("iron_barrel",
            () -> BlockEntityType.Builder.of(IronBarrelBlockEntity::new, ModBlocks.IRON_BARREL.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> IRON_CHEST
        = BLOCK_ENTITIES.register("iron_chest",
            () -> BlockEntityType.Builder.of(IronChestBlockEntity::new, ModBlocks.IRON_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> SILVER_FURNACE
        = BLOCK_ENTITIES.register("silver_furnace",
            () -> BlockEntityType.Builder.of(SilverFurnaceBlockEntity::new, ModBlocks.SILVER_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> SILVER_BLAST_FURNACE
        = BLOCK_ENTITIES.register("silver_blast_furnace",
            () -> BlockEntityType.Builder.of(SilverBlastFurnaceBlockEntity::new, ModBlocks.SILVER_BLAST_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> SILVER_SMOKER
        = BLOCK_ENTITIES.register("silver_smoker",
            () -> BlockEntityType.Builder.of(SilverSmokerBlockEntity::new, ModBlocks.SILVER_SMOKER.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> SILVER_BARREL
        = BLOCK_ENTITIES.register("silver_barrel",
            () -> BlockEntityType.Builder.of(SilverBarrelBlockEntity::new, ModBlocks.SILVER_BARREL.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> SILVER_CHEST
        = BLOCK_ENTITIES.register("silver_chest",
            () -> BlockEntityType.Builder.of(SilverChestBlockEntity::new, ModBlocks.SILVER_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> GOLD_FURNACE
        = BLOCK_ENTITIES.register("gold_furnace",
            () -> BlockEntityType.Builder.of(GoldFurnaceBlockEntity::new, ModBlocks.GOLD_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> GOLD_BLAST_FURNACE
        = BLOCK_ENTITIES.register("gold_blast_furnace",
            () -> BlockEntityType.Builder.of(GoldBlastFurnaceBlockEntity::new, ModBlocks.GOLD_BLAST_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> GOLD_SMOKER
        = BLOCK_ENTITIES.register("gold_smoker",
            () -> BlockEntityType.Builder.of(GoldSmokerBlockEntity::new, ModBlocks.GOLD_SMOKER.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> GOLD_BARREL
        = BLOCK_ENTITIES.register("gold_barrel",
            () -> BlockEntityType.Builder.of(GoldBarrelBlockEntity::new, ModBlocks.GOLD_BARREL.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> GOLD_CHEST
        = BLOCK_ENTITIES.register("gold_chest",
            () -> BlockEntityType.Builder.of(GoldChestBlockEntity::new, ModBlocks.GOLD_CHEST.get()).build(null));
}