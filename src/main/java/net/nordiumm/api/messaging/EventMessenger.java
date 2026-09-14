package net.nordiumm.api.messaging;

import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.nio.charset.StandardCharsets;

public class EventMessenger {

    private static final String CHANNEL = "nixon:events";

    private final Plugin plugin;

    public EventMessenger(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer()
                .getMessenger()
                .registerOutgoingPluginChannel(
                        plugin,
                        CHANNEL
                );
    }

    public void sendEventFinished(JsonObject data) {

        JsonObject message = new JsonObject();

        message.addProperty(
                "type",
                "EVENT_FINISHED"
        );

        message.add(
                "data",
                data
        );

        byte[] bytes = message.toString()
                .getBytes(StandardCharsets.UTF_8);

        for (Player player :
                plugin.getServer().getOnlinePlayers()) {

            player.sendPluginMessage(
                    plugin,
                    CHANNEL,
                    bytes
            );

            break;
        }
    }

    public void close() {

        plugin.getServer()
                .getMessenger()
                .unregisterOutgoingPluginChannel(
                        plugin,
                        CHANNEL
                );
    }
}