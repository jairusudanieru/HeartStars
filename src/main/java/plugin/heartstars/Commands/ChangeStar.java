package plugin.heartstars.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ChangeStar implements CommandExecutor {

    private final JavaPlugin plugin;
    public ChangeStar(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("changestar")) return false;
        String star = args[0];

        plugin.getConfig().set("starsAmount", star);
        plugin.saveConfig();
        sender.sendMessage("Set the stars value to: " + star);
        return true;
    }
}
