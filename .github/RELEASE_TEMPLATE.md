<!--
  Standard Release Template for [MBB] Austenium.
  Structure: English section first, then the same content in Chinese.
  Version scheme: stable = MAJOR.MINOR.PATCH (0.0.1);
  prerelease = <Minor>.<stage>.<N> (alpha/beta/rc), rc sequence continuous within a Minor.
-->

# Release v{{VERSION}} — {{RELEASE_NAME}}

> **Stage:** {{alpha | beta | rc | stable}}
> **Minecraft:** 1.20.1 &nbsp;|&nbsp; **Forge:** 47.4.10 &nbsp;|&nbsp; **Java:** 17
> **License:** GPL-3.0-only

## Change Log (this release)

- GitHub auto-generated commit list (本次变更清单):
- `https://github.com/<owner>/Austenium/releases/tag/v{{VERSION}}`

## Release Checklist (all must be checked before publishing)

- [ ] Version is consistent in all four places: `gradle.properties` (mod_version), `mods.toml` (version), `MbbAustenium.java` (VERSION), `project.toml` (current_version)
- [ ] `./gradlew build` succeeds (BUILD SUCCESSFUL)
- [ ] Artifacts named: `[MBB]Austenium_{{VERSION}}.jar` and `[MBB]Austenium_{{VERSION}}_source.jar`
- [ ] Cold start without crash logs (no ModLauncher/Registry exceptions on client)
- [ ] Starts cleanly with the latest JEI installed (optional dependency [15,) )
- [ ] Core regression passes (see Test Checklist below)
- [ ] Every locale carries the same key set as en_us (13 locales at 0.rc.2)
- [ ] Release notes body reviewed in-session by the user before tagging
- [ ] `git tag v{{VERSION}}` points to the release commit; both jars are attached to the Release
- [ ] `docs/CHANGELOG.md` updated in Keep a Changelog sections (this file is the source)

## Test Checklist

- [ ] Tier machines run at the documented multiplier on product and fuel (copper x1.25 ... aurelianium x50)
- [ ] Tier barrels and chests match the documented sizes (copper 36 ... aurelianium 162); a paired chest doubles the grid
- [ ] Container GUIs: themed background aligned 1:1 with slots, centred player area, no transparency or scaling artifacts
- [ ] Tier chest 3D render: tier textures, lid open/close animation, left and right paired textures
- [ ] Tier machine series: lit and unlit blockstate switching
- [ ] Crafting recipes, Forge tags and loot tables all resolve
- [ ] Coal family: sixteen coals, ten coal ores and their burn values (headless suite)
- [ ] Austeniumcraft World: four bands, ores and the portal round trip
- [ ] JEI: recipes and info pages render; Jade shows tier, speed, fuel and band data; JER plots the coal ores
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

## Call for Testing

Outside testers are welcome. Please use the issue templates so reports land with the version, environment and reproduction steps:

- Bug report: https://github.com/<owner>/Austenium/issues/new?template=bug_report.yml
- Feature request: https://github.com/<owner>/Austenium/issues/new?template=feature_request.yml

## Notes

- Compatibility: required on both client and server; on a dedicated server the client does not need it
- Known issues: {{none | ...}}
- Credits: {{contributors / testers}}

---

# Release v{{VERSION}} — {{RELEASE_NAME}}

> **阶段：** {{alpha | beta | rc | stable}}
> **Minecraft：** 1.20.1 &nbsp;|&nbsp; **Forge：** 47.4.10 &nbsp;|&nbsp; **Java：** 17
> **许可证：** GPL-3.0-only

## 本次变更清单（Change Log）

- GitHub 自动生成的提交列表：
- `https://github.com/<owner>/Austenium/releases/tag/v{{VERSION}}`

## 发布检查清单（全部勾选后发布）

- [ ] 版本号四处一致：`gradle.properties`(mod_version)、`mods.toml`(version)、`MbbAustenium.java`(VERSION)、`project.toml`(current_version)
- [ ] `./gradlew build` 成功（BUILD SUCCESSFUL）
- [ ] 产物命名：`[MBB]Austenium_{{VERSION}}.jar` 与 `[MBB]Austenium_{{VERSION}}_source.jar`
- [ ] 冷启动无崩溃日志（客户端无 ModLauncher/Registry 异常）
- [ ] 已安装最新 JEI 启动无警告（可选依赖 [15,)）
- [ ] 核心回归通过（见下方测试清单）
- [ ] 各语种键集与 en_us 完全一致（0.rc.2 为 13 个语种）
- [ ] 打 tag 前，Release 正文已在会话中由用户确认
- [ ] `git tag v{{VERSION}}` 指向发布提交，Release 已挂载两个 jar 附件
- [ ] `docs/CHANGELOG.md` 按 Keep a Changelog 分节更新（本文件为来源）

## 测试清单

- [ ] 各档机器速度按文档生效（铜 ×1.25 至奥雷利亚尼姆 ×50，产物与燃料同速）
- [ ] 各档木桶与箱子容量符合文档（铜 36 至奥雷利亚尼姆 162）；并排箱子容量翻倍
- [ ] 容器 GUI：主题背景与格子 1:1 对齐、玩家区居中、无透明或缩放异常
- [ ] 各档箱子 3D 渲染：档位贴图、盖子开合动画、左右并排纹理
- [ ] 各档机器：点燃与未点燃状态方块模型切换
- [ ] 配方合成、Forge 标签与战利品表全部可解析
- [ ] 煤炭族：十六种煤炭、十种煤矿与燃烧值（headless 套件）
- [ ] 奥氏挖矿维度：四个岩带、矿物与传送门往返
- [ ] JEI：配方页与信息页正常；Jade 显示档位/速度/燃料/岩带；JER 标出煤矿分布
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

## 测试征集

欢迎外部测试者参与。请使用 issue 模板提交，报告里会带上版本、环境与复现步骤：

- 缺陷报告：https://github.com/<owner>/Austenium/issues/new?template=bug_report.yml
- 功能建议：https://github.com/<owner>/Austenium/issues/new?template=feature_request.yml

## 附注

- 兼容性：客户端与服务端均需安装；专用服务端下客户端无需安装
- 已知问题：{{无 | ...}}
- 鸣谢：{{贡献者 / 测试人员}}

---
*Template: Keep a Changelog 1.1.0 / GitHub Release checklist — adapted for [MBB] Austenium.*
