package com.tintingpatch.modMode.listeners;

import com.tintingpatch.modMode.ModMode;
import com.tintingpatch.modMode.managers.ModeManager;
import com.tintingpatch.modMode.managers.NotificationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeSwitchListener implements Listener {
    @EventHandler
    public void onGamemodeSwitch(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer();
        if(ModeManager.isInModMode(player.getUniqueId())){
            switch (event.getNewGameMode()){
                case CREATIVE:
                    if(ModMode.getInstance().getConfig().getBoolean("notifyCreative")){
                        NotificationManager.addNotification(player);
                    }else {
                        NotificationManager.endTimer(player.getUniqueId());
                    }
                    break;
                case SURVIVAL:
                    if(ModMode.getInstance().getConfig().getBoolean("notifySurvival")){
                        NotificationManager.addNotification(player);
                    }else {
                        NotificationManager.endTimer(player.getUniqueId());
                    }
                    break;
                case SPECTATOR:
                    if(ModMode.getInstance().getConfig().getBoolean("notifySpectator")){
                        NotificationManager.addNotification(player);
                    }else {
                        NotificationManager.endTimer(player.getUniqueId());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
