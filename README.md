# Info TAB

NeoForge mod that displays player info in the tab list for Minecraft 26.1.2: current dimension, AFK status, and more.

## Features

- Shows the dimension of each player in the tab list
- **AFK Detection** — players inactive for 10+ minutes show a `[AFK]` prefix in the tab list and above their head
- AFK status resets on movement, world interactions, chat, mouse look, clicks, or key presses
- Per-dimension colors (Overworld, Nether, End, and modded dimensions)
- Configurable format with tokens: `%dim:name%`, `%dim:id%`, `%dim:namespace%`, `%dim:path%`
- Dimension aliases (custom display names)
- Configurable tag position (prepend or append)
- `/infotab` command for runtime configuration
- Config screen in the Mods menu (client-side only)
- No external dependencies

## Requirements

- **NeoForge** 26.1.2.76 or later
- **Minecraft** 26.1.2

## Installation

1. Download the JAR from CurseForge
2. Place it in the `mods` folder on both the server and the clients — the client is required for the AFK name-tag and input-based activity detection
3. Launch the game

## Building

```bash
./gradlew.bat build
```

The JAR is generated at `build/libs/infotab-26.1.2-neoforge-<version>.jar`.

## Credits & License

Original mod: **DimensionViewer** by *Sick Stick 10*. Ported to NeoForge by **Stalking Dragons**.

**License:** All Rights Reserved.
