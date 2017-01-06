package com.alaurentino;

import com.alaurentino.Commands.*;
import com.alaurentino.Events.*;
import com.alaurentino.Managers.FileManager;
import com.alaurentino.Scoreboard.Board;
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

        getServer().getPluginManager().registerEvents(new ServerListEvent(), this);
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new LaunchpadEvent(), this);
        getServer().getPluginManager().registerEvents(new RainEvent(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelEvent(), this);
        getServer().getPluginManager().registerEvents(new DoubleJumpEvent(), this);
        getServer().getPluginManager().registerEvents(new DropItemEvent(), this);
        getServer().getPluginManager().registerEvents(new ProtectionEvent(), this);

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
        getCommand("language").setExecutor(new LanguageCommand());
        getCommand("idioma").setExecutor(new LanguageCommand());
        getCommand("tpa").setExecutor(new TeleportCommand());
        getCommand("tpaccept").setExecutor(new TeleportCommand());
        getCommand("tpaceitar").setExecutor(new TeleportCommand());
        getCommand("tpdeny").setExecutor(new TeleportCommand());
        getCommand("tpnegar").setExecutor(new TeleportCommand());

        Board.setup();
    }
}
