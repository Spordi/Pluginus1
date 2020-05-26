package wasdenn.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wasdenn.Main;

public class JoinLeaveKickListener implements Listener {
    private final Main plugin;
    public JoinLeaveKickListener(Main main) {
        this.plugin = main;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            e.setJoinMessage("§4" + p.getName() + " §bist dem Spiel beigetreten");
        } else {
            e.setJoinMessage("§b" + p.getName() + " §3ist schwul");
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
    }
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
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


    //mallo Heyssam
}