package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;

public class WahrheitCommand implements CommandExecutor {
    private final Main plugin;

    public WahrheitCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("wahrheit")) {
            p.sendMessage("§2[Info] §5Der Server ist so schwul wie Meyssus");
            p.sendMessage("§3imagine being as schwul as the Server");
            return true;
        }
        return true;
    }
}
