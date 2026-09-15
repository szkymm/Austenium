# Changelog

All notable changes to [MBB] Austenium are documented in this file.
The format follows Keep a Changelog, and every released version links to its GitHub release page.
Version identity: the functional version is `A.B.C` (stable `MAJOR.MINOR.PATCH`, prerelease `<Minor>.<stage>.<N>`), and the platform suffix `_D.E.F` is appended only when one functional version ships for more than one Minecraft and loader platform.
The target is Minecraft 1.20.1 with Forge 47.4 or newer.
0.0.1 is the first stable release; 0.0.2 is the next planned cycle.

## [0.0.1] - 2026-09-16
Release: https://github.com/szkymm/Austenium/releases/tag/v0.0.1
Verified: nine static checkers pass, headless regression 441/441 and the coal suite 38/38 on a freshly generated world; the jar boots Forge 47.4.0, 47.4.10 and 47.4.23 servers with `mbb_austenium v0.0.1 initialised.` and is refused outside the declared ranges; `MBB_Austenium_0.0.1.jar` 1571916 B sha256 d3d9725018ae and `MBB_Austenium_0.0.1_source.jar` 1337177 B sha256 5c703304ad98.

### Added

- The README states the supported platform, the platforms the mod refuses and a roadmap for the coverage planned next: the whole Forge 47 line in 0.0.2, Minecraft 1.20 to 1.20.1 in 0.0.3, 1.20 to 1.20.3 in 0.1.0 and 1.19.4 to 1.20.3 in 0.2.0.

### Changed

- The Forge dependency floor moved from `[47,)` to `[47.4.0,)`. Forge 47.0 to 47.3 lack the `ResourceLocation` helpers this source calls, so the wide range over-claimed.
- The Minecraft dependency range narrowed from `[1.20,1.20.1]` to `[1.20.1,1.20.2)`. Minecraft 1.20 fails to load the mod, and Forge rejects a range whose boundaries are identical.
- Installation instructions now ask for Forge 47.4 or newer, matching the measured floor.

## [0.rc.2] - 2026-09-14
Release: https://github.com/szkymm/Austenium/releases/tag/v0.rc.2
Verified: nine static checkers pass, headless regression 441/441 and the coal suite 38/38 on a freshly generated world; `MBB_Austenium_0.rc.2.jar` 1571913 B sha256 7d9be85a9206 and `MBB_Austenium_0.rc.2_source.jar` 1337174 B sha256 2a6e35d5f518.

### Added

- Thirteen client locales: English (United States, United Kingdom, Australia), French (France), Spanish (Spain), Italian (Italy), Japanese, Korean, Russian, Simplified Chinese, Traditional Chinese (Hong Kong and Taiwan) and Literary Chinese.
- A mod logo in two sizes: 128x128 for the Forge mod list and 400x400 for the README header.

### Changed

- Version identity gains a platform suffix: `A.B.C` stays the functional version in all four version places, and `_D.E.F` (D: Minecraft version group, E: loader feature line, F: release counter) appears only when one functional version ships for several platforms.
- README rebuilt as an introduction: identity, six feature highlights, supported languages, installation, version and download naming rules, links and licence.
- The machine, container, ore, coal and dimension tables moved out of the README into a bilingual player guide.
- JEI information pages moved from hardcoded Chinese text to translation keys, so they follow the client locale.

### Fixed

- The Minecraft dependency range no longer claims the whole 1.20.x line: the mod declares the 1.20 to 1.20.1 platform group it is built and verified for.
- The mod metadata now credits the author and the co-author exactly as the project files do, so the in-game mod list matches the documentation.

## [0.rc.1] - 2026-09-13

Release: https://github.com/szkymm/Austenium/releases/tag/v0.rc.1
Verified: six static checkers pass, headless regression 441/441 and coal suite 38/38 on a freshly generated world; `MBB_Austenium_0.rc.1.jar` 1,475,454 B sha256 4fe7d182c365 and `MBB_Austenium_0.rc.1_source.jar` 1,236,988 B sha256 e4a0d1b8.

### Added

- Jade integration with six client providers: machine tier and speed, hopper rate with cooldown and batch, container and shulker box slot count, coal block fuel budget, ore mining tier and generation band, and the portal rule; inside the dimension every line also names the band.
- Just Enough Resources entries for the coal ores.
- 42 Just Enough Items pages for the coal family.
- Tag pass: the twelve tier shulker boxes joined `minecraft:shulker_boxes`, the hero debris joined `forge:ore_rates/singular` on both registries, the sixteen coal blocks joined `forge:storage_blocks/coal` and the ten coal ores joined `minecraft:coal_ores`.

