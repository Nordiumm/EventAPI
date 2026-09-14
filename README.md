# EventAPI

Internal API for NixonMC event gamemodes.

EventAPI provides a simple interface for event gamemode developers to track player results during an event and submit those results to the NixonMC Event system.

## Features

- Track players participating in an event
- Track kills
- Track deaths
- Track points
- Track winners
- Submit event results to the NixonMC Event system
- Hide Velocity, plugin messaging, and JSON implementation details from gamemode developers

## Example

A gamemode can create an event instance:

```java
EventAPI event = new EventAPI(plugin);
```

Register players when the event starts:

```java
for (Player player : players) {
    event.addPlayer(player);
}
```

Track statistics during the game:

```java
event.getPlayer(killer).addKill();
event.getPlayer(victim).addDeath();
event.getPlayer(player).addPoints(10);
```

Mark the winner:

```java
event.getPlayer(winner).setWinner(true);
```

When the event finishes:

```java
event.finish();
```

The API handles creating the event result message and communicating it with the NixonMC Event system.

## Player Data

Each player can have the following event data:

| Data | Description |
|---|---|
| Kills | Number of kills during the event |
| Deaths | Number of deaths during the event |
| Points | Points earned during the event |
| Winner | Whether the player won the event |

These values represent the **current event only**.

Persistent statistics such as total kills, total wins, total deaths, and total games played are handled by the Event Lobby.

## Architecture

```text
Gamemode
    │
    │ EventAPI
    ▼
Event Result
    │
    ▼
Velocity / NixonEvents
    │
    ▼
Event Lobby
    │
    ├── Points
    └── Persistent Statistics
```

Gamemode developers do not need to interact directly with:

- Velocity
- JSON serialization
- Plugin messaging
- Event Lobby storage
- Persistent player statistics

The API handles the communication layer.

## Development

This project is currently an internal NixonMC API.

The API is under active development and its API may change between versions.

## Dependencies

### EventAPI

The API itself uses the following dependencies:

```kotlin
dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")
    implementation("com.google.code.gson:gson:2.13.2")
}
```

Paper is declared as `compileOnly` because the Paper server provides the Paper API at runtime.

Gson is included by EventAPI because it is used internally for event result messaging.

### Using EventAPI

Gamemode developers should add EventAPI as a `compileOnly` dependency:

```kotlin
dependencies {
    compileOnly("net.nordiumm:nixon-event-api:VERSION")
}
```

Replace `VERSION` with the version of EventAPI being used.

EventAPI is intended to be used alongside a Paper plugin and does not need to be shaded into the gamemode plugin.

## Requirements

- Java 25
- Paper 26.2+
- NixonMC Event system

## License

Copyright © 2026 Nordiumm.

All rights reserved.

EventAPI is proprietary software owned by Nordiumm.

Unauthorized copying, redistribution, modification, or use of this software is prohibited without explicit permission from the copyright holder.