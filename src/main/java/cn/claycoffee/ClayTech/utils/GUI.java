package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.api.exceptions.AlreadyProtectedException;
import cn.claycoffee.ClayTech.handlers.ItemProtectHandler;
import cn.claycoffee.ClayTech.handlers.MenuBlockHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/7/29 9:40
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public abstract class GUI {
    protected List<MenuBlockHandler> handlers = new ArrayList<MenuBlockHandler>();
    protected boolean isProtected;
    protected int size;
    protected String title;
    Map<String, Inventory> menu = new HashMap<String, Inventory>();

    public GUI(int size, String title, boolean isProtected) {
        this.register(size, title, isProtected);
    }

    public abstract void init();

    public abstract void setupMenu(Inventory menu, Player p);

    public abstract String getID();

    private void register(int size, String title, boolean isProtected) {
        this.isProtected = isProtected;
        this.size = size;
        this.title = title;
        this.init();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GUI)) return false;
        GUI oth = (GUI) obj;
        if (oth.getID().equals(this.getID())) return true;
        return false;
    }

    public List<MenuBlockHandler> getHandlers() {
        return handlers;
    }

    public void registerBlockHandler(MenuBlockHandler bh) throws AlreadyProtectedException {
        if (this.isProtected && bh instanceof ItemProtectHandler) throw new AlreadyProtectedException();
        handlers.add(bh);
    }

    public void show(final Player p) {
        final Inventory pm = Bukkit.createInventory(new GUIHolder(this, p), size, title);
        this.menu.put(p.getUniqueId().toString(), pm);
        setupMenu(pm, p);
        final Player player = p;
        new BukkitRunnable() {
            public void run() {
                player.closeInventory();
                player.openInventory(pm);
            }
        }.runTask(ClayTech.getInstance());
    }

    public boolean isProtected() {
        return this.isProtected;
    }

    public Map<String, Inventory> getNowInventory() {
        return this.menu;
    }
}
