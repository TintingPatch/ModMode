package com.tintingpatch.modMode.managers;

import com.tintingpatch.modMode.ModMode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ModeManager {
    public static void setInModMode(Player player, boolean inModMode){
        ModMode.getCustomConfig().getYamlConfiguration().set("inMode." + player.getUniqueId().toString(), inModMode);
        ModMode.getCustomConfig().save();
        if(inModMode){
            ModMode.getInstance().getLogger().info(player.getName() + " entered ModMode");
            savePos(player);
            saveGameMode(player);
        }else {
            ModMode.getInstance().getLogger().info(player.getName() + " left ModMode");
            if(ModMode.getInstance().getConfig().getBoolean("resetPos")){
                tpBack(player);
            }
            if(!ModMode.getInstance().getConfig().getBoolean("allowGamemodeChangeOutsideModMode")){
                restoreGamemode(player);
            }
        }
        AttributeManager.refreshAttributes(player);
    }



    public static boolean isInModMode(UUID player){
        ModMode.getInstance().getLogger().info(Bukkit.getOfflinePlayer(player).getName() + " left ModMode");
        return ModMode.getCustomConfig().getYamlConfiguration().getBoolean("inMode." + player.toString());
    }

    static void savePos(Player player){
        ModMode.getCustomConfig().getYamlConfiguration().set("pos." + player.getUniqueId().toString(), player.getLocation());
        ModMode.getCustomConfig().save();
    }

    static void saveGameMode(Player player){
        ModMode.getCustomConfig().getYamlConfiguration().set("gm." + player.getUniqueId().toString(), player.getGameMode().toString());
        ModMode.getCustomConfig().getYamlConfiguration().set("isFlying." + player.getUniqueId().toString(), player.isFlying());
        ModMode.getCustomConfig().save();
    }

    static void tpBack(Player player){
        player.teleport(ModMode.getCustomConfig().getYamlConfiguration().getLocation("pos." + player.getUniqueId().toString()));
    }

    static void restoreGamemode(Player player){
        player.setGameMode(GameMode.valueOf(ModMode.getCustomConfig().getYamlConfiguration().getString("gm." + player.getUniqueId().toString())));
        player.setFlying(ModMode.getCustomConfig().getYamlConfiguration().getBoolean("isFlying." + player.getUniqueId().toString()));
    }
}
