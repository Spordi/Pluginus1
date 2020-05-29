package wasdenn.gungame.utils;

import wasdenn.Main;
import wasdenn.gungame.commands.MainCommand;
import wasdenn.gungame.events.GGListener;

/**
 * Created by Meyssam Saghiri on Mai 29, 2020
 */
public class GGInitialize {

    public static void initialize(Main main) {
        Main.ggState = GGState.LOBBY;
        main.getCommand("gg").setExecutor(new MainCommand(main));
        main.getServer().getPluginManager().registerEvents(new GGListener(main), main);
    }

}
