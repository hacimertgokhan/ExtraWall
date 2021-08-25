package net.mixeration.extrawall.user;

import de.domedd.developerapi.messagebuilder.ChatMessageBuilder;
import net.mixeration.extrawall.ExtraWall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class user$B implements CommandExecutor, Listener {
    public ExtraWall eWall;

    public user$B(ExtraWall eWall) {
        this.eWall = eWall;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player user = (Player) sender;
            YamlConfiguration spigotFile = Bukkit.getServer().spigot().getConfig();
            if(command.getName().equalsIgnoreCase("eWallSpigot")) {
                if(sender.hasPermission("extrawall.spigot.help")) {
                    if(args.length == 0) {
                        if (eWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ChatMessageBuilder cmb$0 = new ChatMessageBuilder().sendClickableMessage(user, "§9§lExtra Wall §9| §bSpigot", "§fVersion §3" + Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getVersion(), "extrawall menu");
                            ChatMessageBuilder cmb$1 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7See default settings.", "ewallspigot defaultsettings");
                            ChatMessageBuilder cmb$2 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7Change debug.", "ewallspigot debug");
                            ChatMessageBuilder cmb$3 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7Change Save user cache.", "ewallspigot saveusercache");
                            ChatMessageBuilder cmb$4 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7Change Filter creative items.", "ewallspigot filtercreativeitems");
                            ChatMessageBuilder cmb$5 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7Change Latebind.", "ewallspigot latebind");
                            ChatMessageBuilder cmb$6 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7Change Bungeecord.", "ewallspigot bungeecord");
                            ChatMessageBuilder cmb$8 = new ChatMessageBuilder().sendClickableMessage(user, "  §8§l» ", "§fClick me and §7Change Restart on crash.", "ewallspigot restartoncrash");
                            ChatMessageBuilder cmb$9 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change moved wrongly threshold.", "§7/eWallSpigot movedwronglythreshold §b<§finteger§b>");
                            ChatMessageBuilder cmb$10 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change moved too quickly threshold.", "§7/eWallSpigot movedtooquicklythreshold §b<§finteger§b>");
                            ChatMessageBuilder cmb$11 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change player shuffle.", "§7/eWallSpigot playershuffle §b<§finteger§b>");
                            ChatMessageBuilder cmb$12 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change sample count.", "§7/eWallSpigot samplecount §b<§finteger§b>");
                            ChatMessageBuilder cmb$13 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change user cache size.", "§7/eWallSpigot usercachesize §b<§finteger§b>");
                            ChatMessageBuilder cmb$14 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change int cache limit.", "§7/eWallSpigot intcachelimit §b<§finteger§b>");
                            ChatMessageBuilder cmb$15 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change Timeout time.", "§7/eWallSpigot timeouttime §b<§finteger§b>");
                            ChatMessageBuilder cmb$16 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change Netty theards.", "§7/eWallSpigot nettytheards §b<§finteger§b>");
                            ChatMessageBuilder cmb$17 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the §7Change restart script.", "§7/eWallSpigot restartscript §b<§fString (Needed starts with `./`)§b>");
                        } else if (eWall.getEWall().getString("modern-mode").equals("none")) {
                            for(String message : eWall.getMessage().getStringList("messages.user-command.spigot-help-messages")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                            }
                            return true;
                        }
                        return true;
                    } else if (args.length == 1) {
                        if(args[0].equalsIgnoreCase("defaultsettings")) {
                            sender.sendMessage(" - Debug: " + spigotFile.getString("settings.debug"));
                            sender.sendMessage(" - Save user cache: " + spigotFile.getString("settings.save-user-cache-on-stop-only"));
                            sender.sendMessage(" - Filter creative items: " + spigotFile.getString("settings.filter-creative-items"));
                            sender.sendMessage(" - Moved wrongly: " + spigotFile.getDouble("settings.moved-wrongly-threshold"));
                            sender.sendMessage(" - Moved too quick: " + spigotFile.getDouble("settings.moved-too-quickly-threshold"));
                            sender.sendMessage(" - LateBind: " + spigotFile.getDouble("settings.late-bind"));
                            sender.sendMessage(" - Player Shuffle: " + spigotFile.getInt("settings.player-shuffle"));
                            sender.sendMessage(" - Sample count: " + spigotFile.getInt("settings.sample-count"));
                            sender.sendMessage(" - User cache size: " + spigotFile.getDouble("settings.user-cache-size"));
                            sender.sendMessage(" - Bungeecord: " + spigotFile.getString("settings.bungeecord"));
                            sender.sendMessage(" - Netty theards: " + spigotFile.getString("settings.netty-threads"));
                            sender.sendMessage(" - Restart on crash: " + spigotFile.getString("settings.restart-on-crash"));
                            sender.sendMessage(" - Restart on crash: " + spigotFile.getString("settings.restart-script"));
                            sender.sendMessage(" - Timeout time: " + spigotFile.getInt("settings.timeout-time"));
                            sender.sendMessage(" - Int cache limit: " + spigotFile.getInt("settings.int-cache-limit"));
                            return true;
                        }
                        if(args[0].equalsIgnoreCase("debug")) {
                            if(!spigotFile.getBoolean("settings.debug")) {
                                spigotFile.set("settings.debug", true);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Debug").replace("%changed_to%", "true")));
                            } else {
                                spigotFile.set("settings.debug", false);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Debug").replace("%changed_to%", "false")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("saveusercache")) {
                            if(!spigotFile.getBoolean("settings.save-user-cache-on-stop-only")) {
                                spigotFile.set("settings.save-user-cache-on-stop-only", true);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Save user cache").replace("%changed_to%", "true")));
                            } else {
                                spigotFile.set("settings.save-user-cache-on-stop-only", false);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Save user cache").replace("%changed_to%", "false")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("filtercreativeitems")) {
                            if(!spigotFile.getBoolean("settings.filter-creative-items")) {
                                spigotFile.set("settings.filter-creative-items", true);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Filter creative items").replace("%changed_to%", "true")));
                            } else {
                                spigotFile.set("settings.filter-creative-items", false);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Filter creative items").replace("%changed_to%", "false")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("latebind")) {
                            if(!spigotFile.getBoolean("settings.late-bind")) {
                                spigotFile.set("settings.late-bind", true);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Late bind").replace("%changed_to%", "true")));
                            } else {
                                spigotFile.set("settings.late-bind", false);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Late bind").replace("%changed_to%", "false")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("bungeecord")) {
                            if(!spigotFile.getBoolean("settings.bungeecord")) {
                                spigotFile.set("settings.bungeecord", true);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Bungeecord").replace("%changed_to%", "true")));
                            } else {
                                spigotFile.set("settings.bungeecord", false);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Bungeecord").replace("%changed_to%", "false")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("restartoncrash")) {
                            if(!spigotFile.getBoolean("settings.restart-on-crash")) {
                                spigotFile.set("settings.restart-on-crash", true);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Restart on crash").replace("%changed_to%", "true")));
                            } else {
                                spigotFile.set("settings.restart-on-crash", false);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Restart on crash").replace("%changed_to%", "false")));
                            }
                        }
                    } else if(args.length == 2) {
                        Integer formattedarg = Integer.parseInt(args[1]);
                        if(args[0].equalsIgnoreCase("movedwronglythreshold")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.moved-wrongly-threshold", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Moved wrongly threshold").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot movedwronglythreshold <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("restartscript")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.restart-script", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Restart script").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot restartscript <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("playershuffle")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.player-shuffle", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Player shuffle").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot playershuffle <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("intcachelimit")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.int-cache-limit", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Int cache limit").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot intcachelimit <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("timeouttime")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.timeout-time", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Timeout time").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot timeouttime <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("samplecount")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.sample-count", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Sample count").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot samplecount <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("nettythreads")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.netty-threads", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Netty threads").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot nettythreads <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("usercachesize")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.user-cache-size", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "User cache size").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot usercachesize <Integer>")));
                            }
                        }
                        if(args[0].equalsIgnoreCase("movedtooquicklythreshold")) {
                            if (!args[1].isEmpty()) {
                                spigotFile.set("settings.moved-too-quickly-threshold", formattedarg);
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.changed-settings").replace("%settings_name%", "Moved too quickly threshold").replace("%changed_to%", args[1])));
                            } else {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', eWall.getMessage().getString("messages.spigot-file.wrong-usage-type").replace("%command%", "/eWallSpigot movedtooquicklythreshold <Integer>")));
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
