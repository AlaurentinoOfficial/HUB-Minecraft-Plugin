package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class LaunchpadEvent implements Listener {
    private List<String> jumpers = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().hasPermission("HUB.launchpad")
                && FileManager.getConfig().getBoolean("enable_launchpad")) {
            if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType().equals(Material.STONE_PLATE)) {
                e.getTo().getBlock().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_STONE_PRESSUREPLATE_CLICK_OFF, 1.5f, 1f);
                e.getTo().getBlock().getWorld().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 3);

                e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(FileManager.getConfig().getDouble("LaunchPower")));
                e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
                jumpers.add(e.getPlayer().getDisplayName());
            }
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
