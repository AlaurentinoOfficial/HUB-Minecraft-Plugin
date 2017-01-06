package com.alaurentino.Scoreboard;

import com.alaurentino.HUB;
import com.alaurentino.Managers.FileManager;
import com.alaurentino.Managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anderson Laurentino on 05/01/2017.
 */
public class Board {
    private static Scoreboard board;
    private static Objective o;

    public static Map<Player, Integer> increments = new HashMap<>();

    public static void setBoard(Player p) {
        board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        o = board.registerNewObjective("test", "dummy");
        o.setDisplayName(FileManager.getConfig().getString(MessageManager.filterMsg(p, "scoreboard_title")));
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        setup(p);

        if(FileManager.getConfig().getBoolean("enable_scoreboard"))
            p.setScoreboard(board);
    }

    private static void setup(Player p) {
        List<String> lines = FileManager.getBoard().getStringList("Scoreboard");
        Collections.reverse(lines);

        if(!increments.containsKey(p))
            increments.put(p, 0);

        int m = lines.size() - increments.get(p);

        for(int i = (m-15); i < m; i++) {
            String line = lines.get(i);
            line = line == null || line.isEmpty() ? "&a &7 " : line;
            Score s = o.getScore(Bukkit.getOfflinePlayer(MessageManager.filterMsg(p, line)));
            s.setScore(i);
        }

        if(increments.get(p) == lines.size() - 15)
            increments.remove(p);
        else
            increments.put(p, increments.get(p) + 1);
    }

    public static void update() {
        if(FileManager.getConfig().getBoolean("enable_scoreboard")) {
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(HUB.getInstace,new Runnable() {
                public void run() {
                    for(Player p : Bukkit.getOnlinePlayers())
                        if(p.getWorld().getName().equals(FileManager.getConfig().getString("hub_world")))
                            Board.setBoard(p);
                }
            }, 0L, FileManager.getConfig().getLong("scroll_time_scoreboard") * 20L);
        }
    }
}
