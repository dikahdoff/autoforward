package me.bisquit.autoforward;

import fr.xephi.authme.events.LoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AuthMeLoginListener implements Listener {
    @EventHandler
    public void onLogin(LoginEvent e) {
        if(AutoForward.getPlugin(AutoForward.class).getConfig().getBoolean("forward.enabled")) {
            if(AutoForward.getPlugin(AutoForward.class).getConfig().getBoolean("forward.trigger-on-login")) {
                Functions.manageLogin(e.getPlayer());
            }
        } else {
            System.out.println("[AUTOFOR] Ignoring " + e.getPlayer().getDisplayName() + ", because forwarding is disabled in config.");
        }
    }
}
