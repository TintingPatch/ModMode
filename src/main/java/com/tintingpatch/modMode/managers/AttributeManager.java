package com.tintingpatch.modMode.managers;

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
            if(player.getGameMode() == GameMode.CREATIVE){
                player.setAllowFlight(true);
            }else {
                player.setAllowFlight(false);
            }
            if(invinciblePlayers.contains(player.getUniqueId())){
                invinciblePlayers.remove(player.getUniqueId());
            }
            if(nonAttackingPlayers.contains(player.getUniqueId())){
                nonAttackingPlayers.remove(player.getUniqueId());
            }
        }

    }

    public static void setIsGlowing(Player player, boolean value){

    }

    public static boolean getIsGlowing(Player player){
        return false;
    }

    public static void setIsAllowedToFly(Player player, boolean value){

    }

    public static boolean getIsAllowedToFly(Player player){
        return false;
    }

    public static void setIsInvincible(Player player, boolean value){

    }

    public static boolean getIsInvicible(Player player){
        return false;
    }

    public static void setAllowAttack(Player player, boolean value){

    }

    public static boolean getIsAllowedToAttack(Player player){
        return false;
    }
}
