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

import java.util.Iterator;

import static org.bukkit.event.EventPriority.HIGH;

public class event$B implements Listener {
    public ExtraWall eWall;

    public event$B(ExtraWall eWall) {
        this.eWall = eWall;
    }

    @EventHandler(priority = HIGH)
    public void proxyAdress(PlayerLoginEvent event) {
        Iterator var2 = eWall.getEWall().getStringList("extra-wall.settings.null-address.closed-address-channels").iterator();

        while(var2.hasNext()) {
            String proxy = (String)var2.next();
            if (event.getAddress().getHostAddress().equals(proxy)) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, eWall.getMessage().getString("messages.extra-wall.closed-address-channels.kick-suspect").replaceAll("&", "§"));
                for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                    if (!(eWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                        if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ActionbarBuilder ab = new ActionbarBuilder(eWall.getMessage().getString("messages.notifications.closed-address-channel").replaceAll("&", "§").replace("%suspect%", event.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", event.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(event.getPlayer()))).send(onlineStaff);
                        } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                            onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notifications.closed-address-channel")).replace("%suspect%", event.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", event.getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(event.getPlayer())));
                        }
                    }
                }
            }
        }
    }
}
