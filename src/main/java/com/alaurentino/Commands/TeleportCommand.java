package com.alaurentino.Commands;

import com.alaurentino.HUB;
import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anderson Laurentino on 06/01/2017.
 */
public class TeleportCommand implements CommandExecutor {
    public static Map<Player, Player> requests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(((Player) sender).getPlayer().hasPermission("HUB.tpa")) {
                if(cmd.getName().equals("tpa")) {
                    if(args.length == 1)
                        if(Bukkit.getPlayer(args[0]) != null)
                            submitRequest(((Player) sender).getPlayer(), Bukkit.getPlayer(args[0]));
                        else
                            ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "tpa"));
                }
                else if(cmd.getName().equals("tpaccept") || cmd.getName().equals("tpaceitar"))
                    response(requests.get(((Player) sender).getPlayer()), ((Player) sender).getPlayer(), true);
                else
                    response(requests.get(((Player) sender).getPlayer()), ((Player) sender).getPlayer(), false);
            }
            else
                ((Player) sender).getPlayer().sendMessage(MessageManager.getMessage(((Player) sender).getPlayer(), "notPermission"));
        }
        else
            sender.sendMessage(MessageManager.getMessage("commandForPlayers"));

        return true;
    }

    private void submitRequest(final Player source, final Player target) {
        requests.put(target, source);
        source.sendMessage(MessageManager.getMessage(source, "tpaSendRequest"));
        target.sendMessage(MessageManager.getMessage(source, "tpaRequest"));

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HUB.getInstace, new Runnable() {
            public void run() {
                if(TeleportCommand.requests.get(target) != null) {
                    source.sendMessage(MessageManager.getMessage(source, "tpaRequestExpired"));
                    target.sendMessage(MessageManager.getMessage(target, "tpaRequestExpired"));
                    requests.put(target, null);
                }
            }
        }, FileManager.getConfig().getLong("requestion_expiration_time") * 20L);
    }

    private void response(Player source, Player target, boolean status) {
        if (source != null) {
            requests.put(target, null);

            if (status) {
                source.sendMessage(MessageManager.getMessage(source, "tpaccept"));
                target.sendMessage(MessageManager.getMessage(target, "tpaccept"));

                source.teleport(target.getLocation());
            } else {
                source.sendMessage(MessageManager.getMessage(source, "tpaccept"));
                target.sendMessage(MessageManager.getMessage(target, "tpdeny"));
            }
        } else
            target.sendMessage(MessageManager.getMessage(target, "tpaNotRequest"));
    }
}
