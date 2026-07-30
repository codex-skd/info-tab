# Changelog

Todas las versiones notables de Info TAB (26.2) están documentadas aquí.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
y este proyecto adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Para el historial completo de la versión 26.1.2, ver `info_tab/26.1.2/CHANGELOG.md` (rama `minecraft/26.1.2/neoforge-26.1.2.76/production`).

## [0.0.0-beta.3] - 2026-07-31

### Fixed
- **Crash on player join**: `META-INF/services/com.skd.infotab.platform.services.IConfigHelper` was missing from the initial 26.2 port, so `ServiceLoader` found no implementation and `Services.<clinit>` threw a `NullPointerException` the first time `PlayerListHandler` tried to render a tab list name — crashing the integrated/dedicated server as soon as a player joined. Restored the missing service file.

## [0.0.0-beta.2] - 2026-07-30

### Changed
- **Breaking**: `mod_id` normalizado de `infotab` a `info_tab` para coincidir con el nombre de la carpeta del proyecto, según la convención estándar del workspace. Cambia el canal de red (`info_tab:activity_ping`), el namespace de assets (`assets/info_tab/`), y el nombre del JAR generado (`info_tab-26.2-neoforge-<version>.jar`). El comando `/infotab` y el paquete Java (`com.skd.infotab`) no cambian.

## [0.0.0-beta.1] - 2026-07-30

### Added
- Port inicial a Minecraft 26.2 / NeoForge 26.2.0.32-beta, partiendo del estado estable de la versión 26.1.2 (`1.0.5`): dimensión en tab list, detección de AFK (movimiento, interacción, chat, ratón/teclado), tag `[AFK]` en tab list y nametag flotante, aliases de dimensión, colores por dimensión, comando `/infotab`.
