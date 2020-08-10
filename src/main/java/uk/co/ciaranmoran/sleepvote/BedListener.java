package uk.co.ciaranmoran.sleepvote;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.Collection;

public class BedListener implements Listener {

    @EventHandler
    public void onBedEnter(final PlayerBedEnterEvent e) {
        if(e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Collection<? extends Player> playersOnline = Bukkit.getOnlinePlayers();
            long playersSleeping = playersOnline.stream().filter(LivingEntity::isSleeping).count();

            if((float)playersSleeping/playersOnline.size() >= 0.5){
                e.getPlayer().getWorld().setTime(0);
                playersOnline.forEach(p -> p.setStatistic(Statistic.TIME_SINCE_REST, 0));
            }
        }
    }
}
