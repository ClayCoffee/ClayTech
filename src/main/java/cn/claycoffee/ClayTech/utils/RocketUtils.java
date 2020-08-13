package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.api.ClayTechManager;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RocketUtils {
    public static int getFuel(ItemStack im) {
        if (ClayTechManager.isRocket(im)) {
            for (String str : Utils.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Fuel"))) {
                    str = StrUtils.getRightStr(str, "§6");
                    str = StrUtils.getLeftStr(str, "§9");
                    return new Integer(str).intValue();
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static int getMaxFuel(ItemStack im) {
        if (ClayTechManager.isRocket(im)) {
            for (String str : Utils.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Fuel"))) {
                    str = StrUtils.getRightStr(str, "§9/§a");
                    return new Integer(str).intValue();
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static void setFuel(ItemStack im, int fuel) {
        if (ClayTechManager.isRocket(im)) {
            int i = 0;
            List<String> lore = Utils.getLoreList(im);
            for (String str : lore) {
                if (str.startsWith(Lang.readGeneralText("Fuel"))) {
                    str = str.replaceAll("§6" + getFuel(im), "§6" + fuel);
                    lore.set(i, str);
                }
                i++;
            }
            Utils.setLore(im, lore);
        }
    }

    public static int getOxygen(ItemStack im) {
        if (ClayTechManager.isSpaceSuit(im) || ClayTechManager.isOxygenDistributer(im)) {
            for (String str : Utils.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Oxygen"))) {
                    str = StrUtils.getRightStr(str, "§6");
                    str = StrUtils.getLeftStr(str, "§9");
                    return new Integer(str).intValue();
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static void setOxygen(ItemStack im, int oxygen) {
        if (ClayTechManager.isSpaceSuit(im) || ClayTechManager.isOxygenDistributer(im)) {
            int i = 0;
            List<String> lore = Utils.getLoreList(im);
            for (String str : lore) {
                if (str.startsWith(Lang.readGeneralText("Oxygen"))) {
                    str = str.replaceAll("§6" + getOxygen(im), "§6" + oxygen);
                    lore.set(i, str);
                }
                i++;
            }
            Utils.setLore(im, lore);
        }
    }

    public static int getMaxOxygen(ItemStack im) {
        if (ClayTechManager.isSpaceSuit(im) || ClayTechManager.isOxygenDistributer(im)) {
            for (String str : Utils.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Oxygen"))) {
                    str = StrUtils.getRightStr(str, "§9/§a");
                    return new Integer(str).intValue();
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static int getProtectLevel(ItemStack im) {
        if (ClayTechManager.isSpaceSuit(im)) {
            for (String str : Utils.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("ProtectLevel"))) {
                    str = StrUtils.getRightStr(str, "§6");
                    return new Integer(str).intValue();
                }
            }
        } else {
            return -1;
        }
        return -1;
    }
}
