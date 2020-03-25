package club.claycoffee.ClayTech.implementation.resources;

import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.ClayTechItems;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Slimefunutils;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;

public class ClayFuel implements GEOResource {
	private final NamespacedKey key = new NamespacedKey(ClayTech.getInstance(), "CLAY_FUEL");

	public ClayFuel() {
		Slimefunutils.registerResource(this);
	}

	@Override
	public @NotNull NamespacedKey getKey() {
		return key;
	}

	@Override
	public int getDefaultSupply(Environment environment, Biome biome) {
		if (environment == Environment.NORMAL && biome == Biome.THE_END) {
			return 12;
		} else if (environment == Environment.NORMAL) {
			return 4;
		} else if (environment == Environment.NETHER) {
			return 5;
		} else {
			return 0;
		}
	}

	@Override
	public int getMaxDeviation() {
		return 2;
	}

	@Override
	public String getName() {
		return Lang.readResourcesText("CLAY_FUEL");
	}

	@Override
	public ItemStack getItem() {
		return ClayTechItems.CLAY_FUEL.clone();
	}

	@Override
	public boolean isObtainableFromGEOMiner() {
		return true;
	}

}
