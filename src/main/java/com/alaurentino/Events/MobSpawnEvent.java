package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Anderson Laurentino on 06/01/2017.
 */
public class MobSpawnEvent implements Listener {
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if(!(e.getEntity() instanceof Player) && e.getEntity().getWorld().getName().equals(FileManager.getConfig().getString("hub_world")))
            e.setCancelled(FileManager.getConfig().getBoolean("protection.spawn_mobs"));
    }
}
