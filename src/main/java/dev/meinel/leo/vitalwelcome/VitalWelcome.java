/*
 * File: VitalWelcome.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwelcome;

import dev.meinel.leo.vitalwelcome.files.Messages;
import dev.meinel.leo.vitalwelcome.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VitalWelcome extends JavaPlugin {

    private Messages messages;

    @Override
    public void onEnable() {
        registerListeners();
        messages = new Messages();
        Bukkit
                .getLogger()
                .info("VitalWelcome v" + this.getDescription().getVersion() + " enabled");
        Bukkit.getLogger().info("Copyright (C) 2022 Leopold Meinel");
        Bukkit.getLogger().info("This program comes with ABSOLUTELY NO WARRANTY!");
        Bukkit
                .getLogger()
                .info(
                        "This is free software, and you are welcome to redistribute it under certain conditions.");
        Bukkit
                .getLogger()
                .info(
                        "See https://github.com/LeoMeinel/VitalWelcome/blob/main/LICENSE for more details.");
    }

    @Override
    public void onDisable() {
        Bukkit
                .getLogger()
                .info(
                        "VitalWelcome v" + this.getDescription().getVersion() + " disabled");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    public Messages getMessages() {
        return messages;
    }
}
