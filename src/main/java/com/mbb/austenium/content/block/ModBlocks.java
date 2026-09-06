/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.ModBlocks
 * TYPE: Java Source
 * DESCRIPTION: Registry of all [MBB] Austenium blocks.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 */
package com.mbb.austenium.content.block;

import com.mbb.austenium.MbbAustenium;

import net.minecraft.world.level.block.Block;
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
}