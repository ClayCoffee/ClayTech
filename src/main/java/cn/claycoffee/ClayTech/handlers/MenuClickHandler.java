package cn.claycoffee.ClayTech.handlers;


import cn.claycoffee.ClayTech.utils.GUI;
import org.bukkit.entity.Player;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/7/29 9:46
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public abstract class MenuClickHandler extends MenuBlockHandler {
    private int[] slots;

    public MenuClickHandler(GUI menu, int[] slots) {
        super(menu);
        this.slots = slots;
    }

    public abstract void onLeftClick(Player p, int slot);

    public abstract void onRightClick(Player p, int slot);

    public int[] getSlots() {
        return slots;
    }
}
