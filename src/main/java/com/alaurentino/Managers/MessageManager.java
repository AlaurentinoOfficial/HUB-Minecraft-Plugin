package com.alaurentino.Managers;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Anderson Laurentino on 04/01/2017.
 */
public class MessageManager {
    public static String filterMsg(Player player, String message) {
        message = MessageFilter.serverTagsFilter(message);
        message = MessageFilter.playerTagsFilter(player, message);
        message = MessageFilter.colorFilter(message);
        return message;
    }

    public static String filterMsg(String message) {
        message = MessageFilter.serverTagsFilter(message);
        message = MessageFilter.colorFilter(message);
        return message;
    }

    public static String getMessage(Player player, String message) {
        String lang = FileManager.getLanguage().getString(player.getDisplayName());
        return filterMsg(player, FileManager.getLanguage().getString(lang + "." + message));
    }

    public static String getMessage(String message) {
        String lang = FileManager.getConfig().getString("default_language");
        return filterMsg(FileManager.getLanguage().getString(lang + "." + message));
    }

    public static List<String> getMessageList(Player player, String message) {
        String lang = FileManager.getLanguage().getString(player.getDisplayName());
        return FileManager.getLanguage().getStringList(lang + "." + message);
    }
}
