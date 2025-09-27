package com.tintingpatch.modMode.managers;

import com.tintingpatch.modMode.ModMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class NotificationManager {
    static HashMap<UUID, BukkitRunnable> runnables = new HashMap<>();
    public static void addNotification(Player player){
        if(runnables.containsKey(player.getUniqueId())){
            return;
        }
        runnables.put(player.getUniqueId(), new BukkitRunnable() {
            int timer = 0;
            @Override
            public void run() {
                timer++;
                if(timer > ModMode.getInstance().getConfig().getInt("notificationPeriod")){
                    timer = 0;
                    player.sendMessage(ModMode.getInstance().getConfig().getString("messages.notification"));
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f);
                }
            }
        });
        runnables.get(player.getUniqueId()).runTaskTimer(ModMode.getInstance(), 20, 20);
    }

    public static void endTimer(UUID uuid){
        if(runnables.containsKey(uuid)){
            runnables.get(uuid).cancel();
            runnables.remove(uuid);
        }
    }
}
