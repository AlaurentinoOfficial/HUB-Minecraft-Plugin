package com.alaurentino.Managers;

import com.alaurentino.HUB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class FileManager {
    private static FileConfiguration language;
    private static FileConfiguration spawn;
    private static FileConfiguration board;

    private static File lfile = new File(HUB.getInstace.getDataFolder(), "language.yml");
    private static File sfile = new File(HUB.getInstace.getDataFolder(), "spawn.yml");
    private static File bfile = new File(HUB.getInstace.getDataFolder(), "scoreboard.yml");
    private static File ifile = new File(HUB.getInstace.getDataFolder(), "server-icon.png");

    private static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte['?'];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setup() {
        if (!lfile.exists()) {
            lfile.getParentFile().mkdirs();
            copy(HUB.getInstace.getResource("language.yml"), lfile);
        }
        if (!ifile.exists()) {
            ifile.getParentFile().mkdirs();
            copy(HUB.getInstace.getResource("server-icon.png"), ifile);
        }
        if (!sfile.exists()) {
            sfile.getParentFile().mkdirs();
            copy(HUB.getInstace.getResource("spawn.yml"), sfile);
        }
        if (!bfile.exists()) {
            bfile.getParentFile().mkdirs();
            copy(HUB.getInstace.getResource("scoreboard.yml"), bfile);
        }
        if (!new File(HUB.getInstace.getDataFolder(), "config.yml").exists())
            HUB.getInstace.saveDefaultConfig();

        language = YamlConfiguration.loadConfiguration(lfile);
        spawn = YamlConfiguration.loadConfiguration(sfile);
        board = YamlConfiguration.loadConfiguration(bfile);
    }

    public static void saveSpawn() {
        try {
            spawn.save(sfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSpawn(Location location) {
        spawn.set("spawn.x", location.getX());
        spawn.set("spawn.y", location.getY());
        spawn.set("spawn.z", location.getZ());
        spawn.set("spawn.yaw", location.getYaw());
        spawn.set("spawn.pitch", location.getPitch());
        spawn.set("spawn.world", location.getWorld().getName());
        saveSpawn();
    }

    public static Location getSpawn(Player p) {
        Location l;
        if(spawn.contains("spawn")) {
            double x = spawn.getDouble("spawn.x");
            double y = spawn.getDouble("spawn.y");
            double z = spawn.getDouble("spawn.z");
            double yaw = spawn.getDouble("spawn.yaw");
            double pitch = spawn.getDouble("spawn.pitch");
            String world = spawn.getString("spawn.world");
            World w = Bukkit.getWorld(world);
            l = new Location(w, x, y, z, Float.valueOf(String.valueOf(yaw)), Float.valueOf(String.valueOf(pitch)));
        }
        else {
            setSpawn(p.getWorld().getSpawnLocation());
            l = getSpawn(p);
        }

        return l;
    }

    private static void saveLanguage() {
        try {
            language.save(lfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getLanguage() {
        return language;
    }

    public static void setLanguage(String path, Object value) {
        getLanguage().set(path, value);
        saveLanguage();
    }

    public static FileConfiguration getConfig() {
        return HUB.getInstace.getConfig();
    }

    public static void saveConfig() {
        HUB.getInstace.saveConfig();
    }

    public static FileConfiguration getBoard() {
        return board;
    }

    private static void saveBoard() {
        try {
            board.save(bfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
