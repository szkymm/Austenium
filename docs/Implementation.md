# [MBB] Austenium Implementation Notes

Technical description of how the mod is built: registration, the tier ladder, every system, world generation, client integration, localisation and the verification harness. Player-facing content lives in `docs/content.md`; the project rules live in `AGENT.md`.

Every number in this document is taken from source, configuration or tool output, not from memory.

## 1. Architecture

The mod is a single Forge 1.20.1 mod rooted at `com.mbb.austenium`, built with Java 17 from a Gradle project kept in `tools/build/`.

| Package | Responsibility |
|---|---|
| `com.mbb.austenium` | `MbbAustenium` entry point, the `VERSION` constant and the mod bus wiring |
| `content` | Registration: blocks, items, block entities, menus, recipes, creative tab, tier definitions, armour materials |
| `content.block`, `content.item`, `content.block.entity`, `content.menu`, `content.recipe`, `content.tab` | One class per registered object or per shared behaviour |
| `client` | Renderers, screens and the client-side open-lid tracker |
| `jei` | The JEI plugin, the Jade plugin and the JER world generation config |
| `worldgen` | The Austeniumcraft World chunk generator, portal shape and linker, and the mirrored ore feature |

Registration goes through `DeferredRegister` throughout; blocks, items, block entities, menus and features are declared in one place each and the creative tab is an explicit ordered table.

## 2. The tier ladder

Twelve tiers, in order: copper, iron, silver, gold, diamond, emerald, orichalcum, mythril, adamantite, netherite, radiant, aurelianium. Every tier repeats the same shape: machines, containers, a hopper, a shulker box, tools and armour.

Machine speed, container size, hopper rate and fuel value all rise with the tier. The ladder is deliberately open ended: the only physical limit is one tick per operation, so later tiers may go faster than the current maximum.

## 3. Machines

Furnace, blast furnace and smoker exist for every tier. Each runs at a per-tier multiplier applied to both the product and the fuel, and the fuel is scaled once at ignition.

| Tier | Multiplier | Scale |
|---|---|---|
| Copper | x1.25 | 0.8 |
| Iron | x2.5 | 0.4 |
| Silver | x3 | 0.3333 |
| Gold | x5 | 0.2 |
| Diamond | x6 | 0.1667 |
| Emerald | x8 | 0.125 |
| Orichalcum | x10 | 0.1 |
| Mythril | x12 | 0.0833 |
| Adamantite | x15 | 0.0667 |
| Netherite | x20 | 0.05 |
| Radiant | x25 | 0.04 |
| Aurelianium | x50 | 0.02 |

Each tier is upgraded from the one below by eight ingots of the new material in a ring around the lower tier machine. Blast furnaces and smokers additionally accept a same-tier cross recipe (five iron ingots plus smooth stone, four logs in a cross). Netherite and radiant machines accept two upgrade paths.

Machines carry a lit and an unlit blockstate; blast furnaces and smokers need `mcmeta` animation files so their lit textures do not stack frames.

## 4. Containers

Chests and barrels exist per tier, and each tier pairs into a larger grid when two chests stand side by side.

| Tier | Chest | Paired | Barrel | GUI |
|---|---|---|---|---|
| Copper | 9x4 = 36 | 9x8 = 72 | 36 | #F9801D |
| Iron | 10x4 = 40 | 10x8 = 80 | 40 | #9D9D97 |
| Silver | 9x5 = 45 | 9x10 = 90 | 45 | #F9FFFE |
| Gold | 12x4 = 48 | 12x8 = 96 | 48 | #FED83D |
| Diamond | 10x5 = 50 | 10x10 = 100 | 50 | #3AB3DA |
| Emerald | 12x5 = 60 | 12x10 = 120 | 60 | #80C71F |
| Orichalcum | 9x7 = 63 | 9x14 = 126 | 63 | #B02E26 |
| Mythril | 14x5 = 70 | 14x10 = 140 | 70 | #8932B8 |
| Adamantite | 15x5 = 75 | 15x10 = 150 | 75 | #5E7C16 |
| Netherite | 15x7 = 105 | 15x14 = 210 | 105 | #835432 |
| Radiant | 15x9 = 135 | 15x18 = 270 | 135 | #F38BAA |
| Aurelianium | 9x18 = 162 | 18x18 = 324 | 162 | #1D1D21 |

