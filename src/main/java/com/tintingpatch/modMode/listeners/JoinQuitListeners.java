package com.tintingpatch.modMode.listeners;

import com.tintingpatch.modMode.ModMode;
import com.tintingpatch.modMode.managers.AttributeManager;
import com.tintingpatch.modMode.managers.ModeManager;
import com.tintingpatch.modMode.managers.NotificationManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListeners implements Listener {
    @EventHandler
    public void joinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        AttributeManager.refreshAttributes(player);
        if(ModeManager.isInModMode(player.getUniqueId())){
            switch (player.getGameMode()){
                case GameMode.CREATIVE:
                    if(ModMode.getInstance().getConfig().getBoolean("notifyCreative")){
                        NotificationManager.addNotification(player);
                    }else {
                        NotificationManager.endTimer(player.getUniqueId());
                    }
                    break;
                case GameMode.SPECTATOR:
                    if(ModMode.getInstance().getConfig().getBoolean("notifySpectator")){
                        NotificationManager.addNotification(player);
                    }else {
                        NotificationManager.endTimer(player.getUniqueId());
                    }
                case GameMode.SURVIVAL:
                    if(ModMode.getInstance().getConfig().getBoolean("notifySurvival")){
                        NotificationManager.addNotification(player);
                    }else {
                        NotificationManager.endTimer(player.getUniqueId());
                    }
            }
        }
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        NotificationManager.endTimer(player.getUniqueId());
    }
}
