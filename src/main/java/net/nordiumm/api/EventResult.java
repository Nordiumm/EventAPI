package net.nordiumm.api;

import com.google.gson.JsonObject;

import java.util.UUID;

public class EventResult {

    private final UUID uuid;
    private final int kills;
    private final int deaths;
    private final int points;
    private final boolean winner;

    public EventResult(EventPlayer player) {
        this.uuid = player.getUuid();
        this.kills = player.getKills();
        this.deaths = player.getDeaths();
        this.points = player.getPoints();
        this.winner = player.isWinner();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getPoints() {
        return points;
    }

    public boolean isWinner() {
        return winner;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("uuid", uuid.toString());
        json.addProperty("kills", kills);
        json.addProperty("deaths", deaths);
        json.addProperty("points", points);
        json.addProperty("winner", winner);

        return json;
    }
}