package com.alaurentino;

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
    }

    @Override
    public void onDisable() {

    }
}
