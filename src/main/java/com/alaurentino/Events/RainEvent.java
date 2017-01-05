package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class RainEvent implements Listener {
    @EventHandler
    public void noRain(RainEvent e) {
        Bukkit.getWorld(FileManager.getConfig().getString("hub_world")).setStorm(!FileManager.getConfig().getBoolean("disable_rain"));
    }
}
