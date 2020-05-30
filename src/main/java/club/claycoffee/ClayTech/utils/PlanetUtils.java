package club.claycoffee.ClayTech.utils;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.Planet;

public class PlanetUtils {
	private static final int[] planet = { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41,
			42, 43 };
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
				int x = new Random().nextInt(10000);
				int z = new Random().nextInt(10000);
				int y = getHighestBlockAt(w, x, z);
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
		int i = 0;
		int c = 0;
		List<Planet> pl = ClayTech.getPlanets();
		for (Planet each : pl) {
			c++;
			if (c > (currentPage - 1) * 21 && c <= currentPage * 21) {
				i++;
				ItemStack di = each.getDisplayStack();
				di = Utils.setLoreList(di, new String[] {
						Lang.readMachinesText("DISTANCE_TO_PLANET").replaceAll("%ly%",
								"" + PlanetUtils.getDistance(current, each)),
						Lang.readMachinesText("FUEL_TO_PLANET").replaceAll("%fuel%",
								"" + PlanetUtils.getFuel(current, each)),
						Lang.readMachinesText("PLANET_HABITABLE").replaceAll("%habitable%",
								PlanetUtils.booleanToString(each.getHabitable())),
						Lang.readMachinesText("PLANET_GRAVITY").replaceAll("%gravity%", "1/" + each.getGravity()),
						Lang.readMachinesText("PLANET_HARM_LEVEL").replaceAll("%harm_level%",
								each.getHarmLevel() + "") });
				Preset.setItem(planet[i - 1], di);
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
}