### Changed

- The creative tab is an explicit group table of eight groups and 237 entries with a runtime coverage guard and an exclusion table; the portal plane stays registered and outside the tab.
- Namespace and tag pass over every registry path and tag file, all of them `replace:false`.

### Fixed

- The Just Enough Resources, Just Enough Items and tag gaps that 0.beta.7 left open.

## [0.beta.7] - 2026-09-13

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.7
Verified: audit and self check ALL CHECKS PASSED, full regression 441/441 on a freshly generated world, jar 1,449,317 B sha256 baa31382c945.

### Added

- Sixteen coals: twelve tier coals from copper to aurelianium and four dimension coals for the overworld, the nether, the end and the Austeniumcraft World.
- Tiered fuel values: a tier coal burns 160 items times the machine multiplier of its tier, from copper 20 items up to aurelianium 800 items, and a dimension coal burns 5, 10, 15 or 20 vanilla coal blocks.
- Coal blocks: nine coals to one block and back, and a block burns exactly ten times its coal.
- Torches: one coal and one stick yield four torches plus four per tier, eight for copper coal up to 68 for Austeniumcraft coal.
- Ten coal ores on five host rocks: overworld stone and deepslate, nether netherrack and blackstone, end end stone, and the Austeniumcraft World stone, deepslate, netherrack, blackstone and end stone; every family owns a spot pattern and a one pixel border colour, drops one coal with fortune, keeps the block with silk touch, grants zero to two experience and accepts a wooden pickaxe.
- World generation: the overworld uses the vanilla coal parameters, the nether uses the vanilla nether gold parameters, the end gets two bands of 8 and 12, and the Austeniumcraft World gets its own ore in all four bands plus compressed copies of the three home coals.

### Changed

- The Gradle project and every agent tool moved into `tools/`, leaving the repository root with the sources, the documents, the launchers and the regenerable leftovers.

### Fixed

- End coal density: both end placements had copied the overworld counts into a band with a fraction of the rock volume, so the families dropped from count 20 and 30 to count 8 and 12, which took the end coal ore from 458.9 to 186.4 blocks per chunk over a 256 chunk window.

## [0.beta.6] - 2026-09-12

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.6
Verified: audit ALL CHECKS PASSED (114 blocks, 196 items, 317 language keys), headless 63/63, jar 1,330,214 B; palette scans found geodes in 70 of 576 chunks, fossils in 31 of 576 and ancient cities in 24 of 64 and 47 of 576, and the stronghold is absent inside the dimension.

### Added

- The Austeniumcraft World (奥氏挖矿维度), id `mbb_austenium:austeniumcraft_world`, an overworld like dimension with a single biome `mbb_austenium:austeniumcraft`, coordinate scale 1:1 and logical height 448.
- Layered terrain: min_y -64, height 448, max_y 383, a solid cake of bedrock at -64, deepslate from -63 to -1, stone from 0 to 256, netherrack from 257 to 354 and end stone from 355 to 383, with the seams dithered three to eight blocks per column, and no water and no lava anywhere.
- A noise based chunk generator with a band rewrite, aquifers off, default fluid air, sea level pinned to min_y and a final density taken from three thresholded vanilla cave noises, plus 47 placed feature sets whose heights are mapped from their source dimension.
- Amethyst geodes, fossils and ancient cities, with the biome joining the overworld, nether and end vanilla biome tags.
- Mob spawns per band: overworld hostiles in all four bands, bats in the deepslate and stone bands and endermen in the end stone band.
- The portal: a 5x5 ring without corners of the twelve tier storage blocks around a 3x3 plane of `mbb_austenium:austeniumcraft_portal`, opened by left clicking the aurelianium block with any pickaxe, connecting the overworld and the dimension only, 1:1 in x and z, with the counterpart searched within 16 blocks or carved out, and breaking a frame block breaks that plane.

### Changed

- The mod's own ore lists are stripped from the dimension by a modifier, so nothing is generated twice.

### Fixed

- Nether portals are inert inside the dimension.

## [0.beta.5] - 2026-09-11

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.5
Verified: final audit ALL CHECKS PASSED (113 blocks, 195 items, 268 recipes, 313 language keys), jar 1,273,717 B, and the user verified the version in game.

### Added

