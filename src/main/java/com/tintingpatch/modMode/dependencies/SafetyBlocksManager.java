package com.tintingpatch.modMode.dependencies;

public class SafetyBlocksManager {
    boolean usesSafetyBlocks = false;

    public SafetyBlocksManager(){

    }

    public void activateSafetyBlocks(){
        usesSafetyBlocks = true;
    }
}
