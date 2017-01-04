package com.alaurentino.Commands;

import com.alaurentino.HUB;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(((Player) sender).getPlayer().hasPermission("HUB.gamemode")) {
                if(args.length == 1)
                    setGamemode(((Player) sender).getPlayer(), args[0]);
                else if(args.length == 2) {
                    if(Bukkit.getPlayer(args[0]) != null)
                        setGamemode(Bukkit.getPlayer(args[0]), args[0]);
                    else
                        ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "playerNotFound"));
                }
                else ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage((Player) ((Player) sender).getPlayer(), "gamemode"));
            }
            else ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "gamemodeNotPermission"));
        }
        else {
            if(args.length == 1)
                sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
            else if(args.length == 2) {
                if(Bukkit.getPlayer(args[0]) != null)
                    setGamemode(Bukkit.getPlayer(args[0]), args[0]);
                else
                    sender.sendMessage(MessageManager.getMessage("playerNotFound"));
            }
            else sender.sendMessage(MessageManager.getMessage("gamemode"));
        }
        return false;
    }

    private void setGamemode(Player p, String arg) {
        switch (arg) {
            case "0":
                p.setGameMode(GameMode.SURVIVAL);
                break;
            case "1":
                p.setGameMode(GameMode.CREATIVE);
                break;
            case "2":
                p.setGameMode(GameMode.ADVENTURE);
                break;
            default:
                p.sendMessage(MessageManager.getMessage(p, "gamemode"));
                break;
        }
    }
}
