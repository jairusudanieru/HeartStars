package plugin.heartstars.Events;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerSentStars {

    public void onPlayerSentStars(String amount, String comment, JavaPlugin plugin) {
        int maxHearts = plugin.getConfig().getInt("maxHearts");
        if (maxHearts < 0) maxHearts = 1;
        int stars = Integer.parseInt(amount);
        //Dividing the amount of stars to 10, so if a user sent 100 stars, the player will receive 10 stars
        int hearts = stars / 10;
        if (hearts > 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                String playerName = player.getName();
                AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                double maxHeartsInValue = maxHearts * 2;
                if (maxHealth == null) return;
                double maxHealthBaseValue = maxHealth.getBaseValue();
                double newValue = maxHealthBaseValue + (hearts*2);
                if (newValue > maxHeartsInValue) {
                    newValue = maxHeartsInValue;
                }
                //I'm sleepy that's why I don't know what am I doing here
                //gonna fix this tomorrow haha
                if (comment.contains(playerName)) {
                    maxHealth.setBaseValue(newValue);
                    int newHearts = (int) ((newValue - maxHealthBaseValue)/2);
                    String heartsString = Integer.toString(newHearts);
                    player.sendMessage("You received " + heartsString + " hearts!");
                    break;
                }
            }
        }
    }

}
