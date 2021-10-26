package me.bisquit.autoforward;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FallbackTimerTask extends BukkitRunnable {
    private final JavaPlugin plugin;

    public FallbackTimerTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if(plugin.getConfig().getBoolean("forward.enabled")) {
            if(plugin.getServer().getOnlinePlayers().stream().count() > 0) {
                for (Player p : plugin.getServer().getOnlinePlayers()) {
                    if(AuthMeApi.getInstance().isAuthenticated(p)) {
                        System.out.println("[AUTOFOR] TIMER: Trying to send " + p.getDisplayName() + " to " + plugin.getConfig().getString("forward.server") + "...");
                        if (Functions.sendToServer(p, plugin.getConfig().getString("forward.server"))) {
                            // Success
                        }
                    } else {
                        System.out.println("[AUTOFOR] TIMER: " + p.getDisplayName() + " is not authenticated, ignoring...");
                    }
                }
            }
        }
    }
}
