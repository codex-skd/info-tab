## Features

Displays the current dimension of each player in the **tab list**, making it easy to see where everyone is at a glance.

- **Per-dimension colors** — Overworld, Nether, End, and custom colors for modded dimensions
- **Customizable format** — Use tokens like `%dim:name%`, `%dim:id%`, `%dim:namespace%`, `%dim:path%`
- **Dimension aliases** — Give dimensions custom display names (e.g., show "Aether" instead of "aether:the_aether")
- **Configurable position** — Prepend or append the dimension tag relative to the player name
- **Optional chat display** — Toggle `dimInChatName` in the config to also show dimensions in chat
- **In-game commands** — Full runtime configuration via `/dimensiontab`

## Server-side only

This mod runs entirely on the server. Clients do **not** need to install it. If installed on the client, it provides an in-game config screen via the Mods menu.

## Commands (`/dimensiontab`)

| Command | Description |
|---|---|
| `color <dimension> <color>` | Set color for a specific dimension |
| `color default <color>` | Set the default dimension color |
| `color overworld <color>` | Set the Overworld color |
| `color nether <color>` | Set the Nether color |
| `color end <color>` | Set the End color |
| `alias <dimension> <alias>` | Set an alias (custom name) for a dimension |
| `resetalias <dimension>` | Remove a dimension alias |
| `resetcolor <dimension>` | Remove a custom color |
| `format <format>` | Set the display format (e.g., `[%dim:name%]`) |
| `placement prepend\|append` | Set tag position relative to player name |
| `setting <key> <true\|false>` | Toggle boolean settings |
| `dimid` | Show your current dimension ID |
| `refresh` | Refresh display names for all online players |
| `list aliases` | List all dimension aliases |
| `list colors` | List all custom colors |
| `reload` | Reload config and refresh players |

## Configuration

All settings can be changed via the config file (`dimensiontab-common.toml`), the in-game Mods menu (if installed on client), or the `/dimensiontab` command.

### Format Tokens

| Token | Output |
|---|---|
| `%dim:id%` | Full dimension ID (e.g., `minecraft:the_nether`) |
| `%dim:name%` | Human-readable name (e.g., `The Nether`) |
| `%dim:namespace%` | Namespace only (e.g., `minecraft`) |
| `%dim:path%` | Path only (e.g., `the_nether`) |

### Default Format

```
%dim:name%
```

### Color Format

Colors can be specified as:
- Minecraft color names: `gold`, `red`, `green`, `aqua`, `dark_purple`, etc.
- Hex colors: `#FF0000`, `#00FF00`

### Default Colors

| Dimension | Default Color |
|---|---|
| Default | `gold` |
| Overworld | `green` |
| Nether | `red` |
| End | `dark_purple` |

## Requirements

- **NeoForge** 26.1.2.76 or later
- **Minecraft** 26.1.2 (1.21.x era)
- No additional dependencies
