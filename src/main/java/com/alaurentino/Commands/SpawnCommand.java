package com.alaurentino.Commands;

import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
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
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "spawn"));
                }
                else
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "spawnNotPermission"));
            }
            else {
                if(((Player) sender).getPlayer().hasPermission("HUB.setspawn")) {
                    FileManager.setSpawn(((Player) sender).getPlayer().getLocation());
                    FileManager.saveSpawn();
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setspawn"));
                }
                else
                    ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "setSpawnNotPermission"));
            }
        }
        else
            sender.sendMessage(MessageManager.getMessage("commandForPlayers"));
        return true;
    }
}
