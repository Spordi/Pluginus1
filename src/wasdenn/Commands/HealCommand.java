//Created by Lasse Strosina on 25.05.2020 18:43 - Noice!

package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wasdenn.Main;

import java.util.UUID;

public class HealCommand implements CommandExecutor {

    private final Main plugin;

    public HealCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu heuscheler, das geht nicht. Was denkst du wer du bist?!?ß1ß1ß?!ß!ß1?!?!?!?!");
        return true;
        }
        Player p = (Player) sender;
        UUID a = p.getUniqueId();
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (plugin.marmeladenbrotmithonig.containsKey(a) && !p.isOp()) {
                p.sendMessage("§6lass das §4:rage:");
                p.sendMessage("§cDu musst noch §e" + plugin.marmeladenbrotmithonig.get(a) + " §cSekunden warten!");
                return true;
            }

            plugin.marmeladenbrotmithonig.put(a, 6000);
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
                    if(plugin.marmeladenbrotmithonig.get(a) == 0) {
                        plugin.marmeladenbrotmithonig.remove(a);
                        cancel();
                        return;
                    }
                    plugin.marmeladenbrotmithonig.replace(a, plugin.marmeladenbrotmithonig.get(a)-1);
                    //plugin.marmeladenbrotmithonig.remove(a);
                }
            }.runTaskTimer(plugin, 0, 20);

            return true;
        }
        return true;
    }
}
