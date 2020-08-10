package uk.co.ciaranmoran.sleepvote;

import org.bukkit.plugin.java.JavaPlugin;

public class SleepVote extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BedListener(), this);
    }
}
