package de.xtkq.voidgen.generator.interfaces;

import de.xtkq.voidgen.generator.settings.ChunkGenSettings;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

abstract class ChunkGen extends ChunkGenerator {

    protected ChunkGenSettings chunkGenSettings;

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0d, 64d, 0d);
    }

    @Override
    public boolean shouldGenerateCaves() {
        return this.chunkGenSettings.isCaves();
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return this.chunkGenSettings.isDecoration();
    }

    @Override
    public boolean shouldGenerateMobs() {
        return this.chunkGenSettings.isMobs();
    }

    @Override
    public boolean shouldGenerateStructures() {
        return this.chunkGenSettings.isStructures();
    }

    @Override
    public boolean isParallelCapable() {
        return true;
    }

    protected void placeBedrock(ChunkData paramChunkData, int paramChunkX, int paramChunkZ) {
        // Bedrock block position
        int x = 0, y = 64, z = 0;

        if ((x >= paramChunkX * 16) && (x < (paramChunkX + 1) * 16)) {
            if ((z >= paramChunkZ * 16) && (z < (paramChunkZ + 1) * 16)) {
                paramChunkData.setBlock(x, y, z, Material.BEDROCK);
            }
        }
    }

    protected void placeBedrock(byte[][] result, int paramChunkX, int paramChunkZ) {
        // Bedrock block position
        int x = 0, y = 64, z = 0;

        if ((x >= paramChunkX * 16) && (x < (paramChunkX + 1) * 16)) {
            if ((z >= paramChunkZ * 16) && (z < (paramChunkZ + 1) * 16)) {
                if (result[y >> 4] == null) {
                    result[y >> 4] = new byte[4096];
                }
                result[y >> 4][(0) | x] = (byte) 7;
            }
        }
    }
}