The container GUI is a themed texture per (columns, rows) pair with a 9-argument blit and a centred player inventory; the themed background is aligned one to one with the slots. Containers are mined with an axe; copper has no tool gate, iron through emerald need iron level, orichalcum and above need diamond level. Every tier chest is drawn by `ChestRenderer`, so every one of them sets `noOcclusion()` in its block properties.

## 5. Tiered hoppers

Twelve hoppers share one block entity type and hold a per-tier cooldown and batch size.

| Tier | Rate | Cooldown | Batch |
|---|---|---|---|
| Copper | 3.3 items/s | 6 ticks | 1 |
| Iron | 5.0 items/s | 4 ticks | 1 |
| Silver | 6.7 items/s | 3 ticks | 1 |
| Gold | 8.0 items/s | 5 ticks | 2 |
| Diamond | 10 items/s | 2 ticks | 1 |
| Emerald | 12 items/s | 5 ticks | 3 |
| Orichalcum | 15 items/s | 4 ticks | 3 |
| Mythril | 20 items/s | 1 tick | 1 |
| Adamantite | 30 items/s | 2 ticks | 3 |
| Netherite | 40 items/s | 1 tick | 2 |
| Radiant | 60 items/s | 1 tick | 3 |
| Aurelianium | 100 items/s | 1 tick | 5 |

Transfer goes through `HopperBlockEntity.addItem` with the side set to the opposite of the hopper facing, and `WorldlyContainer.getSlotsForFace(side)` decides which slots are legal. The argument order of that helper is (source, destination, stack, direction); passing the target first makes the hopper put the stack back into itself, which reads in game as a hopper that never transfers.

## 6. Shulker boxes

Twelve tier shulker boxes, each holding exactly the matching barrel capacity, each reusing that barrel's menu type and GUI texture. The copper box is the only entry point and takes any vanilla shulker box through the tag `mbb_austenium:upgradeable_shulker_boxes`; every later tier takes the box below it.

Contents survive both breaking and upgrading: the loot table copies `Items` into `BlockEntityTag.Items`, and the upgrade is a custom `ShapedRecipe` subclass (`mbb_austenium:shaped_shulker_box_upgrade`) that copies the block entity tag during assembly while keeping the recipe type `CRAFTING` so JEI and the recipe book still show it. Nesting is refused in `canPlaceItem`, stacks stay at one, and the lid animates through a block entity renderer driven by a block event, because the opener counter only exists on the server.

## 7. The coal family

Sixteen coals: twelve tier coals and four dimension coals (Overworld, Nether, End, Austeniumcraft). A tier coal burns sixteen items times that tier's machine multiplier, so copper coal covers 20 items and aurelianium coal 800. A dimension coal burns 5, 10, 15 or 20 vanilla coal blocks.

A coal block is nine coals and burns exactly ten times its coal. One coal and one stick make eight torches, and each tier adds four more, up to 68 for the Austeniumcraft coal.

Ten coal ores sit on five host rocks: Overworld stone and deepslate, Nether netherrack and blackstone, End stone, and all four Austeniumcraft bands. Each drops one coal with fortune, keeps its block with silk touch, and takes a wooden pickaxe.

JEI carries 42 pages for the coal family, and JER plots the five home dimension coal ores inside the y 0..319 window it can draw.

## 8. World generation

Ore placement lives in `worldgen/placed_feature/*.json`, one file per band. The authoritative height ranges:

