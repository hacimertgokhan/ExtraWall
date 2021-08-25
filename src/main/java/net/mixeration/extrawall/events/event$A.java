package net.mixeration.extrawall.events;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.net.InetAddress;

import static org.bukkit.event.EventPriority.HIGHEST;

public class event$A implements Listener {
    public ExtraWall eWall;

    public event$A(ExtraWall eWall) {
        this.eWall = eWall;
    }

    @EventHandler(priority = HIGHEST)
    public boolean nullAddress(PlayerLoginEvent event) {
        final Player suspect = event.getPlayer();
        final InetAddress suspect_hostName = event.getAddress();
        final String suspect_hostName_String = String.format(String.valueOf(suspect_hostName));
        final String message_PlayerLoginEvent_NullAddress = eWall.getMessage().getString("messages.extra-wall.null-address.kick-suspect");
        if (suspect_hostName == null) {
            if (eWall.warncount.get(suspect.getUniqueId()) < eWall.getEWall().getInt("extra-wall.warning.max-warn")) {
                event.setKickMessage(ChatColor.translateAlternateColorCodes('&', message_PlayerLoginEvent_NullAddress).replace("%next_line%", "\n\n").replace("%suspect_address%", suspect_hostName_String).replace("%suspect_locale%", USER$module.getLocale(suspect)));
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                eWall.warncount.put(suspect.getUniqueId(), 1);
                for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                    if (!(eWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                        if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ActionbarBuilder ab = new ActionbarBuilder(eWall.getMessage().getString("messages.notifications.null-address").replaceAll("&", "§").replace("%suspect%", event.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", event.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(event.getPlayer()))).send(onlineStaff);
                        } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                            onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notifications.null-address")).replace("%suspect%", event.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", event.getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(event.getPlayer())));
                        }
                    }
                }
            } else if (eWall.warncount.get(suspect.getUniqueId()) >= eWall.getEWall().getInt("extra-wall.warning.max-warn")) {
                eWall.warncount.remove(suspect.getUniqueId());
                for (String commands : eWall.getEWall().getStringList("extra-wall.warning.punishment")) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%suspect%", suspect.getName()));
                }
            }
        }
        return true;
    }
}
