/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.AurelianiumChestBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Aurelianium chest container with 162 slots per chest; 324 when paired.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.GenericChestMenu;
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
 * AurelianiumChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM AurelianiumChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.AurelianiumChestBlockEntity:
 *     Aurelianium chest container with 162 slots per chest; 324 when paired.
 */
public class AurelianiumChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 162;

    public AurelianiumChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    public AurelianiumChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.AURELIANIUM_CHEST.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    protected Component getDefaultName() {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return Component.translatable(large
            ? "container.mbb_austenium.large_aurelianium_chest"
            : "container.mbb_austenium.aurelianium_chest");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        // A paired chest holds 324 slots and opens as an 18x18 grid; a single chest stays 9x18.
        if (this.getContainerSize() > 162) {
            return new GridMenu(ModMenuTypes.AURELIANIUM_18X18.get(),
                containerId, inventory, this, 18, 18);
        }
        return new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(18).get(),
            containerId, inventory, this, 18);
    }
}
