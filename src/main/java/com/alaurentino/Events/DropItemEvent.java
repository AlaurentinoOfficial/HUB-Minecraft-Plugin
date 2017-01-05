package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class DropItemEvent implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(FileManager.getConfig().getBoolean("disable_drop_item"));
    }
}
