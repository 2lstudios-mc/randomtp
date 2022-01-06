package dev._2lstudios.randomtp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.randomtp.RandomTP;

public class PlayerJoinListener implements Listener {

    private RandomTP plugin;

    public PlayerJoinListener(final RandomTP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        if (!this.plugin.hasPlayerSpawn(e.getPlayer())) {
            e.getPlayer().teleport(this.plugin.getPlayerSpawn(e.getPlayer()));
        }
    }
}
