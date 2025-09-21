package com.tintingpatch.modMode.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private static YamlConfiguration yamlConfiguration;
    File file;

    public CustomConfig(String name, File dir) {
        file = new File(dir, name);
        dir.mkdirs();
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String path, Object value) {
        yamlConfiguration.set(path, value);
    }

    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }

    public void save() {
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}