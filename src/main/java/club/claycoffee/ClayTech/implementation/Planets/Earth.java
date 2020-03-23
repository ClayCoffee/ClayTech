package club.claycoffee.ClayTech.implementation.Planets;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

public class Earth extends ChunkGenerator {
	public Earth() {
		new Planet(ClayTech.getOverworld(),
				Utils.newItemD(Material.GREEN_GLAZED_TERRACOTTA, Lang.readPlanetsText("Earth")), this,
				Environment.NORMAL, true, 1, 0, 0, false).register();
	}
}
