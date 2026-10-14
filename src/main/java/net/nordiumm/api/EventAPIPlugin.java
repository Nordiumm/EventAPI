package net.nordiumm.api;

import net.nordiumm.api.messaging.Database;
import org.bukkit.plugin.java.JavaPlugin;

public class EventAPIPlugin extends JavaPlugin {

    private EventAPI api;
    private static EventAPIPlugin instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        api = new EventAPI(this);

        getLogger().info("EventAPI enabled!");

    }

    @Override
    public void onDisable() {
        if (api != null) {
            api.close();
        }

        getLogger().info("EventAPI disabled!");
    }
    public static EventAPIPlugin getInstance() {
        return instance;
    }
    public EventAPI getAPI() {
        return api;
    }
}