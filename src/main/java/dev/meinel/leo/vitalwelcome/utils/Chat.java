/*
 * File: Chat.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalwelcome.utils;

import dev.meinel.leo.vitalwelcome.VitalWelcome;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Chat {

  private static final VitalWelcome main = JavaPlugin.getPlugin(
      VitalWelcome.class);

  private Chat() {
    throw new IllegalStateException("Utility class");
  }

  public static void sendBroadcast(
      @NotNull Map<String, String> placeholders,
      @NotNull String message) {
    List<String> messages = getMessages(message);
    for (String string : messages) {
      for (Map.Entry<String, String> entry : placeholders.entrySet()) {
        if (string.contains(entry.getKey())) {
          string = string.replace(entry.getKey(), entry.getValue());
        }
      }
      for (Player player : Bukkit.getServer().getOnlinePlayers()) {
        player.sendMessage(replaceColors(string));
        player.playSound(
            player.getLocation(),
            Sound.ENTITY_TURTLE_EGG_HATCH,
            1,
            1);
      }
    }
  }

  private static List<String> getMessages(@NotNull String message) {
    List<String> messages;
    if (main.getMessages().getMessagesConf().isList(message)) {
      messages = Objects.requireNonNull(
          main.getMessages().getMessagesConf().getStringList(message));
    } else {
      messages = new ArrayList<>();
      messages.add(main.getMessages().getMessagesConf().getString(message));
    }
    return messages;
  }

  public static String replaceColors(@NotNull String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
}
