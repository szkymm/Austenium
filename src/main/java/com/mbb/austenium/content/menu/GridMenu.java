/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * GridMenu CLASS IS CORE PART OF [MBB] AUSTENIUM GridMenu.java.
 *
 * com.mbb.austenium.content.menu.GridMenu:
 *     Container menu for the wide tier chests; keeps the slot layout of that tier's GUI.
 */
public class GridMenu extends AbstractContainerMenu {

    private final Container container;
    private final int rows;
    private final int cols;

    /**
     * Creates the GridMenu instance.
     *
     * @param type the block entity type
     * @param containerId the container id assigned by the menu
     * @param inventory the player inventory
     * @param container the container argument
     * @param rows the rows argument
     * @param cols the cols argument
     */
    public GridMenu(MenuType<?> type, int containerId, Inventory inventory, Container container, int rows, int cols) {
        super(type, containerId);
        this.container = container;
        this.rows = rows;
        this.cols = cols;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.addSlot(new Slot(container, row * cols + col, 8 + col * 18, 18 + row * 18));
            }
        }
        int playerStart = 103 + (rows - 4) * 18;
        int playerX = 8 + (cols * 18 - 162) / 2;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, playerX + col * 18, playerStart + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, playerX + col * 18, playerStart + 58));
        }
        // Mirror ChestMenu: openers start at menu construction (client container is a no-op).
        if (inventory.player != null) {
            this.container.startOpen(inventory.player);
        }
    }

    /**
     * Creates the GridMenu instance.
     *
     * @param type the block entity type
     * @param containerId the container id assigned by the menu
     * @param inventory the player inventory
     * @param rows the rows argument
     * @param cols the cols argument
     */
    public GridMenu(MenuType<?> type, int containerId, Inventory inventory, int rows, int cols) {
        this(type, containerId, inventory, new SimpleContainer(rows * cols), rows, cols);
    }

    /**
     * Returns the number of container rows this menu shows.
     *
     * @return the container row count
     */
    public int getRowCount() { return this.rows; }
    /**
     * Returns the number of container columns this menu shows.
     *
     * @return the container column count
     */
    public int getColumnCount() { return this.cols; }

    /** {@inheritDoc} */
    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    /** {@inheritDoc} */
    @Override
    public void removed(Player player) {
        // Mirror ChestMenu: stop tracking openers when the menu is removed.
        this.container.stopOpen(player);
        super.removed(player);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack item = slot.getItem();
            result = item.copy();
            int containerSlots = this.rows * this.cols;
            if (index < containerSlots) {
                if (!this.moveItemStackTo(item, containerSlots, this.slots.size(), true)) return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(item, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (item.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
            if (item.getCount() == result.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, item);
        }
        return result;
    }
}