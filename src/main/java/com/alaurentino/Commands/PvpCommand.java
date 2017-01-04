package com.alaurentino.Commands;

import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class PvpCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(((Player) sender).getPlayer().hasPermission("HUB.pvp")) {
                if(args.length == 1 && (args[0].toLowerCase().equals("on") || args[0].toLowerCase().equals("off"))) {
                    String worldName = ((Player) sender).getPlayer().getWorld().getName();
                    setPvp(sender, args[0], worldName);
                }
                else if(args.length == 2
                        && (args[1].toLowerCase().equals("on") || args[1].toLowerCase().equals("off"))
                        && Bukkit.getWorld(args[0]) != null) {
                    String worldName = args[0];
                    setPvp(sender, args[1], worldName);
                }
                else ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "pvp"));
            }
            else ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "pvpNotPermission"));
        }
        else {
            if(args.length == 2
                    && (args[1].toLowerCase().equals("on") || args[1].toLowerCase().equals("off"))
                    && Bukkit.getWorld(args[0]) != null) {
                String worldName = args[0];
                setPvp(sender, args[1], worldName);
            }
            else sender.sendMessage(MessageManager.getMessage("pvp"));
        }
        return true;
    }

    private void setPvp(CommandSender sender, String arg, String worldName) {
        if(sender instanceof Player) {
            List<String> list = FileManager.getConfig().getStringList("protection.pvp");
            if(arg.toLowerCase().equals("on")) {
                if(list.contains(worldName)) {
                    list.remove(worldName);
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "pvpEnable"));
                }
                else ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "pvpAlreadyActivated"));
            }
            else {
                if(!list.contains(worldName)) {
                    list.add(worldName);
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "pvpDisable"));
                }
                else ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "pvpAlreadyDisable"));
            }

            FileManager.getConfig().set("protection.pvp", list);
            FileManager.saveConfig();
        }
        else {
            List<String> list = FileManager.getConfig().getStringList("protection.pvp");
            if(arg.toLowerCase().equals("on")) {
                if(list.contains(worldName)) {
                    list.remove(worldName);
                    sender.sendMessage(MessageManager.getMessage("pvpEnable"));
                }
                else sender.sendMessage(MessageManager.getMessage("pvpAlreadyActivated"));
            }
            else {
                if(!list.contains(worldName)) {
                    list.add(worldName);
                    sender.sendMessage(MessageManager.getMessage("pvpDisable"));
                }
                else sender.sendMessage(MessageManager.getMessage("pvpAlreadyDisable"));
            }

            FileManager.getConfig().set("protection.pvp", list);
            FileManager.saveConfig();
        }
    }
}
