package com.alaurentino;

import com.alaurentino.API.TitleAPI;
import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.io.File;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //if(!FileManager.getLanguage().contains(e.getPlayer().getDisplayName()))
        FileManager.getLanguage().set(e.getPlayer().getDisplayName(), FileManager.getConfig().get("default_language"));

        e.getPlayer().teleport(FileManager.getSpawn(e.getPlayer()));

        TitleAPI.sendTitle(e.getPlayer(),
                           FileManager.getConfig().getInt("fade_in"),
                           FileManager.getConfig().getInt("fade_out"),
                           FileManager.getConfig().getInt("time"),
                           FileManager.getConfig().getString("title"),
                           FileManager.getConfig().getString("sub_title"));

        for (String line : MessageManager.getMessageList(e.getPlayer(), "joinPlayer"))
            e.getPlayer().sendMessage(MessageManager.filterMsg(e.getPlayer(), line));

        e.setJoinMessage(MessageManager.getMessage(e.getPlayer(), "join"));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String msg = MessageManager.getMessage(e.getPlayer(), "leave");
        msg = MessageManager.filterMsg(e.getPlayer(), msg);
        e.setQuitMessage(msg);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(MessageManager.getMessage(e.getEntity().getPlayer(), "playerDeath"));
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        e.setRespawnLocation(FileManager.getSpawn(e.getPlayer()));
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        String icon = FileManager.getConfig().getString("icon");
        String s = FileManager.getConfig().getString("server_motd.one");
        String s2 = FileManager.getConfig().getString("server_motd.two");
        boolean iconenable = FileManager.getConfig().getBoolean("icon_enable");
        boolean motd = FileManager.getConfig().getBoolean("MOTD_Enable");
        int max = FileManager.getConfig().getInt("MaxPlayers");
        if (motd) {
            s = MessageManager.filterMsg(s);
            s2 = MessageManager.filterMsg(s2);

            e.setMotd(s + "\n" + s2);
            e.setMaxPlayers(max);
        }
        else if (!motd) {
            ConsoleCommandSender c = Bukkit.getConsoleSender();
        }
        try {
            if (iconenable)
                e.setServerIcon(Bukkit.loadServerIcon(new File(icon)));
        }
        catch (IllegalArgumentException e2) {
            Bukkit.broadcast(ChatColor.RED + "[ServerMotd] [ERROR] - Please, set a correct path for the icon! ", "icon - in Config.yml");
        }
        catch (UnsupportedOperationException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setMessage(MessageManager.filterMsg(e.getPlayer(), e.getMessage()));
    }

    @EventHandler
    public void noHunger(FoodLevelChangeEvent e) {
        if (e.getEntityType() == EntityType.PLAYER)
            for(String worldName : FileManager.getConfig().getStringList("disable_hunger_world"))
                if(worldName.equals(e.getEntity().getWorld().getName()))
                    e.setCancelled(true);
    }

    @EventHandler
    public void noRain(WeatherChangeEvent e) {
        for (String worldName : FileManager.getConfig().getStringList("disable_rain_world"))
            if (worldName.equals(e.getWorld().getName()))
                e.setCancelled(true);
    }

    @EventHandler
    public void noFallDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {

            // Protection by pvp
            if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                for(String worldName : FileManager.getConfig().getStringList("protection.pvp"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by fall
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL)
                for(String worldName : FileManager.getConfig().getStringList("protection.fall"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by fire
            if(e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)
                for(String worldName : FileManager.getConfig().getStringList("protection.fire"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by lava
            if(e.getCause() == EntityDamageEvent.DamageCause.LAVA)
                for(String worldName : FileManager.getConfig().getStringList("protection.lava"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by drowning
            if(e.getCause() == EntityDamageEvent.DamageCause.DROWNING)
                for(String worldName : FileManager.getConfig().getStringList("protection.drowning"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by suffocation
            if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION)
                for(String worldName : FileManager.getConfig().getStringList("protection.suffocation"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by explosion
            if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
                for(String worldName : FileManager.getConfig().getStringList("protection.explosion"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);

            // Protection by wither
            if(e.getCause() == EntityDamageEvent.DamageCause.WITHER)
                for(String worldName : FileManager.getConfig().getStringList("protection.wither"))
                    if(worldName.equals(e.getEntity().getWorld().getName()))
                        e.setCancelled(true);
        }
    }
}
