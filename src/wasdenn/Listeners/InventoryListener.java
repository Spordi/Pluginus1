package wasdenn.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
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

        if(e.getView().getTitle().equalsIgnoreCase("§b" + p.getName() + "'s Kompass")) {

            if(e.getCurrentItem().getType() == Material.STONE) {
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
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!p.isOp()){
            if(e.getClickedInventory().getType() == InventoryType.PLAYER) {
                if (p.getWorld().getName().equalsIgnoreCase("world")) {
                e.setCancelled(true);
                }
            }
        }

    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(!p.isOp()){
                if (p.getWorld().getName().equalsIgnoreCase("world")) {
                    e.setCancelled(true);
            }
        }

    }
}