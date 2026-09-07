# [MBB] Austenium

Minecraft Forge 1.20.1 survival mod.
**Authored by:** Suzuki Yumemi <szkymm@gmail.com>
**Co-authored-by:** Matt Belfast Brown <thedayofthedo@gmail.com>
AUTHOR: Suzuki Yumemi
CONTACT: szkymm@gmail.com
MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>

**License:** GPL-3.0-only | **Java:** 17 | **Forge:** 47.3.0 | **Minecraft:** 1.20.1

## Current Version

**0.alpha.4** (Prerelease)

| Version | Content |
|---------|---------|
| 0.alpha.1 | Copper |
| 0.alpha.2 | Iron |
| 0.alpha.3 | Silver + Copper/Silver chains + Copper equipment |
| 0.alpha.4 | Gold |

## Content

### Machines (speed multiplier vs vanilla, applies to product & fuel)
| Material | Furnace | Blast Furnace | Smoker | Speed |
|----------|---------|---------------|--------|-------|
| Copper | Copper Furnace | Copper Blast Furnace | Copper Smoker | x1.25 |
| Iron | Iron Furnace | Iron Blast Furnace | Iron Smoker | x2.5 |
| Silver | Silver Furnace | Silver Blast Furnace | Silver Smoker | x3 |
| Gold | Gold Furnace | Gold Blast Furnace | Gold Smoker | x5 |

- Each machine uses the vanilla fire animation of its own type (blast/smoker frame-stack mcmeta).
- Textures: material-colored metal body; copper (orange), iron (grey + subtle rust), silver (cold white), gold (multi-tone gold).

### Containers
| Material | Barrel | Chest | Large Chest (paired) | GUI color |
|----------|--------|-------|-----------------------|-----------|
| Copper | 9x4 = 36 | 9x4 = 36 | 9x8 = 72 | #F9801D |
| Iron | 10x4 = 40 | 10x4 = 40 | 10x8 = 80 | #9D9D97 |
| Silver | 9x5 = 45 | 9x5 = 45 | 9x10 = 90 | #F9FFFE |
| Gold | 12x4 = 48 | 12x4 = 48 | 12x8 = 96 | #FED83D |
| Diamond | 10x5 = 50 | 10x5 = 50 | 10x10 = 100 | #3AB3DA |
| Emerald | 12x5 = 60 | 12x5 = 60 | 12x10 = 120 | #80C71F |
| Orichalcum | 9x7 = 63 | 9x7 = 63 | 9x14 = 126 | #B02E26 |
| Mythril | 14x5 = 70 | 14x5 = 70 | 14x10 = 140 | #8932B8 |
| Adamantite | 15x5 = 75 | 15x5 = 75 | 15x10 = 150 | #5E7C16 |
| Netherite | 15x7 = 105 | 15x7 = 105 | 15x14 = 210 | #835432 |
| Radiant | 15x9 = 135 | 15x9 = 135 | 15x18 = 270 | #F38BAA |
| Aurelianium | 9x18 = 162 | 9x18 = 162 | 18x18 = 324 | #1D1D21 |
(planned; implemented: Copper, Iron, Silver, Gold)

- Large chests are formed by placing two chests side by side (vanilla mechanic), titled "Large <Material> Chest".
- GUI backgrounds are themed textures (material color, 18px vanilla-exact slot grid, centered player area).

### Ores & Materials
- Silver ore / deepslate silver ore (stone-level pickaxe; 1-2 raw silver, fortune/silk touch; iron-like distribution plus rare large vein).
- Raw silver / silver ingot / silver block / raw silver block; silver nugget; copper nugget; copper chain; silver chain; gold chain.

### Equipment
- Silver tools & armor (iron-plus stats), copper tools & armor (vanilla has no copper equipment), plus silver armor set.

### Recipes
- Tier upgrades: 8 ingots ring + lower-tier machine/container (same type); gold accepts both silver (8 gold ingots) and iron (4 gold blocks cross) sources.
- Vanilla-style machine crafting for blast/smoker (machine + smooth stone / logs).
- Ingot <-> nugget (9), block <-> ingot (9), tools/armor/chains standard patterns.

### Tags & Integration
- Forge tags: ores, ingots, raw materials, storage blocks, chests, barrels.
- Minecraft tags: mineable/pickaxe, needs_stone_tool (silver ores), needs_iron_tool (silver blocks).
- Loot tables for all blocks; JEI catalysts for all machines; en_us + zh_cn localization.

### World Generation (Silver)
- Features in data/mbb_austenium/worldgen; forge biome modifier (forge/biome_modifier, singular).
- Distribution: lower y -24..56 (x20), upper y 80..384 (x90), rare large vein (1/24).

## Building

```bash
./gradlew build
```
Artifacts: `build/libs/[MBB]Austenium_<version>.jar` and `*_source.jar` (renamed MBB_* on releases).

## Repository Conventions

- Version: stable MAJOR.MINOR.PATCH; prerelease <Minor>.<stage>.<N> (alpha/beta/rc; rc continuous within a Minor).
- Commit template: [Type] capitalised, Keep-a-Changelog sections, Authored/Co-authored/Commit-at lines.
- Releases follow .github/RELEASE_TEMPLATE.md (English then Chinese); issues use .github/ISSUE_TEMPLATE (English forms).
