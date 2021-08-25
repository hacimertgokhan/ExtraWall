package net.mixeration.extrawall.user;

import de.domedd.developerapi.messagebuilder.ChatMessageBuilder;
import net.mixeration.extrawall.ExtraWall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class user$A implements CommandExecutor, Listener {
    public ExtraWall eWall;

    public user$A(ExtraWall eWall) {
        this.eWall = eWall;
    }

    Inventory help = Bukkit.getServer().createInventory(null, 9, "Extrawall");

    public void inventorySettings(){
        ItemStack item$1 = new ItemStack(Material.BOOK);
        ItemMeta item$1_ref = item$1.getItemMeta();
        ItemStack item$2 = new ItemStack(Material.ITEM_FRAME);
        ItemMeta item$2_ref = item$1.getItemMeta();
        ArrayList<String> lore$1= new ArrayList<String>();
        ArrayList<String> lore$2= new ArrayList<String>();
        //
        lore$1.add(" ");
        lore$1.add("  §7Version: §f" + Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getVersion());
        lore$1.add("  §7Author: §f" + Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getAuthors());
        lore$1.add("  §7Click and get permission list !");
        lore$1.add("");
        //
        lore$2.add("");
        lore$2.addAll(Collections.singleton(eWall.getEWall().getStringList("extra-wall.settings.null-address.closed-address-channels").toString()));
        lore$2.add("");
        //
        item$1_ref.setLore(lore$1);
        item$2_ref.setLore(lore$2);
        item$1_ref.setDisplayName("§dExtraWall");
        item$1.setItemMeta(item$1_ref);
        item$2_ref.setDisplayName("§dClosed address channels");
        item$2.setItemMeta(item$2_ref);
        help.setItem(4, item$1);
        help.setItem(7, item$2);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player user = (Player) sender;
            if (command.getName().equalsIgnoreCase("Extrawall")) {
                if (user.hasPermission("extrawall.staff.help")) {
                    if (args.length <= 0) {
                        if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ChatMessageBuilder cmb$0 = new ChatMessageBuilder().sendClickableMessage(user, "§9§lExtra Wall ", "§fVersion §3" + Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getVersion(), "extrawall menu");
                            ChatMessageBuilder cmb$1 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7reload extrawall.", "extrawall reload");
                            ChatMessageBuilder cmb$2 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7get server info.", "extrawall server");
                            ChatMessageBuilder cmb$3 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7change your notify settings.", "extrawall notify");
                            ChatMessageBuilder cmb$4 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the register staff command.", "§7/Extrawall registerstaff §b<§fnew user§b> <§fnew user password§b>");
                            ChatMessageBuilder cmb$5 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the register operator command.", "§7/Extrawall registeroperator §b<§fnew user§b> <§fnew user password§b>");
                        } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                            for(String message : eWall.getMessage().getStringList("messages.user-command.help-messages")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                            }
                            return true;
                        }
                        return true;
                    }
                    if (args.length == 1) {
                        if(args[0].equalsIgnoreCase("menu")) {
                            inventorySettings();
                            user.openInventory(help);
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("notify")) {
                            if (eWall.notification.get(user.getUniqueId()) == null) {
                                eWall.notification.put(user.getUniqueId(), "Enable");
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notify-options.enable")));
                            } else {
                                eWall.notification.remove(user.getUniqueId());
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.notify-options.disable")));
                            }
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("reload")) {
                            try {
                                eWall.reloadExtrawall();
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.extra-wall.reload.successful")));
                            } catch (IOException e) {
                                e.printStackTrace();
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.extra-wall.reload.unsuccessful")));
                            }
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("server")) {
                            Server server = Bukkit.getServer();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Port : &b" + server.getPort()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Address : &b" + server.getIp()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Version : &b" + server.getVersion()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Bukkit Version : &b" + server.getBukkitVersion()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Max player : &b" + server.getMaxPlayers()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Remaining online slot : &b" + (server.getMaxPlayers() - server.getOnlinePlayers().size())));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Online : &b" + server.getOnlinePlayers().size()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- View distance : &b" + server.getViewDistance()));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
                            try {
                                Runtime r = Runtime.getRuntime();
                                float usedMemory = (r.totalMemory() - r.freeMemory()) / 1048576F;
                                int usedMemoryPercentage = (int) ((100 * usedMemory) / (r.maxMemory() / 1048576));
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- Ram ? &fUsed " + (int) usedMemory + "§b/§f Total " + (r.totalMemory() / 1048576) + "§b/§f Maximum " + (r.maxMemory() / 1048576) + "§bMB (§f" + usedMemoryPercentage + "§b%)"));
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.error.cant-getting-ram-information")));
                            }
                        }
                    } else if(args.length == 3) {
                        String userTarget = args[1];
                        if (args[0].equalsIgnoreCase("registerstaff")) {
                            if (!(eWall.getEWall().getString("extra-wall.staff-accounts." + userTarget) == null)) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lHEY ! &7Player &f(&2" + userTarget + "&f) &7already registered !"));
                            } else {
                                eWall.getEWall().set("extra-wall.staff-accounts." + userTarget + ".password", args[2]);
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&lSUCCESS ! &7Player &f(&2" + userTarget + "&f) &7registered !"));
                                try {
                                    eWall.getEWall().save(eWall.C$File);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR ! &7Player &f(&2" + userTarget + "&f) &7not registered !"));
                                }
                                return true;
                            }
                        }
                        return true;
                    } else if (args.length == 3) {
                        String userTarget = args[1];
                        if (args[0].equalsIgnoreCase("registeroperator")) {
                            Player getUser = Bukkit.getPlayer(userTarget);
                            if (!(eWall.getEWall().getString("extra-wall.operator-accounts." + userTarget) == null)) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lHEY ! &7Player &f(&2" + userTarget + "&f) &7already registered !"));
                            } else {
                                eWall.getEWall().set("extra-wall.operator-accounts." + userTarget + ".can-use-op-command", "sure");
                                eWall.getEWall().set("extra-wall.operator-accounts." + userTarget + ".password", args[2]);
                                eWall.getEWall().getStringList("extra-wall.operators").add(userTarget);
                                eWall.getEWall().set("extra-wall.operator-accounts." + userTarget + ".ip-address", getUser.getAddress().getAddress().getHostAddress());
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&lSUCCESS ! &7Player &f(&2" + userTarget + "&f) &7registered !"));
                                try {
                                    eWall.getEWall().save(eWall.C$File);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR ! &7Player &f(&2" + userTarget + "&f) &7not registered !"));
                                }
                                return true;
                            }
                        }
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.error.no-permission")));
                    return true;
                }
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.console.only-in-game")));
            return true;
        }
        return true;
    }

    @EventHandler
    private void inventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("Extrawall")) {
            e.setCancelled(true);
            if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            }


            if (e.getSlot() == 4 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dExtraWall"))) {
                p.sendMessage("");
                p.sendMessage("§dExtraWall Permissions;");
                for (Permission message : Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getPermissions()) {
                    p.sendMessage(" §7- §f" + message.getName());
                }
                p.closeInventory();
                return;
            }

            if (e.getSlot() == 7 && (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dClosed address channels"))) {
                e.setCancelled(true);
                return;
            }
        }
    }
}
