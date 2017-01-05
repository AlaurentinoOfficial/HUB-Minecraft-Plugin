package com.alaurentino.Events;

import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class ServerListEvent implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent e) {
        String icon = FileManager.getConfig().getString("icon");
        String s = FileManager.getConfig().getString("server_motd.one");
        String s2 = FileManager.getConfig().getString("server_motd.two");
        boolean iconenable = FileManager.getConfig().getBoolean("icon_enable");
        boolean motd = FileManager.getConfig().getBoolean("MOTD_Enable");
        int max = FileManager.getConfig().getInt("MaxPlayers");
        if (motd) {
            e.setMotd(MessageManager.filterMsg(s + "\n" + s2));
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
            Bukkit.broadcast(ChatColor.RED + "[HUB] [ERROR] - Please, set a correct path for the icon! ", "icon - in config.yml");
        }
        catch (UnsupportedOperationException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
