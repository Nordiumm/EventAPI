package net.nordiumm.api;

import java.util.UUID;

public class EventPlayer {

    private final UUID uuid;

    private int kills;
    private int deaths;
    private int points;
    private boolean winner;

    public EventPlayer(UUID uuid) {
        this.uuid = uuid;
        this.kills = 0;
        this.deaths = 0;
        this.points = 0;
        this.winner = false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        kills++;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeath() {
        deaths++;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}