package com.alaurentino.Commands;

import com.alaurentino.Managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class RulesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(((Player) sender).getPlayer().hasPermission("HUB.rules"))
                for(String line : MessageManager.getMessageList(((Player) sender).getPlayer(), "rules"))
                    ((Player) sender).getPlayer().sendMessage(MessageManager.filterMsg(line));
            else
                ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
        }
        else
            for(String line : MessageManager.getMessageList("rules"))
                sender.sendMessage(MessageManager.filterMsg(line));
        return true;
    }
}
