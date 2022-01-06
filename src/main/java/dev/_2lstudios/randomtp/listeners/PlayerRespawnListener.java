package dev._2lstudios.randomtp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import dev._2lstudios.randomtp.RandomTP;

public class PlayerRespawnListener implements Listener {

    private RandomTP plugin;

    public PlayerRespawnListener(final RandomTP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent e) {
        e.setRespawnLocation(this.plugin.getPlayerSpawn(e.getPlayer()));
    }
}
