/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */
package com.mbb.austenium.jei;

import com.mbb.austenium.MbbAustenium;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JerWorldGenConfig CLASS IS CORE PART OF [MBB] AUSTENIUM JerWorldGenConfig.java.
 *
 * com.mbb.austenium.jei.JerWorldGenConfig:
 *     Writes and extends the JER (Just Enough Resources) DIY worldgen data so the
 *     ore distribution graph appears even on JER builds with the broken
 *     {@code @JERPlugin} scanner (1.4.0.247 Forge used IJERPlugin by mistake).
 *
 * ATTRIBUTES:
 *     DIMENSION (String): Target dimension id shared by every entry.
 *     ORE_DEFINITIONS (String[][]): Block id, distribution points and raw drop id per ore.
 *
 * PUBLIC METHODS:
 *     ensure() -> void:
 *         Creates config/world-gen.json when missing and appends entries that are
 *         not present yet, so mod updates reach existing installations.
 *
 * USAGE:
 *     Call ensure() once during client setup; repeated calls stay idempotent.
 */
public final class JerWorldGenConfig {

    private static final String DIMENSION = "minecraft:overworld";

    // Per-material ore definitions: block id, distribution points, raw drop id.
    private static final String[][] ORE_DEFINITIONS = {
        {"orichalcum_ore", "5,0;20,0.2;35,1.0;50,0.2;65,0;-65,0;-50,0.2;-35,1.0;-20,0.2;-5,0", "raw_orichalcum"},
        {"deepslate_orichalcum_ore", "5,0;20,0.2;35,1.0;50,0.2;65,0;-65,0;-50,0.2;-35,1.0;-20,0.2;-5,0", "raw_orichalcum"},
        {"mythril_ore", "5,0;15,0.5;25,1.0;35,0.5;45,0;-45,0;-35,0.5;-25,1.0;-15,0.5;-5,0", "raw_mythril"},
        {"deepslate_mythril_ore", "5,0;15,0.5;25,1.0;35,0.5;45,0;-45,0;-35,0.5;-25,1.0;-15,0.5;-5,0", "raw_mythril"},
        {"adamantite_ore", "5,0;11,1.0;19,1.0;25,0.3;45,0.1;60,0;-60,0;-45,0.1;-25,0.3;-19,1.0;-11,1.0;-5,0", "raw_adamantite"},
        {"deepslate_adamantite_ore", "5,0;11,1.0;19,1.0;25,0.3;45,0.1;60,0;-60,0;-45,0.1;-25,0.3;-19,1.0;-11,1.0;-5,0", "raw_adamantite"},
        {"silver_ore", "-24,0;0,0.4;16,0.7;56,0.1;80,0;200,0.3;384,0.05", "raw_silver"},
        {"deepslate_silver_ore", "-24,0;0,0.4;16,0.7;56,0.1;80,0;200,0.3;384,0.05", "raw_silver"},
    };

    private JerWorldGenConfig() {}

    /**
     * Creates or extends config/world-gen.json with every missing ore entry.
     */
    public static void ensure() {
        Path configDirectory = FMLPaths.CONFIGDIR.get();
        Path file = configDirectory.resolve("world-gen.json");
        try {
            Files.createDirectories(configDirectory);
            JsonArray entries = readEntries(file);
            int addedCount = 0;
            // Append only entries whose block id is still absent from the file.
            for (String[] definition : ORE_DEFINITIONS) {
                String blockId = MbbAustenium.MOD_ID + ":" + definition[0];
                if (!containsBlock(entries, blockId)) {
                    entries.add(buildEntry(definition[0], definition[1], definition[2]));
                    addedCount++;
                }
            }
            if (addedCount == 0) {
                return;
            }
            String json = new GsonBuilder().setPrettyPrinting().create().toJson(entries);
            Files.writeString(file, json);
            String messageInfo = "Wrote " + addedCount + " missing JER world-gen.json entries.";
            MbbAustenium.LOGGER.info(messageInfo);
        } catch (IOException ioError) {
            String messageError = "Could not write JER world-gen.json: " + ioError.toString();
            MbbAustenium.LOGGER.warn(messageError);
        } catch (RuntimeException parseError) {
            String messageError = "Could not update JER world-gen.json: " + parseError.toString();
            MbbAustenium.LOGGER.warn(messageError);
        }
    }

    /**
     * Reads the existing entry array; a missing or broken file starts a fresh array.
     *
     * @param file path of the JER configuration file
     * @return parsed entry array, empty when the file is absent or malformed
     * @throws IOException when the file exists but cannot be read
     */
    private static JsonArray readEntries(Path file) throws IOException {
        if (!Files.exists(file)) {
            return new JsonArray();
        }
        String text = Files.readString(file);
        try {
            return JsonParser.parseString(text).getAsJsonArray();
        } catch (RuntimeException parseError) {
            String messageWarn = "Replacing malformed JER world-gen.json: " + parseError.toString();
            MbbAustenium.LOGGER.warn(messageWarn);
            return new JsonArray();
        }
    }

    /**
     * Reports whether the entry array already describes the given block id.
     *
     * @param entries parsed JER entry array
     * @param blockId fully qualified block id to look for
     * @return true when an entry already targets that block
     */
    private static boolean containsBlock(JsonArray entries, String blockId) {
        // Walk every entry and compare its block field against the wanted id.
        for (int index = 0; index < entries.size(); index++) {
            JsonObject entry = entries.get(index).getAsJsonObject();
            if (entry.has("block") && blockId.equals(entry.get("block").getAsString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds one JER ore entry with its single raw drop.
     *
     * @param block ore block id without the namespace
     * @param distribution JER distribution point string
     * @param drop raw material item id without the namespace
     * @return complete JER entry object
     */
    private static JsonObject buildEntry(String block, String distribution, String drop) {
        JsonObject entry = new JsonObject();
        entry.addProperty("mod", MbbAustenium.MOD_ID);
        entry.addProperty("block", MbbAustenium.MOD_ID + ":" + block);
        entry.addProperty("distrib", distribution);
        entry.addProperty("dim", DIMENSION);
        entry.addProperty("silktouch", false);
        JsonObject dropEntry = new JsonObject();
        dropEntry.addProperty("itemStack", MbbAustenium.MOD_ID + ":" + drop);
        JsonObject fortunes = new JsonObject();
        fortunes.addProperty("0", 1.0);
        dropEntry.add("fortunes", fortunes);
        JsonArray drops = new JsonArray();
        drops.add(dropEntry);
        entry.add("dropsList", drops);
        return entry;
    }
}
