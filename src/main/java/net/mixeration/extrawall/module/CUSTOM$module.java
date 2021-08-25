package net.mixeration.extrawall.module;

import net.mixeration.extrawall.ExtraWall;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CUSTOM$module {

    public static void sendAdvancedMessage(String message, Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',ExtraWall.getExtraWall().getMessage().getString("header") + ExtraWall.getExtraWall().getMessage().getString(message)));
    }

}
