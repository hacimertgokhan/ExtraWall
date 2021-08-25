package net.mixeration.extrawall.module;

import net.mixeration.extrawall.ExtraWall;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class EXTRAWALL$module {
    public ExtraWall eWall;
    public EXTRAWALL$module(ExtraWall eWall) {
        this.eWall = eWall;
    }

    public static HashMap<UUID, Long> cooldown = new HashMap();

    public static boolean checkDeveloperAPI(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("DeveloperAPI");
        if(Bukkit.getPluginManager().getPlugin("DeveloperAPI") == null) {
            Bukkit.getLogger().warning("( ExtraWall | API ) DeveloperAPI cant found, please change modern-mode to do none.");
            Bukkit.getLogger().warning("( ExtraWall | API ) Please do not open modern mode to do api support, developer api cant hooked");
        } else {
            Bukkit.getLogger().info("( ExtraWall | API ) DeveloperAPI found, please change modern-mode to do api-support.");
        }
        return true;
    }

    public static boolean checkExtraPrivatePlugin(){
        Plugin ame = Bukkit.getPluginManager().getPlugin("AuthMe");
        Plugin uauth = Bukkit.getPluginManager().getPlugin("UltraAuth");
        if(Bukkit.getPluginManager().getPlugin("AuthMe") == null) {
            Bukkit.getLogger().warning("( ExtraWall | API ) AuthMe cant found, auth me support cant hooked.");
        } else {
            Bukkit.getLogger().info("( ExtraWall | API ) Authme found, auth me support added.");
        }
        if(Bukkit.getPluginManager().getPlugin("UltraAuth") == null) {
            Bukkit.getLogger().warning("( ExtraWall | API ) UltraAuth cant found, UltraAuth support cant hooked.");
        } else {
            Bukkit.getLogger().info("( ExtraWall | API ) UltraAuth found, UltraAuth support added.");
        }
        return true;
    }

}
