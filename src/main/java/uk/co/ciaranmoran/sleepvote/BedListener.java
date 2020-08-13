package uk.co.ciaranmoran.sleepvote;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.Collection;
import java.util.logging.Logger;

public class BedListener implements Listener {

    private final Logger log;
    public BedListener(Logger logger) {
        this.log = logger;
    }

    @EventHandler
    public void onBedEnter(final PlayerBedEnterEvent e) {
        if(e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Collection<? extends Player> playersOnline = Bukkit.getOnlinePlayers();

            long playersOverworld = playersOnline.stream().filter(
                    p -> p.getWorld().equals(e.getPlayer().getWorld())).count();

            long playersSleeping = playersOnline.stream().filter(LivingEntity::isSleeping).count() + 1;

            log.info(String.format("Online[%d] Overworld[%d] Bed[%d]",
                    playersOnline.size(),
                    playersOverworld,
                    playersSleeping));

            if((float)playersSleeping/playersOverworld >= 0.5){
                log.info("Skipping night");
                e.getPlayer().getWorld().setTime(0);
                playersOnline.forEach(p -> p.setStatistic(Statistic.TIME_SINCE_REST, 0));
            }
        }
    }
}
