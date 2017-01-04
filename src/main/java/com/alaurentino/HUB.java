package com.alaurentino;

import com.alaurentino.Commands.GamemodeCommand;
import com.alaurentino.Commands.PvpCommand;
import com.alaurentino.Commands.SpawnCommand;
import com.alaurentino.Managers.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class HUB extends JavaPlugin {

    public static HUB getInstace;

    @Override
    public void  onEnable() {
        getInstace = this;
        FileManager.setup();

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("pvp").setExecutor(new PvpCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("hub").setExecutor(new SpawnCommand());
        getCommand("lobby").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SpawnCommand());
        getCommand("sethub").setExecutor(new SpawnCommand());
        getCommand("setlobby").setExecutor(new SpawnCommand());
    }

    @Override
    public void onDisable() {

    }
}
