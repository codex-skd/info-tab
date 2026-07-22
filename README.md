# Dimension TAB

Server-side NeoForge mod that displays each player's current dimension in the tab list.

## Features

- Shows the dimension of each player in the tab list
- Per-dimension colors (Overworld, Nether, End, and modded dimensions)
- Configurable format with tokens: `%dim:name%`, `%dim:id%`, `%dim:namespace%`, `%dim:path%`
- Dimension aliases (custom display names)
- Configurable tag position (prepend or append)
- `/dimensiontab` command for runtime configuration
- Config screen in the Mods menu (client-side only)
- No external dependencies

## Requirements

- **NeoForge** 26.1.2.76 or later
- **Minecraft** 26.1.2

## Installation

1. Download the JAR from CurseForge
2. Place it in the `mods` folder on your server
3. Clients do not need to install anything

## Building

```bash
./gradlew.bat build
```

The JAR is generated at `build/libs/dimensiontab-26.1.2-neoforge-<version>.jar`.

## Credits

Original mod: **DimensionViewer** by *Sick Stick 10*.
Ported to NeoForge by **Stalking Dragons**.