- Twelve tiered hoppers on one shared block entity type of the mod's own, from copper 3.3 items per second up to aurelianium 100 items per second, with one activation that ejects and then sucks, suction sharing the tier rate, insertion through the sided slot contract and a chain recipe per tier.

### Changed

- Tier textures rebuilt from the vanilla luminance with a four stop palette plus a per tier motif, so the twelve tiers no longer read as one muddy family.

### Fixed

- Every block drawn by a block entity renderer now sets `noOcclusion`, which fixes the twelve tier chests and the twelve tier shulker boxes drawing black in the world.
- Hopper side feed: the transfer goes through the sided slot contract, so fuel lands in the fuel slot and items land in the smelting slot.
- Shulker box brightness calibrated against the vanilla atlas, and the boxes now render through the dedicated shulker atlas instead of the block atlas.
- The black box and the lid flicker of the tier shulker boxes.

## [0.beta.4] - 2026-09-10

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.4
Verified: static audit 247/247 and headless 31/31; a follow up whole tree audit fixed thirteen defects in the same cycle.

### Added

- The block of netherite scrap: nine `minecraft:netherite_scrap` to one block and back, mirroring the vanilla netherite block properties, diamond level gating and a texture recoloured from bamboo mosaic.
- Twelve tier shulker boxes from copper to aurelianium, each holding exactly the capacity of the matching barrel and reusing that barrel menu and background.
- Fourteen upgrade recipes that chain like the barrels, with the vanilla shulker box (any of the seventeen plain and coloured forms) as the only entry point.
- Contents survive breaking and upgrading through loot functions and a custom shaped recipe type.
- Vanilla lid animation through a block entity renderer, nesting refused, stack size one and a three dimensional item form.

### Fixed

- Shulker box upgrades keep their contents, the diamond box menu size, the missing mineable tag, nesting in vanilla boxes, the lid and spectator handling, the creative drop, the container identity check, the Just Enough Resources end entry and per ore dimension, the mirrored ore feature rewrite, 49 deprecation warnings, 418 over long lines and 63 missing Javadoc blocks.

## [0.beta.3] - 2026-09-10

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.3
Verified: audits 25/25 and 88 blocks, 170 items, 264 language keys; tag coverage and gating clean, speed ladder 61/61, headless 25/25.

### Added

- The Aurelianium (奥雷利亚尼姆) tier, the End tier, with hero debris (英雄躯骸) generated as scattered ore in two sizes, Gaussian distributed between y 19 and y 60 over end stone.
- Scrap, ingot, block and upgrade template chain, with the template duplication recipe.
- Machines at x50 speed, that is four ticks in a furnace and two ticks in a blast furnace or smoker, upgraded from the matching radiant piece.
- Containers: a single 9x18 chest of 162 slots and a paired 18x18 chest of 324 slots, with the near black panel using light labels.
- Gear smithed from radiant gear, tier 4000 durability, and innate enchantments applied at recipe assembly.
- An anvil with a Silk Touch book swaps the innate Fortune for Silk Touch while keeping durability and every other enchantment.
- Wearing all four armour pieces negates melee, projectile and explosion damage.

### Fixed

- Legacy defects carried over from earlier tiers, plus a duplicate GUI texture removed.

## [0.beta.2] - 2026-09-09

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.2
Verified: audit 80 blocks, 150 items, 232 language keys; 69 gated blocks with no hard coded tool ids; headless 44/44 and 20 mod client smoke clean.

### Added

- The Radiant (耀金) tier: overworld debris (光辉遗骸) that smelts or blasts into scrap, the scrap, ingot and block chain, and the upgrade template with its duplication recipe.
- Machines at x25 speed upgraded from the matching netherite piece, and containers of 135 and 270 slots.
- Gear smithed from netherite gear with innate enchantments baked at recipe assembly by a custom smithing type.
- World generation mirrored block by block around y zero, with two scattered ore shapes.
- Cross mod drop gating: drops are decided by block properties and vanilla tags, so any mod tool of sufficient tier works and the loot tables carry no tool ids.

### Changed

- The creative tab shows the full tier set.
- Ten armour layer textures generated for the mod tiers.

### Fixed

- The innate enchantment set is baked when the recipe is assembled, so the smithing preview matches the taken item.
- The Just Enough Resources writer clamps every distribution point to y 0 to 319.

## [0.beta.1] - 2026-09-09

Release: https://github.com/szkymm/Austenium/releases/tag/v0.beta.1
Verified: the user verified the version in game.

