package plugin.heartstars.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import plugin.heartstars.Messages.EventMessages;

public class PlayerRespawn implements Listener {

    private final JavaPlugin plugin;
    EventMessages messages = new EventMessages();
    public PlayerRespawn(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(@NotNull PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth == null) return;
        double baseValue = maxHealth.getBaseValue();
        double newBaseValue = maxHealth.getBaseValue() - 2;

        if (baseValue > 2) {
            maxHealth.setBaseValue(newBaseValue);
            player.setHealth(newBaseValue);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Component.text(messages.heartRemoved(plugin)));
            }, 1L);
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Location prison = plugin.getConfig().getLocation("prison.location");
                if (prison == null) prison = player.getWorld().getSpawnLocation();
                player.teleport(prison);
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(Component.text(messages.eliminated(plugin)));
            }, 1L);
        }
    }
}
