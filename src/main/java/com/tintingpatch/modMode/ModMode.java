package com.tintingpatch.modMode;

import com.tintingpatch.modMode.commands.ModModeCommand;
import com.tintingpatch.modMode.utils.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ModMode extends JavaPlugin {
    //TODO: Notification


    static CustomConfig customConfig;
    static ModMode instance;

    @Override
    public void onEnable() {
        if(instance == null){
            instance = this;
        }else {
            getComponentLogger().error("Second instance of main file has been created. Preventing...");
            return;
        }
        getComponentLogger().info("ModMode by TintingPatch enabled");
        saveDefaultConfig();
        customConfig = new CustomConfig("data.yml", getDataFolder());
        register();
    }

    void register(){
        Bukkit.getPluginCommand("modmode").setExecutor(new ModModeCommand());
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
