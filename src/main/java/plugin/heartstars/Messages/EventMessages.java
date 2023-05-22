package plugin.heartstars.Messages;

import org.bukkit.plugin.java.JavaPlugin;

public class EventMessages {

    public String heartRemoved(JavaPlugin plugin) {
        String message = plugin.getConfig().getString("message.heartRemoved");
        if (message != null && !message.isEmpty()) message = message.replace("&","§");
        return message;
    }

    public String heartAdded(JavaPlugin plugin) {
        String message = plugin.getConfig().getString("message.heartAdded");
        if (message != null && !message.isEmpty()) message = message.replace("&","§");
        return message;
    }

    public String eliminated(JavaPlugin plugin) {
        String message = plugin.getConfig().getString("message.eliminated");
        if (message != null && !message.isEmpty()) message = message.replace("&","§");
        return message;
    }

    public String heartsMaxed(JavaPlugin plugin) {
        String message = plugin.getConfig().getString("message.heartsMaxed");
        if (message != null && !message.isEmpty()) message = message.replace("&","§");
        return message;
    }

}
