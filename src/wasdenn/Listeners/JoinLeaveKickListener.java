package wasdenn.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wasdenn.Main;
import wasdenn.Utils.Utils;
import wasdenn.gungame.events.GGJoinEvent;
import wasdenn.gungame.events.GGLeaveEvent;
import wasdenn.gungame.utils.GGMain;

public class JoinLeaveKickListener implements Listener {

    private final Main plugin;
    public JoinLeaveKickListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(1024);
        Utils.lobbyteleport (plugin, p); //NEU erklär ich dir später
        if (p.isOp()) {
            e.setJoinMessage("§4" + p.getName() + " §bist dem Spiel beigetreten");
        } else {
            if(plugin.isWartung) {
                p.kickPlayer("§cDer Server befindet sich im Wartungsmodus! Warte bis die Arbeiten abgeschlossen sind.");
            }
            e.setJoinMessage("§b" + p.getName() + " §3ist schwul");
        }
        if(GGMain.isWorld(p.getWorld())) {
            Bukkit.getPluginManager().callEvent(new GGJoinEvent(p, p.getWorld(), p.getWorld()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            e.setQuitMessage("§bTschuessi §4" + p.getName() + " §b:^)");
        } else {
            e.setQuitMessage("§b" + p.getName() + " §3hat das Spiel verlassen");
        }
        if(GGMain.isWorld(p.getWorld())) {
            Bukkit.getPluginManager().callEvent(new GGLeaveEvent(p, p.getWorld(), p.getWorld()));
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        if(GGMain.isWorld(p.getWorld())) {
            Bukkit.getPluginManager().callEvent(new GGJoinEvent(p, p.getWorld(), p.getWorld()));
        }
        if(p.isBanned()){
            e.setReason("§3BigMac hat dich weggebannt, diggah!");
            return;
        }
        if(p.isOp()) {
            e.setLeaveMessage("§fdu Arschi §f:rage:");
        }
        else {
            e.setLeaveMessage("§fLass das §4:rage:");
        }
    }

    /*
    Eigene Funktionen
     */

    //public static void initialize(Main plugin, Player p) {
    //    p.getInventory().clear();
    //    new BukkitRunnable() {
    //        @Override
    //        public void run() {
    //            p.getInventory().setItem(4, Utils.kompass());
    //            p.getInventory().setItem(2, Utils.netherstar());
    //            p.updateInventory();
    //        }
    //    }.runTaskLater(plugin, 10);
//
    //    p.teleport(plugin.fm.getLocation("lobby"));
    //}

}