### Added

- The Netherite tier with no new ore, material or equipment: machines at x20 speed and containers of 105 and 210 slots, all built on the vanilla netherite materials.
- Two upgrade paths for all five machine and container pieces, from the diamond tier and from the adamantite tier.
- Smithing recipes that turn adamantite gear into vanilla netherite gear with the vanilla upgrade template and keep the enchantments.

### Fixed

- Forge chest and barrel tags missing adamantite, and the missing adamantite catalysts and information in Just Enough Items.

## [0.alpha.9] - 2026-09-08

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.9
Verified: the user verified world generation, loot tiers, machine speed and container sizes in game.

### Added

- The Adamantite tier: ore and deepslate ore that drop raw adamantite and smelt or blast straight into an ingot, the raw, ingot, nugget, block and raw block chain, machines at x15 speed, containers of 75 and 150 slots, and a full equipment set.
- A custom world generation shape: a trapezoid core between y 5 and y 25 with a plateau and a peak, and a low probability tail up to y 60, mirrored below zero.

### Changed

- Adamantite ore is a recoloured emerald ore, the block a recoloured gold block and the raw block a recoloured end stone.

## [0.alpha.8] - 2026-09-08

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.8
Verified: the user verified the tier in game; the release closed the alpha stage.

### Added

- The Mythril tier: ore and deepslate ore with light level 8 that smelt or blast straight into an ingot, the raw, ingot, nugget and block chain, machines at x12 speed, containers of 70 and 140 slots, and a full equipment set with Protection II armour and Efficiency II tools.
- Dual trapezoid world generation between y 5 and y 45 and between y -45 and y -5.
- Blast furnace and smoker alternatives for the orichalcum and mythril tiers.

### Fixed

- Ore and tag consistency across the earlier tiers.

## [0.alpha.7] - 2026-09-08

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.7
Verified: the user verified the tier in game.

### Added

- The Orichalcum tier: ore and deepslate ore at light level 9 that smelt or blast straight into an ingot, the raw, ingot, nugget and block chain, machines at x10 speed, containers of 63 and 126 slots, and a full equipment set.
- Dual trapezoid world generation with peaks around y 35 and y -35 and rare tails.
- Just Enough Items and Just Enough Resources integration for the tier.

### Changed

- The ore texture is a copper and diamond hybrid in a red bronze theme colour, with theme colour glow baked into the raw material, the ingot, the tools and the armour.

## [0.alpha.6] - 2026-09-08

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.6
Verified: the user verified the tier in game.

### Added

- The Emerald tier: machines at x8 speed and containers of 60 and 120 slots.

### Changed

- Machine recipes overhauled across every tier.
- High tier textures refreshed.

## [0.alpha.5] - 2026-09-08

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.5
Verified: the user verified mining speed, minimum tool drops, chest animation, diamond textures, the copper nugget recipe and the silver ore raw drop in game.

### Added

- The Diamond tier: machines at x6 speed and containers of 50 and 100 slots, with a bright cyan gem skin instead of a metal look.

### Fixed

- Mining drops and minimum tool behaviour.
- Chest animation.

## [0.alpha.4] - 2026-09-07

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.4
Verified: the user verified the tier in game.

### Added

- The Gold tier: machines at x5 speed and containers of 48 and 96 slots.
- A second upgrade path into gold from iron, using four gold blocks around the iron piece.

### Changed

- README, project attribution and file descriptions updated.

## [0.alpha.3] - 2026-09-07

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.3
Verified: the user verified the tier in game.

### Added

- The Silver tier: silver ore and deepslate ore, the raw material chain, machines at x3 speed and containers of 45 and 90 slots.
- Material chains for copper and silver, built as nugget, ingot, nugget, and never as chainmail.
- Copper equipment.

## [0.alpha.2] - 2026-09-07

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.2
Verified: the user verified the tier in game.

### Added

- The Iron tier: machines at x2.5 speed and containers of 40 and 80 slots.

## [0.alpha.1] - 2026-09-07

Release: https://github.com/szkymm/Austenium/releases/tag/v0.alpha.1
Verified: the user verified the tier in game.

### Added

- The Copper tier: machines at x1.25 speed and containers of 36 and 72 slots, the first step of the upgrade chain from vanilla blocks.
- The mod skeleton: one creative tab, the registry layout, the container screens and the Forge mod metadata.

### Changed

- Repository initialised with the licence, the README and the ignore rules.
