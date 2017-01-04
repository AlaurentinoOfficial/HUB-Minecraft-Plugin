package com.alaurentino.Managers;

import com.alaurentino.HUB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class MessageFilter {

    public static String serverTagsFilter(String message) {
        return message.replaceAll("<Server>", HUB.getInstace.getServer().getName())
                      .replaceAll("<Version>", "1.11")
                      .replaceAll("<Motd>", HUB.getInstace.getServer().getMotd())
                      .replaceAll("<MaxPlayers>", String.valueOf(HUB.getInstace.getServer().getMaxPlayers()))
                      .replaceAll("<OnlinePlayers>", String.valueOf(HUB.getInstace.getServer().getOnlinePlayers().size()))
                      .replaceAll("<MaxMemory>", String.valueOf(Runtime.getRuntime().maxMemory()/1024)+"GB")
                      .replaceAll("<AllocatedMemory>", String.valueOf(Runtime.getRuntime().totalMemory()/1024)+"GB")
                      .replaceAll("<FreeMemory>", String.valueOf(Runtime.getRuntime().freeMemory()/1024)+"GB")
                      .replaceAll("<Developer>", "Alaurentino")
                      .replaceAll("<NewLine>", "\n")
                      .replaceAll("<br>", "\n");
    }

    public static String playerTagsFilter(Player player, String message) {
        return message.replaceAll("<Player>", player.getDisplayName())
                      .replaceAll("<PlayerUUID>", String.valueOf(player.getUniqueId()))
                      .replaceAll("<PlayerIpAddress>", player.getAddress().getHostName())
                      .replaceAll("<PlayerWorldName>", player.getWorld().getName())
                      .replaceAll("<PLayerWorldTime>", String.valueOf(player.getWorld().getTime())
                      .replaceAll("<PlayerExp>", String.valueOf(player.getExp())))
                      .replaceAll("<PlayerLavel>", String.valueOf(player.getLevel()))
                      .replaceAll("<PlayerFoodLevel>", String.valueOf(player.getFoodLevel()))
                      .replaceAll("<PlayerHealthLevel>", String.valueOf(player.getHealth()))
                      .replaceAll("<PlayerVelocity>", String.valueOf(player.getVelocity()))
                      .replaceAll("<PlayerWalkSpeed>", String.valueOf(player.getWalkSpeed()))
                      .replaceAll("<PlayerFlySpeed>", String.valueOf(player.getFlySpeed()))
                      .replaceAll("<PlayerLocation>", String.valueOf(player.getLocation()));
    }

    public static String colorFilter(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
