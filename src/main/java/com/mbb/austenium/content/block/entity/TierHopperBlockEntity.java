package com.mbb.austenium.content.block.entity;

import com.mbb.austenium.content.ModBlockEntities;
import com.mbb.austenium.content.block.TierHopperBlock;
import com.mbb.austenium.content.block.TierHopperBlock.HopperTier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/** Tier hopper block entity: five slots, tier cadence, own insert and suction. */
public class TierHopperBlockEntity extends RandomizableContainerBlockEntity implements Hopper {

    private static final String TIER_COOLDOWN_TAG = "TierCooldown";
    private static final int CONTAINER_SIZE = 5;

    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    private int tierCooldown;
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        /** {@inheritDoc} */
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            // Vanilla hoppers stay silent.
        }

        /** {@inheritDoc} */
        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            // Vanilla hoppers stay silent.
        }

        /** {@inheritDoc} */
        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
            // The hopper shape never changes with the opener count.
        }

        /** {@inheritDoc} */
        @Override
        protected boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof HopperMenu;
        }
    };

    /**
     * Creates the TierHopperBlockEntity instance.
     *
     * @param pos the block position
     * @param state the block state
     */
    public TierHopperBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TIER_HOPPER.get(), pos, state);
    }

    /**
     * Returns the tier this block belongs to.
     * @return the tier of this block
     */
    public HopperTier getTier() {
        return ((TierHopperBlock) this.getBlockState().getBlock()).getTier();
    }

    /** {@inheritDoc} */
    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mbb_austenium." + this.getTier().id() + "_hopper");
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
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new HopperMenu(containerId, inventory, this);
    }

    /** {@inheritDoc} */
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
        tag.putInt(TIER_COOLDOWN_TAG, this.tierCooldown);
    }

    /** {@inheritDoc} */
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
        this.tierCooldown = tag.getInt(TIER_COOLDOWN_TAG);
    }

    /** {@inheritDoc} */
    @Override
    public double getLevelX() {
        return this.worldPosition.getX() + 0.5;
    }

    /** {@inheritDoc} */
    @Override
    public double getLevelY() {
        return this.worldPosition.getY() + 0.5;
    }

    /** {@inheritDoc} */
    @Override
    public double getLevelZ() {
        return this.worldPosition.getZ() + 0.5;
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
     * Runs one tier activation: like the vanilla hopper it may push one item out and
     * pull one in during the same activation, repeated for the tier batch size.
     *
     * @param level the level holding the hopper
     * @param pos the hopper position
     * @param state the hopper block state
     * @param hopper the hopper block entity
     */
    public static void tick(Level level, BlockPos pos, BlockState state, TierHopperBlockEntity hopper) {
        // An idle hopper keeps polling every tick, exactly like the vanilla one.
        if (hopper.tierCooldown > 0) {
            --hopper.tierCooldown;
            return;
        }
        if (level.isClientSide || !state.getValue(HopperBlock.ENABLED)) {
            return;
        }
        HopperTier tier = hopper.getTier();
        int movedCount = 0;
        // Each step mirrors the vanilla order: eject first, then top up from above.
        for (int step = 0; step < tier.itemsPerActivation(); step++) {
            boolean acted = false;
            if (!hopper.isEmpty()) {
                acted = ejectOneItem(level, pos, state, hopper);
            }
            if (!isInventoryFull(hopper)) {
                acted |= suckOneItem(level, hopper);
            }
            if (!acted) {
                break;
            }
            movedCount++;
        }
        if (movedCount > 0) {
            hopper.tierCooldown = tier.cooldownTicks() - 1;
            hopper.setChanged();
        }
    }

    /**
     * Pushes one item into the container the hopper faces.
     *
     * <p>The receiving face decides which slots are legal: a furnace exposes its smelting slot on
     * top, its fuel slot on the sides and its output below. The insertion therefore goes through
     * the vanilla helper, which asks the target for the slots that face allows. Filling the first
     * slot that merely accepts the item drops fuel into the smelting slot instead (0.beta.5 bug,
     * reported 2026-09-11).</p>
     *
     * @param level the level holding the hopper
     * @param pos the hopper position
     * @param state the hopper block state
     * @param hopper the hopper block entity
     * @return true when one item left the hopper
     */
    private static boolean ejectOneItem(Level level, BlockPos pos, BlockState state, TierHopperBlockEntity hopper) {
        Direction facing = state.getValue(HopperBlock.FACING);
        Container target = HopperBlockEntity.getContainerAt(level, pos.relative(facing));
        if (target == null) {
            return false;
        }
        // Items enter the target through the face opposite to the direction the hopper points in.
        Direction receivingFace = facing.getOpposite();
        if (isFullContainer(target, receivingFace)) {
            return false;
        }
        for (int slot = 0; slot < hopper.getContainerSize(); slot++) {
            if (hopper.getItem(slot).isEmpty()) {
                continue;
            }
            // Keep the untouched stack so a refused item can be restored without losing the rest.
            ItemStack original = hopper.getItem(slot).copy();
            ItemStack taken = hopper.removeItem(slot, 1);
            if (taken.isEmpty()) {
                continue;
            }
            // The vanilla helper takes the source first and the destination second (verified with javap:
            // its second parameter is the one asked for getSlotsForFace and getContainerSize).
            ItemStack leftover = HopperBlockEntity.addItem(hopper, target, taken, receivingFace);
            if (leftover.isEmpty()) {
                target.setChanged();
                return true;
            }
            // The target refused the item, so restore exactly what the slot held before.
            hopper.setItem(slot, original);
        }
        return false;
    }

    /**
     * Returns whether every slot the given face exposes is already full.
     *
     * @param container the target container
     * @param side the face the items enter through
     * @return true when no exposed slot can take another item
     */
    private static boolean isFullContainer(Container container, Direction side) {
        for (int slot : exposedSlots(container, side)) {
            ItemStack stack = container.getItem(slot);
            int limit = Math.min(container.getMaxStackSize(), stack.getMaxStackSize());
            if (stack.isEmpty() || stack.getCount() < limit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the slot indices a container exposes on one face.
     *
     * @param container the target container
     * @param side the face the items enter through
     * @return the slot indices that face allows
     */
    private static int[] exposedSlots(Container container, Direction side) {
        if (container instanceof WorldlyContainer worldly) {
            return worldly.getSlotsForFace(side);
        }
        int size = container.getContainerSize();
        int[] all = new int[size];
        for (int i = 0; i < size; i++) {
            all[i] = i;
        }
        return all;
    }

    /**
     * Pulls one item in from above: the container directly above, or a dropped item
     * entity. The vanilla helper implements both rules, so it is called directly.
     *
     * @param level the level holding the hopper
     * @param hopper the hopper block entity
     * @return true when one item was taken in
     */
    private static boolean suckOneItem(Level level, TierHopperBlockEntity hopper) {
        return HopperBlockEntity.suckInItems(level, hopper);
    }

    private static boolean isInventoryFull(TierHopperBlockEntity hopper) {
        for (int slot = 0; slot < hopper.getContainerSize(); slot++) {
            ItemStack stack = hopper.getItem(slot);
            if (stack.isEmpty() || stack.getCount() < stack.getMaxStackSize()) {
                return false;
            }
        }
        return true;
    }
}
