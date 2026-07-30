# Graph Report - 26.2  (2026-07-30)

## Corpus Check
- 28 files · ~81,665 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 273 nodes · 436 edges · 22 communities (20 shown, 2 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `002d3232`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Flujo de trabajo — Info TAB (NeoForge)
- IConfigHelper
- NeoForgeConfigHelper
- .registerCommands
- AfkTracker
- InfoTab.java
- PlayerListHandler
- ActivityPingPacket
- Publicación a GitHub (CI/CD)
- Config
- CommonUtils
- Info TAB
- InfoTabClient.java
- [0.0.0-beta.1] - 2026-07-30
- CLAUDE.md — info_tab (26.2)
- gradlew
- project_vars.md

## God Nodes (most connected - your core abstractions)
1. `IConfigHelper` - 29 edges
2. `NeoForgeConfigHelper` - 26 edges
3. `CustomCommands` - 22 edges
4. `AfkTracker` - 15 edges
5. `Flujo de trabajo — Info TAB (NeoForge)` - 13 edges
6. `PlayerListHandler` - 11 edges
7. `Publicación a GitHub (CI/CD)` - 11 edges
8. `Config` - 10 edges
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

## Communities (22 total, 2 thin omitted)

### Community 0 - "Flujo de trabajo — Info TAB (NeoForge)"
Cohesion: 0.06
Nodes (32): Archivos de CurseForge, Buenas prácticas, Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, ¿Cuándo incrementar versión?, Ejemplo de estructura HTML para release notes, Ejemplos (+24 more)

### Community 1 - "IConfigHelper"
Cohesion: 0.07
Nodes (6): DimensionPosition, APPEND, PREPEND, IConfigHelper, PlayerList, Services

### Community 2 - "NeoForgeConfigHelper"
Cohesion: 0.14
Nodes (3): Override, PlayerList, NeoForgeConfigHelper

### Community 3 - ".registerCommands"
Cohesion: 0.25
Nodes (4): CommandContext, CommandDispatcher, CommandSourceStack, CustomCommands

### Community 4 - "AfkTracker"
Cohesion: 0.16
Nodes (13): EntityInteract, LeftClickBlock, RightClickBlock, RightClickEmpty, RightClickItem, ServerChatEvent, ServerPlayer, AfkTracker (+5 more)

### Community 5 - "InfoTab.java"
Cohesion: 0.13
Nodes (15): IEventBus, PlayerChangedDimensionEvent, PlayerEvent, PlayerLoggedInEvent, PlayerRespawnEvent, RegisterCommandsEvent, RegisterPayloadHandlersEvent, InfoTab (+7 more)

### Community 6 - "PlayerListHandler"
Cohesion: 0.16
Nodes (11): CanRender, MutableComponent, Player, AfkNameTagHandler, EventBusSubscriber, SubscribeEvent, Identifier, PlayerListHandler (+3 more)

### Community 7 - "ActivityPingPacket"
Cohesion: 0.20
Nodes (11): CustomPacketPayload, FriendlyByteBuf, Key, AfkActivityClientHandler, EventBusSubscriber, Post, SubscribeEvent, ActivityPingPacket (+3 more)

### Community 8 - "Publicación a GitHub (CI/CD)"
Cohesion: 0.17
Nodes (12): 0. Determinar alcance de versión, 1. Desarrollo, 2. Probar en local, 3. Preparar versión para CurseForge, 4. Release estable, 5. Actualizar Knowledge Graph (Graphify), Archivos que pasan a GitHub, Backend LLM: Ollama local (+4 more)

### Community 9 - "Config"
Cohesion: 0.22
Nodes (6): BooleanValue, ConfigValue, EnumValue, IntValue, ModConfigSpec, Config

### Community 11 - "Info TAB"
Cohesion: 0.29
Nodes (6): Building, Credits, Features, Info TAB, Installation, Requirements

### Community 12 - "InfoTabClient.java"
Cohesion: 0.60
Nodes (3): InfoTabClient, Mod, ModContainer

### Community 13 - "[0.0.0-beta.1] - 2026-07-30"
Cohesion: 0.33
Nodes (5): [0.0.0-beta.1] - 2026-07-30, [0.0.0-beta.2] - 2026-07-30, Added, Changed, Changelog

### Community 14 - "CLAUDE.md — info_tab (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — info_tab (26.2), Paso 0 obligatorio, Prioridad de instrucciones

### Community 15 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **46 isolated node(s):** `PREPEND`, `APPEND`, `Paso 0 obligatorio`, `Prioridad de instrucciones`, `Changed` (+41 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **2 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IConfigHelper` connect `IConfigHelper` to `NeoForgeConfigHelper`?**
  _High betweenness centrality (0.135) - this node is a cross-community bridge._
- **Why does `NeoForgeConfigHelper` connect `NeoForgeConfigHelper` to `IConfigHelper`?**
  _High betweenness centrality (0.106) - this node is a cross-community bridge._
- **Why does `PlayerListHandler` connect `PlayerListHandler` to `NeoForgeConfigHelper`, `InfoTab.java`?**
  _High betweenness centrality (0.068) - this node is a cross-community bridge._
- **What connects `PREPEND`, `APPEND`, `Paso 0 obligatorio` to the rest of the system?**
  _46 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Flujo de trabajo — Info TAB (NeoForge)` be split into smaller, more focused modules?**
  _Cohesion score 0.06060606060606061 - nodes in this community are weakly interconnected._
- **Should `IConfigHelper` be split into smaller, more focused modules?**
  _Cohesion score 0.06628787878787878 - nodes in this community are weakly interconnected._
- **Should `NeoForgeConfigHelper` be split into smaller, more focused modules?**
  _Cohesion score 0.1402116402116402 - nodes in this community are weakly interconnected._