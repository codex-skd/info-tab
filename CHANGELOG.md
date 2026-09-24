# Info TAB (1.21.1) — Changelog

Branch `minecraft/1.21.1/neoforge-21.1.249/production`. History independent of the 26.2 branch.

## [1.0.0] - 2026-09-09

First stable release for **Minecraft 1.21.1 / NeoForge 21.1.249** (Java 21). Consolidates the
`0.0.0-beta.1` → `0.0.0-beta.2` line with no further code changes. This build has been running in
the *(Develop) Mystical Realms* modded-server pack.

### Summary of the beta line

- **beta.1** — initial API port of the stable 26.2 line (1.1.0), 14 classes, no dependencies, no
  real mixins. Full feature set unchanged: dimension in the tab list, per-dimension colours, token
  format, aliases, prepend/append placement, the `[AFK]` tag in the tab list and above the head,
  the `/infotab` command and the config screen. 26.2 → 1.21.1 API reversions: `Identifier` →
  `ResourceLocation`; `ResourceKey<Level>.identifier()` → `.location()`;
  `RenderNameTagEvent.CanRender` → `RenderNameTagEvent`; `ClientPacketDistributor` →
  `PacketDistributor`; `Minecraft.gui.screen()` → `Minecraft.screen`.
- **beta.2** — bundled the Spanish (`es_es`) locale: all 21 keys (creative-tab name and every
  config-screen label, including the per-dimension colour options).

### Notes

- No gameplay change relative to `0.0.0-beta.2`. Verified: `./gradlew clean build` is green;
  `./gradlew runServer` reaches `Done` with 0 FATAL.
- Same CurseForge project as the 26.2 line (`1599911`); pick the file that matches your Minecraft
  version.

## [0.0.0-beta.2] - 2026-09-08

### Added

- **Spanish (`es_es`) locale**: full translation of all 21 keys (creative-tab name and every
  config-screen label, including the per-dimension colour options). Taken from the Mystical Realms
  Translation & Fixes resource-pack QA pass so it ships with the mod. No code change.

## [0.0.0-beta.1] - 2026-09-02

### Added

- **Initial port to Minecraft 1.21.1 / NeoForge 21.1.249** (Java 21). API port of the stable
  26.2 line (1.1.0), 14 classes, no dependencies, no mixins (the `.mixins.json` configs are
  empty scaffold). Full feature set unchanged: dimension in the tab list, per-dimension colours,
  token format, aliases, prepend/append placement, the `[AFK]` tag in the tab list and above the
  head, the `/infotab` command and the config screen.

### Technical

- 26.2 → 1.21.1 API reversions:
  - `net.minecraft.resources.Identifier` → `net.minecraft.resources.ResourceLocation`
    (`CommonUtils`, `CustomCommands`, `PlayerListHandler`, `network/ActivityPingPacket`).
  - `ResourceKey<Level>.identifier()` → `.location()`.
  - `RenderNameTagEvent.CanRender` → the single `RenderNameTagEvent` (the sub-event split does
    not exist in NeoForge 21.1).
  - `net.neoforged.neoforge.client.network.ClientPacketDistributor` →
    `net.neoforged.neoforge.network.PacketDistributor` (`sendToServer`).
  - `Minecraft.gui.screen()` → `Minecraft.screen`.
- Build: `net.neoforged.moddev` `2.0.142` retargeted to NeoForge 21.1.249 / Java 21;
  `modLoader` / `loaderVersion` + the `minecraft` dependency added to `neoforge.mods.toml`;
  `pack.mcmeta` `pack_format` → 34.
- Verified: `./gradlew build` OK; `./gradlew runServer` → `Done (6.3s)`, 0 FATAL, mod loads,
  gametest namespace registered, no info_tab warnings. Client-side rendering not verified in-game.
