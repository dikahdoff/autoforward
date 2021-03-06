package me.bisquit.autoforward;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(AutoForward.getPlugin(AutoForward.class).getConfig().getBoolean("forward.enabled")) {
            if(!AutoForward.getPlugin(AutoForward.class).getConfig().getBoolean("forward.trigger-on-login")) {
                Functions.manageLogin(e.getPlayer());
            }
        } else {
            System.out.println("[AUTOFOR] Ignoring " + e.getPlayer().getDisplayName() + ", because forwarding is disabled in config.");
        }
    }
}
