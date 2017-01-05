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
public class SpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            if(sender instanceof Player) {
                if(args.length == 0) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.speedstatus")) {
                        Float speed = ((Player) sender).getPlayer().getWalkSpeed() * 10;
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speedStatus"));
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                } else if(args.length == 1) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.setspeed")) {
                        try {
                            setSpeed(((Player) sender).getPlayer(), args[0]);
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speed"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                }
                else if(args.length == 2) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.speed.admin")) {
                        try {
                            setSpeed(Bukkit.getPlayer(args[0]), args[1]);
                        }
                        catch (NumberFormatException e) {
                            ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speed"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                }
                else
                    sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speed"));
            }
            else {
                if(args.length == 1)
                    sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
                else if(args.length == 2) {
                    try {
                       setSpeed(Bukkit.getPlayer(args[0]), args[1]);
                    }
                    catch (NumberFormatException e) {
                        sender.sendMessage(MessageManager.getMessage("speed"));
                    }
                }
                else
                    sender.sendMessage(MessageManager.getMessage("speed"));
            }
        }
        catch (NullPointerException e) {
            sender.sendMessage(MessageManager.getMessage("playerNotFound"));
        }
        return true;
    }

    private void setSpeed(Player player, String arg) {
        Float speed = Float.parseFloat(arg);
        speed = speed > 10 ? 10 : speed;
        speed = speed < 0 ? 0 : speed;

        player.getPlayer().setFlySpeed(speed / 10);
        player.getPlayer().setWalkSpeed(speed / 10);
        player.getPlayer().sendMessage(MessageManager.getMessage(player.getPlayer(), "setSpeed").replaceAll("<Speed>", String.valueOf(speed)));
    }
}
