package net.mixeration.extrawall.module;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class USER$module {

    public static String getLocale(final Player player) {
        String locale;
        try {
            locale = player.getClass().getMethod("getLocale").invoke(player, (Object[]) null).toString();
        } catch (final Exception exception) {
            try {
                final Player.Spigot spigot = player.spigot();
                locale = spigot.getClass().getMethod("getLocale").invoke(spigot, (Object[]) null).toString();
            } catch (final Exception exception1) {
                locale = "en";
            }
        }
        if (locale != null && locale.length() > 1) {
            locale = locale.substring(0, 2);
        } else {
            locale = "en";
        }
        return locale;
    }

    public static int getPing(Player player) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            return entityPlayer.getClass().getField("ping").getInt(entityPlayer);
        } catch (NoSuchFieldException e) {
            return getPing(player);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
