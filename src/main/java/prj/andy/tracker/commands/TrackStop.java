package prj.andy.tracker.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import prj.andy.tracker.Tracker;

import java.util.ArrayList;

public class TrackStop implements CommandExecutor {

    private Tracker plugin;

    public TrackStop(Tracker plugin) {
        this.plugin = plugin;
        plugin.getCommand("trackstop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;


        for (int i = TrackCommand.trackingPlayers.size()-1; i >= 0; i--) {
            if (TrackCommand.trackingPlayers.get(i).tracker() == player) {
                System.out.println("TESTTT-----0----");
                TrackCommand.trackingPlayers.remove(i);
            }
        }

        System.out.println(TrackCommand.trackingPlayers);

        return false;
    }
}
