/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.
 * TYPE: Java Source
 * DESCRIPTION: Tier shulker box content for [MBB] Austenium.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.block.TierShulkerBoxBlock;
import com.mbb.austenium.content.block.TierShulkerBoxBlock.ShulkerTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * TierShulkerBoxBlockEntity CLASS IS CORE PART OF [MBB] AUSTENIUM TierShulkerBoxBlockEntity.java.
 *
 * com.mbb.austenium.content.block.entity.TierShulkerBoxBlockEntity:
 *     Container with the capacity of the matching barrel, vanilla-style lid
 *     animation state and the vanilla rule that shulker boxes cannot be nested.
 */
public class TierShulkerBoxBlockEntity extends RandomizableContainerBlockEntity {

    /** Block event id used to mirror the server opener count onto the client lid animation. */
    private static final int LID_EVENT = 1;

    private NonNullList<ItemStack> items;
    private float progress;
    private float progressOld;
    private int clientOpeners;

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        /** {@inheritDoc} */
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            TierShulkerBoxBlockEntity.this.playSound(state, SoundEvents.SHULKER_BOX_OPEN);
        }

        /** {@inheritDoc} */
        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            TierShulkerBoxBlockEntity.this.playSound(state, SoundEvents.SHULKER_BOX_CLOSE);
        }

        /** {@inheritDoc} */
        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
            // A client never runs the opener counter, so mirror the count through a block event;
            // without it the lid animation only ever happens on the server and is never seen in game.
            level.blockEvent(pos, state.getBlock(), LID_EVENT, newCount);
        }

        /** {@inheritDoc} */
        @Override
        protected boolean isOwnContainer(Player player) {
            AbstractContainerMenu menu = player.containerMenu;
            // Compare the actual container: any other grid menu must not count as this box.
            if (menu == null || menu.slots.isEmpty()) {
                return false;
            }
            return menu.slots.get(0).container == TierShulkerBoxBlockEntity.this;
        }
    };

    /**
     * Creates the TierShulkerBoxBlockEntity instance.
     *
     * @param type the block entity type
     * @param pos the block position
     * @param state the block state
     */
    public TierShulkerBoxBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.items = NonNullList.withSize(this.getTier().containerSize(), ItemStack.EMPTY);
    }

    /**
     * Creates the TierShulkerBoxBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public TierShulkerBoxBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.TIER_SHULKER_BOX.get(), pos, state);
    }

    /**
     * Returns the tier this block or block entity belongs to.
     *
     * @return the tier of this element
     */
    public ShulkerTier getTier() {
        return ((TierShulkerBoxBlock) this.getBlockState().getBlock()).getTier();
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
        return this.getTier().containerSize();
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
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return !isShulkerBoxStack(stack);
    }

    /**
     * Vanilla forbids putting a shulker box inside another shulker box; the same
     * rule covers every tier of this mod.
     */
    private static boolean isShulkerBoxStack(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        Item item = stack.getItem();
        if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {
            return true;
        }
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        return id != null
            && MbbAustenium.MOD_ID.equals(id.getNamespace())
            && id.getPath().endsWith("_shulker_box");
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium." + this.getTier().id() + "_shulker_box");
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return this.getTier().createMenu(containerId, inventory, this);
    }

    /**
     * Advances the lid animation towards the current opener count.
     *
     * @param level the level holding the box
     * @param pos the box position
     * @param state the box block state
     * @param blockEntity the box block entity
     */
    public static void tick(Level level, BlockPos pos, BlockState state, TierShulkerBoxBlockEntity blockEntity) {
        blockEntity.progressOld = blockEntity.progress;
        if (blockEntity.isLidOpen()) {
            blockEntity.progress = Math.min(1.0f, blockEntity.progress + 0.1f);
        } else {
            blockEntity.progress = Math.max(0.0f, blockEntity.progress - 0.1f);
        }
    }

    /**
     * Returns whether the lid has to be open right now.
     *
     * <p>The server tracks the opener count in its container counter. A client cannot see that
     * counter, so it mirrors the count from the block event sent by openerCountChanged; without
     * that mirror the lid never moves on the client side.</p>
     *
     * @return true while at least one player has this box open
     */
    private boolean isLidOpen() {
        Level level = this.getLevel();
        if (level != null && level.isClientSide) {
            return this.clientOpeners > 0;
        }
        return this.openersCounter.getOpenerCount() > 0;
    }

    /**
     * Mirrors the server opener count onto the client so the lid animates there.
     *
     * @param id the block event id
     * @param value the block event payload, here the opener count
     * @return true when this block entity consumed the event
     */
    @Override
    public boolean triggerEvent(int id, int value) {
        if (id == LID_EVENT) {
            this.clientOpeners = value;
            return true;
        }
        return super.triggerEvent(id, value);
    }

    /**
     * Returns the lid opening progress interpolated for the current frame.
     *
     * @param partialTick the partial tick of the current frame
     * @return the lid progress between 0 and 1
     */
    public float getProgress(float partialTick) {
        return Mth.lerp(partialTick, this.progressOld, this.progress);
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

    private void playSound(BlockState state, SoundEvent soundEvent) {
        Direction facing = state.getValue(TierShulkerBoxBlock.FACING);
        Vec3i normal = facing.getNormal();
        double x = this.worldPosition.getX() + 0.5 + normal.getX() / 2.0;
        double y = this.worldPosition.getY() + 0.5 + normal.getY() / 2.0;
        double z = this.worldPosition.getZ() + 0.5 + normal.getZ() / 2.0;
        this.level.playSound(null, x, y, z, soundEvent,
            SoundSource.BLOCKS, 0.5f, this.level.random.nextFloat() * 0.1f + 0.9f);
    }
}
