package de.xtkq.voidgen.generator.settings;

import org.bukkit.block.Biome;

public class ChunkGenSettings {

    private Biome biome = null;
    private boolean caves = false;
    private boolean decoration = false;
    private boolean mobs = false;
    private boolean structures = false;

    public Biome getBiome() {
        return this.biome;
    }

    public boolean isCaves() {
        return this.caves;
    }

    public boolean isDecoration() {
        return this.decoration;
    }

    public boolean isMobs() {
        return this.mobs;
    }

    public boolean isStructures() {
        return this.structures;
    }
}
