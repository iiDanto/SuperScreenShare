package dev.iiDanto.superScreenShare.listeners;

import dev.iiDanto.superScreenShare.SuperScreenShare;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
    private final SuperScreenShare sss;

    public PlayerListener(SuperScreenShare sss) {
        this.sss = sss;
    }

    @EventHandler
    public void onMovement(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (sss.screenedPlayers.contains(p)){
            p.teleport(e.getFrom());
        }
    }
}
