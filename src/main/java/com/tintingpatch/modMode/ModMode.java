package com.tintingpatch.modMode;

import com.tintingpatch.modMode.commands.ModModeCommand;
import com.tintingpatch.modMode.listeners.AttackListener;
import com.tintingpatch.modMode.listeners.JoinQuitListeners;
import com.tintingpatch.modMode.managers.BStatsManager;
import com.tintingpatch.modMode.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ModMode extends JavaPlugin {
    //TODO: Time Checkup
    boolean production = true;

    static CustomConfig customConfig;
    static ModMode instance;
    final int pluginID = 27334;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "███    ███  ██████  ██████  ███    ███  ██████  ██████  ███████");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "████  ████ ██    ██ ██   ██ ████  ████ ██    ██ ██   ██ ██");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "██ ████ ██ ██    ██ ██   ██ ██ ████ ██ ██    ██ ██   ██ █████");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "██  ██  ██ ██    ██ ██   ██ ██  ██  ██ ██    ██ ██   ██ ██");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "██      ██  ██████  ██████  ██      ██  ██████  ██████  ███████" + ChatColor.AQUA + " v." + getPluginMeta().getVersion());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "┏┓ ╻ ╻      ╺┳╸╻┏┓╻╺┳╸╻┏┓╻┏━╸┏━┓┏━┓╺┳╸┏━╸╻ ╻");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "┣┻┓┗┳┛       ┃ ┃┃┗┫ ┃ ┃┃┗┫┃╺┓┣━┛┣━┫ ┃ ┃  ┣━┫");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "┗━┛ ╹        ╹ ╹╹ ╹ ╹ ╹╹ ╹┗━┛╹  ╹ ╹ ╹ ┗━╸╹ ╹");
        debugLog("This is a not production version of this plugin.");

        if(this.getConfig().getBoolean("inDebug")){
            production = false;
            debugLog("Activated debug mode in production version.");
        }
        //prevent double instancing
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if(plugin.getName().equals(this.getPluginMeta().getName()) && plugin != this){
                if(Integer.parseInt(plugin.getPluginMeta().getVersion().split("\\.")[0]) >= Integer.parseInt(this.getPluginMeta().getVersion().split("\\.")[0]) || Integer.parseInt(plugin.getPluginMeta().getVersion().split("\\.")[1]) >= Integer.parseInt(this.getPluginMeta().getVersion().split("\\.")[1])){
                    this.getLogger().info("Found other newer or equal instance of this plugin. Disabling this plugin...");
                    Bukkit.getPluginManager().disablePlugin(this);
                    return;
                }else {
                    this.getLogger().info("Found other older instance of this plugin. Disabling older plugin...");
                    Bukkit.getPluginManager().disablePlugin(plugin);
                    return;
                }
            }
        }
        if(instance != null){
            this.getLogger().info("Found another instance of main class. Disabling...");
            return;
        }
        instance = this;
        getComponentLogger().info("ModMode by TintingPatch enabled");
        saveDefaultConfig();
        customConfig = new CustomConfig("data.yml", getDataFolder());
        register();
        BStatsManager bStatsManager = new BStatsManager(this, pluginID);
    }

    void register(){
        Bukkit.getPluginCommand("modmode").setExecutor(new ModModeCommand());
        Bukkit.getPluginManager().registerEvents(new AttackListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListeners(), this);
    }

    public void debugLog(String msg){
        if(!this.production){
            this.getLogger().info("Debug: " + msg);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ModMode getInstance() {
        return instance;
    }

    public static CustomConfig getCustomConfig() {
        return customConfig;
    }
}
