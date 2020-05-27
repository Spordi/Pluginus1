package wasdenn.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import wasdenn.Main;

import java.io.File;
import java.io.IOException;

/**
 * Created by Meyssam Saghiri on Mai 27, 2020
 */
public class FileManager {

    private Main plugin = null;
    public File file = new File(plugin.getDataFolder(), "locations.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public FileManager(Main main) {
        this.plugin = main;
    }

    public void setLocation(String name, Location loc) {
        cfg.set(name + ".world", loc.getWorld().getName());

        cfg.set(name + ".x", loc.getBlockX());
        cfg.set(name + ".y", loc.getBlockY());
        cfg.set(name + ".z", loc.getBlockZ());

        cfg.set(name + ".yaw", loc.getYaw());
        cfg.set(name + ".pitch", loc.getPitch());

        saveConfig();
    }

    public Location getLocation(String name) {
        String world = cfg.getString(name + ".world");

        int x = cfg.getInt(name + ".x");
        int y = cfg.getInt(name + ".y");
        int z = cfg.getInt(name + ".z");

        float yaw = (float) cfg.getDouble(name + ".yaw");
        float pitch = (float) cfg.getDouble(name + ".pitch");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

        return loc;
    }

    public void saveConfig() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
