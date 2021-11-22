package de.xtkq.voidgen.events;

import de.xtkq.voidgen.utils.UpdateUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLoginListener implements Listener {

    private final JavaPlugin plugin;

    public PlayerLoginListener(JavaPlugin paramPlugin) {
        this.plugin = paramPlugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (UpdateUtils.isUpdateAvailable()) {
            if (player.isOp()) {
                this.plugin.getServer().getScheduler().runTaskLater(this.plugin, () -> player.sendMessage(this.getUpdateMessage()), 60L);
            }
        }
    }

    private String getUpdateMessage() {
        String updateMessage = String.format("&e%s &7v.%s &ris available here: &e%s&r", this.plugin.getName(), UpdateUtils.getLatestVersion(), UpdateUtils.getResourceURL());
        return ChatColor.translateAlternateColorCodes('&', updateMessage);
    }

    public void terminate() {
        PlayerLoginEvent.getHandlerList().unregister(this.plugin);
    }
}
