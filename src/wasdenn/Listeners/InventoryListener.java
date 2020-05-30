package wasdenn.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import wasdenn.Main;

public class InventoryListener implements Listener {
    private Main plugin;

    public InventoryListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase("§6" + p.getName() + "'s Peletorter")) {
            if (e.getClickedInventory() != null) {
                if (e.getCurrentItem().getType() == Material.SAND) {
                    p.teleport(plugin.fm.getLocation("insel"));
                }
            if (e.getCurrentItem().getType() == Material.FIRE_CHARGE) {
                p.teleport(plugin.fm.getLocation("lobby"));
            }
            if (e.getCurrentItem().getType() == Material.WOODEN_AXE) {
                p.teleport(plugin.fm.getLocation("gungame1"));
            }
        }
            e.setCancelled(true);
        }

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!p.isOp()){
            if( e.getClickedInventory()==null ) return;
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