package wasdenn.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wasdenn.Main;

public class InventoryListener implements Listener {
    private Main plugin;

    public InventoryListener(Main main) {
        this.plugin = main;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equalsIgnoreCase("§b" + p.getName() + "'s Kompass")) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.STONE) {
                p.sendMessage("§6Hmm, yes. The floor is made of floor.");
                p.getInventory().addItem(new ItemStack(Material.STONE, 32, 0,));
                ItemStack item = new ItemStack(Material.STONE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("Floor, hmm");
            }
        }
    }
}