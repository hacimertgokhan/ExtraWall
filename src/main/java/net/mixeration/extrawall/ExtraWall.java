package net.mixeration.extrawall;

import net.mixeration.extrawall.events.epMode.ep$A;
import net.mixeration.extrawall.events.*;
import net.mixeration.extrawall.module.EXTRAWALL$module;
import net.mixeration.extrawall.module.USER$module;
import net.mixeration.extrawall.user.operator.operator$A;
import net.mixeration.extrawall.user.user$A;
import net.mixeration.extrawall.user.user$B;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public final class ExtraWall extends JavaPlugin implements Listener, PluginMessageListener {
    public List<UUID> operatorList = new ArrayList();
    public List<String> operatorList$Listener = new ArrayList();
    public HashMap<UUID, String> notification = new HashMap<UUID, String>();
    public HashMap<UUID, Integer> warncount = new HashMap<UUID, Integer>();

    public boolean ExtraPrivate;
    public String ExtraPrivateAPI;

    public File file;
    public FileConfiguration config;

    public File MFile;
    public FileConfiguration MConfig;

    public File C$File;
    public FileConfiguration C$FConfig;

    private void createMessage() {
        this.MFile = new File(this.getDataFolder(), "messages.yml");
        if (!this.MFile.exists()) {
            this.MFile.getParentFile().mkdirs();
            this.saveResource("messages.yml", false);
        }
        this.MConfig = new YamlConfiguration();
        try {
            this.MConfig.load(this.MFile);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public FileConfiguration getMessage() {
        return this.MConfig;
    }

    private void createEWall() {
        this.C$File = new File(this.getDataFolder(), "extra_wall.yml");
        if (!this.C$File.exists()) {
            this.C$File.getParentFile().mkdirs();
            this.saveResource("extra_wall.yml", false);
        }
        this.C$FConfig = new YamlConfiguration();
        try {
            this.C$FConfig.load(this.C$File);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }

    public FileConfiguration getEWall() {
        return this.C$FConfig;
    }

    @Override
    public void onEnable() {
        createEWall();
        createMessage();
        importEvent();
        importCommands();
        importMessenger();
        getConfig().options().copyDefaults(true);
        ExtraPrivate = getEWall().getBoolean("extrawall-login-private-mode");
        ExtraPrivateAPI = getEWall().getString("extrawall-login-private-api");
        this.config = this.getConfig();
        this.file = new File(this.getDataFolder(), "config.yml");
        this.saveDefaultConfig();
        EXTRAWALL$module.checkExtraPrivatePlugin();
        if(EXTRAWALL$module.checkDeveloperAPI()) {
            getConfig().set("modern-mode", "none");
        } else {
            getConfig().set("modern-mode", "api-support");
        }
        saveConfig();
    }

    public void importEvent(){
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("nulled$address")) {
            Bukkit.getPluginManager().registerEvents(new event$A(this), this);
        }
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("closed$address$channels")) {
            Bukkit.getPluginManager().registerEvents(new event$B(this), this);
        }
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("asci$charsets")) {
            Bukkit.getPluginManager().registerEvents(new event$C(this), this);
        }
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("anti$spam")) {
            Bukkit.getPluginManager().registerEvents(new event$D(this), this);
        }
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("anti$uuid$spoof")) {
            Bukkit.getPluginManager().registerEvents(new event$E(this), this);
        }
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("anti$nulled$crash")) {
            Bukkit.getPluginManager().registerEvents(new event$F(this), this);
        }
        if(getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("anti$op")) {
            Bukkit.getPluginManager().registerEvents(new event$G(this), this);
        }
        Bukkit.getPluginManager().registerEvents(new user$A(this), this);
        Bukkit.getPluginManager().registerEvents(new ep$A(this), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getLogger().info("( Extra Wall | Import ) All events successfully imported from Extrawall");
    }

    public void importCommands(){
        getCommand("Extrawall").setExecutor(new user$A(this));
        getCommand("eWallOperator").setExecutor(new operator$A(this));
        getCommand("eWallSpigot").setExecutor(new user$B(this));
        Bukkit.getLogger().info("( Extra Wall | Import ) All commands successfully imported from Extrawall");
    }

    public void importMessenger() {
        Messenger messenger = Bukkit.getMessenger();
        if (getEWall().getStringList("extra-wall.event-classes-to-be-activated").contains("mc$brand")) {
            messenger.registerIncomingPluginChannel(this, "MC|Brand", this);
        }
    }

    @Deprecated
    @EventHandler(priority = EventPriority.NORMAL)
    public void pingControlEvent(PlayerMoveEvent event) {
        Player user = event.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                for (String groups : getEWall().getConfigurationSection("extra-wall.ping-control-event").getKeys(false)) {
                    int pingControlMS = getEWall().getInt("extra-wall.ping-control-event." + groups + ".ms");
                    if (USER$module.getPing(user) > pingControlMS) {
                        user.sendMessage(ChatColor.translateAlternateColorCodes('&', getEWall().getString("extra-wall.ping-control-event." + groups + ".message")));
                    }
                }
            }
        }, 40, 1280);
    }

    public static ExtraWall extraWall;
    public static ExtraWall getExtraWall() {
        return ExtraWall.extraWall;
    }

    public void reloadExtrawall() throws IOException {
        getMessage().saveToString();
        getEWall().saveToString();
        getMessage().save(MFile);
        getEWall().save(C$File);
        Bukkit.getLogger().info("( Extra Wall | Plugin ) Plugin reloaded succesfuly, version: " + Bukkit.getPluginManager().getPlugin("ExtraWall").getDescription().getVersion());
    }

    public void onPluginMessageReceived(String channel, Player p, byte[] msg) {
        try {
            String brand = (new String(msg, "UTF-8")).substring(1);
            if (!this.getEWall().getBoolean("extra-wall.mc-brand.enable")) {
                return;
            }

            Iterator var5;
            String str;
            String kickMsg;
            if (this.getEWall().getString("extra-wall.settings.mc-brand.channel-mode", "blacklist").equals("blacklist")) {
                var5 = this.getEWall().getStringList("extra-wall.settings.mc-brand.blacklist.channels").iterator();
                if (var5.hasNext()) {
                    str = (String)var5.next();
                    if (brand.toLowerCase().contains(str.toLowerCase())) {
                        kickMsg = this.getMessage().getString("messages.extra-wall.disallowed-client.kick-suspect").replace("%suspect_client%", brand);
                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickMsg));
                        this.getLogger().info("( Extra Wall | Channel ) " +p.getName() + " was kicked for using " + brand);
                        for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                            if (onlineStaff.hasPermission("extrawall.notifications") || !(notification.get(onlineStaff.getUniqueId()) == null)) {
                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', getMessage().getString("messages.notifications.disallowed-client")).replace("%suspect%", p.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", p.getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(p.getPlayer())));
                            }
                        }
                    }

                    return;
                }
            } else if (this.getEWall().getString("extra-wall.settings.mc-brand.channel-mode", "whitelist").equals("whitelist")) {
                var5 = this.getEWall().getStringList("extra-wall.settings.mc-brand.whitelist.channels").iterator();
                if (var5.hasNext()) {
                    str = (String)var5.next();
                    if (!brand.toLowerCase().contains(str.toLowerCase())) {
                        kickMsg = this.getMessage().getString("messages.extra-wall.disallowed-client.kick-suspect").replace("%suspect_client%", brand);
                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickMsg));
                        this.getLogger().info("( Extra Wall | Channel ) " +p.getName() + " was kicked for using " + brand);
                        for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                            if (onlineStaff.hasPermission("extrawall.notifications") || !(notification.get(onlineStaff.getUniqueId()) == null)) {
                                onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', getMessage().getString("messages.notifications.disallowed-client")).replace("%suspect%", p.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", p.getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(p.getPlayer())));
                            }
                        }
                    }

                    return;
                }
            }
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
            this.getLogger().severe("( Extra Wall | Channel ) " + "There was an error getting " + p.getName() + "'s client, try contacting the developer.");
            for (Player onlineStaff : Bukkit.getOnlinePlayers()) {
                if (onlineStaff.hasPermission("extrawall.notifications") || !(notification.get(onlineStaff.getUniqueId()) == null)) {
                    onlineStaff.sendMessage(ChatColor.translateAlternateColorCodes('&', getMessage().getString("messages.notifications.disallowed-client")).replace("%suspect%", p.getPlayer().getName()).replace("%next_line%", "\n\n").replace("%suspect_address%", p.getAddress().getAddress().getHostAddress()).replace("%suspect_locale%", USER$module.getLocale(p.getPlayer())));
                }
            }
        }

    }

    @Override
    public void onDisable(){
        saveConfig();
        getMessage().saveToString();
        getEWall().saveToString();
    }
}
