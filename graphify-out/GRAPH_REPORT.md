# Graph Report - 26.1.2  (2026-07-30)

## Corpus Check
- 40 files · ~82,333 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 320 nodes · 471 edges · 34 communities (32 shown, 2 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `45f02f1b`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Flujo de trabajo — Info TAB (NeoForge)
- IConfigHelper
- Changelog
- NeoForgeConfigHelper
- InfoTab.java
- .registerCommands
- AfkTracker
- PlayerListHandler
- Config
- Formato de descripciones CurseForge
- CommonUtils
- Info TAB
- InfoTabClient.java
- CLAUDE.md — info_tab (26.1.2)
- v1.0.0 — Initial stable release
- gradlew
- project_vars.md
- ActivityPingPacket

## God Nodes (most connected - your core abstractions)
1. `IConfigHelper` - 29 edges
2. `NeoForgeConfigHelper` - 26 edges
3. `CustomCommands` - 22 edges
4. `AfkTracker` - 15 edges
5. `Flujo de trabajo — Info TAB (NeoForge)` - 14 edges
6. `PlayerListHandler` - 11 edges
7. `Config` - 10 edges
8. `Changelog` - 10 edges
9. `ModEventHandler` - 9 edges
10. `DimensionPosition` - 8 edges

## Surprising Connections (you probably didn't know these)
- `Config` --references--> `DimensionPosition`  [EXTRACTED]
  src/main/java/com/skd/infotab/Config.java → src/main/java/com/skd/infotab/CommonUtils.java
- `InfoTab` --references--> `PlayerListHandler`  [EXTRACTED]
  src/main/java/com/skd/infotab/InfoTab.java → src/main/java/com/skd/infotab/PlayerListHandler.java
- `NeoForgeConfigHelper` --implements--> `IConfigHelper`  [EXTRACTED]
  src/main/java/com/skd/infotab/platform/NeoForgeConfigHelper.java → src/main/java/com/skd/infotab/platform/services/IConfigHelper.java
- `PlayerListHandlerNeoForge` --inherits--> `PlayerListHandler`  [EXTRACTED]
  src/main/java/com/skd/infotab/PlayerListHandlerNeoForge.java → src/main/java/com/skd/infotab/PlayerListHandler.java
- `Services` --references--> `IConfigHelper`  [EXTRACTED]
  src/main/java/com/skd/infotab/platform/Services.java → src/main/java/com/skd/infotab/platform/services/IConfigHelper.java

## Import Cycles
- None detected.

## Communities (34 total, 2 thin omitted)

### Community 0 - "Flujo de trabajo — Info TAB (NeoForge)"
Cohesion: 0.06
Nodes (34): 1. Desarrollo, 2. Copiar a instancia de pruebas, 3. Probar en instancia, 4. Preparar versión para CurseForge, 5. Release estable, 6. Actualizar Knowledge Graph (Graphify), Archivos que pasan a GitHub, Buenas prácticas (+26 more)

### Community 1 - "IConfigHelper"
Cohesion: 0.07
Nodes (6): DimensionPosition, APPEND, PREPEND, IConfigHelper, PlayerList, Services

### Community 2 - "Changelog"
Cohesion: 0.09
Nodes (22): [0.1.0-beta] - 2026-07-06, [0.1.1-beta] - 2026-07-06, [1.0.0] - 2026-07-06, [1.0.1] - 2026-07-16, [1.0.2-beta.1] - 2026-07-21, [1.0.2-beta.2] - 2026-07-23, [1.0.2-beta.3] - 2026-07-23, [1.0.2-beta.4] - 2026-07-23 (+14 more)

### Community 4 - "InfoTab.java"
Cohesion: 0.13
Nodes (15): IEventBus, PlayerChangedDimensionEvent, PlayerEvent, PlayerLoggedInEvent, PlayerRespawnEvent, RegisterCommandsEvent, RegisterPayloadHandlersEvent, InfoTab (+7 more)

### Community 5 - ".registerCommands"
Cohesion: 0.25
Nodes (4): CommandContext, CommandDispatcher, CommandSourceStack, CustomCommands

### Community 6 - "AfkTracker"
Cohesion: 0.16
Nodes (13): EntityInteract, LeftClickBlock, RightClickBlock, RightClickEmpty, RightClickItem, ServerChatEvent, ServerPlayer, AfkTracker (+5 more)

### Community 7 - "PlayerListHandler"
Cohesion: 0.16
Nodes (11): CanRender, MutableComponent, Player, AfkNameTagHandler, EventBusSubscriber, SubscribeEvent, Identifier, PlayerListHandler (+3 more)

### Community 8 - "Config"
Cohesion: 0.12
Nodes (9): BooleanValue, ConfigValue, EnumValue, IntValue, ModConfigSpec, CommonUtils, Identifier, Config (+1 more)

### Community 9 - "Formato de descripciones CurseForge"
Cohesion: 0.22
Nodes (9): Archivos de CurseForge, Buenas prácticas, Ejemplo de estructura HTML para release notes, Elementos HTML disponibles, Elementos HTML permitidos, Estructura de la descripción general, Estructura del proyecto, Formato de descripciones CurseForge (+1 more)

### Community 10 - "CommonUtils"
Cohesion: 0.12
Nodes (16): [1.0.2] - 2026-07-28, [1.0.2-beta.6] - 2026-07-23, [1.0.2-beta.7] - 2026-07-26, [1.0.2-beta.8] - 2026-07-28, [1.0.3] - 2026-07-28, [1.0.4] - 2026-07-28, [1.0.5] - 2026-07-29, Added (+8 more)

### Community 11 - "Info TAB"
Cohesion: 0.29
Nodes (6): Building, Credits, Features, Info TAB, Installation, Requirements

### Community 12 - "InfoTabClient.java"
Cohesion: 0.60
Nodes (3): InfoTabClient, Mod, ModContainer

### Community 13 - "CLAUDE.md — info_tab (26.1.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — info_tab (26.1.2), Paso 0 obligatorio, Prioridad de instrucciones

### Community 14 - "v1.0.0 — Initial stable release"
Cohesion: 0.50
Nodes (3): Changed, Removed, v1.0.0 — Initial stable release

### Community 15 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 30 - "ActivityPingPacket"
Cohesion: 0.20
Nodes (11): CustomPacketPayload, FriendlyByteBuf, Key, AfkActivityClientHandler, EventBusSubscriber, Post, SubscribeEvent, ActivityPingPacket (+3 more)

## Knowledge Gaps
- **66 isolated node(s):** `PREPEND`, `APPEND`, `Paso 0 obligatorio`, `Prioridad de instrucciones`, `Added` (+61 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **2 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IConfigHelper` connect `IConfigHelper` to `Config`, `NeoForgeConfigHelper`?**
  _High betweenness centrality (0.098) - this node is a cross-community bridge._
- **Why does `NeoForgeConfigHelper` connect `NeoForgeConfigHelper` to `Config`, `IConfigHelper`?**
  _High betweenness centrality (0.077) - this node is a cross-community bridge._
- **Why does `PlayerListHandler` connect `PlayerListHandler` to `Config`, `InfoTab.java`?**
  _High betweenness centrality (0.050) - this node is a cross-community bridge._
- **What connects `PREPEND`, `APPEND`, `Paso 0 obligatorio` to the rest of the system?**
  _66 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Flujo de trabajo — Info TAB (NeoForge)` be split into smaller, more focused modules?**
  _Cohesion score 0.05714285714285714 - nodes in this community are weakly interconnected._
- **Should `IConfigHelper` be split into smaller, more focused modules?**
  _Cohesion score 0.06628787878787878 - nodes in this community are weakly interconnected._
- **Should `Changelog` be split into smaller, more focused modules?**
  _Cohesion score 0.09090909090909091 - nodes in this community are weakly interconnected._