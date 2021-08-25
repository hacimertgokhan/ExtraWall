package net.mixeration.extrawall.events;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import java.nio.charset.StandardCharsets;

public class event$C implements Listener {
    public ExtraWall eWall;

    public event$C(ExtraWall eWall) {
        this.eWall = eWall;
    }

    @EventHandler
    public void onBookEdit(PlayerEditBookEvent e) {
        if (eWall.warncount.get(e.getPlayer().getUniqueId()) < eWall.getEWall().getInt("extra-wall.warning.max-warn")) {
            for (String bookPage : e.getNewBookMeta().getPages()) {
                if (!StandardCharsets.US_ASCII.newEncoder().canEncode(bookPage)) {
                    eWall.warncount.put(e.getPlayer().getUniqueId(), 1);
                    e.setCancelled(true);
                    String antiBookBan = eWall.getMessage().getString("messages.extra-wall.asci-charsets.cancel-event");
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', antiBookBan));
                    for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                        if (!(eWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                            if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                                ActionbarBuilder ab = new ActionbarBuilder(eWall.getMessage().getString("messages.notifications.asci-charsets").replaceAll("&", "§").replace("%suspect%", e.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", e.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(e.getPlayer()))).send(onlineStaff);
                            } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notifications.asci-charsets")).replace("%suspect%", e.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", e.getPlayer().getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(e.getPlayer())));
                            }
                        }
                    }
                }
            }
        } else if (eWall.warncount.get(e.getPlayer().getUniqueId()) >= eWall.getEWall().getInt("extra-wall.warning.max-warn")) {
            eWall.warncount.remove(e.getPlayer().getUniqueId());
            for (String commands : eWall.getEWall().getStringList("extra-wall.warning.punishment")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%suspect%", e.getPlayer().getName()));
            }
        }
    }
}
