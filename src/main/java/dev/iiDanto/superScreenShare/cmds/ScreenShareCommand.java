package dev.iiDanto.superScreenShare.cmds;

import dev.iiDanto.superScreenShare.SuperScreenShare;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class ScreenShareCommand implements CommandExecutor {
    private final SuperScreenShare sss;

    public ScreenShareCommand(SuperScreenShare sss) {
        this.sss = sss;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players may use this feature!");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1){
            p.sendRichMessage(ChatColor.RED + "Invalid Arguments!");
            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null & target.isOnline()){
            if (sss.screenedPlayers.contains(target.getUniqueId())){
                sss.screenedPlayers.remove(target.getUniqueId());
                target.sendRichMessage("<gray>You have been <#ff5e36><bold>RELEASED <reset><gray>from the Screenshare!");
                target.playSound(target, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                target.removePotionEffect(PotionEffectType.SLOWNESS);
                target.removePotionEffect(PotionEffectType.BLINDNESS);
                target.setInvulnerable(false);
                p.sendRichMessage("<#73ff36>You have successfully released <gold>%p".replace("%p", target.getName()));
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
            } else {
                target.sendTitle(net.md_5.bungee.api.ChatColor.of("#ff5e36") + "YOU ARE BEING SCREENSHARED", ChatColor.GRAY + "Please read further instructions in chat.");
                target.sendRichMessage("<gray>You are Being <#ff5e36><bold>SCREENSHARED!");
                target.sendRichMessage("<#ff5e36>1. <gray>Do NOT Attempt to Leave, or Disconnect from the server. Doing so will result in you being banned!");
                String discordURL = sss.getConfig().getString("configuration.discord-url");
                target.sendRichMessage("<#ff5e36>2. <gray>Ensure you are in our Discord Server, if not; Join the server. (" + discordURL + ")");
                target.sendRichMessage("<#ff5e36>3. <gray>Join the Waiting Room Voice call and await a staff member.");
                target.sendRichMessage("");
                target.setInvulnerable(true);
                p.sendRichMessage("<#73ff36>You have successfully screenshared <gold>%p".replace("%p", target.getName()));
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                sss.screenedPlayers.add(target.getUniqueId());
                PotionEffect slowness = new PotionEffect(PotionEffectType.SLOWNESS, 10000000, 225);
                PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 10000000, 225);
                target.addPotionEffect(blindness);
                target.addPotionEffect(slowness);
            }
        }
        return true;
    }
}
