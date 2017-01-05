package com.alaurentino.Commands;

import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class HealthCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            if(sender instanceof Player) {
                if(args.length == 0) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.healthstatus"))
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "healthStatus"));
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                } else if(args.length == 1) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.sethealth")) {
                        try {
                            setHealth(((Player) sender).getPlayer(), args[0]);
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "health"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                }
                else if(args.length == 2) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.health.admin")) {
                        try {
                            setHealth(Bukkit.getPlayer(args[0]), args[1]);
                        }
                        catch (NumberFormatException e) {
                            ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "health"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                }
                else
                    sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "health"));
            }
            else {
                if(args.length == 1)
                    sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
                else if(args.length == 2) {
                    try {
                       setHealth(Bukkit.getPlayer(args[0]), args[1]);
                    }
                    catch (NumberFormatException e) {
                        sender.sendMessage(MessageManager.getMessage("health"));
                    }
                }
                else
                    sender.sendMessage(MessageManager.getMessage("health"));
            }
        }
        catch (NullPointerException e) {
            sender.sendMessage(MessageManager.getMessage("playerNotFound"));
        }
        return true;
    }

    private void setHealth(Player player, String arg) {
        double health = Float.parseFloat(arg);
        health = health > 20 ? 20 : health;
        health = health < 0 ? 0 : health;

        player.getPlayer().setHealth(health);
        player.getPlayer().sendMessage(MessageManager.getMessage(player.getPlayer(), "setHealth"));
    }
}
