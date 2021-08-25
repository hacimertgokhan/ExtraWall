package net.mixeration.extrawall.user.operator;

import de.domedd.developerapi.messagebuilder.ActionbarBuilder;
import de.domedd.developerapi.messagebuilder.ChatMessageBuilder;
import net.mixeration.extrawall.ExtraWall;
import net.mixeration.extrawall.module.USER$module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class operator$A implements CommandExecutor {
    public ExtraWall extraWall;
    public operator$A(ExtraWall extraWall) {
        this.extraWall = extraWall;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(command.getName().equalsIgnoreCase("eWallOperator")) {
                Player user = (Player) sender;
                if(sender.isOp()) {
                    if(args.length == 0) {
                        if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                            ChatMessageBuilder cmb$0 = new ChatMessageBuilder().sendClickableMessage(user, "§9§lExtra Wall §9| §bOperator", "§fVersion §3" + Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getVersion(), "extrawall menu");
                            ChatMessageBuilder cmb$1 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the register staff command.", "§7/eWallOperator add §b<§fpassword§b> §b<§fuser§b>");
                            ChatMessageBuilder cmb$2 = new ChatMessageBuilder().sendHoverableMessage(user, "  §8§l» ", "§fHover over me to see the register operator command.", "§7/eWallOperator remove §b<§fpassword§b> §b<§fuser§b>");
                        } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                            for(String message : extraWall.getMessage().getStringList("messages.user-command.operator-help-messages")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                            }
                            return true;
                        }
                        return true;
                    } else if (args.length == 3) {
                        String format$1 = args[1];
                        String format$2 = args[2];
                        Player player = Bukkit.getPlayer(format$2);
                        if (format$1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.operator-password"))) {
                            if (args[0].equalsIgnoreCase("add")) {
                                if (player == null) {
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.error.player-not-online")));
                                } else {
                                    player.setOp(true);
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.operator.op-gived").replace("%player%", player.getName())));
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lTIP ! &7If player " + player.getName() + " is not in operator list, player never be operator. First you need to add player in operator list. &f/Extrawall registeroperator <user>"));
                                    for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                                        if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                                            if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                                                ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.operators.now-is-operator").replaceAll("&", "§").replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(player))).send(onlineStaff);
                                            } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.operators.now-is-operator")).replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(player)));
                                            }
                                        }
                                    }
                                }
                            }
                            if (args[0].equalsIgnoreCase("remove")) {
                                if (player == null) {
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.error.player-not-online")));
                                } else {
                                    player.setOp(false);
                                    user.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.operator.op-gived").replace("%player%", player.getName())));
                                    for(Player onlineStaff : Bukkit.getOnlinePlayers()) {
                                        if (!(extraWall.notification.get(onlineStaff.getUniqueId()) == null)) {
                                            if (extraWall.getEWall().getString("modern-mode").equals("api-support")) {
                                                ActionbarBuilder ab = new ActionbarBuilder(extraWall.getMessage().getString("messages.notifications.operators.now-is-not-operator").replaceAll("&", "§").replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(player))).send(onlineStaff);
                                            } else if (extraWall.getEWall().getString("modern-mode").equals("none")) {
                                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.notifications.operators.now-is-not-operator")).replace("%suspect%", player.getName()).replace("%next_line%", "\n\n").replace("%suspect_locale%", USER$module.getLocale(player)));
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            user.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.error.wrong-password").replace("%player%", player.getName())));
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
