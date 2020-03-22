package club.claycoffee.ClayTech.implementation.Planets.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import club.claycoffee.ClayTech.ClayTechItems;
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
				source.getBlock(x, y, z).setType(ClayTechItems.KREEP_ROCK.getType());
				BlockStorage.addBlockInfo(source.getBlock(x, y, z), "id", "KREEP_ROCK",true);
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
				y = Math.max(y - 1, 29);
				break;
			default:
				z--;
				break;
			}
		}
	}

}
