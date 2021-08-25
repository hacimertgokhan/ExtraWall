package net.mixeration.extrawall.events;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.EXTRAWALL$module;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class event$D implements Listener {
    public ExtraWall eWall;

    public event$D(ExtraWall eWall) {
        this.eWall = eWall;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        int cooldowntime = eWall.getEWall().getInt("extra-wall.anti-spam.cooldown");
        Player player = e.getPlayer();
        if (EXTRAWALL$module.cooldown.containsKey(player.getUniqueId())) {
            long secondsleft = (Long) EXTRAWALL$module.cooldown.get(player.getUniqueId()) / 1000L + (long)cooldowntime - System.currentTimeMillis() / 1000L;
            String format = String.format(String.valueOf(secondsleft));
            if (secondsleft > 0L) {
                e.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',eWall.getMessage().getString("messages.extra-wall.anti-spam.please-wait").replace("%delay%", format)));
                for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                    if (!(eWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                        if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ActionbarBuilder ab = new ActionbarBuilder(eWall.getMessage().getString("messages.notifications.anti-spam").replaceAll("&", "§").replace("%suspect%", e.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", e.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(e.getPlayer()))).send(onlineStaff);
                        } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                            onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notifications.anti-spam")).replace("%suspect%", e.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", e.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(e.getPlayer())));
                        }
                    }
                }
            } else {
                EXTRAWALL$module.cooldown.remove(player.getUniqueId());
                EXTRAWALL$module.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
            }
        } else {
            EXTRAWALL$module.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
        }

    }

}
