package cn.claycoffee.ClayTech.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/8/3 21:14
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class GUIHolder implements InventoryHolder {
    private GUI gui;
    private Player holder;

    public GUIHolder(GUI gui, Player holder) {
        this.gui = gui;
        this.holder = holder;
    }


    public GUI getGUI() {
        return gui;
    }

    public Player getHolder() {
        return holder;
    }

    @NotNull
    public Inventory getInventory() {
        return gui.getNowInventory().get(holder.getUniqueId().toString());
    }
}
