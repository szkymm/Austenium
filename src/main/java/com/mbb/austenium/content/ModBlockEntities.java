/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModBlockEntities
 * TYPE: Java Source
 * DESCRIPTION: Registry of all [MBB] Austenium block entity types.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;
import com.mbb.austenium.content.block.entity.CopperBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.CopperBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.CopperChestBlockEntity;
import com.mbb.austenium.content.block.entity.CopperFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.CopperSmokerBlockEntity;

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
}
