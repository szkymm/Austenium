/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.NetheriteChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Netherite chest container with 105 slots per chest; 210 when paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.GridMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

/**
 * NetheriteChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM NetheriteChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.NetheriteChestBlockEntity:
 *     Netherite chest container with 105 slots per chest; 210 when paired.
 */
public class NetheriteChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 105;

    public NetheriteChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    public NetheriteChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.NETHERITE_CHEST.get(), pos, state);
    }

    @Override
    public void recheckOpen() {
        // GridMenu is not a ChestMenu; the vanilla recheck would reset the openers count to zero,
        // which makes the lid twitch. The menu lifecycle starts/stops openers instead.
    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    protected Component getDefaultName() {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return Component.translatable(large
            ? "container.mbb_austenium.large_netherite_chest"
            : "container.mbb_austenium.netherite_chest");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return large
            ? new GridMenu(ModMenuTypes.NETHERITE_15X14.get(), containerId, inventory, this, 14, 15)
            : new GridMenu(ModMenuTypes.NETHERITE_15X7.get(), containerId, inventory, this, 7, 15);
    }
}
