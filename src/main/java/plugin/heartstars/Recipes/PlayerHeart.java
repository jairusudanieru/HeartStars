package plugin.heartstars.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerHeart {

    public ItemStack item() {
        ItemStack result = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta meta = result.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(ChatColor.RESET + "Player Heart");
        result.setItemMeta(meta);
        return result;
    }

    public void registerRecipe(JavaPlugin plugin) {
        ItemStack result = item();
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "player_heart"), result);
        recipe.shape("DND", "NTN", "DND");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('N', Material.NETHERITE_SCRAP);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

        Bukkit.getServer().addRecipe(recipe);
    }

}
