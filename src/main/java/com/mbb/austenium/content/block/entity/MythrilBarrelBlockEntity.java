/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.block.entity.MythrilBarrelBlockEntity
 * TYPE: Java Source
 * DESCRIPTION: Mythril barrel container with 70 internal slots (14x5).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.ModMenuTypes;
import com.mbb.austenium.content.menu.GridMenu;
import com.mbb.austenium.content.block.MythrilBarrelBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * MythrilBarrelBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM MythrilBarrelBlockEntity.java.
 */
public class MythrilBarrelBlockEntity extends RandomizableContainerBlockEntity {

    public static final int CONTAINER_SIZE = 70;

    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        /** {@inheritDoc} */
        @Override
        protected void onOpen(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
            MythrilBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_OPEN);
        }

        /** {@inheritDoc} */
        @Override
        protected void onClose(net.minecraft.world.level.Level level, BlockPos pos, BlockState state) {
            MythrilBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_CLOSE);
        }

        /** {@inheritDoc} */
        @Override
        protected void openerCountChanged(net.minecraft.world.level.Level level,
            BlockPos pos, BlockState state, int oldCount, int newCount) {
            MythrilBarrelBlockEntity.this.updateBlockState(state, newCount > 0);
        }

        /** {@inheritDoc} */
        @Override
        protected boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof GridMenu;
        }
    };

    /**
     * Creates the MythrilBarrelBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public MythrilBarrelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    }

    /**
     * Creates the MythrilBarrelBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public MythrilBarrelBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.MYTHRIL_BARREL.get(), pos, state);
    }

    /** {@inheritDoc} */
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    /** {@inheritDoc} */
    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    /** {@inheritDoc} */
    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium.mythril_barrel");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new GridMenu(ModMenuTypes.MYTHRIL_14X5.get(), containerId, inventory, this, 5, 14);
    }

    /** {@inheritDoc} */
    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    /**
     * Re-evaluates the opener count after the container is loaded again.
     *
     */
    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    private void updateBlockState(BlockState state, boolean open) {
        this.level.setBlock(this.getBlockPos(), state.setValue(MythrilBarrelBlock.OPEN, open), 3);
    }

    private void playSound(BlockState state, SoundEvent soundEvent) {
        Direction facing = state.getValue(MythrilBarrelBlock.FACING);
        Vec3i normal = facing.getNormal();
        double x = this.worldPosition.getX() + 0.5 + normal.getX() / 2.0;
        double y = this.worldPosition.getY() + 0.5 + normal.getY() / 2.0;
        double z = this.worldPosition.getZ() + 0.5 + normal.getZ() / 2.0;
        this.level.playSound(null, x, y, z, soundEvent,
            SoundSource.BLOCKS, 0.5f, this.level.random.nextFloat() * 0.1f + 0.9f);
    }
}
