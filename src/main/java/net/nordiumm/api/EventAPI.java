package net.nordiumm.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventAPI {

    private final Map<UUID, EventPlayer> players = new HashMap<>();

    public EventPlayer addPlayer(Player player) {
        return addPlayer(player.getUniqueId());
    }

    public EventPlayer addPlayer(UUID uuid) {
        return players.computeIfAbsent(
                uuid,
                EventPlayer::new
        );
    }

    public EventPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public EventPlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    public Collection<EventPlayer> getPlayers() {
        return players.values();
    }

    public JsonObject finish() {
        JsonArray playerResults = new JsonArray();

        for (EventPlayer player : players.values()) {
            EventResult result =
                    new EventResult(player);

            playerResults.add(
                    result.toJson()
            );
        }

        JsonObject data = new JsonObject();

        data.add(
                "players",
                playerResults
        );

        return data;
    }

    public void clear() {
        players.clear();
    }
}