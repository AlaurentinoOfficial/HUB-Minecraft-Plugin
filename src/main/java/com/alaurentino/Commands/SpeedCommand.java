package com.alaurentino.Commands;

import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;

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
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speedStatus").replaceAll("<Speed>", String.valueOf(speed)));
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speedStatusNotPermission"));
                } else if(args.length == 1) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.setspeed")) {
                        try {
                            Float speed = Float.parseFloat(args[0]);
                            speed = speed > 10 ? 10 : speed;
                            speed = speed < 0 ? 0 : speed;

                            ((Player) sender).getPlayer().setFlySpeed(speed / 10);
                            ((Player) sender).getPlayer().setWalkSpeed(speed / 10);
                            ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setspeed").replaceAll("<Speed>", String.valueOf(speed)));
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speed"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setspeedNotPermission"));
                }
                else if(args.length == 2 && !Bukkit.getPlayer(args[0]).equals(null)) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.speed.admin")) {
                        try {
                            Float speed = Float.parseFloat(args[1]);
                            speed = speed > 10 ? 10 : speed;
                            speed = speed < 0 ? 0 : speed;

                            Bukkit.getPlayer(args[0]).setFlySpeed(speed / 10);
                            Bukkit.getPlayer(args[0]).setWalkSpeed(speed / 10);
                            Bukkit.getPlayer(args[0]).sendMessage(MessageManager.getMessage(Bukkit.getPlayer(args[0]), "setspeed").replaceAll("<Speed>", String.valueOf(speed)));
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speed"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setspeedNotPermission"));
                }
                else
                    sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "speed"));
            }
            else {
                if(args.length == 1)
                    sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
                else if(args.length == 2 && !Bukkit.getPlayer(args[0]).equals(null)) {
                    try {
                        Float speed = Float.parseFloat(args[1]);
                        speed = speed > 10 ? 10 : speed;
                        speed = speed < 0 ? 0 : speed;

                        Bukkit.getPlayer(args[0]).setFlySpeed(speed / 10);
                        Bukkit.getPlayer(args[0]).setWalkSpeed(speed / 10);
                        Bukkit.getPlayer(args[0]).sendMessage(MessageManager.getMessage(Bukkit.getPlayer(args[0]), "setspeed").replaceAll("<Speed>", String.valueOf(speed)));
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
            sender.sendMessage(MessageManager.getMessage("speed"));
        }
        return true;
    }
}
