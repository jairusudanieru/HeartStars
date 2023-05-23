package plugin.heartstars.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import plugin.heartstars.Configuration.EventMessages;

public class PlayerUseHearts implements Listener {

    private final JavaPlugin plugin;
    EventMessages messages = new EventMessages();
    public PlayerUseHearts(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUseHeart(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if (item == null) return;

        Material itemType = player.getInventory().getItemInMainHand().getType();
        int maxHearts = plugin.getConfig().getInt("maxHearts");
        boolean isRightClick = action.isRightClick();
        boolean isHeartOfTheSea = itemType.equals(Material.HEART_OF_THE_SEA);
        ItemMeta meta = item.getItemMeta();

        if (isRightClick && isHeartOfTheSea && meta.hasEnchant(Enchantment.DURABILITY)) {
            AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

            if (maxHealth != null && maxHealth.getBaseValue() < (maxHearts * 2)) {
                double newValue = maxHealth.getBaseValue() + 2;
                maxHealth.setBaseValue(newValue);
                ItemStack mainHandItem = player.getInventory().getItemInMainHand();
                mainHandItem.setAmount(mainHandItem.getAmount() - 1);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                player.sendMessage(Component.text(messages.heartAdded(plugin)));
            } else {
                player.sendMessage(Component.text(messages.heartsMaxed(plugin)));
            }
        }
    }

}
