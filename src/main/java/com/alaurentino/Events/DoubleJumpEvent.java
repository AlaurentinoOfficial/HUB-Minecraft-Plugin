package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class DoubleJumpEvent implements Listener {
    private List<String> jumpers = new ArrayList<>();

    @EventHandler
    public void onPlayerToggleFlght(PlayerToggleFlightEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setCancelled(true);
        e.getPlayer().setAllowFlight(false);
        e.getPlayer().setFlying(false);
        e.getPlayer().setVelocity(new Vector(0, 0.5 ,0));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE
                && e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR
                && !e.getPlayer().isFlying()
                && e.getPlayer().hasPermission("HUB.doublejump")
                && FileManager.getConfig().getBoolean("enable_double_jump")) {
            e.getPlayer().setAllowFlight(true);
            jumpers.add(e.getPlayer().getDisplayName());
        }
    }

    @EventHandler
    public void noFallDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            if(jumpers.contains(((Player) e.getEntity()).getPlayer().getDisplayName()) && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
                jumpers.remove(((Player) e.getEntity()).getPlayer().getDisplayName());
            }
        }
    }
}
