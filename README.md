# [MBB] Austenium

<img src="docs/images/logo.png" alt="[MBB] Austenium" width="160">

A Minecraft Forge 1.20.1 survival expansion: twelve upgrade tiers from copper to aurelianium, each with its own machines, storage, fuel and gear, plus the Austeniumcraft World mining dimension.

**English** | [中文](README.zh-cn.md)

[![License](https://img.shields.io/badge/license-GPL--3.0--only-blue.svg)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green.svg)](https://www.minecraft.net/)
[![Forge](https://img.shields.io/badge/Forge-47.4%2B-orange.svg)](https://files.minecraftforge.net/)
[![Release](https://img.shields.io/github/v/release/szkymm/Austenium?include_prereleases&label=release)](https://github.com/szkymm/Austenium/releases)
[![Languages](https://img.shields.io/badge/languages-13-blueviolet.svg)](#supported-languages)
[![Issues](https://img.shields.io/github/issues/szkymm/Austenium)](https://github.com/szkymm/Austenium/issues)
[![Downloads](https://img.shields.io/github/downloads/szkymm/Austenium/total)](https://github.com/szkymm/Austenium/releases)

- **Authored by:** Suzuki Yumemi <szkymm@gmail.com>
- **Co-authored by:** Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
- **Maintainer:** Matt Belfast Brown (MBB)
- **License:** GPL-3.0-only

**Current version:** 0.0.1, the first stable release.

## Features

- **Twelve-tier production line.** Furnaces, blast furnaces and smokers from copper to aurelianium, running at x1.25 up to x50 vanilla speed on both fuel and product.
- **Storage and logistics that scale.** Chests and barrels up to 324 slots, twelve chained shulker boxes that keep their contents, and twelve tiered hoppers from 3.3 to 100 items per second.
- **Sixteen kinds of coal.** Twelve tier coals and four dimension coals, ten coal ores on five host rocks, and fuel values up to 8000 items per block.
- **The Austeniumcraft World.** A layered mining dimension from y -64 to y 383 with compressed ores, its own mobs and a portal built from the twelve tier storage blocks.
- **Gear that grows with you.** Every tier has tools and armour; the radiant and aurelianium sets carry innate enchantments, and a full aurelianium set negates melee, projectile and explosion damage.
- **Thirteen languages and full information mod support.** Every client string is translatable, and JEI, Jade and JER describe recipes, machines, ores and fuel in game.

![Twelve-tier production line](docs/images/production_line.png) ![Storage and logistics](docs/images/storage_logistics.png) ![The Austeniumcraft World](docs/images/austeniumcraft_world.png)

## Supported Languages

| Family | Locales |
|---|---|
| English | English (US) `en_us`, English (UK) `en_gb`, English (Australia) `en_au` |
| Chinese | 简体中文 `zh_cn`, 繁體中文（香港）`zh_hk`, 繁體中文（台灣）`zh_tw`, 文言 `lzh` |
| European | Français `fr_fr`, Español `es_es`, Italiano `it_it`, Русский `ru_ru` |
| East Asian | 日本語 `ja_jp`, 한국어 `ko_kr` |

![Thirteen languages in game](docs/images/languages.png)

## Installation

1. Install Minecraft 1.20.1 with Forge 47.4 or newer (Java 17).
2. Drop the release jar into the `mods` folder of your profile.
3. Optional: add JEI, Jade or JER for in-game recipe and block information.

## Version and Download Naming

The functional version is `A.B.C`: stable releases use `MAJOR.MINOR.PATCH`, prereleases use `<Minor>.<stage>.<N>` with the stage `alpha`, `beta` or `rc`.

When one functional version ships for more than one platform, a platform suffix is appended and the file name becomes `A.B.C_D.E.F`: `D` is the Minecraft version group, `E` the loader feature line inside that group and `F` the release counter for that platform.

The current platform is `0L.5.0`: the 1.20 to 1.20.1 group on the Forge 47.4 line, first release. While a version ships for a single platform, its file name stays the plain `A.B.C`.

## Limitations

0.0.1 ships a single package and runs only where it declares:

- **Minecraft 1.20.1 with the Forge 47.4 line**, every build from 47.4.0 to 47.4.23. The jar was booted on Forge 47.4.0, 47.4.10 and 47.4.23 servers; all three reached `Done` with `mbb_austenium v0.0.1 initialised.` and no linkage error.
- **Unsupported platforms are refused, not half loaded.** Forge 47.0 to 47.3 and Minecraft 1.20 miss APIs this source calls (`ResourceLocation.fromNamespaceAndPath`, `withDefaultNamespace`, `MobSpawnEvent.PositionCheck`), so the code does not build for them and the declared version ranges keep the jar out.
- **Minecraft 1.20.2 and later need a port**, and 1.20.6 and later cannot be built with the current toolchain at all.

Wider coverage is planned work, described below. It is not a setting that can be switched on.

## Roadmap

Planned platforms, in order. The numbers are the plan, not released versions.

| Version | Target |
|---|---|
| `0.0.2` | The whole Forge 47 line: replace the mapping-only `ResourceLocation` helpers with the constructor form, so one jar covers Forge 47.0 through 47.4 instead of 47.4 alone. |
| `0.0.3` | Minecraft 1.20 to 1.20.1 complete: add the spawn hook the 1.20 loader provides, so the mod also runs on the Forge 46 line. |
| `0.1.0` | Minecraft 1.20 to 1.20.3: a port, covering the registry and data pack format changes between 1.20.1 and 1.20.3. |
| `0.2.0` | Minecraft 1.19.4 to 1.20.3: extend that port downwards to 1.19.4. |

## Links

- [Releases](https://github.com/szkymm/Austenium/releases)
- [Changelog](docs/CHANGELOG.md)
- [Issues](https://github.com/szkymm/Austenium/issues)
- [Content guide](docs/content.md)
- [License](LICENSE)
