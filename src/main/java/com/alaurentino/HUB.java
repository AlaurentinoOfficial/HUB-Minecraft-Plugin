package com.alaurentino;

import com.alaurentino.Commands.*;
import com.alaurentino.Events.EventListener;
import com.alaurentino.Events.LaunchpadEvent;
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
        getServer().getPluginManager().registerEvents(new LaunchpadEvent(), this);

        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("pvp").setExecutor(new PvpCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("hub").setExecutor(new SpawnCommand());
        getCommand("lobby").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SpawnCommand());
        getCommand("sethub").setExecutor(new SpawnCommand());
        getCommand("setlobby").setExecutor(new SpawnCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("velocidade").setExecutor(new SpeedCommand());
        getCommand("health").setExecutor(new HealthCommand());
        getCommand("vida").setExecutor(new HealthCommand());
        getCommand("food").setExecutor(new FoodCommand());
        getCommand("comida").setExecutor(new FoodCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("regras").setExecutor(new RulesCommand());
    }
}
