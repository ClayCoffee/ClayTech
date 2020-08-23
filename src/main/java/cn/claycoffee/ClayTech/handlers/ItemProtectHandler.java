package cn.claycoffee.ClayTech.handlers;

import cn.claycoffee.ClayTech.utils.GUI;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/8/3 21:29
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class ItemProtectHandler extends MenuBlockHandler {
    private int slot;

    public ItemProtectHandler(GUI menu, int slot) {
        super(menu);
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
