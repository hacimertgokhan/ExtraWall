package net.mixeration.extrawall.events;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class event$E implements Listener {
    public ExtraWall eWall;
    public event$E(ExtraWall eWall) {
        this.eWall = eWall;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        if (eWall.getEWall().getBoolean("extra-wall.uuid-spoof.enable", true)) {
            if (eWall.getConfig().contains("uuid-player." + player.getUniqueId().toString())) {
                if (!eWall.getConfig().getString("uuid-player." + player.getUniqueId().toString()).equals(player.getName())) {
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.extra-wall.uuid-spoof.kick-suspect")));
                    for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                        if (!(eWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                            if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                                ActionbarBuilder ab = new ActionbarBuilder(eWall.getMessage().getString("messages.notifications.uuid-spoof").replaceAll("&", "§").replace("%suspect%", playerJoinEvent.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", playerJoinEvent.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(playerJoinEvent.getPlayer()))).send(onlineStaff);
                            } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notifications.uuid-spoof")).replace("%suspect%", playerJoinEvent.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", playerJoinEvent.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(playerJoinEvent.getPlayer())));
                            }
                        }
                    }
                }
            } else if (eWall.getConfig().contains("uuid-player." + player.getName())) {
                if (!eWall.getConfig().getString("uuid-player." + player.getName()).equals(player.getUniqueId().toString())) {
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.extra-wall.uuid-spoof.kick-suspect")));
                    for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                        if (!(eWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                            if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                                ActionbarBuilder ab = new ActionbarBuilder(eWall.getMessage().getString("messages.notifications.uuid-spoof").replaceAll("&", "§").replace("%suspect%", playerJoinEvent.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", playerJoinEvent.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(playerJoinEvent.getPlayer()))).send(onlineStaff);
                            } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notifications.uuid-spoof")).replace("%suspect%", playerJoinEvent.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", playerJoinEvent.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(playerJoinEvent.getPlayer())));
                            }
                        }
                    }
                }
            } else {
                eWall.getConfig().set("uuid-player." + player.getUniqueId().toString(), player.getName());
                eWall.getConfig().set("uuid-player." + player.getName(), player.getUniqueId().toString());
                eWall.saveConfig();
            }
        } else {
            return;
        }
    }
}
