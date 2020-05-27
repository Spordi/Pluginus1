package wasdenn.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;

public class BackCommand implements CommandExecutor {

    private final Main plugin;

    public BackCommand(Main main) {
        this.plugin = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("back")) {
            final Location loc = p.getLocation();
            p.sendMessage("§5Du wirst in 5 Sek zurück Teleportiert!");
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.teleport(loc);
                    p.sendMessage("§5Na was sagst du jetzt?");

                }
            }.runTaskLater(plugin, 20*5);

        }
        return true;
    }
}