package wasdenn.gungame.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import wasdenn.Main;
import wasdenn.gungame.utils.GGMain;
import wasdenn.gungame.utils.GGState;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class GGListener implements Listener {

    private final Main plugin;

    public GGListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void on(PlayerChangedWorldEvent e) {
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("gungame1")) {
            Bukkit.getPluginManager().callEvent(new GGJoinEvent(e.getPlayer(), e.getFrom(), e.getPlayer().getWorld()));
        }else if(e.getFrom().getName().equalsIgnoreCase("gungame1")) {
            Bukkit.getPluginManager().callEvent(new GGLeaveEvent(e.getPlayer(), e.getFrom(), e.getPlayer().getWorld()));
        }
    }

    @EventHandler
    public void on(GGJoinEvent e) {
        Player p = e.getPlayer();
        GGMain.sendToWorld("§e" + p.getName() + " §ahat das Spiel betreten");
        if(Main.ggState == GGState.LOBBY) {
            p.teleport(plugin.fm.getLocation("gungame.lobby"));
            p.getInventory().clear();
            if(!GGMain.counterStarted && GGMain.countPeople() > 1) {
                GGMain.startCounter(plugin);
            }
        }
    }

    @EventHandler
    public void on(GGLeaveEvent e) {
        Player p = e.getPlayer();
        GGMain.sendToWorld("§e" + p.getName() + " §chat das Spiel verlassen");
        if(Main.ggState == GGState.LOBBY) {
            if(GGMain.counterStarted && GGMain.countPeople() < 2) {
                GGMain.counterStarted = false;
            }
        }
    }
}
