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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

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
 *     ORE_DEFINITIONS (String[][]): Block id, distribution points, drop id and dimension per ore.
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

    private static final String OVERWORLD_DIMENSION = "minecraft:overworld";
    private static final String END_DIMENSION = "minecraft:the_end";
    private static final int MAXIMUM_DISTRIBUTION_Y = 319;

    // Per-material ore definitions: block id, distribution points, drop id, dimension.
    private static final String[][] ORE_DEFINITIONS = {
        {"orichalcum_ore", "5,0;20,0.2;35,1.0;50,0.2;65,0;"
            + "-65,0;-50,0.2;-35,1.0;-20,0.2;-5,0", "raw_orichalcum", OVERWORLD_DIMENSION},
        {"deepslate_orichalcum_ore", "5,0;20,0.2;35,1.0;50,0.2;65,0;"
            + "-65,0;-50,0.2;-35,1.0;-20,0.2;-5,0", "raw_orichalcum", OVERWORLD_DIMENSION},
        {"mythril_ore", "5,0;15,0.5;25,1.0;35,0.5;45,0;"
            + "-45,0;-35,0.5;-25,1.0;-15,0.5;-5,0", "raw_mythril", OVERWORLD_DIMENSION},
        {"deepslate_mythril_ore", "5,0;15,0.5;25,1.0;35,0.5;45,0;"
            + "-45,0;-35,0.5;-25,1.0;-15,0.5;-5,0", "raw_mythril", OVERWORLD_DIMENSION},
        {"adamantite_ore", "5,0;11,1.0;19,1.0;25,0.3;45,0.1;60,0;"
            + "-60,0;-45,0.1;-25,0.3;-19,1.0;-11,1.0;-5,0", "raw_adamantite", OVERWORLD_DIMENSION},
        {"deepslate_adamantite_ore", "5,0;11,1.0;19,1.0;25,0.3;45,0.1;60,0;"
            + "-60,0;-45,0.1;-25,0.3;-19,1.0;-11,1.0;-5,0", "raw_adamantite", OVERWORLD_DIMENSION},
        {"silver_ore", "-24,0;0,0.4;16,0.7;56,0.1;80,0;200,0.3;384,0.05",
            "raw_silver", OVERWORLD_DIMENSION},
        {"deepslate_silver_ore", "-24,0;0,0.4;16,0.7;56,0.1;80,0;200,0.3;384,0.05",
            "raw_silver", OVERWORLD_DIMENSION},
        {"radiant_debris", "-64,0.05;-40,0.05;-24,0.15;-16,1.0;-8,0.15;0,0;"
            + "8,0.15;16,1.0;24,0.15;40,0.05;64,0.05", "radiant_scrap", OVERWORLD_DIMENSION},
        // The hero debris only generates in the End, so its points follow the same Gaussian bands.
        {"aurelianium_debris", "19,0;22,0.05;28,0.3;34,0.75;40,1.0;"
            + "46,0.75;52,0.3;58,0.05;60,0", "aurelianium_scrap", END_DIMENSION},
    };

    private JerWorldGenConfig() {}

    /**
     * Creates or extends config/world-gen.json and repairs entries JER cannot plot.
     */
    public static void ensure() {
        Path configDirectory = FMLPaths.CONFIGDIR.get();
        Path file = configDirectory.resolve("world-gen.json");
        try {
            Files.createDirectories(configDirectory);
            JsonArray entries = readEntries(file);
            int changedCount = 0;
            // Add missing ores and rewrite any entry whose points JER would reject.
            for (String[] definition : ORE_DEFINITIONS) {
                String blockId = MbbAustenium.MOD_ID + ":" + definition[0];
                String distribution = sanitizeDistribution(definition[1]);
                String dimension = definition[3];
                JsonObject existing = findEntry(entries, blockId);
                if (existing == null) {
                    entries.add(buildEntry(definition[0], distribution, definition[2], dimension));
                    changedCount++;
                    continue;
                }
                JsonElement existingDistribution = existing.get("distrib");
                JsonElement existingDimension = existing.get("dim");
                // Repair entries that lost a field as well, so one broken entry cannot stop the rest.
                boolean isDistributionStale = existingDistribution == null
                    || !distribution.equals(existingDistribution.getAsString());
                boolean isDimensionStale = existingDimension == null
                    || !dimension.equals(existingDimension.getAsString());
                if (isDistributionStale || isDimensionStale) {
                    existing.addProperty("distrib", distribution);
                    existing.addProperty("dim", dimension);
                    changedCount++;
                }
            }
            if (changedCount == 0) {
                return;
            }
            String json = new GsonBuilder().setPrettyPrinting().create().toJson(entries);
            Files.writeString(file, json);
            String messageInfo = "Wrote " + changedCount + " JER world-gen.json entries.";
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
     * Keeps only the y values JER can plot and normalises the point order.
     *
     * <p>JER indexes its distribution array by the raw y value, so negative
     * points (and anything above {@link #MAXIMUM_DISTRIBUTION_Y}) abort the
     * whole DIY data load. Negative points are dropped because the graph has
     * no room for them.</p>
     *
     * @param points semicolon separated y,value pairs
     * @return rebuilt point string containing only plottable y values
     */
    private static String sanitizeDistribution(String points) {
        Map<Integer, Float> values = new TreeMap<>();
        // Parse each pair, drop negative y and clamp the top of the world.
        for (String point : points.split(";")) {
            String[] parts = point.split(",");
            if (parts.length != 2) {
                continue;
            }
            int y;
            float value;
            try {
                y = Integer.parseInt(parts[0].trim());
                value = Float.parseFloat(parts[1].trim());
            } catch (NumberFormatException numberError) {
                continue;
            }
            if (y < 0) {
                continue;
            }
            if (y > MAXIMUM_DISTRIBUTION_Y) {
                y = MAXIMUM_DISTRIBUTION_Y;
            }
            values.merge(y, value, Math::max);
        }
        StringBuilder builder = new StringBuilder();
        // Emit ascending y so JER draws a monotonic curve.
        for (Map.Entry<Integer, Float> entry : values.entrySet()) {
            if (builder.length() > 0) {
                builder.append(';');
            }
            builder.append(entry.getKey()).append(',').append(entry.getValue());
        }
        return builder.toString();
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
     * Finds the entry that already describes the given block id.
     *
     * @param entries parsed JER entry array
     * @param blockId fully qualified block id to look for
     * @return the matching entry, or null when the block is absent
     */
    private static JsonObject findEntry(JsonArray entries, String blockId) {
        // Walk every entry and compare its block field against the wanted id.
        for (int index = 0; index < entries.size(); index++) {
            JsonObject entry = entries.get(index).getAsJsonObject();
            if (entry.has("block") && blockId.equals(entry.get("block").getAsString())) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Builds one JER ore entry with its single raw drop.
     *
     * @param block ore block id without the namespace
     * @param distribution JER distribution point string
     * @param drop raw material item id without the namespace
     * @param dimension dimension id the ore generates in
     * @return complete JER entry object
     */
    private static JsonObject buildEntry(String block, String distribution, String drop, String dimension) {
        JsonObject entry = new JsonObject();
        entry.addProperty("mod", MbbAustenium.MOD_ID);
        entry.addProperty("block", MbbAustenium.MOD_ID + ":" + block);
        entry.addProperty("distrib", distribution);
        entry.addProperty("dim", dimension);
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
