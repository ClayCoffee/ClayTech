package club.claycoffee.ClayTech;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

public class ClayTechData {
	public static Map<Inventory, Block> RunningLaunchersG = new HashMap<Inventory, Block>();
	public static Map<Inventory, Block> RunningInjectors = new HashMap<Inventory, Block>();
	public static Map<Inventory, Block> RunningInjectorsOxygen = new HashMap<Inventory, Block>();
	public static String currentVersion = "";
}
