package com.alaurentino.Commands;

import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(cmd.getName().equals("spawn") || cmd.getName().equals("hub") || cmd.getName().equals("lobby")) {
                if(sender.hasPermission("HUB.spawn")) {
                    ((Player) sender).getPlayer().teleport(FileManager.getSpawn(((Player) sender).getPlayer()));
                    ((Player) sender).getPlayer().playSound(FileManager.getSpawn(((Player) sender).getPlayer()), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                    ((Player) sender).getPlayer().playEffect(((Player) sender).getPlayer().getLocation(), Effect.ENDER_SIGNAL, 3);
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "spawn"));
                }
                else
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
            }
            else {
                if(((Player) sender).getPlayer().hasPermission("HUB.setspawn")) {
                    FileManager.setSpawn(((Player) sender).getPlayer().getLocation());
                    FileManager.saveSpawn();
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setSpawn"));
                }
                else
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
            }
        }
        else
            sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
        return true;
    }
}
