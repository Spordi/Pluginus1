//Created by Lasse Strosina on 25.05.2020 18:43 - Noice!

package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;

public class HealCommand implements CommandExecutor {
    private final Main plugin;

    public HealCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (plugin.marmeladenbrotmithonig.contains(p) && !p.isOp()) {
                p.sendMessage("§6lass das §4:rage:");
                return true;
            }

            plugin.marmeladenbrotmithonig.add(p);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.sendMessage("§2Treffen sich zwei Jäger im Wald");
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage("§6Beide tot.");
                }
            }.runTaskLater(plugin, 27);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage("§e*sad Jäger noises*");
                }
            }.runTaskLater(plugin, 53);
            new BukkitRunnable() {
                @Override
                public void run() {
                    plugin.marmeladenbrotmithonig.remove(p);

                }
            }.runTaskLater(plugin, 6000);

            return true;
        }
        return true;
    }
}
