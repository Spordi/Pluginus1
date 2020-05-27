package wasdenn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import wasdenn.Main;

public class BackCommand implements CommandExecutor {

    public BackCommand(Main main) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("back")) {
            final Location loc = p.getLocation();
            p.sendMessage("§5Du wirst in 5 Sek zurück Teleportiert!");
            int i = Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                @Override
                public void run() {
                    p.teleport(loc);
                    p.sendMessage("§5Na was sagst du jetzt?");
                }

            }, 20 * 5);
            {
                ;
            }
        }
        return true;
    }
}