package prj.andy.tracker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import prj.andy.tracker.commands.TrackCommand;
import prj.andy.tracker.commands.TrackStop;
import prj.andy.tracker.listeners.LocationListener;
import prj.andy.tracker.listeners.TrackerWaypoint;
import prj.andy.tracker.timedEvents.DisplayWaypoint;
import prj.andy.tracker.timedEvents.LightningStrike;
import prj.andy.tracker.timedEvents.LocationTracker;

public final class Tracker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new TrackCommand(this);
        new TrackStop(this);
        new TrackListener(this);
        new LocationListener(this);
        new TrackerWaypoint(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

class TrackListener implements Listener {

    private final Tracker plugin;

    public TrackListener(Tracker plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        BukkitTask task = new LocationTracker(this.plugin).runTaskTimer(this.plugin, 0, 5);
        BukkitTask task1 = new LightningStrike(this.plugin).runTaskTimer(this.plugin, 0, 100);
        BukkitTask task2 = new DisplayWaypoint(this.plugin).runTaskTimer(this.plugin, 0, 5);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        for (int i = TrackCommand.trackingPlayers.size()-1; i >= 0; i--) {
            if (TrackCommand.trackingPlayers.get(i).contains(player)) {
                System.out.println("Player " + player + " left. Removing");
                TrackCommand.trackingPlayers.remove(i);
            }
        }
    }

}


