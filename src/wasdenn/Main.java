package wasdenn;

import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import wasdenn.Commands.*;
import wasdenn.Listeners.*;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {
    public Inventory Inv = null;

    public HashMap<UUID, Integer> marmeladenbrotmithonig = new HashMap<>();
    public boolean isWartung = false;

    @Override
    public void onEnable() {

        registerEvents();
        registerCommands();

        System.out.println("[Info] Plugin erfolgreich aktiviert!");
    }
    @Override
    public void onDisable() {
        System.out.println("[Info] Plugin erfolgreich deaktiviert!");
    }




        public void registerCommands() {
            getCommand("dersatzdesmeyssam").setExecutor(new SdmCommand(this));
            getCommand("gm").setExecutor(new GmCommand(this));
            getCommand("heal").setExecutor(new HealCommand(this));
            getCommand("wahrheit").setExecutor(new WahrheitCommand(this));
            getCommand("inv").setExecutor(new InvCommand(this));
            getCommand("Ope").setExecutor(new OpCommand(this));
            getCommand("Back").setExecutor(new BackCommand(this));
            getCommand("wartung").setExecutor(new Wartungsmodus(this));
    }


    public void registerEvents()  {
            //getServer().getPluginManager().registerEvents(this, this);
            getServer().getPluginManager().registerEvents(new JoinLeaveKickListener(this), this);
            getServer().getPluginManager().registerEvents(new ChatListener(this), this);
            getServer().getPluginManager().registerEvents(new InteractListener(this), this);
            getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
            getServer().getPluginManager().registerEvents(new PingListener(this), this);
            }


        }