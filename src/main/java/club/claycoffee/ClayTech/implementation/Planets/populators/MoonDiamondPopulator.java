package club.claycoffee.ClayTech.implementation.Planets.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;


public class MoonDiamondPopulator extends BlockPopulator {

	@Override
	public void populate(@NotNull World world, @NotNull Random random, @NotNull Chunk source) {
		int x = random.nextInt(16);
		int y = random.nextInt(15) + 1;
		int z = random.nextInt(16);
		int count = 0;
		while (random.nextDouble() < 0.9D && count <= 12) {
			if (source.getBlock(x, y, z).getType() == Material.STONE) {
				source.getBlock(x, y, z).setType(Material.DIAMOND_ORE);
			}

			switch (random.nextInt(6)) {
			case 0:
				x++;
				break;
			case 1:
				y++;
				break;
			case 2:
				z++;
				break;
			case 3:
				x--;
				break;
			case 4:
				y = Math.max(y - 1, 0);
				break;
			default:
				z--;
				break;
			}
		}
	}

}
