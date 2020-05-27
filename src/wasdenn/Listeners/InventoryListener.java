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
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("§b" + p.getName() + "'s Kompass")){

            if(e.getCurrentItem().getType().equals(Material.STONE)) {
                p.sendMessage("§6Hmm, yes. The floor is made of floor.");
                ItemStack item = new ItemStack(Material.STONE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("Floor, hmm");
                item.setItemMeta(meta);
                p.getInventory().addItem(item);
            }
            e.setCancelled(true);
        }
    }
}