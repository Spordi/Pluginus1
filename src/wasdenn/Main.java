package wasdenn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import wasdenn.Commands.*;
import wasdenn.Listeners.ChatListener;
import wasdenn.Listeners.JoinLeaveKickListener;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    public ArrayList<Player> marmeladenbrotmithonig = new ArrayList<>();
    @Override
    public void onEnable() {

        registerEvents();
        registerCommands();

        this.getServer().getPluginManager().registerEvents(this, this);

        System.out.println("[Info] Plugin erfolgreich aktiviert!");
    }
    @Override
    public void onDisable() {
        System.out.println("[Info] Plugin erfolgreich deaktiviert!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
        Player p = (Player) sender;

        return false;
    }
        public void registerCommands() {
            getCommand("dersatzdesmeyssam").setExecutor(new SdmCommand(this));
            getCommand("gm").setExecutor(new GmCommand(this));
            getCommand("heal").setExecutor(new HealCommand(this));
            getCommand("wahrheit").setExecutor(new WahrheitCommand(this));
            getCommand("inv").setExecutor(new InvCommand(this));
    }
        public void registerEvents() {
            new JoinLeaveKickListener(this);
            new ChatListener(this);
        }


    }