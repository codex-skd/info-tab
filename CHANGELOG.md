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
