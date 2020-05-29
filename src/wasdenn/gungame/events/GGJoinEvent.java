package wasdenn.gungame.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class GGJoinEvent extends Event implements Cancellable {

    public static HandlerList handlers = new HandlerList();
    public boolean cancelled = false;
    Player player;
    World previousWorld;
    World world;

    public GGJoinEvent(Player player, World previousWorld, World world) {
        this.player = player;
        this.previousWorld = previousWorld;
        this.world = world;
    }

    public Player getPlayer() {
        return player;
    }

    public World getPreviousWorld() {
        return previousWorld;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }
}
