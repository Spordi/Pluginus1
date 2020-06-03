package wasdenn.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wasdenn.Main;
import wasdenn.Utils.Utils;
import wasdenn.gungame.utils.GGMain;

import java.util.ArrayList;
import java.util.UUID;

public class InteractListener implements Listener {

    private Main plugin;



    public InteractListener(Main main) {
        this.plugin = main;
        }
     ArrayList<String> sichtbarkeit = new ArrayList();
           

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        UUID i = p.getUniqueId();
        if(e.getAction() == Action.LEFT_CLICK_BLOCK | e.getAction() == Action.LEFT_CLICK_AIR) {

            if(e.getMaterial().equals(Material.IRON_SWORD)) {
                if(plugin.erschreckend.containsKey(p.getUniqueId()) && System.currentTimeMillis()-plugin.erschreckend.get(p.getUniqueId()) <= 6000) {
                return;}
                plugin.erschreckend.put(i, System.currentTimeMillis());
                GGMain.lobby.getPlayers().forEach(playerer -> playerer.sendTitle("§b*Erschreckend Piratnoises*", "by §e" + p.getName(), 10, 50, 10));
                GGMain.lobby.getPlayers().forEach(playerer -> playerer.playSound(playerer.getLocation(), Sound.ENTITY_DROWNED_DEATH_WATER, 10, 10));
            }
        }

        if(e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getMaterial().equals(Material.NETHER_STAR)) {
                for(Player players : Bukkit.getOnlinePlayers()) {
                    if(sichtbarkeit.contains(p.getName())) {
                        p.showPlayer(plugin, players);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§eSpieler §8>> §2Sichtbar").create());
                        //p.sendMessage("§8[§eSichtbarkeit§8] Spieler sind für dich jetzt: §2Sichtbar");
                        sichtbarkeit.remove(p.getName());
                        return;
                    } else if(!sichtbarkeit.contains(p.getName())) {
                            p.hidePlayer(plugin, players);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§eSpieler §8>> §4Unsichtbar").create());
                            //p.sendMessage("§8[§eSichtbarkeit§8] Spieler sind für dich jetzt: §4Unsichtbar");
                            sichtbarkeit.add(p.getName());
                            return;
                        }
                }
            }else if(e.getMaterial().equals(Material.RED_BED)) {
                if(GGMain.isWorld(p.getWorld())) {
                    Utils.lobbyteleport(plugin, p);
                }
            }
                else if(e.getMaterial().equals(Material.COMPASS)) {
                plugin.Inv = p.getServer().createInventory(null, 9, "§6" + p.getName() + "'s Peletorter");

                ItemStack gungameportStack = new ItemStack(Material.WOODEN_AXE);
                ItemMeta gungameportmeta = gungameportStack.getItemMeta();
                gungameportmeta.setDisplayName("§6GunGame");
                gungameportStack.setItemMeta(gungameportmeta);
                plugin.Inv.setItem(1, gungameportStack);

                ItemStack inselportStack = new ItemStack(Material.SAND);
                ItemMeta inselportmeta = inselportStack.getItemMeta();
                inselportmeta.setDisplayName("§6Insel");
                inselportStack.setItemMeta(inselportmeta);
                plugin.Inv.setItem(7, inselportStack);

                ItemStack lobbyportStack = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta lobbyportmeta = lobbyportStack.getItemMeta();
                lobbyportmeta.setDisplayName("§6Lobby");
                lobbyportStack.setItemMeta(lobbyportmeta);
                plugin.Inv.setItem(4, lobbyportStack);

                p.openInventory(plugin.Inv);
            }
        }

    }


}
