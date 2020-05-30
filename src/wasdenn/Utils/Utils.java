package wasdenn.Utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wasdenn.Main;

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
    public static ItemStack hubBett() {
        ItemStack item = new ItemStack(Material.RED_BED);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Hub");
        item.setItemMeta(meta);
        return item;
    }
    public static void lobbyteleport(Main plugin, Player p) {
        p.teleport(plugin.fm.getLocation("lobby"));
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        p.getInventory().setItem(4, kompass());
        p.getInventory().setItem(2, netherstar());
        p.sendMessage("§aDu bist jetzt in der Lobby");
    }
}
