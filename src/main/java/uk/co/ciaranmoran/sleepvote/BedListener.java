package uk.co.ciaranmoran.sleepvote;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedListener implements Listener {
    @EventHandler
    public void onBedEnter(final PlayerBedEnterEvent e) {
        e.getPlayer().sendMessage("Entered Bed");
    }
}
