<!--
  Standard Release Template for [MBB] Austenium.
  Structure: English section first, then the same content in Chinese.
  Version scheme: stable = MAJOR.MINOR.PATCH (0.0.1);
  prerelease = <Minor>.<stage>.<N> (alpha/beta/rc), rc sequence continuous within a Minor.
-->

# Release v{{VERSION}} — {{RELEASE_NAME}}

> **Stage:** {{alpha | beta | rc | stable}}
> **Minecraft:** 1.20.1 &nbsp;|&nbsp; **Forge:** 47.3.0 &nbsp;|&nbsp; **Java:** 17
> **License:** GPL-3.0-only

## Release Checklist (all must be checked before publishing)

- [ ] Version is consistent in all three places: `gradle.properties` (mod_version), `mods.toml` (version), `MbbAustenium.java` (VERSION)
- [ ] `./gradlew build` succeeds (BUILD SUCCESSFUL)
- [ ] Artifacts named: `[MBB]Austenium_{{VERSION}}.jar` and `[MBB]Austenium_{{VERSION}}_source.jar`
- [ ] Cold start without crash logs (no ModLauncher/Registry exceptions on client)
- [ ] Starts cleanly with the latest JEI installed (optional dependency [15,) )
- [ ] Core regression passes (see Test Checklist below)
- [ ] en_us and zh_cn language keys are complete
- [ ] `git tag v{{VERSION}}` points to the release commit; both jars are attached to the Release
- [ ] CHANGELOG updated in Keep a Changelog sections (this file is the source)

## Test Checklist

- [ ] Copper furnace / blast furnace / smoker: ×1.25 speed (product and fuel) works
- [ ] Copper barrel 36, copper chest 36, double chest 72 (title "Large Copper Chest")
- [ ] All three container GUIs: themed background aligned 1:1 with slots, 9-column centered player area, no transparency/scaling artifacts
- [ ] 3D copper chest render: copper textures, lid open/close animation, left/right double-chest textures
- [ ] Copper furnace series: lit/unlit blockstate switching
- [ ] Crafting recipes (7) + Forge tags (chests/barrels) + loot tables
- [ ] JEI: container/machine items visible and categorized correctly
- [ ] Tested in both a new world and an existing world

## Changes (Changelog)

### Added
- {{...}}

### Changed
- {{...}}

### Fixed
- {{...}}

### Removed
- {{...}}

### Deprecated
- {{...}}

### Security
- {{...}}

## Notes

- Compatibility: required on both client and server; on a dedicated server the client does not need it
- Known issues: {{none | ...}}
- Credits: {{contributors / testers}}

---

# Release v{{VERSION}} — {{RELEASE_NAME}}

> **阶段：** {{alpha | beta | rc | stable}}
> **Minecraft：** 1.20.1 &nbsp;|&nbsp; **Forge：** 47.3.0 &nbsp;|&nbsp; **Java：** 17
> **许可证：** GPL-3.0-only

## 发布检查清单（全部勾选后发布）

- [ ] 版本号三处一致：`gradle.properties`(mod_version)、`mods.toml`(version)、`MbbAustenium.java`(VERSION)
- [ ] `./gradlew build` 成功（BUILD SUCCESSFUL）
- [ ] 产物命名：`[MBB]Austenium_{{VERSION}}.jar` 与 `[MBB]Austenium_{{VERSION}}_source.jar`
- [ ] 冷启动无崩溃日志（客户端无 ModLauncher/Registry 异常）
- [ ] 已安装最新 JEI 启动无警告（可选依赖 [15,)）
- [ ] 核心回归通过（见下方测试清单）
- [ ] en_us / zh_cn 语言键全覆盖
- [ ] `git tag v{{VERSION}}` 指向发布提交，Release 已挂载两个 jar 附件
- [ ] CHANGELOG 按 Keep a Changelog 分节更新（本文件为来源）

## 测试清单

- [ ] 铜熔炉/高炉/烟熏炉：速度 ×1.25（产物+燃料）均生效
- [ ] 铜木桶 36、铜箱子 36、双箱合并 72（标题为「大型铜箱子」）
- [ ] 三种容器 GUI：主题背景与格子 1:1 对齐、玩家区 9 列居中、无透明/缩放异常
- [ ] 3D 铜箱子渲染：铜色贴图、盖子开合动画、左/右双箱纹理
- [ ] 铜熔炉系列：火苗/点燃状态方块模型切换
- [ ] 配方合成（7 条）+ Forge 标签（chests/barrels）+ 战利品表
- [ ] JEI：容器/机器相关物品可见、分类正常
- [ ] 新世界 + 旧世界存档各测一次

## 变更（Changelog）

### Added（新增）
- {{...}}

### Changed（变更）
- {{...}}

### Fixed（修复）
- {{...}}

### Removed（移除）
- {{...}}

### Deprecated（弃用）
- {{...}}

### Security（安全）
- {{...}}

## 附注

- 兼容性：客户端与服务端均需安装；专用服务端下客户端无需安装
- 已知问题：{{无 | ...}}
- 鸣谢：{{贡献者 / 测试人员}}

---
*Template: Keep a Changelog 1.1.0 / GitHub Release checklist — adapted for [MBB] Austenium.*
