package com.fragondemand.MrTrousers.Reincarnate;

import com.fragondemand.MrTrousers.Reincarnate.SpawnLocations;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;


/**
 * Handle events for all Player related events
 * @author MrTrousers
 */
public class ReincarnatePlayerListener extends PlayerListener {
    private final Reincarnate plugin;

    public ReincarnatePlayerListener(Reincarnate instance) {
        plugin = instance;
    }
    
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Location location = SpawnLocations.getSpawnLocation(event.getPlayer().getName());
        System.out.println("RESPAWN");
        System.out.println(location);
        if (location != null)
            event.setRespawnLocation(location);    
    }
}
