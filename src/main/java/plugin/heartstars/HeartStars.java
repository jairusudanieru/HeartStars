package plugin.heartstars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.heartstars.Commands.ChangeStar;
import plugin.heartstars.Events.PlayerRespawn;
import plugin.heartstars.Events.PlayerUseHearts;
import plugin.heartstars.Facebook.FacebookSocket;
import plugin.heartstars.Recipes.PlayerHeart;

public final class HeartStars extends JavaPlugin {

    PlayerHeart heart = new PlayerHeart();
    FacebookSocket socket = new FacebookSocket();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        socket.onFacebookEvent(this);
        Bukkit.getPluginCommand("changestar").setExecutor(new ChangeStar(this));
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerUseHearts(this), this);
        heart.registerRecipe(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
