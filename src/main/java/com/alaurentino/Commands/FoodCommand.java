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
public class FoodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            if(sender instanceof Player) {
                if(args.length == 0) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.foodstatus"))
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "foodStatus"));
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                } else if(args.length == 1) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.setfood")) {
                        try {
                            setFoodLevel(((Player) sender).getPlayer(), args[0]);
                        }
                        catch (NumberFormatException e) {
                            sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "food"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                }
                else if(args.length == 2) {
                    if(((Player) sender).getPlayer().hasPermission("HUB.food.admin")) {
                        try {
                            setFoodLevel(Bukkit.getPlayer(args[0]), args[1]);
                        }
                        catch (NumberFormatException e) {
                            ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "food"));
                        }
                    }
                    else
                        sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
                }
                else
                    sender.sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "food"));
            }
            else {
                if(args.length == 1)
                    sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
                else if(args.length == 2) {
                    try {
                       setFoodLevel(Bukkit.getPlayer(args[0]), args[1]);
                    }
                    catch (NumberFormatException e) {
                        sender.sendMessage(MessageManager.getMessage("food"));
                    }
                }
                else
                    sender.sendMessage(MessageManager.getMessage("food"));
            }
        }
        catch (NullPointerException e) {
            sender.sendMessage(MessageManager.getMessage("playerNotFound"));
        }
        return true;
    }

    private void setFoodLevel(Player player, String arg) {
        int food = Integer.parseInt(arg);
        food = 20 < food ? 20 : food;
        food = 0 > food ? 0 : food;

        player.getPlayer().setFoodLevel(food);
        player.getPlayer().sendMessage(MessageManager.getMessage(player.getPlayer(), "setFood"));
    }
}
