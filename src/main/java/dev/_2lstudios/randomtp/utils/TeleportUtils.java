package dev._2lstudios.randomtp.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class TeleportUtils {
    public static int randomNumber(final int min, final int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Location randomLocation(final World world, final int ratio) {
        final int x = randomNumber(0, ratio);
        final int z = randomNumber(0, ratio);

        for (int y = 64; y > 0; y--) {
            final Block block = world.getBlockAt(x, y, z);
            final String material = block.getType().name().toLowerCase();

            if (block.isEmpty() || material.contains("leaves")) {
                continue;
            }

            if (block.isLiquid() || material.contains("web")) {
                return null;
            }

            return new Location(world, x, y - 1, z);
        }

        return null;
    }

    public static Location findAvailableSafeLocation(final World world, final int ratio) {
        int tries = 0;
        Location loc = null;

        while (loc == null && tries < 5) {
            loc = randomLocation(world, ratio);
            tries++;
        }

        if (loc == null) {
            loc = world.getSpawnLocation();
        }

        return loc;
    }
}
