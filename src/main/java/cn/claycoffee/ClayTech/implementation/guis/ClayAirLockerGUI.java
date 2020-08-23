package cn.claycoffee.ClayTech.implementation.guis;

import cn.claycoffee.ClayTech.api.exceptions.AlreadyProtectedException;
import cn.claycoffee.ClayTech.handlers.MenuClickHandler;
import cn.claycoffee.ClayTech.utils.BlockGUI;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Utils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @Project: ClayTech
 * @Author: ClayCoffee
 * @Date: 2020/8/23 15:24
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class ClayAirLockerGUI extends BlockGUI {
    private static int[] border = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private Block b = block;

    public ClayAirLockerGUI(int size, String title, boolean isProtected, Block b) {
        super(size, title, isProtected, b);
    }

    @Override
    public void init() {
        try {
            this.registerBlockHandler(new MenuClickHandler(this, new int[]{11, 15}) {
                @Override
                public void onLeftClick(Player player, int i) {
                    int waitTime = new Integer(BlockStorage.getLocationInfo(b.getLocation()).getString("wait-time")).intValue();
                    if (i == 11) {
                        if (waitTime <= 1) return;
                        waitTime--;
                        BlockStorage.addBlockInfo(b.getLocation(), "wait-time", waitTime + "");
                        show(player);
                    } else {
                        waitTime++;
                        BlockStorage.addBlockInfo(b.getLocation(), "wait-time", waitTime + "");
                        show(player);
                    }
                }

                @Override
                public void onRightClick(Player player, int i) {

                }
            });
        } catch (AlreadyProtectedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setupBlockMenu(Inventory inventory, Player player, Block b) {
        if (this.b != null) {
            int waitTime = 5;
            if (BlockStorage.getLocationInfo(b.getLocation()) != null && BlockStorage.getLocationInfo(b.getLocation()).getString("wait-time") != null) {
                waitTime = new Integer(BlockStorage.getLocationInfo(b.getLocation()).getString("wait-time")).intValue();
            } else {
                BlockStorage.addBlockInfo(b.getLocation(), "wait-time", waitTime + "");
            }
            ItemStack borderItem = Utils.newItemD(Material.BLUE_STAINED_GLASS_PANE, Lang.readMachinesText("SPLIT_LINE"));
            ItemStack waitTimeItem = Utils.newItemD(Material.CLOCK, Lang.readMachinesText("wait-time").replaceAll("%TIME%", waitTime + ""));
            ItemStack add = Utils.newItemD(Material.GREEN_STAINED_GLASS_PANE, Lang.readMachinesText("add"));
            ItemStack sub = Utils.newItemD(Material.RED_STAINED_GLASS_PANE, Lang.readMachinesText("sub"));
            for (int i : border) {
                inventory.setItem(i, borderItem);
            }
            inventory.setItem(11, sub);
            inventory.setItem(15, add);
            inventory.setItem(13, waitTimeItem);
        }
    }

    @Override
    public String getID() {
        return "CLAYTECH_AIR_LOCKER";
    }
}
