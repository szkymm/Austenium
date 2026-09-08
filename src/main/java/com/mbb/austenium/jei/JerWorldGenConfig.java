/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jei;

import com.mbb.austenium.MbbAustenium;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes JER (Just Enough Resources) DIY worldgen data (config/world-gen.json)
 * so the ore distribution graph appears even on JER builds with the broken
 * {@code @JERPlugin} scanner (1.4.0.247 Forge used IJERPlugin by mistake).
 */
public final class JerWorldGenConfig {

    private JerWorldGenConfig() {}

    public static void ensure() {
        try {
            Path configDir = FMLPaths.CONFIGDIR.get();
            Path file = configDir.resolve("world-gen.json");
            if (Files.exists(file)) {
                return;
            }
            Files.createDirectories(configDir);
            String json = """
[
  {
    "mod": "mbb_austenium",
    "block": "mbb_austenium:orichalcum_ore",
    "distrib": "5,0;20,0.2;35,1.0;50,0.2;65,0;-65,0;-50,0.2;-35,1.0;-20,0.2;-5,0",
    "dim": "minecraft:overworld",
    "silktouch": false,
    "dropsList": [ { "itemStack": "mbb_austenium:raw_orichalcum", "fortunes": { "0": 1.0 } } ]
  },
  {
    "mod": "mbb_austenium",
    "block": "mbb_austenium:deepslate_orichalcum_ore",
    "distrib": "5,0;20,0.2;35,1.0;50,0.2;65,0;-65,0;-50,0.2;-35,1.0;-20,0.2;-5,0",
    "dim": "minecraft:overworld",
    "silktouch": false,
    "dropsList": [ { "itemStack": "mbb_austenium:raw_orichalcum", "fortunes": { "0": 1.0 } } ]
  },
  {
    "mod": "mbb_austenium",
    "block": "mbb_austenium:silver_ore",
    "distrib": "-24,0;0,0.4;16,0.7;56,0.1;80,0;200,0.3;384,0.05",
    "dim": "minecraft:overworld",
    "silktouch": false,
    "dropsList": [ { "itemStack": "mbb_austenium:raw_silver", "fortunes": { "0": 1.0 } } ]
  },
  {
    "mod": "mbb_austenium",
    "block": "mbb_austenium:deepslate_silver_ore",
    "distrib": "-24,0;0,0.4;16,0.7;56,0.1;80,0;200,0.3;384,0.05",
    "dim": "minecraft:overworld",
    "silktouch": false,
    "dropsList": [ { "itemStack": "mbb_austenium:raw_silver", "fortunes": { "0": 1.0 } } ]
  }
]
""";
            Files.writeString(file, json);
            MbbAustenium.LOGGER.info("Wrote JER world-gen.json for orichalcum/silver ore distribution.");
        } catch (IOException error) {
            MbbAustenium.LOGGER.warn("Could not write JER world-gen.json: {}", error.toString());
        }
    }
}
