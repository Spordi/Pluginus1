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
        if(p.isOp()) e.setMessage("§4" + message);
    }
}
