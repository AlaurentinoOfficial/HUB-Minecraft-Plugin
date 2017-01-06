package com.alaurentino.Commands;

import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 06/01/2017.
 */
public class TellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(((Player) sender).getPlayer().hasPermission("HUB.tell")) {
                if(args.length >= 2) {
                    if(Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]) != ((Player) sender).getPlayer()) {
                        Player source = ((Player) sender).getPlayer();
                        Player target = Bukkit.getPlayer(args[0]);

                        String message = "";
                        for(int i = 1; i < args.length; i++)
                            message += args[i] + " ";

                        source.sendMessage(filter(source, target, message, MessageManager.getMessage(source, "tellSender")));
                        target.sendMessage(filter(source, target, message, MessageManager.getMessage(target, "tellReciver")));
                    }
                    else
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "playerNotFound"));
                }
                else
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "tell"));
            }
            else
                ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
        }
        else
            sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
        return true;
    }

    private String filter(Player source, Player target, String tell, String message) {
        return message.replaceAll("\\{PlayerSource\\}", source.getDisplayName())
                      .replaceAll("\\{PlayerTarget\\}", target.getDisplayName())
                      .replaceAll("\\{Message\\}", tell);
    }
}
