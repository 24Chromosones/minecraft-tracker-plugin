package prj.andy.tracker.commands;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import prj.andy.tracker.Tracker;
import prj.andy.tracker.TrackingPlayers;

import java.util.ArrayList;
import java.lang.Math;

public class TrackCommand implements CommandExecutor{

    private Tracker plugin;

    public static ArrayList<TrackingPlayers> trackingPlayers = new ArrayList<>();

    public TrackCommand(Tracker plugin){
        this.plugin = plugin;
        plugin.getCommand("track").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

        Location targetLoc = targetPlayer.getLocation();

        double targetLocX = targetLoc.getX();
        double targetLocZ = targetLoc.getZ();

        Location playerLoc = player.getLocation();

        double playerLocX = playerLoc.getX();
        double playerLocZ = playerLoc.getZ();

        double targetDistance = Math.sqrt(Math.pow((targetLocX-playerLocX), 2) + (Math.pow((targetLocZ-playerLocZ), 2)));

        if (player == targetPlayer){
            sender.sendMessage("You cannot track yourself!");
            return true;
        } else {

            for (TrackingPlayers group : trackingPlayers) {
                if ((group.contains(player) || group.contains(targetPlayer))) {
                    player.sendMessage("Either you are already tracking a player or the player you are tracking is getting tracked");
                    return true;
                }
            }

            String message = String.format("The player location is (%f, ~, %f)", targetLocX, targetLocZ);
            player.sendMessage(message);

            TrackingPlayers trackingGroup = new TrackingPlayers(player, targetPlayer);


            System.out.println(trackingGroup + "-------------------------------");
            trackingPlayers.add(trackingGroup);
        }


        return false;
    }
}

