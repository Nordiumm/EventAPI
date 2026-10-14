package net.nordiumm.api.messaging;

import com.google.gson.JsonArray;
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

    public void sendEventResult(JsonObject result) {
        JsonObject message = new JsonObject();

        message.addProperty("type", "EVENT_RESULT");
        message.add("data", result);

        sendMessage(message);
    }

    public void sendEventFinished() {
        JsonObject message = new JsonObject();
        message.addProperty("type", "EVENT_FINISHED");
        sendMessage(message);
    }
    private void sendMessage(JsonObject message) {
        Player carrier = plugin.getServer()
                .getOnlinePlayers()
                .stream()
                .findFirst()
                .orElse(null);

        if (carrier == null) {
            plugin.getLogger().severe(
                    "Cannot send event message: no players are online."
            );
            return;
        }

        byte[] bytes = message.toString()
                .getBytes(StandardCharsets.UTF_8);

        carrier.sendPluginMessage(
                plugin,
                CHANNEL,
                bytes
        );
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