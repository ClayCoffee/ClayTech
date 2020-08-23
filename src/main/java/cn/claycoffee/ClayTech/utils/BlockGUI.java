package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/8/23 15:39
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public abstract class BlockGUI extends GUI {
    protected Block block;

    public BlockGUI(int size, String title, boolean isProtected, Block b) {
        super(size, title, isProtected);
        this.block = b;
    }

    public Block getBlock() {
        return this.block;
    }

    public void show(final Player p) {
        final Inventory pm = Bukkit.createInventory(new GUIHolder(this, p), size, title);
        this.menu.put(p.getUniqueId().toString(), pm);
        setupBlockMenu(pm, p, this.block);
        final Player player = p;
        new BukkitRunnable() {
            public void run() {
                player.closeInventory();
                player.openInventory(pm);
            }
        }.runTask(ClayTech.getInstance());
    }

    public abstract void setupBlockMenu(Inventory menu, Player p, Block b);

    @Override
    public void setupMenu(Inventory menu, Player p) {
    }
}
