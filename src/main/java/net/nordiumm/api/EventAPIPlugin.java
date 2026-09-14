package net.nordiumm.api;

import org.bukkit.plugin.java.JavaPlugin;

public class EventAPIPlugin extends JavaPlugin {

    private EventAPI api;

    @Override
    public void onEnable() {
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

    public EventAPI getAPI() {
        return api;
    }
}