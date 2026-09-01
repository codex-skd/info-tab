# Graph Report - .  (2026-09-02)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 230 nodes · 398 edges · 18 communities (16 shown, 2 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.8)
- Token cost: 796 input · 165 output

## Graph Freshness
- Built from commit: `f96970b7`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Color Management
- NeoForge Config
- Custom Commands
- Afk Tracker
- Mod Events
- Player List
- Activity Ping
- Project Setup
- Config Validation
- Common Utilities
- Mod Publishing
- Info Tab Client
- Info Tab Workflow
- Gradle Scripts
- Port to Minecraft
- Session Management

## God Nodes (most connected - your core abstractions)
1. `IConfigHelper` - 29 edges
2. `NeoForgeConfigHelper` - 26 edges
3. `CustomCommands` - 22 edges
4. `AfkTracker` - 15 edges
5. `PlayerListHandler` - 11 edges
6. `Config` - 10 edges
7. `ModEventHandler` - 9 edges
8. `DimensionPosition` - 8 edges
9. `CommonUtils` - 7 edges
10. `ActivityPingPacket` - 7 edges

## Surprising Connections (you probably didn't know these)
- `Info TAB Workflow` --references--> `CurseForge Upload PS1`  [EXTRACTED]
  docs/WORKFLOW_INFO_TAB_26-2.md → ../../codex-docs/scripts/curseforge-upload.ps1
- `Info TAB Mod` --references--> `Gradle Properties`  [EXTRACTED]
  src/main/java/info_tab/InfoTab.java → gradle.properties
- `Info TAB Mod` --references--> `META-INF Neoforge Mods TOML`  [EXTRACTED]
  src/main/java/info_tab/InfoTab.java → META-INF/neoforge.mods.toml
- `v0.0.0-beta.1 - Port to Minecraft 1.21.1` --references--> `Info Tab Icon`  [EXTRACTED]
  docs/curseforge/versions/0.0.0-beta.1.md → src/main/resources/assets/info_tab/icon.png
- `v0.0.0-beta.1 - Port to Minecraft 1.21.1` --references--> `Info Tab UI`  [EXTRACTED]
  docs/curseforge/versions/0.0.0-beta.1.md → src/main/resources/info_tab.png

## Import Cycles
- None detected.

## Communities (18 total, 2 thin omitted)

### Community 0 - "Color Management"
Cohesion: 0.06
Nodes (8): DimensionPosition, APPEND, PREPEND, IConfigHelper, PlayerList, Services, Override, PlayerListHandlerNeoForge

### Community 1 - "NeoForge Config"
Cohesion: 0.14
Nodes (3): Override, PlayerList, NeoForgeConfigHelper

### Community 2 - "Custom Commands"
Cohesion: 0.25
Nodes (4): CommandContext, CommandDispatcher, CommandSourceStack, CustomCommands

### Community 3 - "Afk Tracker"
Cohesion: 0.16
Nodes (13): EntityInteract, LeftClickBlock, RightClickBlock, RightClickEmpty, RightClickItem, ServerChatEvent, ServerPlayer, AfkTracker (+5 more)

### Community 4 - "Mod Events"
Cohesion: 0.13
Nodes (15): IEventBus, PlayerChangedDimensionEvent, PlayerEvent, PlayerLoggedInEvent, PlayerRespawnEvent, RegisterCommandsEvent, RegisterPayloadHandlersEvent, InfoTab (+7 more)

### Community 5 - "Player List"
Cohesion: 0.23
Nodes (9): MutableComponent, Player, RenderNameTagEvent, AfkNameTagHandler, EventBusSubscriber, SubscribeEvent, ResourceLocation, PlayerListHandler (+1 more)

### Community 6 - "Activity Ping"
Cohesion: 0.20
Nodes (11): CustomPacketPayload, FriendlyByteBuf, Key, AfkActivityClientHandler, EventBusSubscriber, Post, SubscribeEvent, ActivityPingPacket (+3 more)

### Community 7 - "Project Setup"
Cohesion: 0.18
Nodes (11): Build Gradle, Info TAB JAR, Project Description, Project Variables, Version Changelog, GitLab Repository, Gradle Properties, Info TAB Mod (+3 more)

### Community 8 - "Config Validation"
Cohesion: 0.22
Nodes (6): BooleanValue, ConfigValue, EnumValue, IntValue, ModConfigSpec, Config

### Community 10 - "Mod Publishing"
Cohesion: 0.40
Nodes (5): Publish Public, 0.0.0-beta.1, mod_curseforge_project_id, mod_group_id, mod_version

### Community 11 - "Info Tab Client"
Cohesion: 0.60
Nodes (3): InfoTabClient, Mod, ModContainer

### Community 12 - "Info Tab Workflow"
Cohesion: 0.67
Nodes (4): Info TAB CLAUDE, CurseForge Upload PS1, Info TAB Workflow, Info TAB README

### Community 13 - "Gradle Scripts"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 14 - "Port to Minecraft"
Cohesion: 0.67
Nodes (3): v0.0.0-beta.1 - Port to Minecraft 1.21.1, Info Tab Icon, Info Tab UI

## Knowledge Gaps
- **17 isolated node(s):** `PREPEND`, `APPEND`, `0.0.0-beta.1`, `mod_version`, `mod_group_id` (+12 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **2 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IConfigHelper` connect `Color Management` to `NeoForge Config`?**
  _High betweenness centrality (0.190) - this node is a cross-community bridge._
- **Why does `NeoForgeConfigHelper` connect `NeoForge Config` to `Color Management`?**
  _High betweenness centrality (0.150) - this node is a cross-community bridge._
- **Why does `PlayerListHandler` connect `Player List` to `Color Management`, `NeoForge Config`, `Mod Events`?**
  _High betweenness centrality (0.096) - this node is a cross-community bridge._
- **What connects `PREPEND`, `APPEND`, `0.0.0-beta.1` to the rest of the system?**
  _17 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Color Management` be split into smaller, more focused modules?**
  _Cohesion score 0.05855855855855856 - nodes in this community are weakly interconnected._
- **Should `NeoForge Config` be split into smaller, more focused modules?**
  _Cohesion score 0.1402116402116402 - nodes in this community are weakly interconnected._
- **Should `Mod Events` be split into smaller, more focused modules?**
  _Cohesion score 0.13333333333333333 - nodes in this community are weakly interconnected._