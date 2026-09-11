# [MBB] Austenium

Minecraft Forge 1.20.1 survival mod.

**English** | [中文](README.zh-cn.md)

- **Authored by:** Suzuki Yumemi <szkymm@gmail.com>
- **Co-authored-by:** Matt Belfast Brown <thedayofthedo@gmail.com>
- **Contact:** szkymm@gmail.com
- **Maintainer:** Matt Belfast Brown (MBB)

**License:** GPL-3.0-only | **Java:** 17 | **Forge:** 47.4.10 (runtime [47,)) | **Minecraft:** 1.20.1

## Current Version

**0.beta.5** (Prerelease / Beta)

| Version | Content |
|---------|---------|
| 0.alpha.1 | Copper |
| 0.alpha.2 | Iron |
| 0.alpha.3 | Silver + Copper/Silver chains + Copper equipment |
| 0.alpha.4 | Gold |
| 0.alpha.5 | Diamond |
| 0.alpha.6 | Emerald |
| 0.alpha.7 | Orichalcum |
| 0.alpha.8 | Mythril + recipe/loot fixes |
| 0.alpha.9 | Adamantite |
| 0.beta.1 | Netherite machines & containers |
| 0.beta.2 | Radiant (debris, machines, containers, gear) |
| 0.beta.3 | Aurelianium (End hero debris, x50 machines, 9x18/18x18 containers, gear, damage immunity) |
| 0.beta.4 | Netherite scrap block + tier shulker boxes (copper to aurelianium) |
| 0.beta.5 | Twelve tier hoppers (copper to aurelianium, 3.3 to 100 items per second) |

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
| Adamantite | Adamantite Furnace | Adamantite Blast Furnace | Adamantite Smoker | x15 |
| Netherite | Netherite Furnace | Netherite Blast Furnace | Netherite Smoker | x20 |
| Radiant | Radiant Furnace | Radiant Blast Furnace | Radiant Smoker | x25 |
| Aurelianium | Aurelianium Furnace | Aurelianium Blast Furnace | Aurelianium Smoker | x50 |

- Speeds are per-tier design points, not a cap: the only physical limit is 1 tick per operation (a 200-tick recipe would need x200), so later tiers can go faster.
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
(implemented up to 0.beta.5: Copper, Iron, Silver, Gold, Diamond, Emerald, Orichalcum, Mythril, Adamantite, Netherite, Radiant, Aurelianium)

- Large chests are formed by placing two chests side by side (vanilla mechanic), titled "Large <Material> Chest".
- GUI backgrounds are themed textures (material color, 18px vanilla-exact slot grid, centered player area).

### Shulker Boxes (0.beta.4)

| Material | Shulker Box | Slots | GUI reused from |
|----------|-------------|-------|-----------------|
| Copper | Copper Shulker Box | 9x4 = 36 | copper barrel |
| Iron | Iron Shulker Box | 10x4 = 40 | iron barrel |
| Silver | Silver Shulker Box | 9x5 = 45 | silver barrel |
| Gold | Gold Shulker Box | 12x4 = 48 | gold barrel |
| Diamond | Diamond Shulker Box | 10x5 = 50 | diamond barrel |
| Emerald | Emerald Shulker Box | 12x5 = 60 | emerald barrel |
| Orichalcum | Orichalcum Shulker Box | 9x7 = 63 | orichalcum barrel |
| Mythril | Mythril Shulker Box | 14x5 = 70 | mythril barrel |
| Adamantite | Adamantite Shulker Box | 15x5 = 75 | adamantite barrel |
| Netherite | Netherite Shulker Box | 15x7 = 105 | netherite barrel |
| Radiant | Radiant Shulker Box | 15x9 = 135 | radiant barrel |
| Aurelianium | Aurelianium Shulker Box | 9x18 = 162 | aurelianium barrel |

- Every tier box holds the same number of slots as that tier's barrel and opens the same GUI.
- Contents are kept when the box is broken and when it is upgraded on a crafting table.
- Vanilla shulker box behaviour: animated lid, contents preserved, and shulker boxes cannot be placed inside another shulker box.
- A tier shulker box can no longer be dyed, because the dye recipe only accepts vanilla shulker boxes.

### Tiered Hoppers (0.beta.5)

| Material | Hopper | Cooldown (ticks) | Per activation | Items per second |
|----------|--------|------------------|----------------|------------------|
| Copper | Copper Hopper | 6 | 1 | 3.3 |
| Iron | Iron Hopper | 4 | 1 | 5.0 |
| Silver | Silver Hopper | 3 | 1 | 6.7 |
| Gold | Gold Hopper | 5 | 2 | 8.0 |
| Diamond | Diamond Hopper | 2 | 1 | 10 |
| Emerald | Emerald Hopper | 5 | 3 | 12 |
| Orichalcum | Orichalcum Hopper | 4 | 3 | 15 |
| Mythril | Mythril Hopper | 1 | 1 | 20 |
| Adamantite | Adamantite Hopper | 2 | 3 | 30 |
| Netherite | Netherite Hopper | 1 | 2 | 40 |
| Radiant | Radiant Hopper | 1 | 3 | 60 |
| Aurelianium | Aurelianium Hopper | 1 | 5 | 100 |

