package dev._2lstudios.randomtp;

import com.dotphin.milkshakeorm.MilkshakeORM;
import com.dotphin.milkshakeorm.providers.Provider;
import com.dotphin.milkshakeorm.repository.Repository;
import com.dotphin.milkshakeorm.utils.MapFactory;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.randomtp.listeners.PlayerJoinListener;
import dev._2lstudios.randomtp.listeners.PlayerRespawnListener;
import dev._2lstudios.randomtp.player.PlayerData;
import dev._2lstudios.randomtp.utils.TeleportUtils;

public class RandomTP extends JavaPlugin {
    private Repository<PlayerData> playerRepository;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        final Provider provider = MilkshakeORM.connect(this.getConfig().getString("database.uri"));
        this.playerRepository = MilkshakeORM.addRepository(PlayerData.class, provider,
                this.getConfig().getString("database.collection"));

        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerRespawnListener(this), this);
    }

    public boolean hasPlayerSpawn(final Player player) {
        final String uuid = player.getUniqueId().toString();
        PlayerData data = this.playerRepository
                .findOne(MapFactory.create("uuid", uuid));

        return data != null && data.spawnX != -1 && data.spawnY != -1 && data.spawnZ != -1;
    }

    public Location getPlayerSpawn(final Player player) {
        final String uuid = player.getUniqueId().toString();
        PlayerData data = this.playerRepository
                .findOne(MapFactory.create("uuid", uuid));

        if (data == null) {
            data = new PlayerData();
            data.uuid = uuid;
        }

        final World world = getServer().getWorlds().get(0);

        if (data.spawnX == -1 || data.spawnY == -1 || data.spawnZ == -1) {
            final Location randomLoc = TeleportUtils.findAvailableSafeLocation(world,
                    this.getConfig().getInt("teleport.ratio"));

            data.spawnX = randomLoc.getX();
            data.spawnY = randomLoc.getY();
            data.spawnZ = randomLoc.getZ();
            data.save();
        }

        return new Location(world, data.spawnX, data.spawnY,
                data.spawnZ);
    }
}