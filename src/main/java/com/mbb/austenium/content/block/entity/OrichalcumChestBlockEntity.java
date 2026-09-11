/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.GenericChestMenu;

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
 * OrichalcumChestBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM OrichalcumChestBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.OrichalcumChestBlockEntity:
 *     Chest block entity of the orichalcum tier: holds that tier's slot count and paired state.
 */
public class OrichalcumChestBlockEntity extends ChestBlockEntity {

    public static final int CONTAINER_SIZE = 63;

    /**
     * Creates the OrichalcumChestBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public OrichalcumChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
    }

    /**
     * Creates the OrichalcumChestBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public OrichalcumChestBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.ORICHALCUM_CHEST.get(), pos, state);
    }

    /** {@inheritDoc} */
    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        boolean large = this.getBlockState().getValue(ChestBlock.TYPE) != ChestType.SINGLE;
        return Component.translatable(large
            ? "container.mbb_austenium.large_orichalcum_chest"
            : "container.mbb_austenium.orichalcum_chest");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new GenericChestMenu(ModMenuTypes.GENERIC_CHEST.get(7).get(), containerId, inventory, this, 7);
    }
}
