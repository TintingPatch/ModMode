package com.tintingpatch.modMode.dependencies;

import com.tintingpatch.modMode.ModMode;
import com.tintingpatch.modMode.managers.ModeManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SafetyBlocksManager {
    ArrayList<UUID> playersAllowedToBreak = new ArrayList<>();
    boolean usingSafetyBlocks = false;

    public SafetyBlocksManager(){
        //constructor
    }

    public void activateSafetyBlocks(){
        usingSafetyBlocks = true;
    }

    public boolean isUsingSafetyBlocks() {
        return usingSafetyBlocks;
    }

    public void setIsAllowedToBreak(UUID player, boolean value){
        ModMode.getInstance().debugLog("Player " + player + " allowed to break protected blocks: " + value);
        if(playersAllowedToBreak.contains(player)){
            if(value) {
                return;
            }
            playersAllowedToBreak.remove(player);
        }else if (value){
            playersAllowedToBreak.add(player);
        }

        ModMode.getInstance().debugLog("Player " + player + " allowed to break protected blocks confirm: " + isAllowedToBreak(player) + ". This value represents if the player could destroy other protected blocks right now.");

    }

    public boolean isAllowedToBreak(UUID player){
        return playersAllowedToBreak.contains(player) && ModeManager.isInModMode(player);
    }
}
