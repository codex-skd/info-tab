# Project Variables — Info TAB (1.21.1)

> **Rama 1.21.1**: `game_versions = 9638, 9639, 11779, 10150` (Client, Server, **1.21.1** id `11779`, NeoForge). `release_type = beta`. JAR `info_tab-1.21.1-neoforge-21.1.249-<version>.jar`. Tag `1.21.1-neoforge-<version>`. Proyecto CurseForge compartido con la rama 26.2 (`1599911`).

## Required
project_id = 1599911
api_token = ee776b0a-ee95-4850-b554-06be02a8657f
game_versions = 9638, 9639, 11779, 10150
release_type = beta

## Optional
relations =

---

El script lee `project_id`, `api_token` y `game_versions` de este archivo, y `mod_id`, `mod_name`,
`minecraft_version`, `mod_version` de `gradle.properties`. Sube el JAR desde `build/libs/` con el
changelog de `docs/curseforge/versions/<version>.md`.

**game_versions**: `9638` Client · `9639` Server · `11779` Minecraft 1.21.1 · `10150` NeoForge.

## Rama
minecraft/1.21.1/neoforge-21.1.249/production

## Tag
Formato: `<mc-version>-<framework>-<version>` — Ejemplo: `1.21.1-neoforge-0.0.0-beta.1`

## Repo GitLab
git@gitlab.com:stalking-dragons/minecraft/info-tab.git

## Historial

- v0.0.0-beta.1: port de API de la línea 26.2 (`1.1.0`) a Minecraft 1.21.1 / NeoForge 21.1.249.
  14 clases, sin dependencias, sin mixins reales (configs vacíos). Reversiones 26.2→1.21.1:
  `Identifier`→`ResourceLocation`, `ResourceKey<Level>.identifier()`→`.location()`,
  `RenderNameTagEvent.CanRender`→`RenderNameTagEvent`, `ClientPacketDistributor`→`PacketDistributor`,
  `Minecraft.gui.screen()`→`Minecraft.screen`. `build` + `runServer` verificados (Done, 0 FATAL).
  Cliente (render de name tag, tab list) compila pero no verificado en juego.
