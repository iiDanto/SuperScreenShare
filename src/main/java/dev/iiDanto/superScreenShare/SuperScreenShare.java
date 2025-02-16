package dev.iiDanto.superScreenShare;

import dev.iiDanto.superScreenShare.cmds.ScreenShareCommand;
import dev.iiDanto.superScreenShare.listeners.PlayerListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class SuperScreenShare extends JavaPlugin {

    public List<UUID> screenedPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("ss").setExecutor(new ScreenShareCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getConsoleSender().sendRichMessage("");
        getServer().getConsoleSender().sendRichMessage("<gold>Super Screen Share <gray>V1.0");
        getServer().getConsoleSender().sendRichMessage("<green>Started Successfully, Enjoy!");
        getServer().getConsoleSender().sendRichMessage("<blue>Author: <gray>iiDanto");
        getServer().getConsoleSender().sendRichMessage("");
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendRichMessage("");
        getServer().getConsoleSender().sendRichMessage("<gold>Super Screen Share <gray>V1.0");
        getServer().getConsoleSender().sendRichMessage("<red>Quit Successfully, Thank you for using SSS!");
        getServer().getConsoleSender().sendRichMessage("<blue>Author: <gray>iiDanto");
        getServer().getConsoleSender().sendRichMessage("");
    }
}
