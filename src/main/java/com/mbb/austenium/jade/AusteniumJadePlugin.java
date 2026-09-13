/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.jade.AusteniumJadePlugin
 * TYPE: Java Source
 * DESCRIPTION: Jade plugin that registers the six Austenium information providers.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jade;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.block.ModBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * AusteniumJadePlugin CLASS IS CORE PART OF [MBB] AUSTENIUM AusteniumJadePlugin.java.
 *
 * com.mbb.austenium.jade.AusteniumJadePlugin:
 *     Collects the mod block families once and hands every family to the provider that owns it.
 *     Jade matches components by block class, so a family is registered once per distinct class and
 *     every provider also guards itself by exact block membership.
 *
 * ATTRIBUTES:
 *     none.
 *
 * PUBLIC METHODS:
 *     registerClient(IWailaClientRegistration) -> void:
 *         Registers the six providers for the mod block families.
 *
 * PRIVATE METHODS:
 *     registerFamily(IWailaClientRegistration, IBlockComponentProvider, Set<Block>) -> void:
 *         Registers one provider for every distinct class of the family.
 *     isMachine(String), isHopper(String), isContainer(String), isCoalBlock(String), isOre(String) -> boolean:
 *         Classify one registry path into its content family.
 */
@WailaPlugin(MbbAustenium.MOD_ID)
public final class AusteniumJadePlugin implements IWailaPlugin {

    /** {@inheritDoc} */
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        Set<Block> machines = new HashSet<>();
        Set<Block> hoppers = new HashSet<>();
        Set<Block> containers = new HashSet<>();
        Set<Block> coalBlocks = new HashSet<>();
        Set<Block> ores = new HashSet<>();
        Set<Block> portals = new HashSet<>();
        // Walk the block registry once and sort every mod block into its information family.
        for (RegistryObject<Block> reference : ModBlocks.BLOCKS.getEntries()) {
            Block block = reference.get();
            String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
            if (isMachine(path)) {
                machines.add(block);
            } else if (isHopper(path)) {
                hoppers.add(block);
            } else if (isContainer(path)) {
                containers.add(block);
            } else if (isCoalBlock(path)) {
                coalBlocks.add(block);
            } else if (isOre(path)) {
                ores.add(block);
            } else if (path.equals("austeniumcraft_portal")) {
                portals.add(block);
            }
        }
        registerFamily(registration, new TierMachineProvider(machines), machines);
        registerFamily(registration, new TierHopperProvider(hoppers), hoppers);
        registerFamily(registration, new TierContainerProvider(containers), containers);
        registerFamily(registration, new TierCoalBlockProvider(coalBlocks), coalBlocks);
        registerFamily(registration, new TierOreProvider(ores), ores);
        registerFamily(registration, new AwPortalProvider(portals), portals);
    }

    /**
     * Registers one provider for every distinct class of its block family.
     *
     * @param registration the Jade client registration
     * @param provider the provider that answers for the family
     * @param blocks the family members
     */
    private static void registerFamily(IWailaClientRegistration registration, IBlockComponentProvider provider,
                                       Set<Block> blocks) {
        Set<Class<? extends Block>> classes = new HashSet<>();
        // Several families share one class, so the class list is deduplicated before registering.
        for (Block block : blocks) {
            classes.add(block.getClass());
        }
        // Jade walks the class hierarchy, so one registration per distinct class covers the family.
        for (Class<? extends Block> blockClass : classes) {
            registration.registerBlockComponent(provider, blockClass);
        }
    }

    /**
     * Reports whether one registry path names a cooking machine.
     *
     * @param path the block registry path
     * @return true for furnace, blast furnace and smoker blocks
     */
    private static boolean isMachine(String path) {
        return path.endsWith("_furnace") || path.endsWith("_blast_furnace") || path.endsWith("_smoker");
    }

    /**
     * Reports whether one registry path names a tiered hopper.
     *
     * @param path the block registry path
     * @return true for the twelve tier hoppers
     */
    private static boolean isHopper(String path) {
        return path.endsWith("_hopper");
    }

    /**
     * Reports whether one registry path names a chest, barrel or shulker box.
     *
     * @param path the block registry path
     * @return true for the mod storage blocks
     */
    private static boolean isContainer(String path) {
        return path.endsWith("_chest") || path.endsWith("_barrel") || path.endsWith("_shulker_box");
    }

    /**
     * Reports whether one registry path names a fuel block.
     *
     * @param path the block registry path
     * @return true for the sixteen coal blocks
     */
    private static boolean isCoalBlock(String path) {
        return path.endsWith("_coal_block");
    }

    /**
     * Reports whether one registry path names an ore or a debris block.
     *
     * @param path the block registry path
     * @return true for the ores and the two debris blocks
     */
    private static boolean isOre(String path) {
        return path.endsWith("_ore") || path.endsWith("_debris");
    }
}
