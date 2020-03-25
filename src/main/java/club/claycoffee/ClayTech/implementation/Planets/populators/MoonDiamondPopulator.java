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
				x = Math.min(x + 1, 15);
				break;
			case 1:
				y = Math.min(y + 1, 15);
				break;
			case 2:
				z = Math.min(z + 1, 15);
				break;
			case 3:
				x = Math.max(x - 1, 0);
				break;
			case 4:
				y = Math.max(y - 1, 0);
				break;
			default:
				z = Math.max(z - 1, 0);
				break;
			}
		}
	}

}
