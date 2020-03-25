package club.claycoffee.ClayTech.implementation.Planets.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import club.claycoffee.ClayTech.ClayTechItems;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class MoonKreepPopulator extends BlockPopulator {

	@Override
	public void populate(@NotNull World world, @NotNull Random random, @NotNull Chunk source) {
		int decreaseY = random.nextInt(9) + 1;
		int x = random.nextInt(16);
		int y = 30 - decreaseY;
		int z = random.nextInt(16);
		int count = 0;
		while (random.nextDouble() < 0.8D && count <= 4) {
			if (source.getBlock(x, y, z).getType() == Material.STONE) {
				if (!SlimefunPlugin.getRegistry().getWorlds().containsKey(world.getName())) {
					BlockStorage bs = new BlockStorage(world);
					SlimefunPlugin.getRegistry().getWorlds().put(world.getName(), bs);
				}
				source.getBlock(x, y, z).setType(ClayTechItems.KREEP_ROCK.getType());
				BlockStorage.addBlockInfo(source.getBlock(x, y, z), "id", "KREEP_ROCK", true);
				count++;

			}

			switch (random.nextInt(6)) {
			case 0:
				x = Math.min(x + 1, 15);
				break;
			case 1:
				y = Math.min(y + 1, 20);
				break;
			case 2:
				z = Math.min(z + 1, 15);
				break;
			case 3:
				x = Math.max(x - 1, 0);
				break;
			case 4:
				y = Math.max(y - 1, 30);
				break;
			default:
				z = Math.max(z - 1, 0);
				break;
			}
		}
	}

}
