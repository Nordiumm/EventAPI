package net.nordiumm.api.messaging;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;


public class Database {
	
	
    private final Plugin plugin;
    private Connection connection;

    // MySQL database settings
    private String host = "172.18.0.1";
    private Integer port = 3306;
    private String database = "s11_Hg_Minigame";
    private String username = "u11_QkzU9o3R0F";
    private String password = "OnB51^org5pb!p=Jmcqflv!B";

    public Database(Plugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.host = config.getString("database.host", "localhost");
        this.port = config.getInt("database.port");
        this.database = config.getString("database.name", "minecraft");
        this.username = config.getString("database.username", "");
        this.password = config.getString("database.password", "");


        plugin.getLogger().info(this.host);
        plugin.getLogger().info(this.port.toString());
        plugin.getLogger().info(this.database);
        plugin.getLogger().info(this.username);
        plugin.getLogger().info(this.password);
    }

    public boolean connect() {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + database
                    + "?useSSL=false&connectTimeout=5000&socketTimeout=5000",
                username,
                password
            );

            plugin.getLogger().info("Connected to the database!");

            createTables();

            return true;

        } catch (SQLException | ClassNotFoundException e) {
            plugin.getLogger().severe("Failed to connect to the database!");
            e.printStackTrace();
            return false;
        }
    }




    private void createTables() {
        String playerStatsTable = """
            CREATE TABLE IF NOT EXISTS player_stats (
                uuid VARCHAR(36) NOT NULL,
                wins INT NOT NULL DEFAULT 0,
                kills INT NOT NULL DEFAULT 0,
                deaths INT NOT NULL DEFAULT 0,
                gamesplayed INT NOT NULL DEFAULT 0,
                points INT NOT NULL DEFAULT 0,
                PRIMARY KEY (uuid)
            )
            """;

        try (PreparedStatement statement = connection.prepareStatement(playerStatsTable)) {
            statement.executeUpdate();

            plugin.getLogger().info("Database tables verified/created!");

        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to create database tables!");
            e.printStackTrace();
        }
    }

    // Disconnect from the database
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                plugin.getLogger().info("Disconnected from the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add wins for a player
    public void AddWins(UUID p, int wins) {
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO player_stats (uuid, wins) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE wins = wins + ?")) {
                statement.setString(1, p.toString());
                statement.setInt(2, wins);
                statement.setInt(3, wins);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Method to add kills for a player
    public void AddKills(UUID p, int kills) {
        if(p == null){
            return;
        }

        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO player_stats (uuid, kills) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE kills = kills + ?")) {
                statement.setString(1, p.toString());
                statement.setInt(2, kills);
                statement.setInt(3, kills);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Method to add deaths for a player
    public void AddDeaths(UUID p, int deaths) {
        if(p == null){
            return;
        }
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO player_stats (uuid, deaths) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE deaths = deaths + ?")) {
                statement.setString(1, p.toString());
                statement.setInt(2, deaths);
                statement.setInt(3, deaths);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void AddGamesPlayed(UUID p, int gamesplayed){
        if(p == null){
            return;
        }
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO player_stats (uuid, gamesplayed) VALUES (?, ?) " +
                            "ON DUPLICATE KEY UPDATE gamesplayed = gamesplayed + ?")) {
                statement.setString(1, p.toString());
                statement.setInt(2, gamesplayed);
                statement.setInt(3, gamesplayed);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void AddPoints(UUID p, int points){
        if(p == null){
            return;
        }
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO player_stats (uuid, points) VALUES (?, ?) " +
                            "ON DUPLICATE KEY UPDATE points = points + ?")) {
                statement.setString(1, p.toString());
                statement.setInt(2, points);
                statement.setInt(3, points);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Method to get the number of wins for a player
    public int GetWins(UUID p) {
        if(p == null){
            return 0;
        }
        int wins = 0;
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT wins FROM player_stats WHERE uuid = ?")) {
                statement.setString(1, p.toString());
                ResultSet results = statement.executeQuery();
                if (results.next()) {
                    wins = results.getInt("wins");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return wins;


    }

    // Method to get the number of kills for a player
    public int GetKills(UUID p) {
        if(p == null){
            return 0;
        }
        int kills = 0;
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT kills FROM player_stats WHERE uuid = ?")) {
                statement.setString(1, p.toString());
                ResultSet results = statement.executeQuery();
                if (results.next()) {
                    kills = results.getInt("kills");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return kills;
    }

    public int GetPoints(UUID p){
        if(p == null){
            return 0;
        }
        int points = 0;
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT points FROM player_stats WHERE uuid = ?")) {
                statement.setString(1, p.toString());
                ResultSet results = statement.executeQuery();
                if (results.next()) {
                    points = results.getInt("points");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return points;
    }


    // Method to get the number of deaths for a player
    public int GetDeaths(UUID p) {
        if(p == null){
            return 0;
        }

        int deaths = 0;
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT deaths FROM player_stats WHERE uuid = ?")) {
                statement.setString(1, p.toString());
                ResultSet results = statement.executeQuery();
                if (results.next()) {
                    deaths = results.getInt("deaths");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return deaths;
    }

    public int GetGamesPlayed(UUID p){
        if(p == null){
            return 0;
        }

        int gamesplayed = 0;
        if (connect()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT gamesplayed FROM player_stats WHERE uuid = ?")) {
                statement.setString(1, p.toString());
                ResultSet results = statement.executeQuery();
                if (results.next()) {
                    gamesplayed = results.getInt("gamesplayed");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return gamesplayed;
    }

    // Method to calculate the kill-death ratio for a player
    public double GetKD(UUID p) {
        if(p == null){
            return 0;
        }

        int kills = GetKills(p);
        int deaths = GetDeaths(p);
        if (deaths == 0) {
        	 return kills > 0 ?  (double) kills : 0.0;
        }
     // Calculate the KD ratio
        double kdRatio = (double) kills / deaths;
        
        BigDecimal bd = new BigDecimal(kdRatio);
        bd = bd.setScale(2, RoundingMode.HALF_UP); // Rounds to 2 decimal places



        return bd.doubleValue();
        
        
        
        
    }

}
