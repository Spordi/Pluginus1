package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wasdenn.Main;
import wasdenn.Utils.Utils;

public class HubCommand implements CommandExecutor {
    private final Main plugin;

    public HubCommand(Main main) { this.plugin = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("hub")) {
            Utils.lobbyteleport(plugin, p);
        }


        return true;
    }
}
