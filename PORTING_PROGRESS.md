# Untamed Wilds - NeoForge 1.21.1 Porting Progress

Last updated: 2026-02-14

## Current Status
- Build status: ✅ passing (`gradlew build`)
- Runtime status: ✅ major NeoForge blockers fixed (rendering, summon, spawn pipeline)
- Remaining work: targeted validation/tuning (mainly spawn density + cleanup of legacy shim usage)

## Completed (High Impact Runtime Fixes)

### Taming/interaction validation
- ✅ Player validation complete: all tameable mobs can be tamed successfully (adults and babies), with expected hearts/smoke feedback and working right-click interaction flow.

### Retaliation crash validation
- ✅ Player validation complete: no `StackOverflowError` reproduced during retaliation/aggro scenarios (previous ticking-entity overflow resolved).

### Spawn validation
- ✅ Player validation complete: mobs are spawning correctly across tested scenarios.

### Dependency version-gate validation
- ✅ Startup dependency gate verified: mod rejects Citadel versions below `2.7.5` as intended.

### Core registration and mod bootstrap
- ✅ Main mod initialization moved to NeoForge lifecycle (`IEventBus`, `ModContainer`, NeoForge events).
- ✅ Deferred registers wired on mod bus from `UntamedWilds`.
- ✅ Attribute and spawn placement listeners explicitly registered in main mod entry.

### Rendering and model visibility
- ✅ Entity renderer registration fixed for NeoForge client lifecycle.
- ✅ `ComplexMob` scale/NBT handling corrected (resolved invisible/tiny render issues after spawn/reload).
- ✅ Big Cat missing texture case fixed via safer texture fallback handling.

### Entity summon/initialization
- ✅ Synced entity data initialization fixed for:
  - `EntityBison`
  - `EntityOpossum`
  - `EntityRhino`
- ✅ These entities now summon without the previous initialization failure.

### Spawn data and biome modifier pipeline
- ✅ Reload listener registration fixed to actual NeoForge event path.
- ✅ Spawn lists are now cleared before reload (`FaunaHandler.clearAllSpawnLists`) to prevent stale/duplicate state.
- ✅ NeoForge biome modifier JSON set added under:
  - `src/main/resources/data/untamedwilds/neoforge/biome_modifier/`
- ✅ Spawn group size selection fixed to inclusive min/max range.

### Fauna worldgen spawn behavior fixes
- ✅ Fixed ignored offset bug in `FaunaSpawn` (`BlockPos.offset(...)` result now applied).
- ✅ Water/lava spawn origin adjusted to avoid bad Y placement from land logic.
- ✅ Ocean feature heightmap usage corrected (`OCEAN_FLOOR_WG` where needed).
- ✅ Ocean sessile placement type corrected to water-appropriate placement (`IN_WATER`).
- ✅ Aquatic spawn placement predicates fixed in `ModEntity.registerSpawnPlacements`:
  - water entities no longer use `Animal::checkAnimalSpawnRules`
  - replaced with permissive predicate for `IN_WATER` registrations (worldgen/location checks still enforced in `FaunaSpawn`)

### Event/handler migration fixes
- ✅ Converted key handler classes from legacy forge-shim usage to NeoForge event imports/annotations:
  - `SpawnDataListenerEvent`
  - `LookThroughSpyglassEvent`
  - `ModVillagerTrades`
  - `ModParticles`

## Functional Notes
- Spawns in this mod are primarily feature/chunk-generation driven, not pure ambient mob-cap behavior.
- Spawn verification must be done in newly generated chunks after datapack reload/restart.
- Ocean fauna are biome-filtered by species spawn tables; some species are intentionally rare and biome-specific.

## Remaining Validation / Cleanup

### Validation (recommended before release)
1. Confirm no regressions on dedicated server startup/reload cycle.

### Technical debt cleanup (non-blocking but recommended)
1. Audit and reduce remaining `net.minecraftforge.*` shim imports where true NeoForge API exists.
2. Remove unused shim classes once no longer referenced.
3. Keep datagen notes updated if biome/tag generation is re-enabled.

## Key Files Changed During Runtime-Fix Pass
- `src/main/java/untamedwilds/UntamedWilds.java`
- `src/main/java/untamedwilds/client/ClientProxy.java`
- `src/main/java/untamedwilds/entity/ComplexMob.java`
- `src/main/java/untamedwilds/entity/mammal/EntityBigCat.java`
- `src/main/java/untamedwilds/entity/mammal/EntityBison.java`
- `src/main/java/untamedwilds/entity/mammal/EntityOpossum.java`
- `src/main/java/untamedwilds/entity/mammal/EntityRhino.java`
- `src/main/java/untamedwilds/util/SpawnDataListenerEvent.java`
- `src/main/java/untamedwilds/world/FaunaHandler.java`
- `src/main/java/untamedwilds/world/FaunaSpawn.java`
- `src/main/java/untamedwilds/world/gen/feature/FeatureOceanSwimming.java`
- `src/main/java/untamedwilds/world/gen/feature/FeatureDenseWater.java`
- `src/main/java/untamedwilds/world/gen/feature/FeatureOceanSessileSpawns.java`
- `src/main/resources/data/untamedwilds/neoforge/biome_modifier/*.json`

## Release Readiness (NeoForge)
- Current assessment: **close to release-ready** for NeoForge port usage, with the above validation pass still recommended for spawn distribution confidence.
