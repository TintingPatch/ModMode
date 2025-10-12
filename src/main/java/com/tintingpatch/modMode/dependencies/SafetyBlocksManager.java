package com.tintingpatch.modMode.dependencies;

import java.util.HashMap;
import java.util.UUID;

public class SafetyBlocksManager {
    HashMap<UUID, Boolean> isAllowedToBreak = new HashMap();
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
        isAllowedToBreak.put(player, value);
    }

    public boolean isAllowedToBreak(UUID player){
        return isAllowedToBreak.get(player);
    }
}
