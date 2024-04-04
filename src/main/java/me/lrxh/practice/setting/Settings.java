package me.lrxh.practice.setting;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum Settings {

    SHOW_PLAYERS("Toggle Players Visibility", Material.ENDER_PEARL, "Show or Hide players."),
    SHOW_SCOREBOARD("Toggle Scoreboard", Material.PAINT, "Show or Hide Scoreboard."),
    ALLOW_SPECTATORS("Toggle Spectators", Material.ENDER_EYE, "Allow players to spectate."),
    ALLOW_DUELS("Toggle Duels", Material.DIAMOND_SWORD, "Allow Duel Requests."),
    KILL_EFFECTS("Kill Effects", Material.DIAMOND_AXE, "Select Kill Effect."),
    THEME("Select Theme", Material.DYE, "Select Color Theme."),
    PING_RANGE("Ping Range", Material.MAP, "Change Your Ping Range."),
    TIME_CHANGE("Change Time", Material.WATCH, "Set Your Custom Time."),
    MENU_SOUNDS("Menu Sounds", Material.BEACON, "Toggle The Menu Sounds.");

    private final String name;
    private final Material material;
    private final String description;

    Settings(String name, Material material, String description) {
        this.name = name;
        this.material = material;
        this.description = description;
    }
}
