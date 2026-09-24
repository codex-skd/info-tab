## [1.0.6] - 2026-07-30


## [1.0.7] - 2026-08-12

### Change

- **Nombre de JAR con versión del cargador**: el artefacto ahora se compila como `info_tab-26.1.2-neoforge-26.1.2.76-1.0.7.jar` (se añade la versión de cargador/NeoForge al nombre del archivo). Empaquetado y documentación; sin cambios de funcionalidad.

### Changed
- **Breaking**: `mod_id` normalized from `infotab` to `info_tab` to match the project folder name, per the workspace's standard naming convention. This changes the mod's internal identifier — the network channel is now `info_tab:activity_ping`, the config file name, the asset namespace (`assets/info_tab/`), and the generated JAR name (`info_tab-26.1.2-neoforge-<version>.jar`). The `/infotab` command and the Java package (`com.skd.infotab`) are unchanged. Existing per-world/server saved config under the old `infotab` id will not carry over automatically.
- Workflow doc synced to `codex-docs/WORKFLOW_GENERIC.md` v1.12.0.
- `graphify-out/` is now versioned in git (was incorrectly excluded via `.gitignore`), per the generic workflow.

## [1.0.5] - 2026-07-29

### Added
- AFK status now also resets on mouse look, mouse clicks, or key presses while playing (no GUI open), not just world interactions or movement. Uses a new lightweight client→server ping packet (`infotab:activity_ping`), throttled to at most once every 3 seconds.

### Removed
- Debug logging added during AFK development (tracking start, status changes, movement, countdown warnings, config reload) — no longer needed now that the feature is stable.

## [1.0.4] - 2026-07-28

### Fixed
- `1.0.3` was rejected by CurseForge ("Failed to verify archive"). Root cause: the raw `templates/META-INF/neoforge.mods.toml` (unresolved `${...}` placeholders) was being copied as-is into the jar under an extra `templates/` top-level folder, alongside the real resolved `META-INF/neoforge.mods.toml`. Excluded `templates/**` from the final jar in `build.gradle`.
- Cleaned up leftover references to the mod's old name ("Dimension TAB", `/dimensiontab` command) in `pack.mcmeta` and the mods.toml description — the mod was renamed to Info TAB (`/infotab`) in 1.0.2-beta.1 but these were never updated.

## [1.0.3] - 2026-07-28

### Changed
- Re-published as 1.0.3: the 1.0.2 CurseForge upload was rejected after repeated failed attempts caused by a broken upload script (see below); no code changes since 1.0.2

## [1.0.2] - 2026-07-28

### Fixed
- `[AFK]` prefix now also shows on the floating nametag rendered above the player's head (previously it only appeared in the tab list)

## [1.0.2-beta.8] - 2026-07-28

### Fixed
- [AFK] prefix not appearing in tab list: refreshTabListName() is now called when AFK status changes

## [1.0.2-beta.7] - 2026-07-26

### Fixed
- AFK detection: evaluate isAfk() every 5 seconds via server tick, not only on TabListNameFormat event

## [1.0.2-beta.6] - 2026-07-23



### Added

- Server debug logs for AFK tracking (start, countdown, activation, return)



### Fixed

- AFK detection logic: status tracking via afkStatus map


# Changelog

Todas las versiones notables de Info TAB estan documentadas aqui.

El formato esta basado en [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
y este proyecto adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.2-beta.5] - 2026-07-23

### Added
- Config option `afkTimeoutMinutes` to set AFK inactivity threshold (default: 10 min)

### Fixed
- AFK detection now checks movement every 5 seconds via server tick, no longer depends on tab list refresh

## [1.0.2-beta.4] - 2026-07-23

### Added
- Config options `showAfk` and `showDimension` to toggle AFK prefix and dimension tag visibility (both default: true)

## [1.0.2-beta.3] - 2026-07-23

### Changed
- Replaced placeholder logo with custom Minecraft-style icon

## [1.0.2-beta.2] - 2026-07-23

### Fixed
- Crash on startup: `PlayerInteractEvent` is abstract, use concrete subclasses instead

## [1.0.2-beta.1] - 2026-07-21

### Added
- AFK detection: players inactive for 10+ minutes show `[AFK]` prefix in creme color
- Mod renamed from Dimension TAB to Info TAB (new mod_id: `infotab`)

### Changed
- Display format updated: `[AFK] PlayerName [%dim:name%]`

## [1.0.1] - 2026-07-16

### Changed
- Updated WORKFLOW.md with branch structure and English commit rules
- Fixed tag format to `<mc-version>-neoforge-<version>` in release steps

## [1.0.0] - 2026-07-06

### Changed
- Formato por defecto cambiado a `[%dim:name%]` (con corchetes)

### Removed
- Soporte de chat: eliminado `NameFormat`, `DIM_IN_CHAT_NAME`, `CHAT_DIM_HOVER`. El mod solo muestra dimensiones en el tab list.

## [0.1.1-beta] - 2026-07-06

### Fixed
- NPE en `TabListNameFormat` al conectar clientes: `getDisplayName()` devuelve null por defecto en esta API, ahora usa `player.getName()` como fallback

## [0.1.0-beta] - 2026-07-06

### Added
- Muestra la dimension actual de cada jugador en el tab list (lista de jugadores)
- Colores personalizables por dimension (Overworld, Nether, End, modded)
- Formato de visualizacion configurable con tokens: `%dim:name%`, `%dim:id%`, `%dim:namespace%`, `%dim:path%`
- Posicion del tag configurable: prepend (antes del nombre) o append (despues)
- Aliases para dimensiones (nombres personalizados)
- Comando `/dimensiontab` con subcomandos para configuracion en tiempo real
- Pantalla de configuracion integrada en el menu de mods (solo cliente)
- Sin dependencias externas (solo NeoForge)
- Mod server-side: no requiere instalacion en el cliente
- Compatible con modpacks (displayTest = IGNORE_SERVER_VERSION)
