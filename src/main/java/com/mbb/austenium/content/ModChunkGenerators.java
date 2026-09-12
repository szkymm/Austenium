/**
 * THIS FILE IS CORE PART OF [MBB] AUSTENIUM BY Matt Belfast Brown (MBB).
 *
 * MODULE: com.mbb.austenium.content.ModChunkGenerators
 * TYPE: Java Source
 * DESCRIPTION: Chunk generator type registry for [MBB] Austenium world generation.
 * LICENSE: GPL-3.0-only (SPDX: GPL-3.0-only)
 * AUTHOR: Suzuki Yumemi
 * CONTACT: szkymm@gmail.com
 * MAINTAINER: Matt Belfast Brown (MBB) <thedayofthedo@gmail.com>
 */

package com.mbb.austenium.content;

import com.mbb.austenium.MbbAustenium;
import com.mbb.austenium.worldgen.AwChunkGenerator;

import com.mojang.serialization.Codec;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * MODCHUNKGENERATORS CLASS IS CORE PART OF [MBB] AUSTENIUM ModChunkGenerators.java.
 *
 * com.mbb.austenium.content.ModChunkGenerators:
 *     DeferredRegister holder for the chunk generator codecs of the mod. The registry
 *     key lives in the vanilla CHUNK_GENERATOR registry, so the dimensions that
 *     reference these codecs can be declared in the mod's own data pack.
 *
 * ATTRIBUTES:
 *     CHUNK_GENERATORS (DeferredRegister<Codec<? extends ChunkGenerator>>): The register itself.
 *     AW (RegistryObject<Codec<? extends ChunkGenerator>>): The Austeniumcraft World generator codec.
 */
public final class ModChunkGenerators {

    private ModChunkGenerators() {}

    public static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS
        = DeferredRegister.create(Registries.CHUNK_GENERATOR, MbbAustenium.MOD_ID);

    public static final RegistryObject<Codec<? extends ChunkGenerator>> AW
        = CHUNK_GENERATORS.register("aw", () -> AwChunkGenerator.CODEC);
}
