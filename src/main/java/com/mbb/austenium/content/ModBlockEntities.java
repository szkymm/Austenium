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
import com.mbb.austenium.content.block.entity.DiamondBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.DiamondBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.DiamondChestBlockEntity;
import com.mbb.austenium.content.block.entity.DiamondFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.DiamondSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.EmeraldBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.EmeraldBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.EmeraldChestBlockEntity;
import com.mbb.austenium.content.block.entity.EmeraldFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.EmeraldSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.OrichalcumBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.OrichalcumBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.OrichalcumChestBlockEntity;
import com.mbb.austenium.content.block.entity.OrichalcumFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.OrichalcumSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.MythrilBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.MythrilBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.MythrilChestBlockEntity;
import com.mbb.austenium.content.block.entity.MythrilFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.MythrilSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.AdamantiteBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.AdamantiteBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.AdamantiteChestBlockEntity;
import com.mbb.austenium.content.block.entity.AdamantiteFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.AdamantiteSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.NetheriteBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.NetheriteBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.NetheriteChestBlockEntity;
import com.mbb.austenium.content.block.entity.NetheriteFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.NetheriteSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.RadiantBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.RadiantBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.RadiantChestBlockEntity;
import com.mbb.austenium.content.block.entity.RadiantFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.RadiantSmokerBlockEntity;
import com.mbb.austenium.content.block.entity.AurelianiumBarrelBlockEntity;
import com.mbb.austenium.content.block.entity.AurelianiumBlastFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.AurelianiumChestBlockEntity;
import com.mbb.austenium.content.block.entity.AurelianiumFurnaceBlockEntity;
import com.mbb.austenium.content.block.entity.AurelianiumSmokerBlockEntity;

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

    public static final RegistryObject<BlockEntityType<?>> DIAMOND_FURNACE
        = BLOCK_ENTITIES.register("diamond_furnace",
            () -> BlockEntityType.Builder.of(DiamondFurnaceBlockEntity::new, ModBlocks.DIAMOND_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> DIAMOND_BLAST_FURNACE
        = BLOCK_ENTITIES.register("diamond_blast_furnace",
            () -> BlockEntityType.Builder.of(DiamondBlastFurnaceBlockEntity::new, ModBlocks.DIAMOND_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> DIAMOND_SMOKER
        = BLOCK_ENTITIES.register("diamond_smoker",
            () -> BlockEntityType.Builder.of(DiamondSmokerBlockEntity::new, ModBlocks.DIAMOND_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> DIAMOND_BARREL
        = BLOCK_ENTITIES.register("diamond_barrel",
            () -> BlockEntityType.Builder.of(DiamondBarrelBlockEntity::new, ModBlocks.DIAMOND_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> DIAMOND_CHEST
        = BLOCK_ENTITIES.register("diamond_chest",
            () -> BlockEntityType.Builder.of(DiamondChestBlockEntity::new, ModBlocks.DIAMOND_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> EMERALD_FURNACE
        = BLOCK_ENTITIES.register("emerald_furnace",
            () -> BlockEntityType.Builder.of(EmeraldFurnaceBlockEntity::new, ModBlocks.EMERALD_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> EMERALD_BLAST_FURNACE
        = BLOCK_ENTITIES.register("emerald_blast_furnace",
            () -> BlockEntityType.Builder.of(EmeraldBlastFurnaceBlockEntity::new, ModBlocks.EMERALD_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> EMERALD_SMOKER
        = BLOCK_ENTITIES.register("emerald_smoker",
            () -> BlockEntityType.Builder.of(EmeraldSmokerBlockEntity::new, ModBlocks.EMERALD_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> EMERALD_BARREL
        = BLOCK_ENTITIES.register("emerald_barrel",
            () -> BlockEntityType.Builder.of(EmeraldBarrelBlockEntity::new, ModBlocks.EMERALD_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> EMERALD_CHEST
        = BLOCK_ENTITIES.register("emerald_chest",
            () -> BlockEntityType.Builder.of(EmeraldChestBlockEntity::new, ModBlocks.EMERALD_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> ORICHALCUM_FURNACE
        = BLOCK_ENTITIES.register("orichalcum_furnace",
            () -> BlockEntityType.Builder.of(OrichalcumFurnaceBlockEntity::new, ModBlocks.ORICHALCUM_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ORICHALCUM_BLAST_FURNACE
        = BLOCK_ENTITIES.register("orichalcum_blast_furnace",
            () -> BlockEntityType.Builder.of(OrichalcumBlastFurnaceBlockEntity::new, ModBlocks.ORICHALCUM_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ORICHALCUM_SMOKER
        = BLOCK_ENTITIES.register("orichalcum_smoker",
            () -> BlockEntityType.Builder.of(OrichalcumSmokerBlockEntity::new, ModBlocks.ORICHALCUM_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ORICHALCUM_BARREL
        = BLOCK_ENTITIES.register("orichalcum_barrel",
            () -> BlockEntityType.Builder.of(OrichalcumBarrelBlockEntity::new, ModBlocks.ORICHALCUM_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ORICHALCUM_CHEST
        = BLOCK_ENTITIES.register("orichalcum_chest",
            () -> BlockEntityType.Builder.of(OrichalcumChestBlockEntity::new, ModBlocks.ORICHALCUM_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> MYTHRIL_FURNACE
        = BLOCK_ENTITIES.register("mythril_furnace",
            () -> BlockEntityType.Builder.of(MythrilFurnaceBlockEntity::new, ModBlocks.MYTHRIL_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> MYTHRIL_BLAST_FURNACE
        = BLOCK_ENTITIES.register("mythril_blast_furnace",
            () -> BlockEntityType.Builder.of(MythrilBlastFurnaceBlockEntity::new, ModBlocks.MYTHRIL_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> MYTHRIL_SMOKER
        = BLOCK_ENTITIES.register("mythril_smoker",
            () -> BlockEntityType.Builder.of(MythrilSmokerBlockEntity::new, ModBlocks.MYTHRIL_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> MYTHRIL_BARREL
        = BLOCK_ENTITIES.register("mythril_barrel",
            () -> BlockEntityType.Builder.of(MythrilBarrelBlockEntity::new, ModBlocks.MYTHRIL_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> MYTHRIL_CHEST
        = BLOCK_ENTITIES.register("mythril_chest",
            () -> BlockEntityType.Builder.of(MythrilChestBlockEntity::new, ModBlocks.MYTHRIL_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> ADAMANTITE_FURNACE
        = BLOCK_ENTITIES.register("adamantite_furnace",
            () -> BlockEntityType.Builder.of(AdamantiteFurnaceBlockEntity::new, ModBlocks.ADAMANTITE_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ADAMANTITE_BLAST_FURNACE
        = BLOCK_ENTITIES.register("adamantite_blast_furnace",
            () -> BlockEntityType.Builder.of(AdamantiteBlastFurnaceBlockEntity::new, ModBlocks.ADAMANTITE_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ADAMANTITE_SMOKER
        = BLOCK_ENTITIES.register("adamantite_smoker",
            () -> BlockEntityType.Builder.of(AdamantiteSmokerBlockEntity::new, ModBlocks.ADAMANTITE_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ADAMANTITE_BARREL
        = BLOCK_ENTITIES.register("adamantite_barrel",
            () -> BlockEntityType.Builder.of(AdamantiteBarrelBlockEntity::new, ModBlocks.ADAMANTITE_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> ADAMANTITE_CHEST
        = BLOCK_ENTITIES.register("adamantite_chest",
            () -> BlockEntityType.Builder.of(AdamantiteChestBlockEntity::new, ModBlocks.ADAMANTITE_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> NETHERITE_FURNACE
        = BLOCK_ENTITIES.register("netherite_furnace",
            () -> BlockEntityType.Builder.of(NetheriteFurnaceBlockEntity::new, ModBlocks.NETHERITE_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NETHERITE_BLAST_FURNACE
        = BLOCK_ENTITIES.register("netherite_blast_furnace",
            () -> BlockEntityType.Builder.of(NetheriteBlastFurnaceBlockEntity::new, ModBlocks.NETHERITE_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NETHERITE_SMOKER
        = BLOCK_ENTITIES.register("netherite_smoker",
            () -> BlockEntityType.Builder.of(NetheriteSmokerBlockEntity::new, ModBlocks.NETHERITE_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NETHERITE_BARREL
        = BLOCK_ENTITIES.register("netherite_barrel",
            () -> BlockEntityType.Builder.of(NetheriteBarrelBlockEntity::new, ModBlocks.NETHERITE_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NETHERITE_CHEST
        = BLOCK_ENTITIES.register("netherite_chest",
            () -> BlockEntityType.Builder.of(NetheriteChestBlockEntity::new, ModBlocks.NETHERITE_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> RADIANT_FURNACE
        = BLOCK_ENTITIES.register("radiant_furnace",
            () -> BlockEntityType.Builder.of(RadiantFurnaceBlockEntity::new, ModBlocks.RADIANT_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> RADIANT_BLAST_FURNACE
        = BLOCK_ENTITIES.register("radiant_blast_furnace",
            () -> BlockEntityType.Builder.of(RadiantBlastFurnaceBlockEntity::new, ModBlocks.RADIANT_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> RADIANT_SMOKER
        = BLOCK_ENTITIES.register("radiant_smoker",
            () -> BlockEntityType.Builder.of(RadiantSmokerBlockEntity::new, ModBlocks.RADIANT_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> RADIANT_BARREL
        = BLOCK_ENTITIES.register("radiant_barrel",
            () -> BlockEntityType.Builder.of(RadiantBarrelBlockEntity::new, ModBlocks.RADIANT_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> RADIANT_CHEST
        = BLOCK_ENTITIES.register("radiant_chest",
            () -> BlockEntityType.Builder.of(RadiantChestBlockEntity::new, ModBlocks.RADIANT_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<?>> AURELIANIUM_FURNACE
        = BLOCK_ENTITIES.register("aurelianium_furnace",
            () -> BlockEntityType.Builder.of(AurelianiumFurnaceBlockEntity::new, ModBlocks.AURELIANIUM_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> AURELIANIUM_BLAST_FURNACE
        = BLOCK_ENTITIES.register("aurelianium_blast_furnace",
            () -> BlockEntityType.Builder.of(AurelianiumBlastFurnaceBlockEntity::new, ModBlocks.AURELIANIUM_BLAST_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> AURELIANIUM_SMOKER
        = BLOCK_ENTITIES.register("aurelianium_smoker",
            () -> BlockEntityType.Builder.of(AurelianiumSmokerBlockEntity::new, ModBlocks.AURELIANIUM_SMOKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> AURELIANIUM_BARREL
        = BLOCK_ENTITIES.register("aurelianium_barrel",
            () -> BlockEntityType.Builder.of(AurelianiumBarrelBlockEntity::new, ModBlocks.AURELIANIUM_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> AURELIANIUM_CHEST
        = BLOCK_ENTITIES.register("aurelianium_chest",
            () -> BlockEntityType.Builder.of(AurelianiumChestBlockEntity::new, ModBlocks.AURELIANIUM_CHEST.get()).build(null));
}