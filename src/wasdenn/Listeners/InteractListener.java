package wasdenn.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wasdenn.Main;

import java.util.ArrayList;

public class InteractListener implements Listener {

    private Main plugin;
    ArrayList<String> hidden = new ArrayList<>();



    public InteractListener(Main main) {
        this.plugin = main;
            }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getMaterial().equals(Material.NETHER_STAR)) {
                p.sendMessage("test");
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(hidden.contains(p.getName())) {
                        hidden.remove(p.getName());
                        p.showPlayer(plugin, players);
                        p.sendMessage("§aSichtbar");
                    } else
                        if(!hidden.contains(p.getName())) {
                            hidden.add(p.getName());
                            p.hidePlayer(plugin, players);
                            p.sendMessage("§aUnsichtbar");
                        }
                }
            }else if(e.getMaterial().equals(Material.COMPASS)) {
                p.sendMessage("test2");
                plugin.Inv = p.getServer().createInventory(null, 9, "§b" + p.getName() + "'s Kompass");

                ItemStack item = new ItemStack(Material.STONE);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("§6Peletorter");
                item.setItemMeta(meta);
                plugin.Inv.setItem(4, item);

                p.openInventory(plugin.Inv);
            }
        }

    }


}
