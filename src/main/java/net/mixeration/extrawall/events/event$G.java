package net.mixeration.extrawall.events;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class event$G implements Listener {
    public ExtraWall extraWall;
    public event$G(ExtraWall extraWall) {
        this.extraWall = extraWall;
    }


    @EventHandler
    public void antiOp$1(PlayerCommandPreprocessEvent preprocessEvent) {
        Player getPlayer = preprocessEvent.getPlayer();
        String message = preprocessEvent.getMessage();
        if(message.equalsIgnoreCase("/Op")) {
            if(!(extraWall.getEWall().getString("extra-wall.operator-accounts." + getPlayer.getName() + ".can-use-op-command").equals("sure"))) {
                preprocessEvent.setCancelled(true);
                getPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.anti-op.event-cancelled")));
            } else {
                getPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.anti-op.usage")));
                preprocessEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void antiOp$2(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(extraWall, new Runnable() {
            @Override
            public void run() {
                if (!(extraWall.getEWall().getStringList("extra-wall.operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                        for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                            if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                                if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                                    ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.server-in-danger").replaceAll("&", "§").replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p))).send(onlineStaff);
                                } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                                    onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.server-in-danger")).replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p)));
                                }
                            }
                        }
                    }
                }
            }
        }, 20L * extraWall.getEWall().getLong("extra-wall.check*time"));
    }

    @EventHandler
    public void antiOp$3(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(extraWall, new Runnable() {
            @Override
            public void run() {
                if (!(extraWall.getEWall().getStringList("extra-wall.operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                        for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                            if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                                if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                                    ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.server-in-danger").replaceAll("&", "§").replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p))).send(onlineStaff);
                                } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                                    onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.server-in-danger")).replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p)));
                                }
                            }
                        }
                    }
                }
            }
        }, 20L * extraWall.getEWall().getLong("extra-wall.check*time"));
    }

    @EventHandler
    public void antiOp$4(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(extraWall, new Runnable() {
            @Override
            public void run() {
                if (!(extraWall.getEWall().getStringList("extra-wall.operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                        for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                            if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                                if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                                    ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.server-in-danger").replaceAll("&", "§").replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p))).send(onlineStaff);
                                } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                                    onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.server-in-danger")).replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p)));
                                }
                            }
                        }
                    }
                }
            }
        }, 20L * extraWall.getEWall().getLong("extra-wall.check*time"));
    }

    @EventHandler
    public void antiOp$5(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(extraWall, new Runnable() {
            @Override
            public void run() {
                if (!(extraWall.getEWall().getStringList("extra-wall.operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                        for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                            if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                                if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                                    ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.server-in-danger").replaceAll("&", "§").replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p))).send(onlineStaff);
                                } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                                    onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.server-in-danger")).replace("%suspect%", p.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(p)));
                                }
                            }
                        }
                    }
                }
            }
        }, 20L * extraWall.getEWall().getLong("extra-wall.check*time"));
    }
}
