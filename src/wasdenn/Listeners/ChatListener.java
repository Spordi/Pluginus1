package wasdenn.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import wasdenn.Main;

public class ChatListener implements Listener {
    private final Object plugin;
    public ChatListener(Main main) {
        this.plugin = main;
    }



    @EventHandler
    public void onSend(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        /*
        als erstes machst du einfach WENN DIE NACHRICHT NICHT MIT "@a" beginnt e.setcancelled true, damit die nachricht nicht gesendet wird
        danach sendes du manuell mit p.getWorld().getPlayers().forEach(player -> player.sendMessage...
         */
        if(p.isOp()) e.setMessage("§4" + message);
    }
    @EventHandler
    public void onSendInWelt(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(!e.getMessage().startsWith("@a")) {
            e.setCancelled(true);
            p.getWorld().getPlayers().forEach(player -> player.sendMessage("<" + p.getName() + "> " + e.getMessage()));

        }else p.sendMessage("<" + p.getName() + "> " + e.getMessage());
    }
}
