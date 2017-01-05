package com.alaurentino.Commands;

import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class LanguageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(((Player) sender).getPlayer().hasPermission("HUB.language") && args.length <= 1) {
                if(args.length == 1) {
                    if(FileManager.getLanguage().contains(args[0])) {
                        FileManager.setLanguage(((Player) sender).getPlayer().getDisplayName(), args[0]);
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setLang"));
                    }
                    else
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "lang"));
                }
                else
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "lang"));
            }
            else
                ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
        }
        else
            sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
        return true;
    }
}
