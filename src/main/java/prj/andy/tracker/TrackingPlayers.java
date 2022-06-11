package prj.andy.tracker;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TrackingPlayers {

    private Player trackingPlayer;
    private Player targetPlayer;
    private double targetDistance;
    private double[] randCoords = new double[2];



    public TrackingPlayers(Player trackingPlayer, Player targetPlayer) {
        this.trackingPlayer = trackingPlayer;
        this.targetPlayer = targetPlayer;
        makeWaypoint();
    }

    public Player tracker(){

        return trackingPlayer;
    }

    public Player target(){

        return targetPlayer;
    }

    public double[] getTrackerCoords() {

        Location playerLoc = trackingPlayer.getLocation();

        double playerLocX = playerLoc.getX();
        double playerLocZ = playerLoc.getZ();

        double[] coords = new double[2];

        coords[0] = playerLocX;
        coords[1] = playerLocZ;

        return coords;

    }

    public void getDistance(){

        Location targetLoc = targetPlayer.getLocation();

        double targetLocX = targetLoc.getX();
        double targetLocZ = targetLoc.getZ();

        Location playerLoc = trackingPlayer.getLocation();

        double playerLocX = playerLoc.getX();
        double playerLocZ = playerLoc.getZ();

        targetDistance = Math.sqrt(Math.pow((targetLocX-playerLocX), 2) + (Math.pow((targetLocZ-playerLocZ), 2)));

    }


    public void makeWaypoint(){

        Location targetLoc = targetPlayer.getLocation();
        Location playerLoc = trackingPlayer.getLocation();

        getDistance();
        double targetRadius = targetDistance / 2;

        double targetLocX = targetLoc.getX();
        double targetLocZ = targetLoc.getZ();

        double playerLocX = playerLoc.getX();
        double playerLocZ = playerLoc.getZ();

        double theta = Math.random() * 2 * Math.PI;

        double randX = targetLocX + targetRadius * Math.cos(theta);
        double randY = targetLocZ + targetRadius * Math.sin(theta);


        randCoords[0] = randX;
        randCoords[1] = randY;

        System.out.println(targetDistance);

    }

    public double[] getWaypoint(){

        return randCoords;

    }



    public boolean contains(Player player){

        return player == trackingPlayer || player == targetPlayer;

    }


}
