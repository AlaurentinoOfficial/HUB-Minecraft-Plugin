package com.alaurentino.Events;

import com.alaurentino.HUB;
import com.alaurentino.Managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class RainEvent implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onWeatherChange(WeatherChangeEvent e) {
        final World w = Bukkit.getWorld(FileManager.getConfig().getString("hub_world"));

        if (FileManager.getConfig().getBoolean("disable_rain") && !w.hasStorm())
            e.setCancelled(true);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HUB.getInstace, new Runnable() {
            public void run() {
                try {
                    if (FileManager.getConfig().getBoolean("disable_rain") && w.hasStorm())
                        w.setStorm(false);
                }
                catch (Exception localException) {}
            }
        }, 5L);
    }
}
