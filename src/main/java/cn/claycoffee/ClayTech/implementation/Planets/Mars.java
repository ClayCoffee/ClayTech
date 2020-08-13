package cn.claycoffee.ClayTech.implementation.Planets;

import cn.claycoffee.ClayTech.api.Planet;
import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Utils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.Random;

public class Mars extends ChunkGenerator {
    private SimplexOctaveGenerator sog;

    public Mars() {
        new Planet("CMars", Utils.newItemD(Material.YELLOW_GLAZED_TERRACOTTA, Lang.readPlanetsText("Mars")), this,
                Environment.NORMAL, true, 1, 100, 0, false).register();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        if (sog == null) {
            sog = new SimplexOctaveGenerator(world.getSeed(), 1);
            sog.setScale(0.00125D);
        }
        // 最低一层为基岩
        chunkData.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int realX = chunkX * 16 + x;
                int realZ = chunkZ * 16 + z;
                double noise1 = sog.noise(realX, realZ, 5D, 12D);
                double noise2 = sog.noise(realX, realZ, 128D, 6D);
                double noise3 = sog.noise(realX, realZ, 8D, 128D);
                double noise4 = sog.noise(realX, realZ, 256D, 16D);
                double noise5 = sog.noise(realX, realZ, 64D, 188D);

                double[] sort = new double[]{noise1, noise2, noise3, noise4, noise5};
                Arrays.sort(sort);

                double basicNoiseValue = sort[0];

                int basicHeight = (int) (basicNoiseValue * 40D + 45D);
                int finalHeight = basicHeight;
                finalHeight += 7D;

                chunkData.setBlock(x, 0, z, Material.BEDROCK);

                for (int eh = 1; eh < finalHeight - 1; eh++) {
                    chunkData.setBlock(x, eh, z, Material.STONE);
                }

                double c1 = random.nextDouble();
                if (c1 >= 0.4) {
                    // 生成沙砾
                    chunkData.setBlock(x, finalHeight - 1, z, Material.GRAVEL);
                } else if (c1 <= 0.4 && c1 >= 0.3) {
                    // 生成红沙
                    chunkData.setBlock(x, finalHeight - 1, z, Material.RED_SAND);
                } else {
                    chunkData.setBlock(x, finalHeight - 1, z, Material.RED_SANDSTONE);
                }

                // 最顶端红沙石覆盖
                chunkData.setBlock(x, finalHeight, z, Material.RED_SANDSTONE);

                if (finalHeight + 1 <= 57) {
                    for (int i = 57; i >= finalHeight + 1; i--) {
                        chunkData.setBlock(x, i, z, Material.LAVA);
                    }
                } else if (finalHeight + 1 <= 60) {
                    for (int i = 60; i >= finalHeight + 1; i--) {
                        chunkData.setBlock(x, i, z, Material.RED_SANDSTONE);
                    }
                }

                biome.setBiome(x, z, Biome.DESERT);
            }
        }
        return chunkData;
    }

//	@Override
//	public List<BlockPopulator> getDefaultPopulators(World world) {
//  	TODO 点缀
//	}
}
