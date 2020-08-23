package cn.claycoffee.ClayTech.listeners;

import cn.claycoffee.ClayTech.ClayTechItems;
import cn.claycoffee.ClayTech.utils.FoodUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;

public class FoodDropListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreakEvent(BlockBreakEvent e) {
        if (!e.isCancelled()) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                FoodUtils.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.OAK_LEAVES),
                        ClayTechItems.CLAY_LEMON, new ItemStack(Material.SHEARS), 10, e);
                try {
                    // 这里放其他事件
                    FoodUtils.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.GRASS),
                            ClayTechItems.DIRTY_TEA, new ItemStack(Material.SHEARS), 10, e);
                    FoodUtils.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.WHEAT), ClayTechItems.FLOUR,
                            new ItemStack(Material.SHEARS), 15, 20, e);
                    FoodUtils.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.POTATOES),
                            ClayTechItems.STARCH, new ItemStack(Material.SHEARS), 15, 20, e);
                    FoodUtils.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.POTATOES),
                            ClayTechItems.CLAY_SWEET_POTATO, new ItemStack(Material.SHEARS), 25, 30, e);
                    FoodUtils.CheckDestroy(e.getPlayer(), e.getBlock(), new ItemStack(Material.GRASS),
                            ClayTechItems.GREEN_GRASS, new ItemStack(Material.SHEARS), 31, 40, e);
                } catch (NullPointerException err) {
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerFishEvent(PlayerFishEvent e) {
        if (e.getState() == State.CAUGHT_FISH) {
            FoodUtils.FishItemCheck(e, 1, 10, ClayTechItems.SNAIL_HEALTHY);
            try {
                // 这里放其他食物/饮料8!!
                FoodUtils.FishItemCheck(e, 11, 20, ClayTechItems.SNAIL_BAD);
                FoodUtils.FishItemCheck(e, 85, 92, ClayTechItems.TUNA_FISH);
            } catch (NullPointerException err) {
            }

        }
    }
}
