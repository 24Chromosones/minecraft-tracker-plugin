package prj.andy.tracker.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import prj.andy.tracker.Tracker;
import prj.andy.tracker.TrackingPlayers;
import prj.andy.tracker.commands.TrackCommand;

import java.util.ArrayList;

public class TrackerWaypoint implements Listener {

    private static Tracker plugin;

    public TrackerWaypoint(Tracker plugin) {

        TrackerWaypoint.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {



        for (TrackingPlayers group : TrackCommand.trackingPlayers) {
//            System.out.println((group.getWaypoint()[0] - 10 < group.getTrackerCoords()[0] && group.getTrackerCoords()[0] < group.getWaypoint()[0] + 10)&&(group.getWaypoint()[1] - 10 < group.getTrackerCoords()[1] && group.getTrackerCoords()[1] < group.getWaypoint()[1] + 10));
            if ((group.getWaypoint()[0] - 10 < group.getTrackerCoords()[0] && group.getTrackerCoords()[0] < group.getWaypoint()[0] + 10) && (group.getWaypoint()[1] - 10 < group.getTrackerCoords()[1] && group.getTrackerCoords()[1] < group.getWaypoint()[1] + 10)) {
                group.makeWaypoint();
                group.tracker().playSound(group.tracker().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
            }
        }
    }

//    public double[] makeWaypoint(Player player) {
//        for (TrackingPlayers group : TrackCommand.trackingPlayers) {
//            if (group.contains(player)) {
//                Player trackingPlayer = group.tracker();
//                Player targetPlayer = group.target();
//
//
//                Location targetLoc = targetPlayer.getLocation();
//
//                double targetLocX = targetLoc.getX();
//                double targetLocZ = targetLoc.getZ();
//
//                Location playerLoc = player.getLocation();
//
//                double playerLocX = playerLoc.getX();
//                double playerLocZ = playerLoc.getZ();
//
//                double targetDistance = Math.sqrt(Math.pow((targetLocX-playerLocX), 2) + (Math.pow((targetLocZ-playerLocZ), 2)));
//                double theta = Math.random() * 2 * Math.PI;
//
//                double randX = targetLocX + targetDistance * Math.cos(theta);
//                double randY = targetLocZ + targetDistance * Math.sin(theta);
//
//                double[] randCoords = new double[2];
//                randCoords[0] = randX;
//                randCoords[1] = randY;
//
//                return randCoords;
//            }
//        }
//        return new double[0];
//    }



}
