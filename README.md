# EventAPI

Internal API for NixonMC event gamemodes.

EventAPI provides a simple interface for event gamemode developers to track player results during an event and submit those results to the NixonMC Event system.

## Requirements

- Java 25
- Paper 26.2+
- NixonMC Event system
- EventAPI installed on the server

## Installation

Add the EventAPI repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://nordiumm.github.io/EventAPI/")
    }
}
```

Add EventAPI as a `compileOnly` dependency:

```kotlin
dependencies {
    compileOnly("net.nordiumm:nixon-event-api:1.0.4")
}
```

EventAPI must also be installed as a plugin on the server.

## Usage

Create an EventAPI instance:

```java
EventAPI event = new EventAPI(plugin);
```

### Add Players

Add players when they join the event:

```java
event.addPlayer(player);
```

You can also add a player using their UUID:

```java
event.addPlayer(player.getUniqueId());
```

### Track Kills

```java
event.getPlayer(killer).addKill();
```

Get the current kill count:

```java
int kills = event.getPlayer(player).getKills();
```

### Track Deaths

```java
event.getPlayer(victim).addDeath();
```

Get the current death count:

```java
int deaths = event.getPlayer(player).getDeaths();
```

### Track Points

Points are controlled by the gamemode.

Add points:

```java
event.getPlayer(player).addPoints(10);
```

Get the current points:

```java
int points = event.getPlayer(player).getPoints();
```

### Mark Winners

Mark a player as a winner:

```java
event.getPlayer(player).setWinner(true);
```

Check if a player is a winner:

```java
boolean winner = event.getPlayer(player).isWinner();
```

## Finishing an Event

When the event is finished:

```java
event.finish();
```

This sends the results of all registered players to the NixonMC Event system.

## Clearing Event Data

After the event has finished, the temporary player data can be cleared:

```java
event.clear();
```

This is useful when reusing the same `EventAPI` instance for another event.

## Example

A basic event could look like this:

```java
EventAPI event = new EventAPI(plugin);

for (Player player : players) {
    event.addPlayer(player);
}

event.getPlayer(killer).addKill();
event.getPlayer(victim).addDeath();

event.getPlayer(killer).addPoints(1);
event.getPlayer(winner).addPoints(10);

event.getPlayer(winner).setWinner(true);

event.finish();
event.clear();
```

## EventPlayer

Each registered player has an `EventPlayer` containing their results for the current event.

Available methods:

| Method | Description |
|---|---|
| `getUuid()` | Gets the player's UUID |
| `getKills()` | Gets the player's kills |
| `addKill()` | Adds one kill |
| `getDeaths()` | Gets the player's deaths |
| `addDeath()` | Adds one death |
| `getPoints()` | Gets the player's points |
| `addPoints(int amount)` | Adds points |
| `isWinner()` | Checks if the player is a winner |
| `setWinner(boolean winner)` | Sets the player's winner status |

## Important

EventAPI only tracks data for the **current event**.

Persistent statistics such as:

- Total points
- Total games played
- Total wins
- Total kills
- Total deaths

are handled by the NixonMC Event Lobby.

## Version

Current version: `1.0.4`

EventAPI is currently an internal NixonMC API and may receive breaking changes in future versions.

## License

Copyright © 2026 Nordiumm.

All rights reserved.

EventAPI is proprietary software owned by Nordiumm.

Unauthorized copying, redistribution, modification, or use of this software is prohibited without explicit permission from the copyright holder.