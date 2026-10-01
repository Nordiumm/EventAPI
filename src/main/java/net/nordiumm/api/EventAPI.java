
package net.nordiumm.api;

import net.nordiumm.api.messaging.EventMessenger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventAPI {

    private final Map<UUID, EventPlayer> players = new HashMap<>();

    private final Plugin plugin;
    private final EventMessenger messenger;

    public EventAPI(Plugin plugin) {
        this.plugin = plugin;
        this.messenger = new EventMessenger(plugin);
    }

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

    public void finish() {
        for (EventPlayer player : players.values()) {
            EventResult result = new EventResult(player);

            messenger.sendEventResult(
                    result.toJson()
            );
        }

        messenger.sendEventFinished();
    }

    public void clear() {
        players.clear();
    }

    public void close() {
        messenger.close();
    }
}
