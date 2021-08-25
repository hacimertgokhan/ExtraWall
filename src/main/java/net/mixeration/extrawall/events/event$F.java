package net.mixeration.extrawall.events;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class event$F implements Listener {
    public ExtraWall extraWall;
    public event$F(ExtraWall extraWall) {
        this.extraWall = extraWall;
    }

    public static char BAD_CHARACTER = '\u0307';

    public static boolean containsBadCharacter(String string) {
        return string.contains(String.valueOf(BAD_CHARACTER));
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (containsBadCharacter(event.getMessage())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.nulled-events.cancelMessage")));
            for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                    if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                        ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.nulled-events").replaceAll("&", "§").replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player))).send(onlineStaff);
                    } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                        onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.nulled-events")).replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player)));
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player oyuncu = event.getPlayer();
        if (containsBadCharacter(event.getMessage())) {
            event.setCancelled(true);
            oyuncu.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.nulled-events.cancelMessage")));
            for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                    if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                        ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.nulled-events").replaceAll("&", "§").replace("%suspect%", oyuncu.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(oyuncu))).send(onlineStaff);
                    } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                        onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.nulled-events")).replace("%suspect%", oyuncu.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(oyuncu)));
                    }
                }
            }
        }
    }

    private boolean checkItem(ItemStack item) {
        if (item == null) return false;
        if (item.getItemMeta() == null) return false;

        String itemName = item.getItemMeta().getDisplayName();
        List<String> lore = item.getItemMeta().getLore();

        if (itemName != null && containsBadCharacter(itemName)) return true;

        if (lore == null) return false;

        for (String line : lore) {
            if (line != null && containsBadCharacter(line)) return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if (checkItem(item)) {
            player.getInventory().setItemInHand(null);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.nulled-events.cancelMessage")));
            for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                    if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                        ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.nulled-events").replaceAll("&", "§").replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player))).send(onlineStaff);
                    } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                        onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.nulled-events")).replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player)));
                    }
                }
            }
        }

        try {
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
            if (checkItem(itemInOffHand)) {
                player.getInventory().setItemInOffHand(null);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.nulled-events.cancelMessage")));
                for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                    if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                        if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.nulled-events").replaceAll("&", "§").replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player))).send(onlineStaff);
                        } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                            onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.nulled-events")).replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player)));
                        }
                    }
                }
            }
        } catch (NoSuchMethodError | Exception ignored) {}
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(event.getInventory() instanceof AnvilInventory)) return;
        InventoryView view = event.getView();
        int rawSlot = event.getRawSlot();

        if (rawSlot == view.convertSlot(rawSlot) && rawSlot == 2) {
            ItemStack item = event.getCurrentItem();
            if (checkItem(item)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.nulled-events.cancelMessage")));
                for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                    if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                        if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.nulled-events").replaceAll("&", "§").replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player))).send(onlineStaff);
                        } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                            onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.nulled-events")).replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale((Player) player)));
                        }
                    }
                }
            }
        }
    }
}

