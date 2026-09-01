# Info TAB

NeoForge mod that displays player info in the tab list for Minecraft 1.21.1: current dimension, AFK status, and more.

> A fork of [Dimension Viewer](https://www.curseforge.com/minecraft/mc-mods/dimension-viewer) by Ewan Selkirk (a.k.a. Sick_Stick_10), ported to NeoForge and reworked by Stalking Dragons. MIT licensed, same as the original. Not affiliated with or endorsed by the original author.

## Status

Beta (`0.0.0-beta.1`). API port of the stable 26.2 line (1.1.0) to the 1.21.1 API — 14 classes, no dependencies, no mixins. `./gradlew build` and `./gradlew runServer` verified: `Done (6.3s)`, 0 FATAL. Client-side rendering (name tag overlay, tab list) compiles but is not yet verified in-game.

## Features

- Shows each player's dimension in the tab list
- **AFK Detection** — players inactive for 10+ minutes show an `[AFK]` prefix in the tab list and above their head
- AFK status resets on movement, world interaction, chat, mouse look, mouse clicks or key presses
- Per-dimension colours (Overworld, Nether, End, and modded dimensions)
- Configurable format with tokens: `%dim:name%`, `%dim:id%`, `%dim:namespace%`, `%dim:path%`
- Dimension aliases (custom display names)
- Configurable tag position (prepend or append)
- `/infotab` command for runtime configuration
- Config screen in the Mods menu (client-side)
- No external dependencies

## Requirements

| Component | Version |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.249+ |
| Java | 21+ |

## Installation

1. Download the JAR from CurseForge.
2. Place it in the `mods` folder on both the server and clients — the client is required for the AFK nameplate and input-based activity detection.

## Building

```bash
./gradlew build
```

The JAR is generated at `build/libs/info_tab-1.21.1-neoforge-21.1.249-<version>.jar`.

## Credits & License

Fork of [Dimension Viewer](https://www.curseforge.com/minecraft/mc-mods/dimension-viewer) by **Ewan Selkirk** (a.k.a. *Sick_Stick_10*), ported to NeoForge and reworked by **Stalking Dragons**. MIT licensed — see [LICENSE](LICENSE).