| Ore | Range |
|---|---|
| Silver | -24 .. 56, plus 80 .. 384 |
| Orichalcum | 5 .. 65 and -65 .. -5, peaks at ±35 |
| Mythril | 5 .. 45 and -45 .. -5, peaks at ±25 |
| Adamantite | core 5 .. 25 with a plateau at 11 .. 19, tail 25 .. 60, mirrored below zero |
| Radiant debris | 8 .. 64, mirrored block by block around y=0 |
| Hero debris | End only, a weighted band 19 .. 60 whose heaviest segment is 37 .. 42 |

Silver, orichalcum, mythril and adamantite ore smell or blast directly into the ingot, and the raw material does the same. Mining gates are expressed as block properties plus vanilla tags, so a tool of sufficient tier from any mod works.

Density is measured from the region files of a freshly generated 256 chunk window, with vanilla ores counted in the same chunks as the control. The mod's overworld coal ore measures 142.07 blocks per chunk against the vanilla coal control at 144.09.

## 9. The Austeniumcraft World

A single biome dimension, `mbb_austenium:austeniumcraft_world`, registered as an overworld-like type with skylight, a day cycle, beds and rain, coordinate scale 1:1 and a logical height of 448.

The envelope runs from y -64 to y 383 as a solid cake: bedrock at the bottom, then deepslate, stone, netherrack and end stone, with seams dithered three to eight blocks per column and no water or lava anywhere. Dry noise karst caves replace the usual cave systems.

`AwChunkGenerator` is noise based: it rewrites the bands after the vanilla fill, runs no surface pass, disables aquifers, pins the sea level to `min_y` and takes the final density as the minimum of three thresholded vanilla cave noises. Forty-seven `aw_` placed features come from `tools/gen_aw_ores.py` with source dimension heights mapped onto the bands; biome tags carry the AW dimension into the overworld, nether and end tag sets, and `aw_remove_own_ores` strips the mod's own ore lists inside it.

The portal is a 5x5 ring without corners of the twelve tier storage blocks around a 3x3 plane of `mbb_austenium:austeniumcraft_portal`, opened by left clicking the aurelianium block with any pickaxe. It links the Overworld and AW only, at 1:1 in x and z; an anchor above y 320 maps back to y 317, the counterpart is searched for within sixteen blocks or carved, breaking a frame block breaks that plane, and Nether portals stay inert inside.

## 10. Gear and enchanting

Every tier has a full tool and armour set. The two smithed tiers, radiant and aurelianium, carry innate enchantments applied by the same custom smithing serializers that keep the set together:

| Tier | Armour defence | Toughness | Durability | Innate |
|---|---|---|---|---|
| Radiant | 6 / 11 / 10 / 6 | 3.5 | 50 | Protection III, Unbreaking III, Mending; Feather Falling III on boots |
| Aurelianium | 9 / 15 / 14 / 9 | 6.0 | 75 | Protection VI, Unbreaking VI, Mending; Feather Falling VI on boots |

Innate enchantments are never downgraded, and a Silk Touch book on an anvil replaces the innate Fortune at the same level while keeping the other enchantments and the durability. A full aurelianium set negates melee, projectile and explosion damage; fall, fire, magic, void and starvation still apply.

Raw and ingot items, tools and armour bake a static theme-coloured halo (`isFoil=false`), so they glow without the vanilla enchantment shimmer and without emitting block light.

## 11. Client integration

- **JEI**: recipes, uses and information pages for every machine, container, ore, coal and block, all built from translation keys. Catalysts and info pages are registered through `addIngredientInfo` and `addItemStackInfo`; the information pages carry the tier, the fuel budget, the mining tier and the generation band.
- **Jade**: six client providers covering machine tier and speed, hopper rate with cooldown and batch, container and shulker box slot count, coal block fuel budget, ore mining tier and generation band, and the portal rule. Inside the AW dimension every line also names the band.
- **JER**: the five home dimension coal ores are plotted, the five AW coal ores are deliberately absent, and every distribution point stays inside the window JER can draw.

Anything the client animates must be driven by data the client has: shulker lids move through a block event, never through the server-only opener counter. Entity and block entity models are textured through their own atlas; the tier shulker boxes use `Sheets.SHULKER_SHEET` with their own `atlases/shulker_boxes.json` entry rather than sampling the block atlas.

