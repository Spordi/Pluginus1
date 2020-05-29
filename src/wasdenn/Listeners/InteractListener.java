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
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(hidden.contains(p.getName())) {
                        hidden.remove(p.getName());
                        p.showPlayer(plugin, players);
                        p.sendMessage("§aSichtbar");
                    } else if(!hidden.contains(p.getName())) {
                            hidden.add(p.getName());
                            p.hidePlayer(plugin, players);
                            p.sendMessage("§aUnsichtbar");
                        }
                }
            }else if(e.getMaterial().equals(Material.COMPASS)) {
                plugin.Inv = p.getServer().createInventory(null, 9, "§6" + p.getName() + "'s Peletorter");

                ItemStack inselportStack = new ItemStack(Material.SAND);
                ItemMeta inselportmeta = inselportStack.getItemMeta();
                inselportmeta.setDisplayName("§6Insel");
                inselportStack.setItemMeta(inselportmeta);
                plugin.Inv.setItem(6, inselportStack);

                ItemStack lobbyportStack = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta lobbyportmeta = lobbyportStack.getItemMeta();
                lobbyportmeta.setDisplayName("§6Lobby");
                lobbyportStack.setItemMeta(lobbyportmeta);
                plugin.Inv.setItem(2, lobbyportStack);

                p.openInventory(plugin.Inv);
            }
        }

    }


}
