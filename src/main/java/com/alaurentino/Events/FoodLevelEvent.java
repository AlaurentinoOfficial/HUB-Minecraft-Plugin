package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.io.File;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class FoodLevelEvent implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if(FileManager.getConfig().getString("hub_world").equals(e.getEntity().getWorld().getName())
                && e.getEntityType() == EntityType.PLAYER)
            e.setCancelled(FileManager.getConfig().getBoolean("disable_hunger"));
    }
}