## 12. Internationalisation

Thirteen locales ship: `en_us`, `en_gb`, `en_au`, `fr_fr`, `es_es`, `it_it`, `ja_jp`, `ko_kr`, `ru_ru`, `zh_cn`, `zh_hk`, `zh_tw` and `lzh`.

`en_us` is the canonical source and every locale carries the identical key set of 593 keys. No user-facing string is hardcoded: every client string is a `Component.translatable` call, including the JEI information pages that were previously written in Chinese in code. Keys are grouped by object (`block.*`, `item.*`, `container.*`), by JEI page (`jei.*`) and by Jade line (`jade.*`), and families that are assembled at runtime (`jei.mbb_austenium.coalname.*`, `jade.mbb_austenium.tier.*` and similar) are checked as families rather than as individual keys.

The English locales use British spelling where a British form exists. The Chinese locales are derived with a conversion pass plus a Minecraft terminology table (終界, 地獄, 界伏盒, 絲綢之觸, 幸運), and `lzh` is written in Literary Chinese with the tier names in their classical forms.

## 13. Verification harness

| Tool | Purpose |
|---|---|
| `tools/full_headless_suite.py` | Whole-mod regression on a freshly generated world: every block placed and read back, every item inserted and read back, burn values measured in a real furnace, loot tables mined, dimension bands sampled. 441 checks. |
| `tools/coal_headless_suite.py` | Coal family detail: the ten coal ores, the coal blocks, the burn values, the drops and the four AW bands. 38 checks. |
| `tools/ore_density_check.py` | Counts ore blocks per chunk straight out of the region files of a freshly generated 256 chunk window, with vanilla ores as the control. |
| `tools/check_i18n.py` | No hardcoded client text, every key used in code exists, all locales carry the same key set and the same placeholder count, no empty values, no forbidden terms, no American spelling in the British locales. |
| `tools/audit_tree.py` | Assets, data, lang, version sync, Java conventions and the file header format. |
| `tools/self_check.py` | Namespaces, tags, versions, git state and packaging consistency with the test instance. |
| `tools/readme_parity.py` | Structural parity of the bilingual pairs plus table well-formedness. |
| `tools/tag_coverage.py`, `tools/tag_namespace_audit.py`, `tools/check_tab_order.py`, `tools/check_lang_keys.py`, `tools/check_jer_coal.py` | Tag coverage, tag path shape, creative tab order, runtime-built lang keys, and the JER coal contract. |

Headless runs start by deleting the world, boot the hidden dev server, drive the checks over RCON and stop the server with the RCON `stop` command. Every gate exits non-zero on failure, and each one has been shown to fail by injecting a real defect and reverting it.

## 14. Traps worth remembering

Collected from the codebase's own history, because each one produced a wrong result before it was understood:

- A block drawn by a block entity renderer must set `noOcclusion()`; without it the block occludes light in its own cell and the world model draws black while the item form looks perfect.
- An animation that reads a server-only counter never moves on the client; vanilla syncs shulker lids with a level block event.
- Reading an entity model's texture from the block atlas samples whatever sprite happens to sit at those coordinates; use the model's own atlas.
- A hopper that fills the first slot which merely accepts the item puts fuel into a furnace's smelting slot; route the transfer through `getSlotsForFace(side)`.
- `/setblock` refuses to write when the target already holds the same blockstate, and a stale world keeps furnace and container state, so a headless run must start from a deleted world.
- A debug probe removed from the source but present in the built class ships in the next build; check the source and the bytecode.
- Texture brightness has to be measured, not eyeballed; a per-tier numeric target beats one global curve.

## 15. Per-version design records

Moved out of AGENT.md so that implementation detail lives with the implementation notes.

### 0.beta.6, the Austeniumcraft World (2026-09-13)

