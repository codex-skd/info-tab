# Graph Report - 26.2  (2026-08-06)

## Corpus Check
- 31 files · ~78,295 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 249 nodes · 318 edges · 47 communities (20 shown, 27 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `8be8ab56`
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
- EventBusSubscriber
- Post
- SubscribeEvent
- EventBusSubscriber
- SubscribeEvent
- EventBusSubscriber
- PlayerLoggedOutEvent
- Post
- SubscribeEvent
- Identifier
- EventBusSubscriber
- Mod
- ModContainer
- PlayerLoggedOutEvent
- SubscribeEvent
- Mod
- ModContainer
- Override
- Override
- PlayerList
- PlayerList
- Identifier
- Override

## God Nodes (most connected - your core abstractions)
1. `IConfigHelper` - 29 edges
2. `NeoForgeConfigHelper` - 26 edges
3. `CustomCommands` - 22 edges
4. `AfkTracker` - 14 edges
5. `PlayerListHandler` - 11 edges
6. `Flujo de trabajo — Info TAB (NeoForge)` - 11 edges
7. `Config` - 9 edges
8. `ModEventHandler` - 8 edges
9. `CommonUtils` - 7 edges
10. `ActivityPingPacket` - 7 edges

## Surprising Connections (you probably didn't know these)
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

## Communities (47 total, 27 thin omitted)

### Community 0 - "Flujo de trabajo — Info TAB (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Info TAB (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 3 - ".registerCommands"
Cohesion: 0.25
Nodes (4): CommandContext, CommandDispatcher, CommandSourceStack, CustomCommands

### Community 4 - "AfkTracker"
Cohesion: 0.15
Nodes (10): EntityInteract, AfkTracker, LeftClickBlock, PlayerEvent, RightClickBlock, RightClickEmpty, RightClickItem, ServerChatEvent (+2 more)

### Community 5 - "InfoTab.java"
Cohesion: 0.14
Nodes (9): InfoTab, ModEventHandler, IEventBus, PlayerChangedDimensionEvent, PlayerLoggedInEvent, PlayerRespawnEvent, RegisterCommandsEvent, RegisterPayloadHandlersEvent (+1 more)

### Community 6 - "PlayerListHandler"
Cohesion: 0.19
Nodes (7): CanRender, AfkNameTagHandler, PlayerListHandler, PlayerListHandlerNeoForge, MutableComponent, Player, TextColor

### Community 7 - "ActivityPingPacket"
Cohesion: 0.22
Nodes (7): CustomPacketPayload, FriendlyByteBuf, AfkActivityClientHandler, ActivityPingPacket, Key, StreamCodec, Type

### Community 8 - "Publicación a GitHub (CI/CD)"
Cohesion: 0.22
Nodes (4): CommonUtils, DimensionPosition, APPEND, PREPEND

### Community 9 - "Config"
Cohesion: 0.22
Nodes (6): BooleanValue, ConfigValue, EnumValue, Config, IntValue, ModConfigSpec

### Community 10 - "CommonUtils"
Cohesion: 0.29
Nodes (6): Building, Credits, Features, Info TAB, Installation, Requirements

### Community 11 - "Info TAB"
Cohesion: 0.50
Nodes (3): CLAUDE.md — info_tab (26.2), Prioridad de instrucciones, Workflow del mod

### Community 12 - "InfoTabClient.java"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 13 - "[0.0.0-beta.1] - 2026-07-30"
Cohesion: 0.17
Nodes (11): [0.0.0-beta.1] - 2026-07-30, [0.0.0-beta.2] - 2026-07-30, [0.0.0-beta.3] - 2026-07-31, [1.0.0] - 2026-07-31, [1.0.1] - 2026-08-05, Added, Change, Changed (+3 more)

## Knowledge Gaps
- **25 isolated node(s):** `PREPEND`, `APPEND`, `Workflow del mod`, `Prioridad de instrucciones`, `Change` (+20 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **27 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IConfigHelper` connect `IConfigHelper` to `NeoForgeConfigHelper`?**
  _High betweenness centrality (0.124) - this node is a cross-community bridge._
- **Why does `NeoForgeConfigHelper` connect `NeoForgeConfigHelper` to `IConfigHelper`?**
  _High betweenness centrality (0.108) - this node is a cross-community bridge._
- **Why does `PlayerListHandler` connect `PlayerListHandler` to `NeoForgeConfigHelper`, `InfoTab.java`?**
  _High betweenness centrality (0.082) - this node is a cross-community bridge._
- **What connects `PREPEND`, `APPEND`, `Workflow del mod` to the rest of the system?**
  _25 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `IConfigHelper` be split into smaller, more focused modules?**
  _Cohesion score 0.07142857142857142 - nodes in this community are weakly interconnected._
- **Should `NeoForgeConfigHelper` be split into smaller, more focused modules?**
  _Cohesion score 0.08307692307692308 - nodes in this community are weakly interconnected._
- **Should `InfoTab.java` be split into smaller, more focused modules?**
  _Cohesion score 0.14035087719298245 - nodes in this community are weakly interconnected._