package com.tintingpatch.modMode.managers;

import com.tintingpatch.modMode.ModMode;
import com.tintingpatch.modMode.dependencies.SafetyBlocksManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class AttributeManager {
    public static ArrayList<UUID> invinciblePlayers = new ArrayList<>();
    public static ArrayList<UUID> nonAttackingPlayers = new ArrayList<>();

    public static void refreshAttributes(Player player){
        if(ModeManager.isInModMode(player.getUniqueId())){
            player.setGlowing(getIsGlowing(player));
            player.setAllowFlight(getIsAllowedToFly(player));
            if(getIsInvicible(player)){
                if(!invinciblePlayers.contains(player.getUniqueId())){
                    invinciblePlayers.add(player.getUniqueId());
                }
            }else {
                if(invinciblePlayers.contains(player.getUniqueId())){
                    invinciblePlayers.remove(player.getUniqueId());
                }
            }
            if(getIsAllowedToAttack(player)){
                if(nonAttackingPlayers.contains(player.getUniqueId())){
                   nonAttackingPlayers.remove(player.getUniqueId());
                }
            }else {
                if(!nonAttackingPlayers.contains(player.getUniqueId())){
                    nonAttackingPlayers.add(player.getUniqueId());
                }
            }
        }else {
            player.setGlowing(false);
            if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){
                player.setAllowFlight(true);
            }else {
                player.setAllowFlight(false);
            }
            if(player.getGameMode() == GameMode.SPECTATOR){
                player.setFlying(true);
            }
            if(invinciblePlayers.contains(player.getUniqueId())){
                invinciblePlayers.remove(player.getUniqueId());
            }
            if(nonAttackingPlayers.contains(player.getUniqueId())){
                nonAttackingPlayers.remove(player.getUniqueId());
            }
            if(ModMode.getInstance().getSafetyBlocksManager().isAllowedToBreak(player.getUniqueId())) ModMode.getInstance().getSafetyBlocksManager().setIsAllowedToBreak(player.getUniqueId(), false);
        }

    }

    public static void setIsGlowing(Player player, boolean value){
        ModMode.getCustomConfig().getYamlConfiguration().set("attributes.glowing." + player.getUniqueId().toString(), value);
        ModMode.getCustomConfig().save();
    }

    public static boolean getIsGlowing(Player player){
        if(ModMode.getCustomConfig().getYamlConfiguration().contains("attributes.glowing." + player.getUniqueId().toString(), true)){
            return ModMode.getCustomConfig().getYamlConfiguration().getBoolean("attributes.glowing." + player.getUniqueId().toString());
        }
        ModMode.getCustomConfig().set("attributes.glowing." + player.getUniqueId().toString(), ModMode.getInstance().getConfig().getBoolean("modsGlow"));
        return ModMode.getInstance().getConfig().getBoolean("modsGlow");
    }

    public static void setIsAllowedToFly(Player player, boolean value){
        ModMode.getCustomConfig().getYamlConfiguration().set("attributes.allowedFlight." + player.getUniqueId().toString(), value);
        ModMode.getCustomConfig().save();
    }

    public static boolean getIsAllowedToFly(Player player){
        if(ModMode.getCustomConfig().getYamlConfiguration().contains("attributes.allowedFlight." + player.getUniqueId().toString(), true)){
            return ModMode.getCustomConfig().getYamlConfiguration().getBoolean("attributes.allowedFlight." + player.getUniqueId().toString());
        }
        ModMode.getCustomConfig().set("attributes.allowedFlight." + player.getUniqueId().toString(), ModMode.getInstance().getConfig().getBoolean("allowFlight"));
        return ModMode.getInstance().getConfig().getBoolean("allowFlight");
    }

    public static void setIsInvincible(Player player, boolean value){
        ModMode.getCustomConfig().getYamlConfiguration().set("attributes.invincible." + player.getUniqueId().toString(), value);
        ModMode.getCustomConfig().save();
    }

    public static boolean getIsInvicible(Player player){
        if(ModMode.getCustomConfig().getYamlConfiguration().contains("attributes.invincible." + player.getUniqueId().toString(), true)){
            return ModMode.getCustomConfig().getYamlConfiguration().getBoolean("attributes.invincible." + player.getUniqueId().toString());
        }
        ModMode.getCustomConfig().set("attributes.invincible." + player.getUniqueId().toString(), ModMode.getInstance().getConfig().getBoolean("allowInvincible"));
        return ModMode.getInstance().getConfig().getBoolean("allowInvincible");
    }

    public static void setAllowAttack(Player player, boolean value){
        ModMode.getCustomConfig().getYamlConfiguration().set("attributes.allowattack." + player.getUniqueId().toString(), value);
        ModMode.getCustomConfig().save();
    }

    public static boolean getIsAllowedToAttack(Player player){
        if(ModMode.getCustomConfig().getYamlConfiguration().contains("attributes.allowattack." + player.getUniqueId().toString(), true)){
            return ModMode.getCustomConfig().getYamlConfiguration().getBoolean("attributes.allowattack." + player.getUniqueId().toString());
        }
        ModMode.getCustomConfig().set("attributes.allowattack." + player.getUniqueId().toString(), ModMode.getInstance().getConfig().getBoolean("allowAttack"));
        return ModMode.getInstance().getConfig().getBoolean("allowAttack");
    }

    public static void setIsAllowedToBreakProtectedBlocks(Player player, Boolean value){
        ModMode.getInstance().getSafetyBlocksManager().setIsAllowedToBreak(player.getUniqueId(), value);
    }

    public static boolean isAllowedToBreakProtectedBlocks(Player player){
        return ModMode.getInstance().getSafetyBlocksManager().isAllowedToBreak(player.getUniqueId());
    }
}
