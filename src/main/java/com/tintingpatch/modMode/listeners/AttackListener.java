package com.tintingpatch.modMode.listeners;

import com.tintingpatch.modMode.managers.AttributeManager;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AttackListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(AttributeManager.invinciblePlayers.contains(event.getEntity().getUniqueId())){
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onAttack(PrePlayerAttackEntityEvent event){
        if(AttributeManager.nonAttackingPlayers.contains(event.getPlayer().getUniqueId())){
            event.setCancelled(true);
        }
    }
}
