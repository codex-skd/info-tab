# Info TAB (1.21.1) — Changelog

Branch `minecraft/1.21.1/neoforge-21.1.249/production`. History independent of the 26.2 branch.

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
