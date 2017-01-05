package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class DoubleJumpEvent implements Listener {

    @EventHandler
    public void onPlayerToggleFlght(PlayerToggleFlightEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setCancelled(true);
        e.getPlayer().setAllowFlight(false);
        e.getPlayer().setFlying(false);
        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5).setY(1));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE
                && e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR
                && !e.getPlayer().isFlying()
                && e.getPlayer().hasPermission("HUB.doublejump")
                && FileManager.getConfig().getBoolean("enable_double_jump"))
            e.getPlayer().setAllowFlight(true);
    }
}
