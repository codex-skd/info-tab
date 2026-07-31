# Graph Report - 26.2  (2026-07-31)

## Corpus Check
- 30 files · ~78,233 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 246 nodes · 407 edges · 23 communities (20 shown, 3 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `a4c151d7`
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
5. `PlayerListHandler` - 11 edges
6. `Flujo de trabajo — Info TAB (NeoForge)` - 11 edges
7. `Config` - 10 edges
8. `ModEventHandler` - 9 edges
9. `DimensionPosition` - 8 edges
10. `CommonUtils` - 7 edges

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

## Communities (23 total, 3 thin omitted)

### Community 0 - "Flujo de trabajo — Info TAB (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Info TAB (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 1 - "IConfigHelper"
Cohesion: 0.07
Nodes (6): DimensionPosition, APPEND, PREPEND, IConfigHelper, PlayerList, Services

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

### Community 9 - "Config"
Cohesion: 0.18
Nodes (7): BooleanValue, ConfigValue, EnumValue, IntValue, ModConfigSpec, Config, PlayerList

### Community 11 - "Info TAB"
Cohesion: 0.29
Nodes (6): Building, Credits, Features, Info TAB, Installation, Requirements

### Community 12 - "InfoTabClient.java"
Cohesion: 0.60
Nodes (3): InfoTabClient, Mod, ModContainer

### Community 13 - "[0.0.0-beta.1] - 2026-07-30"
Cohesion: 0.20
Nodes (9): [0.0.0-beta.1] - 2026-07-30, [0.0.0-beta.2] - 2026-07-30, [0.0.0-beta.3] - 2026-07-31, [1.0.0] - 2026-07-31, Added, Changed, Changed, Changelog (+1 more)

### Community 14 - "CLAUDE.md — info_tab (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — info_tab (26.2), Prioridad de instrucciones, Workflow del mod

### Community 15 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **24 isolated node(s):** `PREPEND`, `APPEND`, `Workflow del mod`, `Prioridad de instrucciones`, `Changed` (+19 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **3 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IConfigHelper` connect `IConfigHelper` to `Config`, `NeoForgeConfigHelper`?**
  _High betweenness centrality (0.166) - this node is a cross-community bridge._
- **Why does `NeoForgeConfigHelper` connect `NeoForgeConfigHelper` to `Config`, `IConfigHelper`?**
  _High betweenness centrality (0.131) - this node is a cross-community bridge._
- **Why does `PlayerListHandler` connect `PlayerListHandler` to `Config`, `InfoTab.java`?**
  _High betweenness centrality (0.084) - this node is a cross-community bridge._
- **What connects `PREPEND`, `APPEND`, `Workflow del mod` to the rest of the system?**
  _24 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `IConfigHelper` be split into smaller, more focused modules?**
  _Cohesion score 0.06628787878787878 - nodes in this community are weakly interconnected._
- **Should `InfoTab.java` be split into smaller, more focused modules?**
  _Cohesion score 0.13333333333333333 - nodes in this community are weakly interconnected._