package wasdenn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import wasdenn.Main;

import java.util.HashMap;

public class InvCommand implements CommandExecutor {
    private final Main plugin;
    HashMap<String, ItemStack[]> inventory = new HashMap<>();
    public InvCommand(Main main) {
        this.plugin = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        Player p = (Player) sender;
        if (args[0].equalsIgnoreCase("weg")) {
            p.sendMessage("Dein Inventar wurde verstaut.");
            inventory.put(p.getName(), p.getInventory().getContents());
            p.getInventory().clear();
        }
        if (args[0].equalsIgnoreCase("holen")) {
            p.sendMessage("Da hast du es wieder");
            ItemStack[] contents = inventory.get(p.getName());
            p.getInventory().setContents(contents);
        }
        return true;
    }
}