- Recipes chain like every other piece: copper is built from the vanilla hopper, every later tier from the hopper below it.
- Suction runs at the same rate as transfer. Insertion honours the receiving face, so a hopper feeding a furnace from the side fills the fuel slot.
- A redstone signal disables a hopper, and the interface is the vanilla hopper screen.
- Mining gate: copper takes any pickaxe, iron through emerald need an iron pickaxe, orichalcum and above need a diamond pickaxe.

### Ores & Materials

- Silver ore / deepslate silver ore (stone-level pickaxe; 1-2 raw silver, fortune/silk touch; ore smelts/blasts directly into silver ingot; iron-like distribution plus rare large vein).
- Orichalcum ore / deepslate orichalcum ore (diamond-level pickaxe; 1-2 raw orichalcum, fortune/silk touch; ore smelts/blasts directly into orichalcum ingot; dual triangle distribution peaking around y=35 and y=-35, fast decay with rare tails near y=65/-60, peak ~ vanilla gold, overall slightly below gold; ore family blocks emit light level 9).
- Mythril ore / deepslate mythril ore (diamond-level pickaxe; 1-2 raw mythril, fortune/silk touch; ore smelts/blasts directly into mythril ingot; dual trapezoid bands peaking around y=25 and y=-25, count 4 per band, size 8; ore family blocks emit light level 8).
- Adamantite ore / deepslate adamantite ore (diamond-level pickaxe; 1-2 raw adamantite, fortune/silk touch; ore smelts/blasts directly into adamantite ingot; dual trapezoid bands peaking around y=15 and y=-15 with low-probability tails toward y=60/-60, count 2 per band, size 8; ore family blocks emit light level 8).
- Raw adamantite / adamantite ingot / adamantite block / raw adamantite block; adamantite nugget; adamantite chain (theme color #5E7C16; block texture is a gold-block recolor; raw block texture is an end-stone recolor; raw/ingot/tools/armor icons have a baked theme-color glow).
- Radiant debris (the radiant ore source; overworld, light level 12, strength 30/1200, allowed to touch air; drops itself and smelts/blasts into radiant scrap at 3.5 XP; generation is the vanilla ancient-debris scattered-ore shape mirrored block by block across y=0).
- Radiant scrap / radiant ingot / block of radiant / block of radiant scrap (theme color #F38BAA; debris texture follows ancient debris, scrap block follows deepslate tiles, radiant block follows the netherite block; scrap and ingot icons plus tools/armor carry a baked theme-color halo).
- Hero's Remains / aurelianium debris (the aurelianium ore source; End dimension, light level 15, strength 30/1200, allowed to touch air; drops itself and smelts/blasts into aurelianium scrap at 4.0 XP; generation is a weighted-list Gaussian around y 39.5 with sigma 7.5 inside y 19..60, replacing end stone).
- Aurelianium scrap / aurelianium ingot / block of aurelianium / block of aurelianium scrap (theme color #1D1D21; debris texture follows ancient debris, scrap block follows nether bricks, block follows the netherite block; items use the vanilla enchantment glint instead of a baked halo; all blocks light level 15; items and blocks are fire resistant).
- Raw silver / silver ingot / silver block / raw silver block; silver nugget; copper nugget; copper chain; silver chain; gold chain.
- Raw orichalcum / orichalcum ingot / orichalcum block / raw orichalcum block; orichalcum nugget; orichalcum chain (theme color #B02E26, close to copper but distinct; raw/ingot icons have a baked theme-color glow).
- Raw mythril / mythril ingot / mythril block / raw mythril block; mythril nugget; mythril chain (theme color #8932B8; mythril block texture is a purple recolor of the vanilla emerald block; raw mythril block texture is a purple recolor of vanilla cobbled deepslate; raw block crafts from 9 raw mythril and uncrafts back; raw/ingot icons have a baked theme-color glow).

### Equipment

- Silver tools & armor (iron-plus stats), copper tools & armor (vanilla has no copper equipment), plus silver armor set.
- Orichalcum tools & armor (diamond-to-netherite stats; tools come with Unbreaking II, armor with Protection I; theme-color baked glow, no vanilla purple glint).
- Mythril tools & armor (netherite-level stats; tools come with Efficiency II, armor with Protection II; theme-color baked glow, no vanilla purple glint).
- Adamantite tools & armor (tier 1900/10.5/+4.75; tools come with Fortune II, armor with Protection III).
- Radiant tools & armor (tier 2500/11.5/+5.5, level 4; smithed from netherite gear with the radiant upgrade template; tools come with Efficiency III + Fortune III + Unbreaking III + Mending, armor with Protection III + Unbreaking III + Mending and boots additionally Feather Falling III; knockback resistance 0.15 per piece; baked theme-color halo, no vanilla purple glint).
- Aurelianium tools & armor (tier 4000/16.0/+8.0, level 4; armor 9/15/14/9 with toughness 6.0 and knockback resistance 0.2; smithed from radiant gear with the aurelianium upgrade template; tools come with Efficiency V + Fortune V + Unbreaking V + Mending, armor with Protection VI + Unbreaking VI + Mending and boots additionally Feather Falling VI; a Silk Touch book in an anvil swaps the innate Fortune for Silk Touch at 5 levels while keeping every other enchantment and the durability; wearing all four pieces negates melee, projectile and explosion damage; vanilla enchantment glint, no baked halo).

### Recipes

**Tier upgrades**：every tier upgrades the same five pieces (furnace, blast furnace, smoker, chest, barrel) from the matching piece of the tier below:

| Tier | Upgrades from | Pattern | Cost |
|------|---------------|---------|------|
| Copper | vanilla piece | CCC / CFC / CCC | 8 copper ingots + vanilla piece |
| Iron | Copper | III / IMI / III | 8 iron ingots + copper piece |
| Silver | Iron | III / IMI / III | 8 silver ingots + iron piece |
| Gold | Silver **or** Iron | III / IMI / III, or G / GIG / G | 8 gold ingots + silver piece, or 4 gold blocks + iron piece |
| Diamond | Gold | III / IMI / III | 8 diamonds + gold piece |
| Emerald | Diamond | III / IMI / III | 8 emeralds + diamond piece |
| Orichalcum | Emerald | III / IMI / III | 8 orichalcum ingots + emerald piece |
| Mythril | Orichalcum | III / IMI / III | 8 mythril ingots + orichalcum piece |
| Adamantite | Mythril | III / IMI / III | 8 adamantite ingots + mythril piece |
| Netherite | Diamond **or** Adamantite | NDN / DPD / NDN, or N / NPN / N | 4 netherite ingots + 4 diamond blocks + diamond piece, or 4 netherite ingots + adamantite piece |
| Radiant | Netherite | RNR / NPN / RNR | 4 radiant ingots + matching netherite piece |
| Aurelianium | Radiant | XNX / NPN / XNX | 4 aurelianium ingots + matching radiant piece |

- III / IMI / III = eight of that tier material in a ring around the matching piece of the tier below; copper is the only tier that starts from the vanilla piece.
- Gold and netherite are the only tiers with two upgrade sources; netherite is the only tier using a 4-ingot cross instead of an 8-ingot ring.
- Cross-type crafting always uses vanilla materials: 5 iron ingots + that tier furnace + 3 smooth stone -> blast furnace; 4 logs around that tier furnace -> smoker.
- Netherite (0.beta.1): adamantite tools and armor smith into the vanilla netherite equivalents with the vanilla netherite upgrade template (template consumed, enchantments inherited).
- Radiant (0.beta.2): radiant scrap x9 <-> block of radiant scrap; 4 radiant scrap + 1 orichalcum ingot + 1 adamantite ingot + 1 mythril ingot + 1 diamond + 1 iron ingot (shapeless) -> 2 radiant ingots; radiant ingot x9 <-> block of radiant; the radiant upgrade smithing template is crafted from 4 deepslate + 4 smooth stone + 1 radiant ingot and duplicated with 1 adamantite ingot + 7 smooth stone -> 2 templates; netherite gear + template (consumed) + radiant ingot -> radiant gear on the smithing table.
- Aurelianium (0.beta.3): hero's remains smelt/blast into aurelianium scrap at 4.0 XP; aurelianium scrap x9 <-> block of aurelianium scrap; 4 aurelianium scrap + 1 radiant ingot + 1 adamantite ingot + 1 mythril ingot + 1 orichalcum ingot + 1 gold ingot (shapeless) -> 3 aurelianium ingots; aurelianium ingot x9 <-> block of aurelianium; the aurelianium upgrade smithing template is crafted from 4 obsidian + 4 end stone + 1 aurelianium ingot (EOE / OAO / EOE) and duplicated with 1 radiant ingot + 7 end stone -> 4 templates; radiant gear + template (consumed) + aurelianium ingot -> aurelianium gear on the smithing table.
- Netherite scrap block (0.beta.4): 9 netherite scrap <-> block of netherite scrap (3x3 ring, and shapeless back into 9 scrap); texture follows the bamboo mosaic weave.
- Tier shulker boxes (0.beta.4): every barrel recipe with the centre barrel replaced by the shulker box of that same tier, so the boxes chain up like the barrels. Copper 8 copper ingots + any vanilla shulker box (the entry point); iron 8 iron ingots + copper box; silver 8 silver ingots + iron box; gold 4 gold blocks + iron box or 8 gold ingots + silver box; diamond 8 diamonds + gold box; emerald 8 emeralds + diamond box; orichalcum 8 orichalcum ingots + emerald box; mythril 8 mythril ingots + orichalcum box; adamantite 8 adamantite ingots + mythril box; netherite 4 netherite ingots + adamantite box or 4 netherite ingots + 4 diamond blocks + diamond box; radiant 4 radiant ingots + netherite box; aurelianium 4 aurelianium ingots + radiant box. Contents are carried over.
- Ingot <-> nugget (9), block <-> ingot (9), tools/armor/chains standard patterns.

### Tags & Integration

- Forge tags: ores, ingots, nuggets, raw materials, storage blocks, chests, barrels (generic aggregates + per-material; all writing `replace: false` so tags merge across mods without clobbering).
- Minecraft tags: mineable/pickaxe + mineable/axe (chest/barrel), needs_stone_tool (silver ores), needs_iron_tool (iron..emerald tiers), needs_diamond_tool (orichalcum, mythril, adamantite, netherite, radiant and aurelianium blocks).
- Radiant tags: forge:ores/radiant (radiant debris), forge:ingots/radiant, forge:storage_blocks/radiant + radiant_scrap, forge:chests/barrels; all radiant blocks are diamond-level and pickaxe/axe mined.
- Aurelianium tags: forge:ores/aurelianium (hero's remains), forge:ores_in_ground/end_stone, forge:ingots/aurelianium, forge:storage_blocks/aurelianium + aurelianium_scrap, forge:chests/barrels, forge:armors/*, minecraft:swords/pickaxes/axes/shovels/hoes; all aurelianium blocks are diamond-level and pickaxe/axe mined.
- Loot tables for all blocks (no hard-coded tools: drop gating is requiresCorrectToolForDrops + mineable/needs_* tags, so other mods' tools work); the six mod tiers are registered in Forge TierSortingRegistry; JEI catalysts for all machines; en_us + zh_cn localization.

### World Generation (Silver, Orichalcum, Mythril, Adamantite, Radiant & Aurelianium)

- Features in data/mbb_austenium/worldgen; forge biome modifier (forge/biome_modifier, singular).
- Silver distribution: lower y -24..56 (x20), upper y 80..384 (x90), rare large vein (1/24).
- Orichalcum distribution: two triangle bands (y 5..65 peak ~35; y -65..-5 peak ~-35), count 4 per band, size 8; peak comparable to vanilla gold, faster decay, rare tails near y=65/-60.
- Mythril distribution: two trapezoid bands (y 5..45 peak ~25; y -45..-5 peak ~-25), count 4 per band, size 8; higher decay than orichalcum.
- Adamantite distribution: core trapezoid y 5..25 (plateau 8, peak 15) plus a low-probability tail y 25..60, mirrored negative, count 2, size 8.
- Radiant distribution: overworld, y 8..24 (scattered ore size 3) plus a uniform band y 8..64 (size 2); every placed block is mirrored block by block to (x, -y, z) by the custom feature mbb_austenium:mirrored_scattered_ore, so the distribution is exactly symmetric about y=0. Air exposure is allowed (discard chance 0), so veins can appear on cave walls.
- Aurelianium distribution: the End dimension (#minecraft:is_end), scattered_ore size 3 and size 2, discard chance 0 (air exposure allowed), replacing end stone through the tag mbb_austenium:aurelianium_replaceables; the height provider is a weighted_list Gaussian approximation (7 uniform bands 6 tall over y 19..60 with binomial weights 1:6:15:20:15:6:1, mean y 39.5, sigma 7.54).
- JER graphs: the mod writes its own world-gen.json distribution points; JER indexes its graph by raw y, so points are clamped to y 0..319 (negative half is not representable in the JER graph).

## Building

```bash
./gradlew build
```
Artifacts: `build/libs/[MBB]Austenium_<version>.jar` and `*_source.jar` (renamed MBB_* on releases).

## Repository Conventions

- Version: stable MAJOR.MINOR.PATCH; prerelease <Minor>.<stage>.<N> (alpha/beta/rc; rc continuous within a Minor).
- Commit template: [Type] capitalised, Keep-a-Changelog sections, Authored/Co-authored/Commit-at lines.
- Releases follow .github/RELEASE_TEMPLATE.md (English then Chinese); issues use .github/ISSUE_TEMPLATE (English forms).
