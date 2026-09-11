/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.menu.GenericChestMenu
 * TYPE: Java Source
 * DESCRIPTION: Generic row-count chest menu for [MBB] Austenium containers.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.content.menu;

import com.mbb.austenium.content.ModMenuTypes;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;

/**
 * GenericChestMenu CLASS IS CORE PART OF [MBB] AUSTENIUM GenericChestMenu.java.
 *
 * com.mbb.austenium.content.menu.GenericChestMenu:
 *     Chest menu with an arbitrary number of 9-wide rows; extends ChestMenu so
 *     the vanilla openers counter and quick-move logic keep working.
 */
public class GenericChestMenu extends ChestMenu {

    /**
     * Creates the GenericChestMenu instance.
     *
     * @param type the block entity type
     * @param containerId the container id assigned by the menu
     * @param inventory the player inventory
     * @param container the container argument
     * @param rows the rows argument
     */
    public GenericChestMenu(MenuType<?> type, int containerId, Inventory inventory, Container container, int rows) {
        super(type, containerId, inventory, container, rows);
    }

    /**
     * Creates the GenericChestMenu instance.
     *
     * @param type the block entity type
     * @param containerId the container id assigned by the menu
     * @param inventory the player inventory
     * @param rows the rows argument
     */
    public GenericChestMenu(MenuType<?> type, int containerId, Inventory inventory, int rows) {
        this(type, containerId, inventory, new SimpleContainer(rows * 9), rows);
    }
}
