package prj.andy.tracker.timedEvents;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import prj.andy.tracker.TrackingPlayers;
import prj.andy.tracker.commands.TrackCommand;

public class DisplayWaypoint extends BukkitRunnable {

    private final JavaPlugin plugin;

    public DisplayWaypoint(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run(){
        for (TrackingPlayers group : TrackCommand.trackingPlayers){
            Player tracker = group.tracker();
            Player target = group.target();

            double targetX = group.getWaypoint()[0];
            double targetY = group.getWaypoint()[1];

            String message = String.format("The player you are tracking is near (%f, ~ %f)", targetX, targetY);

            tracker.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));

        }
    }
}
