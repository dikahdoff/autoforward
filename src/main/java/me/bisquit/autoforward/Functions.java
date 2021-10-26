package me.bisquit.autoforward;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Functions {
    public static void manageLogin(Player p) {
        System.out.println("[AUTOFOR] Managing login for " + p.getDisplayName() + "");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AutoForward.getPlugin(AutoForward.class), new Runnable(){
            @Override
            public void run(){
                if (AutoForward.getPlugin(AutoForward.class).getConfig().getInt("forward.method") == 0) {
                    System.out.println("[AUTOFOR] Forwarding " + p.getDisplayName() + " to " + AutoForward.getPlugin(AutoForward.class).getConfig().getString("forward.server"));
                    if (sendToServer(p, AutoForward.getPlugin(AutoForward.class).getConfig().getString("forward.server"))) {
                        // Success
                    }
                } else if (AutoForward.getPlugin(AutoForward.class).getConfig().getInt("forward.method") == 1) {
                    System.out.println("[AUTOFOR] Running " + AutoForward.getPlugin(AutoForward.class).getConfig().getString("forward.command") + " as " + p.getDisplayName());
                    p.chat(AutoForward.getPlugin(AutoForward.class).getConfig().getString("forward.command"));
                } else {
                    System.out.println("[AUTOFOR] ERROR! Invalid method in config, can't do anything with " + p.getDisplayName());
                }
            }
        }, AutoForward.getPlugin(AutoForward.class).getConfig().getInt("forward.delay"));
    }

    public static boolean sendToServer(Player p, String s) {
        System.out.println("[AUTOFOR] Sending " + p.getDisplayName() + " to " + s);
        p.sendMessage(ChatColor.GOLD + "> " + ChatColor.GRAY + "Connecting to " + s + "...");
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(s);
            p.sendPluginMessage(AutoForward.getPlugin(AutoForward.class), "BungeeCord", out.toByteArray());
            System.out.println("[AUTOFOR] Sent " + p.getDisplayName() + " to " + s);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
