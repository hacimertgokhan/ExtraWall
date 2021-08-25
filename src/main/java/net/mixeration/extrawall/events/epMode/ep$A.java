package net.mixeration.extrawall.events.epMode;

import fr.xephi.authme.api.v3.AuthMeApi;
import net.mixeration.extrawall.ExtraWall;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ultraauth.api.UltraAuthAPI;

public class ep$A implements Listener {
    public ExtraWall extraWall;
    public ep$A(ExtraWall extraWall) {
        this.extraWall = extraWall;
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void loginEvent$A(PlayerMoveEvent event) {
        Player getUser = event.getPlayer();
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("authme")) {
            if (AuthMeApi.getInstance().isAuthenticated(event.getPlayer())) {
                if (!extraWall.operatorList.contains(getUser.getUniqueId())) {
                    if (getUser.hasPermission("extrawall.eaccount.private") && getUser.isOp()) {
                        if(!extraWall.operatorList$Listener.contains(getUser.getName())) {
                            extraWall.operatorList.add(getUser.getUniqueId());
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                return;
            }
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("ultraauth")) {
            if (UltraAuthAPI.isAuthenticated(event.getPlayer())) {
                if (!extraWall.operatorList.contains(getUser.getUniqueId())) {
                    if (getUser.hasPermission("extrawall.eaccount.private") && getUser.isOp()) {
                        if(!extraWall.operatorList$Listener.contains(getUser.getName())) {
                            extraWall.operatorList.add(getUser.getUniqueId());
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                return;
            }
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("none")) {
            if (!extraWall.operatorList.contains(getUser.getUniqueId())) {
                if (getUser.hasPermission("extrawall.eaccount.private") && getUser.isOp()) {
                    if(!extraWall.operatorList$Listener.contains(getUser.getName())) {
                        extraWall.operatorList.add(getUser.getUniqueId());
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void loginEvent$B(AsyncPlayerChatEvent event) {
        Player getUser = event.getPlayer();
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("authme")) {
            if (AuthMeApi.getInstance().isAuthenticated(event.getPlayer())) {
                if (extraWall.operatorList.contains(getUser.getUniqueId())) {
                    event.setCancelled(true);
                } else {
                    return;
                }
            } else {
                getUser.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.first-of-all").replace("%api_plugin%", "AuthMe")));
            }
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("none")) {
            if (extraWall.operatorList.contains(getUser.getUniqueId())) {
                event.setCancelled(true);
            } else {
                return;
            }
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("ultraauth")) {
            if (UltraAuthAPI.isAuthenticated(event.getPlayer())) {
                if (extraWall.operatorList.contains(getUser.getUniqueId())) {
                    event.setCancelled(true);
                } else {
                    return;
                }
            } else {
                getUser.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.first-of-all").replace("%api_plugin%", "UltraAuth")));
            }
        }
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void loginEvent$C(PlayerCommandPreprocessEvent event) {
        Player getUser = event.getPlayer();
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("authme")) {
            if (AuthMeApi.getInstance().isAuthenticated(event.getPlayer())) {
                if (extraWall.operatorList.contains(getUser.getUniqueId())) {
                    event.setCancelled(true);
                } else {
                    return;
                }
            } else {
                getUser.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.first-of-all").replace("%api_plugin%", "AuthMe")));
            }
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("none")) {
            if (extraWall.operatorList.contains(getUser.getUniqueId())) {
                event.setCancelled(true);
            } else {
                return;
            }
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("ultraauth")) {
            if (UltraAuthAPI.isAuthenticated(event.getPlayer())) {
                if (extraWall.operatorList.contains(getUser.getUniqueId())) {
                    event.setCancelled(true);
                } else {
                    return;
                }
            } else {
                getUser.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.first-of-all").replace("%api_plugin%", "UltraAuth")));
            }
        }
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void loginEvent$D(PlayerQuitEvent event) {
        Player getUser = event.getPlayer();
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("authme")) {
            extraWall.operatorList.remove(getUser.getUniqueId());
            extraWall.operatorList$Listener.remove(getUser.getName());
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("ultraauth")) {
            extraWall.operatorList.remove(getUser.getUniqueId());
            extraWall.operatorList$Listener.remove(getUser.getName());
        }
        if(extraWall.ExtraPrivate && extraWall.ExtraPrivateAPI.equalsIgnoreCase("none")) {
            extraWall.operatorList.remove(getUser.getUniqueId());
            extraWall.operatorList$Listener.remove(getUser.getName());
        }
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void loginEvent$E(PlayerCommandPreprocessEvent e) {
        Player getUser = e.getPlayer();
        if (extraWall.ExtraPrivate || extraWall.ExtraPrivateAPI.equalsIgnoreCase("none")) {
            if (extraWall.operatorList.contains(e.getPlayer().getUniqueId())) {
                e.setCancelled(true);
                if (!e.getMessage().contains(" ")) {
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.please-login")));
                    return;
                }
                String cmd = e.getMessage().split(" ")[0];
                String arg1 = e.getMessage().split(" ")[1];
                if (!getUser.isOp()) {
                    if (getUser.hasPermission("extrawall.eaccount.private")) {
                        if (!cmd.equalsIgnoreCase("/" + extraWall.getEWall().getString("extra-wall.login.login-command.staffs"))) {
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.login-password")));
                        } else if (arg1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.staff-accounts." + getUser.getName() + ".password"))) {
                            extraWall.operatorList.remove(e.getPlayer().getUniqueId());
                            extraWall.operatorList$Listener.add(e.getPlayer().getName());
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.join-succesfuly")));
                        } else {
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.wrong-password")));
                        }
                    }
                } else if (getUser.isOp()) {
                    if (!cmd.equalsIgnoreCase("/" + extraWall.getEWall().getString("extra-wall.login.login-command.operators"))) {
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.login-password")));
                    } else if (arg1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.operator-accounts." + getUser.getName() + ".password"))) {
                        if (extraWall.getEWall().getString("extra-wall.operator-accounts." + getUser.getName() + ".ip-address").contains(getUser.getAddress().getAddress().getHostAddress())) {
                            extraWall.operatorList.remove(e.getPlayer().getUniqueId());
                            extraWall.operatorList$Listener.add(e.getPlayer().getName());
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.join-succesfuly")));
                        } else {
                            getUser.kickPlayer(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.address-control-cant-passed").replace("%address%", getUser.getAddress().getAddress().getHostAddress())));
                        }
                    } else {
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.wrong-password")));
                    }
                }
            }
        }else if (extraWall.ExtraPrivate || extraWall.ExtraPrivateAPI.equalsIgnoreCase("authme")) {
            if (AuthMeApi.getInstance().isAuthenticated(e.getPlayer())) {
                if (extraWall.operatorList.contains(e.getPlayer().getUniqueId())) {
                    e.setCancelled(true);
                    if (!e.getMessage().contains(" ")) {
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.please-login")));
                        return;
                    }
                    String cmd = e.getMessage().split(" ")[0];
                    String arg1 = e.getMessage().split(" ")[1];
                    if (!getUser.isOp()) {
                        if (getUser.hasPermission("extrawall.eaccount.private")) {
                            if (!cmd.equalsIgnoreCase("/" + extraWall.getEWall().getString("extra-wall.login.login-command.staffs"))) {
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.login-password")));
                            } else if (arg1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.staff-accounts." + getUser.getName() + ".password"))) {
                                extraWall.operatorList.remove(e.getPlayer().getUniqueId());
                                extraWall.operatorList$Listener.add(e.getPlayer().getName());
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.join-succesfuly")));
                            } else {
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.wrong-password")));
                            }
                        }
                    } else if (getUser.isOp()) {
                        if (!cmd.equalsIgnoreCase("/" + extraWall.getEWall().getString("extra-wall.login.login-command.operators"))) {
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.login-password")));
                        } else if (arg1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.operator-accounts." + getUser.getName() + ".password"))) {
                            if (extraWall.getEWall().getString("extra-wall.operator-accounts." + getUser.getName() + ".ip-address").contains(getUser.getAddress().getAddress().getHostAddress())) {
                                extraWall.operatorList.remove(e.getPlayer().getUniqueId());
                                extraWall.operatorList$Listener.add(e.getPlayer().getName());
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.join-succesfuly")));
                            } else {
                                getUser.kickPlayer(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.address-control-cant-passed").replace("%address%", getUser.getAddress().getAddress().getHostAddress())));
                            }
                        } else {
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.wrong-password")));
                        }
                    }
                }
            } else {
                getUser.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.first-of-all").replace("%api_plugin%", "AuthMe")));
            }
        } else if (extraWall.ExtraPrivate || extraWall.ExtraPrivateAPI.equalsIgnoreCase("ultraauth")) {
            if (UltraAuthAPI.isAuthenticated(e.getPlayer())) {
                if (extraWall.operatorList.contains(e.getPlayer().getUniqueId())) {
                    e.setCancelled(true);
                    if (!e.getMessage().contains(" ")) {
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.please-login")));
                        return;
                    }
                    String cmd = e.getMessage().split(" ")[0];
                    String arg1 = e.getMessage().split(" ")[1];
                    if (!getUser.isOp()) {
                        if (getUser.hasPermission("extrawall.eaccount.private")) {
                            if (!cmd.equalsIgnoreCase("/" + extraWall.getEWall().getString("extra-wall.login.login-command.staffs"))) {
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.login-password")));
                            } else if (arg1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.staff-accounts." + getUser.getName() + ".password"))) {
                                extraWall.operatorList.remove(e.getPlayer().getUniqueId());
                                extraWall.operatorList$Listener.add(e.getPlayer().getName());
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.join-succesfuly")));
                            } else {
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.wrong-password")));
                            }
                        }
                    } else if (getUser.isOp()) {
                        if (!cmd.equalsIgnoreCase("/" + extraWall.getEWall().getString("extra-wall.login.login-command.operators"))) {
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.login-password")));
                        } else if (arg1.equalsIgnoreCase(extraWall.getEWall().getString("extra-wall.operator-accounts." + getUser.getName() + ".password"))) {
                            if (extraWall.getEWall().getString("extra-wall.operator-accounts." + getUser.getName() + ".ip-address").contains(getUser.getAddress().getAddress().getHostAddress())) {
                                extraWall.operatorList.remove(e.getPlayer().getUniqueId());
                                extraWall.operatorList$Listener.add(e.getPlayer().getName());
                                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.join-succesfuly")));
                            } else {
                                getUser.kickPlayer(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.address-control-cant-passed").replace("%address%", getUser.getAddress().getAddress().getHostAddress())));
                            }
                        } else {
                            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.wrong-password")));
                        }
                    }
                }
            } else {
                getUser.sendMessage(ChatColor.translateAlternateColorCodes('&', extraWall.getMessage().getString("messages.extra-wall.staff-accounts.first-of-all").replace("%api_plugin%", "UltraAuth")));
            }
        }
    }
}
