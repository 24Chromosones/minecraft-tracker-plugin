package prj.andy.tracker.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import prj.andy.tracker.Tracker;
import prj.andy.tracker.TrackingPlayers;
import prj.andy.tracker.commands.TrackCommand;

import java.security.PublicKey;
import java.util.ArrayList;

public class LocationListener implements Listener {

    private static Tracker plugin;

    public LocationListener(Tracker plugin) {
        LocationListener.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        for (TrackingPlayers group : TrackCommand.trackingPlayers) {
            Player trackingplayer = group.tracker();
            Player targetPlayer = group.target();

            if (player == trackingplayer) { // TODO: change target player to tracking player and vice versa

                Location loc = trackingplayer.getLocation();

                double x = loc.getX();
                double z = loc.getZ();

                String message = String.format("The player location is (%f, ~, %f)", x, z);

                targetPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));

            }
        }

    }
}
