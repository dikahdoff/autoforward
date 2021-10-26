package me.bisquit.autoforward;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AutoForward extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        try {
            getServer().getPluginManager().registerEvents(new AuthMeLoginListener(),this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(getConfig().getBoolean("forward.enabled") && (getConfig().getInt("forward.method") == 0)) {
            System.out.println("[AUTOFOR] Enabling scheduled timer.");
            BukkitTask task = new FallbackTimerTask(this).runTaskTimer(this, 10, 120);
        } else {
            System.out.println("[AUTOFOR] Not enabling scheduled timer, because forwarding is disabled or method is not bungee in config.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}