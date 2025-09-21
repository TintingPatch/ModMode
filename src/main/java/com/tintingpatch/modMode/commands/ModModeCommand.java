package com.tintingpatch.modMode.commands;

import com.tintingpatch.modMode.ModMode;
import com.tintingpatch.modMode.managers.AttributeManager;
import com.tintingpatch.modMode.managers.ModeManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModModeCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0 || (args.length == 1 && args[0].equals("toggle"))){
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                if(player.hasPermission("modmode.enter")){
                    if(ModeManager.isInModMode(player.getUniqueId())){
                        ModeManager.setInModMode(player, false);
                        player.sendMessage(ModMode.getInstance().getConfig().getString("prefix") + "§r " + ModMode.getInstance().getConfig().getString("messages.leavemodmode"));
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
                    }else {
                        ModeManager.setInModMode(player, true);
                        player.sendMessage(ModMode.getInstance().getConfig().getString("prefix") + "§r " + ModMode.getInstance().getConfig().getString("messages.entermodmode"));
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);
                    }
                }else {
                    sendHelp(commandSender);
                }
            }else {
                sendHelp(commandSender);
            }
        }
        if(args.length == 1){
            if(args[0].equals("help")){
                sendHelp(commandSender);
            }
        }

        if(args.length == 2){
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                if(args[0].equals("gamemode")){
                    if(!ModMode.getInstance().getConfig().getBoolean("allowGamemodeChangeOutsideModMode") || !ModeManager.isInModMode(player.getUniqueId())){
                        player.sendMessage(ModMode.getInstance().getConfig().getString("messages.playerHasToBeInModMode"));
                        return true;
                    }
                    switch (args[1]){
                        case "survival":
                            if(player.hasPermission("modmode.gamemode.survival")){
                                if(player.getGameMode() == GameMode.SURVIVAL){
                                    player.sendMessage(ModMode.getInstance().getConfig().getString("messages.alreadyInGamemode").replace("%gamemode%", "SURVIVAL"));
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                    return true;
                                }
                                player.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.changedGamemode").replace("%gamemode%", "SURVIVAL"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }else {
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                            }
                            break;
                        case "creative":
                            if(player.hasPermission("modmode.gamemode.creative")){
                                if(player.getGameMode() == GameMode.CREATIVE){
                                    player.sendMessage(ModMode.getInstance().getConfig().getString("messages.alreadyInGamemode").replace("%gamemode%", "CREATIVE"));
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                    return true;
                                }
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.changedGamemode").replace("%gamemode%", "CREATIVE"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }else {
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                            }
                            break;
                        case "spectator":
                            if(player.hasPermission("modmode.gamemode.spectator")){
                                if(player.getGameMode() == GameMode.SPECTATOR){
                                    player.sendMessage(ModMode.getInstance().getConfig().getString("messages.alreadyInGamemode").replace("%gamemode%", "SPECTATOR"));
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                    return true;
                                }
                                player.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.changedGamemode").replace("%gamemode%", "SPECTATOR"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }else {
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                            }
                            break;
                        default:
                            player.sendMessage(ModMode.getInstance().getConfig().getString("messages.unknownCommand"));
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                            break;
                    }
                }
                if(args[0].equals("attribute")){
                    switch (args[1]){
                        case "toggleglow":
                            if(!player.hasPermission("modmode.attribute.changeglowing")){
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                return true;
                            }
                            AttributeManager.setIsGlowing(player, !AttributeManager.getIsGlowing(player));
                            if(AttributeManager.getIsGlowing(player)){
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.glowing"));
                            }else {
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.notGlowing"));
                            }
                            AttributeManager.refreshAttributes(player);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            break;
                        case "toggleallowflight":
                            if(!player.hasPermission("modmode.attribute.changeallowflight")){
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                return true;
                            }
                            AttributeManager.setIsAllowedToFly(player, !AttributeManager.getIsAllowedToFly(player));
                            if(AttributeManager.getIsAllowedToFly(player)){
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.allowFlight"));
                            }else {
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.notAllowFlight"));
                            }
                            AttributeManager.refreshAttributes(player);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            break;
                        case "toggleinvinvible":
                            if(!player.hasPermission("modmode.attribute.changeinvincible")){
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                return true;
                            }
                            AttributeManager.setIsInvincible(player, !AttributeManager.getIsInvicible(player));
                            if(AttributeManager.getIsInvicible(player)){
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.invincible"));
                            }else {
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.notInvincible"));
                            }
                            AttributeManager.refreshAttributes(player);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            break;
                        case "toggleallowattack":
                            if(!player.hasPermission("modmode.attribute.changeallowattack")){
                                player.sendMessage("§c" + ModMode.getInstance().getConfig().getString("messages.nopermissions"));
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                                return true;
                            }
                            AttributeManager.setAllowAttack(player, !AttributeManager.getIsAllowedToAttack(player));
                            if(AttributeManager.getIsAllowedToAttack(player)){
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.allowAttack"));
                            }else {
                                player.sendMessage(ModMode.getInstance().getConfig().getString("messages.notAllowAttack"));
                            }
                            AttributeManager.refreshAttributes(player);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            break;
                        default:
                            player.sendMessage(ModMode.getInstance().getConfig().getString("messages.unknownCommand"));
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                            break;

                    }
                }
            }else {
                commandSender.sendMessage(ModMode.getInstance().getConfig().getString("messages.executorHasToBePlayer"));
            }
        }

        return true;
    }

    public void sendHelp(CommandSender commandSender){
        commandSender.sendMessage("§cMod§6Mode §7by §aTintingPatch §7-v." + ModMode.getInstance().getDescription().getVersion());
        commandSender.sendMessage("§7/mod help");
        if(commandSender.hasPermission("modmode.enter")){
            commandSender.sendMessage("§7/mod toggle");
            commandSender.sendMessage("§7alias: /mod");
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        String currentArg = args[args.length - 1];

        if(args.length == 1){
            list.add("help");
            if(commandSender instanceof Player){
                if (commandSender.hasPermission("modmode.enter")){
                    list.add("toggle");
                }
                if(commandSender.hasPermission("modmode.gamemode.survival") || commandSender.hasPermission("modmode.gamemode.creative") || commandSender.hasPermission("modmode.gamemode.spectator")){
                    list.add("gamemode");
                }
                if(commandSender.hasPermission("modmode.attribute.changeglowing") || commandSender.hasPermission("modmode.attribute.changeallowflight") || commandSender.hasPermission("modmode.attribute.changeinvincible") || commandSender.hasPermission("modmode.attribute.changeallowattack")){
                    list.add("attribute");
                }
            }
        }
        if(args.length == 2){
            if(commandSender instanceof Player){
                if(args[0].equals("gamemode")){
                    if(commandSender.hasPermission("modmode.gamemode.survival")){
                        list.add("survival");
                    }
                    if(commandSender.hasPermission("modmode.gamemode.creative")){
                        list.add("creative");
                    }
                    if(commandSender.hasPermission("modmode.gamemode.spectator")){
                        list.add("spectator");
                    }
                }
                if(args[0].equals("attribute")){
                    if(commandSender.hasPermission("modmode.attribute.changeglowing")){
                        list.add("toggleglow");
                    }
                    if(commandSender.hasPermission("modmode.attribute.changeallowflight")){
                        list.add("toggleallowflight");
                    }
                    if(commandSender.hasPermission("modmode.attribute.changeinvincible")){
                        list.add("toggleinvinvible");
                    }
                    if(commandSender.hasPermission("modmode.attribute.changeallowattack")){
                        list.add("toggleallowattack");
                    }
                }
            }
        }

        for (String s : list){
            String s1 = s.toLowerCase();
            if(s1.startsWith(currentArg)){
                returnList.add(s);
            }
        }

        return returnList;
    }
}
