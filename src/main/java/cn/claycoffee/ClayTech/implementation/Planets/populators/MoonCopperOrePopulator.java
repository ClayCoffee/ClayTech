package cn.claycoffee.ClayTech.implementation.Planets.populators;

import cn.claycoffee.ClayTech.ClayTech;
import cn.claycoffee.ClayTech.ClayTechItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.scheduler.BukkitRunnable;
import org.eclipse.jdt.annotation.NonNull;

import java.util.Random;

public class MoonCopperOrePopulator extends BlockPopulator {

    @Override
    public void populate(@NonNull World world, @NonNull Random random, @NonNull Chunk source) {
        new BukkitRunnable() {

            @Override
            public void run() {
                int tryc = 5 + random.nextInt(4);
                for (int i = 0; i < tryc; i++) {
                    int x = random.nextInt(16);
                    int y = random.nextInt(100) + 1;
                    int z = random.nextInt(16);
                    int count = 0;
                    while (count <= 5 || random.nextDouble() < 0.92D && count <= 8) {
                        final int tx = x;
                        final int ty = y;
                        final int tz = z;
                        Block sourceb = source.getBlock(x, y, z);
                        if (sourceb.getType() == Material.STONE) {
                            if (!SlimefunPlugin.getRegistry().getWorlds().containsKey(world.getName())) {
                                BlockStorage bs = new BlockStorage(world);
                                SlimefunPlugin.getRegistry().getWorlds().put(world.getName(), bs);
                            }
                            if (BlockStorage.hasBlockInfo(sourceb.getLocation())) return;
                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    source.getBlock(tx, ty, tz).setType(ClayTechItems.COPPER_ORE.getType(), false);
                                    BlockStorage.addBlockInfo(source.getBlock(tx, ty, tz), "id", "COPPER_ORE",
                                            true);

                                }

                            }.runTask(ClayTech.getInstance());
                            count++;
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
                                y = Math.max(y - 1, 1);
                                break;
                            default:
                                z = Math.max(z - 1, 0);
                                break;
                        }
                    }

                }

            }

        }.runTaskAsynchronously(ClayTech.getInstance());

    }

}
