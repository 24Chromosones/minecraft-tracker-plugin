package prj.andy.tracker.timedEvents;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import prj.andy.tracker.Tracker;
import prj.andy.tracker.TrackingPlayers;
import prj.andy.tracker.commands.TrackCommand;

import javax.sound.midi.Track;
import java.util.ArrayList;

public class LocationTracker extends BukkitRunnable{

    private final JavaPlugin plugin;

    public LocationTracker(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){
        for (TrackingPlayers group : TrackCommand.trackingPlayers) {
            Player player = group.tracker();
            Player targetPlayer = group.target();

            Location loc = targetPlayer.getLocation();

            double x = loc.getX();
            double z = loc.getZ();

            String message = String.format("The player location is (%f, ~, %f)", x, z);
//            loc.getWorld().strikeLightningEffect(loc);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));

        }
    }

}