- Names and ids: Austeniumcraft World / 奥氏挖矿维度, `mbb_austenium:austeniumcraft_world`, single biome `mbb_austenium:austeniumcraft` (Austeniumcraft / 奥氏挖矿); overworld like type (skylight, day cycle, beds, rain), coordinate scale 1:1, logical height 448.
- Envelope: min_y -64, height 448, max_y 383; solid cake of -64 bedrock / -63..-1 deepslate / 0..256 stone / 257..354 netherrack / 355..383 end stone, seams dithered 3..8 blocks per column; no water, no lava, dry noise karst caves.
- Generator: AwChunkGenerator (noise based, band rewrite after the vanilla fill, no surface pass), aquifers off, default fluid air, sea level pinned to min_y, vanilla vein entries on, final density = the minimum of three thresholded vanilla cave noises; 47 aw_ placed features from `tools/gen_aw_ores.py` with source dimension heights mapped (overworld 0.984 / 0.8, nether 0.766, hero debris 0.707), biome tags is_overworld / is_nether / is_end carry AW, aw_remove_own_ores strips the mod own ore lists.
- Structures and mobs: amethyst geodes, fossils, ancient cities plus cross mod structures; overworld hostiles in all four bands, bats in deepslate and stone, endermen in the end stone band; no passive animals, nether mobs or water creatures.
- Portal: 5x5 ring without corners of the twelve tier storage blocks around a 3x3 plane of `mbb_austenium:austeniumcraft_portal`, opened by left clicking the aurelianium block with any pickaxe; Overworld <-> AW only, 1:1 in x/z, an anchor above y 320 maps back to y 317, the counterpart is searched within 16 blocks or carved, breaking a frame block breaks that plane; Nether portals are inert inside AW.
- Verification: headless 63/63, palette scans (geodes 70/576 chunks, fossils 31/576, ancient cities 24/64 and 47/576), no stronghold inside AW; the user ran the remaining checks himself and declared the version tested.

### 0.beta.7 coal density pass (2026-09-13)

- Method: delete the world, boot the hidden dev server, run the whole-mod regression on the fresh world, force-generate 256 chunks (16x16) per dimension, save, stop, and count the paletted sections straight out of the region files (`tools/ore_density_check.py`); vanilla ores are counted in the same chunks as the control.
- First pass, blocks per chunk: Overworld coal 84.6 against vanilla 88.4; Nether 53.6 against vanilla nether gold 52.3; End 458.9 (about fifteen percent of the end stone band, the Overworld counts had been copied into a band with a fraction of the rock volume); AW world coal 847.5 against the vanilla coal already mapped there at 833.5.
- Fix in the same session: both End families from `count 20 + 30` to `count 8 + 12`, the AW side of the coal ore end band from 30 to 12. After: End 186.4 per chunk, AW end stone band 309.6 plus 188.4 (about 6.7 percent), and the End stays roughly 2.2 times richer than the Overworld by user choice.
- The blackstone variants measure near zero in a random Nether window because blackstone is rare outside basalt deltas; they appear wherever blackstone does. Every number here comes from a freshly generated world: `/setblock` refuses to write over an unchanged blockstate and containers keep their state.

### 0.rc.1 (released 2026-09-13)

- Released as a prerelease: tag v0.rc.1 on commit f016606 (the single G6 commit, 26 files), master pushed, assets MBB_Austenium_0.rc.1.jar 1,475,454 B sha256 4fe7d182c365 and MBB_Austenium_0.rc.1_source.jar 1,236,988 B sha256 e4a0d1b8; release record in section 14, notes `tools/release_notes_v0.rc.1.md`.
- Durable decisions: the creative tab is an explicit group table (eight groups, 237 entries) with a runtime guard plus an exclusion table, and the portal plane stays registered and out of the tab; Jade registers block components by class, so every provider guards itself by exact block membership; the JER table stays limited to the three home dimensions because its graph only plots y 0..319; the dimension ships the standard translation key while Xaero names dimensions on its own; third party contracts are verified against their jars (section 12.2).
- The 0.rc.2 record replaces this section when 0.rc.2 ships; the version string structure settled on 2026-09-13 lives in section 1.2.
