package de.xtkq.voidgen.generator.interfaces;

public abstract class ChunkGen2D extends ChunkGen implements IChunkGenBiomeGrid {

    @Override
    @Deprecated
    public void setBiomeGrid(BiomeGrid paramBiomeGrid, ChunkData paramChunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                paramBiomeGrid.setBiome(x, z, this.chunkGenSettings.getBiome());
            }
        }
    }
}
