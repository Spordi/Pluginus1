package wasdenn.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
    public static ItemStack kompass() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Peletorter");
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack netherstar() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Jetzt bin ich weg, und jetzt bin ich wieder da. Und jetzt bin ich wieder weg. :/");
        item.setItemMeta(meta);
        return item;
    }
}
