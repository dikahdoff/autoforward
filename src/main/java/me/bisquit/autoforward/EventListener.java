package me.bisquit.autoforward;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventListener implements Listener {
    @EventHandler
    public void onLogin(LoginEvent e) {
        if(AutoForward.getPlugin(AutoForward.class).getConfig().getBoolean("forward.enabled")) {
            String serverName = AutoForward.getPlugin(AutoForward.class).getConfig().getString("forward.server");

            System.out.println("[AUTOFOR] Sending " + e.getPlayer().getDisplayName() + " to " + serverName);
            e.getPlayer().sendMessage(ChatColor.GOLD + "> " + ChatColor.GRAY + "Connecting to " + serverName + "...");

            try {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(serverName);
                e.getPlayer().sendPluginMessage(AutoForward.getPlugin(AutoForward.class), "BungeeCord", out.toByteArray());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("[AUTOFOR] Ignoring " + e.getPlayer().getDisplayName() + ", because forwarding is disabled in config.");
        }
    }
}
