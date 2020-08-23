package cn.claycoffee.ClayTech.handlers;

import cn.claycoffee.ClayTech.utils.GUI;
import org.bukkit.entity.Player;

/**
 * @Project: ClayAPI
 * @Author: ClayCoffee
 * @Date: 2020/8/3 21:29
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public abstract class MenuActionHandler extends MenuBlockHandler {
    public MenuActionHandler(GUI menu) {
        super(menu);
    }

    public abstract void onClose(Player p);

    public abstract void onOpen(Player p);
}
