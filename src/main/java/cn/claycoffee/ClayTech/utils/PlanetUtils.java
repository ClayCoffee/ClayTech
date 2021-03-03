package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechBiomes;
import cn.claycoffee.ClayTech.api.Planet;
import com.wimbli.WorldBorder.BorderData;
import com.wimbli.WorldBorder.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlanetUtils {
    private static final int[] planet = {19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41,
            42, 43};
    private final static int MAX_TRY_TIMES = 40;

    public static int getHighestBlockAt(World w, int x, int z) {
        int currentHighestY = 0;
        for (int y = 0; y < 255; y++) {
            if (w.getBlockAt(x, y, z).getType() != Material.AIR) {
                currentHighestY = y;
            }
        }
        return currentHighestY;
    }

    public static Location findSafeLocation(World w) {
        boolean pass = false;
        Location ret = null;
        int i = 0;
        while (!pass) {
            // 最多寻找MAX_TRY_TIMES次,否则返回null.
            if (i <= MAX_TRY_TIMES) {
                int x;
                int z;
                if (ClayTech.isWorldBorderEnabled() && Config.Border(w.getName()) != null) {
                    BorderData border = Config.Border(w.getName());
                    x = new Random().nextInt(border.getRadiusX());
                    z = new Random().nextInt(border.getRadiusZ());
                }
                else {
                    x = new Random().nextInt(10000);
                    z = new Random().nextInt(10000);
                }
                int y = getHighestBlockAt(w, x, z);
                if (ClayTech.isWorldBorderEnabled()) {
                    BorderData border = Config.Border(w.getName());
                    if (border != null && !border.insideBorder(x, z)) {
                        i++;
                        continue;
                    }
                }
                Material BlockType = w.getBlockAt(x, y, z).getType();
                if (BlockType != Material.AIR && BlockType != Material.WATER && BlockType != Material.LAVA) {
                    ret = new Location(w, x + 0.0D, y + 0.0D, z + 0.0D);
                    pass = true;
                } else {
                    i++;
                }
            } else {
                return ret;
            }
        }
        return ret;
    }

    public static Planet getPlanet(World w) {
        for (Planet each : ClayTech.getPlanets()) {
            if (each.getPlanetWorldName().equalsIgnoreCase(w.getName())) {
                return each;
            }
        }
        return null;
    }

    public static int getDistance(Planet a, Planet b) {
        if (a.getDistance() > b.getDistance()) {
            return a.getDistance() - b.getDistance();
        }
        if (a.getDistance() < b.getDistance()) {
            return b.getDistance() - a.getDistance();
        }
        return 0;
    }

    public static int getFuel(Planet from, Planet to) {
        int distance = getDistance(from, to);
        if (distance == 0) {
            return 25;
        } else {
            return 25 + distance;
        }
    }

    public static String booleanToString(boolean b) {

        if (b == true) {
            return Lang.readGeneralText("Yes_1");
        } else {
            return Lang.readGeneralText("No_1");
        }
    }

    public static int getTotalPage() {
        if (ClayTech.getPlanets().size() > 21) {
            if (ClayTech.getPlanets().size() % 21 >= 1) {
                return (int) (ClayTech.getPlanets().size() / 21);
            } else {
                return ((int) ClayTech.getPlanets().size() / 21) + 1;
            }
        } else if (ClayTech.getPlanets().size() >= 1) {
            return 1;
        }
        return 0;
    }

    public static Inventory renderLauncherMenu(Planet current, Inventory Preset, int currentPage) {
        int v = 0;
        int c = 0;

        // 排列星球
        List<Planet> pl = new ArrayList<Planet>();
        for (Planet p : ClayTech.getPlanets()) {
            pl.add(p);
        }
        Planet[] pl2 = pl.toArray(new Planet[pl.size()]);
        List<Integer> d = new ArrayList<Integer>();
        for (Planet p : pl2) {
            d.add((Integer) PlanetUtils.getDistance(current, p));
        }
        Integer[] distance = d.toArray(new Integer[d.size()]);
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length - i - 1; j++) {
                if (distance[j].intValue() > distance[j + 1].intValue()) {
                    int temp = distance[j + 1];
                    distance[j + 1] = distance[j];
                    distance[j] = temp;

                    Planet temp2 = pl2[j + 1];
                    pl2[j + 1] = pl2[j];
                    pl2[j] = temp2;
                }
            }
        }
        pl = Arrays.asList(pl2);

        for (Planet each : pl) {
            c++;
            if (c > (currentPage - 1) * 21 && c <= currentPage * 21) {
                v++;
                ItemStack di = each.getDisplayStack();
                di = Utils.setLoreList(di, new String[]{
                        Lang.readMachinesText("DISTANCE_TO_PLANET").replaceAll("%ly%",
                                "" + PlanetUtils.getDistance(current, each)),
                        Lang.readMachinesText("FUEL_TO_PLANET").replaceAll("%fuel%",
                                "" + PlanetUtils.getFuel(current, each)),
                        Lang.readMachinesText("PLANET_HABITABLE").replaceAll("%habitable%",
                                PlanetUtils.booleanToString(each.getHabitable())),
                        Lang.readMachinesText("PLANET_GRAVITY").replaceAll("%gravity%", "1/" + each.getGravity()),
                        Lang.readMachinesText("PLANET_HARM_LEVEL").replaceAll("%harm_level%",
                                each.getHarmLevel() + "")});
                Preset.setItem(planet[v - 1], di);
            } else if (c > currentPage * 21) {
                break;
            }
        }
        int totalPage = PlanetUtils.getTotalPage();
        if (currentPage == 1) {
            Preset.setItem(46,
                    Utils.newItemD(Material.LIME_STAINED_GLASS_PANE, Lang.readMachinesText("PREVIOUS_PAGE_CANT_USE")));
        } else {
            Preset.setItem(46, Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
                    Lang.readMachinesText("PREVIOUS_PAGE") + " (" + (currentPage - 1) + "/" + totalPage + ")"));
        }
        if (currentPage == totalPage) {
            Preset.setItem(52,
                    Utils.newItemD(Material.LIME_STAINED_GLASS_PANE, Lang.readMachinesText("NEXT_PAGE_CANT_USE")));
        } else {
            Preset.setItem(52, Utils.newItemD(Material.LIME_STAINED_GLASS_PANE,
                    Lang.readMachinesText("NEXT_PAGE") + "(" + (currentPage + 1) + "/" + totalPage + ")"));
        }
        return Preset;
    }

    public static ClayTechBiomes getBiome(int height, boolean hasRiver, boolean hasLava, boolean hasCrater) {
        if (height <= 62) {
            if (hasRiver)
                return ClayTechBiomes.RIVER;
            if (hasLava)
                return ClayTechBiomes.LAVA_RIVER;
            if (hasRiver && hasLava) {
                if (hasCrater) {
                    return ClayTechBiomes.LAVA_RIVER;
                } else {
                    if (height <= 39)
                        return ClayTechBiomes.LAVA_RIVER;
                    else
                        return ClayTechBiomes.CRATER;
                }
            }
            if (hasCrater)
                return ClayTechBiomes.CRATER;
            return ClayTechBiomes.PLAIN;
        } else if (height > 62 && height <= 71)
            return ClayTechBiomes.PLAIN;
        else if (height > 72)
            return ClayTechBiomes.MOUNTAIN;

        return ClayTechBiomes.PLAIN;
    }
}
