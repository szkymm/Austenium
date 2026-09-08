# [MBB] Austenium

Minecraft Forge 1.20.1 survival mod.
**Authored by:** Suzuki Yumemi <szkymm@gmail.com>
**Co-authored-by:** Matt Belfast Brown <thedayofthedo@gmail.com>
AUTHOR: Suzuki Yumemi
CONTACT: szkymm@gmail.com
MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>

**License:** GPL-3.0-only | **Java:** 17 | **Forge:** 47.3.0 | **Minecraft:** 1.20.1

## Current Version

**0.alpha.8** (Prerelease)

| Version | Content |
|---------|---------|
| 0.alpha.1 | Copper |
| 0.alpha.2 | Iron |
| 0.alpha.3 | Silver + Copper/Silver chains + Copper equipment |
| 0.alpha.5 | Gold |
| 0.alpha.6 | Emerald |
| 0.alpha.7 | Orichalcum |
| 0.alpha.8 | Mythril + recipe/loot fixes |

## Content

### Machines (speed multiplier vs vanilla, applies to product & fuel)
| Material | Furnace | Blast Furnace | Smoker | Speed |
|----------|---------|---------------|--------|-------|
| Copper | Copper Furnace | Copper Blast Furnace | Copper Smoker | x1.25 |
| Iron | Iron Furnace | Iron Blast Furnace | Iron Smoker | x2.5 |
| Silver | Silver Furnace | Silver Blast Furnace | Silver Smoker | x3 |
| Gold | Gold Furnace | Gold Blast Furnace | Gold Smoker | x5 |
| Diamond | Diamond Furnace | Diamond Blast Furnace | Diamond Smoker | x6 |
| Emerald | Emerald Furnace | Emerald Blast Furnace | Emerald Smoker | x8 |
| Orichalcum | Orichalcum Furnace | Orichalcum Blast Furnace | Orichalcum Smoker | x10 |
| Mythril | Mythril Furnace | Mythril Blast Furnace | Mythril Smoker | x12 |

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
(implemented up to 0.alpha.8: Copper, Iron, Silver, Gold, Diamond, Emerald, Orichalcum, Mythril; planned: Adamantite, Netherite, Radiant, Aurelianium)

- Large chests are formed by placing two chests side by side (vanilla mechanic), titled "Large <Material> Chest".
- GUI backgrounds are themed textures (material color, 18px vanilla-exact slot grid, centered player area).

### Ores & Materials
- Silver ore / deepslate silver ore (stone-level pickaxe; 1-2 raw silver, fortune/silk touch; ore smelts/blasts directly into silver ingot; iron-like distribution plus rare large vein).
- Orichalcum ore / deepslate orichalcum ore (diamond-level pickaxe; 1-2 raw orichalcum, fortune/silk touch; ore smelts/blasts directly into orichalcum ingot; dual triangle distribution peaking around y=35 and y=-35, fast decay with rare tails near y=65/-60, peak ~ vanilla gold, overall slightly below gold; ore family blocks emit light level 9).
- Mythril ore / deepslate mythril ore (diamond-level pickaxe; 1-2 raw mythril, fortune/silk touch; ore smelts/blasts directly into mythril ingot; dual trapezoid bands peaking around y=25 and y=-25, count 4 per band, size 8; ore family blocks emit light level 8).
- Raw silver / silver ingot / silver block / raw silver block; silver nugget; copper nugget; copper chain; silver chain; gold chain.
- Raw orichalcum / orichalcum ingot / orichalcum block / raw orichalcum block; orichalcum nugget; orichalcum chain (theme color #B02E26, close to copper but distinct; raw/ingot icons have a baked theme-color glow).
- Raw mythril / mythril ingot / mythril block / raw mythril block; mythril nugget; mythril chain (theme color #8932B8; mythril block texture is a purple recolor of the vanilla emerald block; raw mythril block texture is a purple recolor of vanilla cobbled deepslate; raw block crafts from 9 raw mythril and uncrafts back; raw/ingot icons have a baked theme-color glow).

### Equipment
- Silver tools & armor (iron-plus stats), copper tools & armor (vanilla has no copper equipment), plus silver armor set.
- Orichalcum tools & armor (diamond-to-netherite stats; tools come with Unbreaking II, armor with Protection I; theme-color baked glow, no vanilla purple glint).
- Mythril tools & armor (netherite-level stats; tools come with Efficiency II, armor with Protection II; theme-color baked glow, no vanilla purple glint).

### Recipes
- Tier upgrades: 8 ingots ring + lower-tier machine/container (same type); gold accepts both silver (8 gold ingots) and iron (4 gold blocks cross) sources; orichalcum upgrades only from emerald (8 orichalcum ingots + matching emerald item); mythril upgrades only from orichalcum (8 mythril ingots + matching orichalcum item).
- Vanilla-style machine crafting for blast/smoker (machine + smooth stone / logs).
- Ingot <-> nugget (9), block <-> ingot (9), tools/armor/chains standard patterns.

### Tags & Integration
- Forge tags: ores, ingots, nuggets, raw materials, storage blocks, chests, barrels (generic aggregates + per-material; all writing `replace: false` so tags merge across mods without clobbering).
- Minecraft tags: mineable/pickaxe + mineable/axe (chest/barrel), needs_stone_tool (silver ores), needs_iron_tool (iron..emerald tiers), needs_diamond_tool (orichalcum + mythril tiers).
- Loot tables for all blocks; JEI catalysts for all machines; en_us + zh_cn localization.

### World Generation (Silver, Orichalcum & Mythril)
- Features in data/mbb_austenium/worldgen; forge biome modifier (forge/biome_modifier, singular).
- Silver distribution: lower y -24..56 (x20), upper y 80..384 (x90), rare large vein (1/24).
- Orichalcum distribution: two triangle bands (y 5..65 peak ~35; y -65..-5 peak ~-35), count 4 per band, size 8; peak comparable to vanilla gold, faster decay, rare tails near y=65/-60.
- Mythril distribution: two trapezoid bands (y 5..45 peak ~25; y -45..-5 peak ~-25), count 4 per band, size 8; higher decay than orichalcum.

## Building

```bash
./gradlew build
```
Artifacts: `build/libs/[MBB]Austenium_<version>.jar` and `*_source.jar` (renamed MBB_* on releases).

## Repository Conventions

- Version: stable MAJOR.MINOR.PATCH; prerelease <Minor>.<stage>.<N> (alpha/beta/rc; rc continuous within a Minor).
- Commit template: [Type] capitalised, Keep-a-Changelog sections, Authored/Co-authored/Commit-at lines.
- Releases follow .github/RELEASE_TEMPLATE.md (English then Chinese); issues use .github/ISSUE_TEMPLATE (English forms).
