package dev.iiDanto.superScreenShare.listeners;

import dev.iiDanto.superScreenShare.SuperScreenShare;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;

import java.time.Instant;
import java.util.Date;

public class PlayerListener implements Listener {
    private final SuperScreenShare sss;

    public PlayerListener(SuperScreenShare sss) {
        this.sss = sss;
    }

    @EventHandler
    public void onMovement(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (this.sss.screenedPlayers.contains(p.getUniqueId())){
            p.teleport(e.getFrom());
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        if (this.sss.screenedPlayers.contains(p.getUniqueId())){
            e.setCancelled(true);
            p.sendRichMessage("<gray>You are still being <#ff5e36><bold>SCREENSHARED!");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (this.sss.screenedPlayers.contains(p)){
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tempban " + p.getName() + " 7d Refusal To Screenshare");
            this.sss.screenedPlayers.remove(p);
        }
    }
}
