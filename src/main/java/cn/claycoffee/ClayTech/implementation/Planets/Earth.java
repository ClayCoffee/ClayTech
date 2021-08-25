package cn.claycoffee.ClayTech.implementation.Planets;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.api.Planet;
import cn.claycoffee.ClayTech.utils.Lang;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;

public class Earth extends ChunkGenerator {
    public Earth() {
        new Planet(ClayTech.getOverworld(),
                new CustomItem(Material.GREEN_GLAZED_TERRACOTTA, Lang.readPlanetsText("Earth")), this,
                Environment.NORMAL, true, 1, 0, 0, false).register();
    }
}
