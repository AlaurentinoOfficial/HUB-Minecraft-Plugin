package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class ProtectionEvent implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player && e.getEntity().getWorld().getName().equals(FileManager.getConfig().getString("hub_world"))) {
            // PVP protection
            if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.pvp"));

            // Fall protection
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL)
                if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                    e.setCancelled(FileManager.getConfig().getBoolean("protection.fall"));

            // Fire protection
            if(e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.fire"));

            // Lava protection
            if(e.getCause() == EntityDamageEvent.DamageCause.LAVA)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.lava"));

            // Drowning protection
            if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.drowning"));

            // Suffocation protection
            if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.suffocation"));

            // Explosion protection
            if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.explosion"));

            // Wither protection
            if(e.getCause() == EntityDamageEvent.DamageCause.WITHER)
                e.setCancelled(FileManager.getConfig().getBoolean("protection.wither"));
        }
    }
}
