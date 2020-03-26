package club.claycoffee.ClayTech.implementation.Planets;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import club.claycoffee.ClayTech.api.Planet;
import club.claycoffee.ClayTech.implementation.Planets.populators.MoonClayFusionOrePopulator;
import club.claycoffee.ClayTech.implementation.Planets.populators.MoonCoalPopulator;
import club.claycoffee.ClayTech.implementation.Planets.populators.MoonCopperOrePopulator;
import club.claycoffee.ClayTech.implementation.Planets.populators.MoonDiamondPopulator;
import club.claycoffee.ClayTech.implementation.Planets.populators.MoonKreepPopulator;
import club.claycoffee.ClayTech.utils.Lang;
import club.claycoffee.ClayTech.utils.Utils;

import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.google.common.collect.ImmutableList;

public class Moon extends ChunkGenerator {
	private SimplexOctaveGenerator sog;

	public Moon() {
		new Planet("CMoon", Utils.newItemD(Material.GRAY_GLAZED_TERRACOTTA, Lang.readPlanetsText("Moon")), this,
				Environment.NORMAL, false, 3, 100, 1, true).register();
	}

	@SuppressWarnings("deprecation")
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		ChunkData chunkData = createChunkData(world);
		if (sog == null) {
			sog = new SimplexOctaveGenerator(world.getSeed(), 1);
			sog.setScale(0.0025D);
		}
		// 最低一层为基岩
		chunkData.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int realX = chunkX * 16 + x;
				int realZ = chunkZ * 16 + z;
				double noiseValue = sog.noise(realX, realZ, Math.random(), Math.random());
				int height;
				height = (int) (Math.pow(noiseValue, 2) * 40D + 90D);
				for (int y = 1; y < height; y++) {
					chunkData.setBlock(x, y, z, Material.STONE);
				}
				if (height <= 66) {
					for (; height <= 66; height++) {
						chunkData.setBlock(x, height, z, Material.LAVA);
					}
				} else {
					chunkData.setBlock(x, height, z, Material.END_STONE);
				}
				biome.setBiome(x, z, Biome.THE_END);

			}
		}
		return chunkData;
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return ImmutableList.of(new MoonKreepPopulator(), new MoonDiamondPopulator(), new MoonCoalPopulator(), new MoonCopperOrePopulator(), new MoonClayFusionOrePopulator());
	}
}
