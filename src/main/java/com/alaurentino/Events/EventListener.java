package com.alaurentino.Events;

import com.alaurentino.API.TitleAPI;
import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import com.alaurentino.Scoreboard.Board;
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
        if(!FileManager.getLanguage().contains(e.getPlayer().getDisplayName()))
            FileManager.setLanguage(e.getPlayer().getDisplayName(), FileManager.getConfig().get("default_language"));

        e.getPlayer().teleport(FileManager.getSpawn(e.getPlayer()));

        if(FileManager.getConfig().getBoolean("title_enable"))
            TitleAPI.sendTitle(e.getPlayer(),
                               FileManager.getConfig().getInt("fade_in"),
                               FileManager.getConfig().getInt("fade_out"),
                               FileManager.getConfig().getInt("time"),
                               MessageManager.filterMsg(e.getPlayer(), FileManager.getConfig().getString("title")),
                               MessageManager.filterMsg(e.getPlayer(), FileManager.getConfig().getString("sub_title")));

        for (String line : MessageManager.getMessageList(e.getPlayer(), "joinPlayer"))
            e.getPlayer().sendMessage(MessageManager.filterMsg(e.getPlayer(), line));

        e.setJoinMessage(MessageManager.getMessage(e.getPlayer(), "join"));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String msg = MessageManager.getMessage(e.getPlayer(), "leave");
        msg = MessageManager.filterMsg(e.getPlayer(), msg);
        e.setQuitMessage(msg);

        Board.increments.remove(e.getPlayer());
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
    public void onChat(AsyncPlayerChatEvent e) {
        e.setMessage(MessageManager.filterMsg(e.getPlayer(), e.getMessage()));
    }
}
