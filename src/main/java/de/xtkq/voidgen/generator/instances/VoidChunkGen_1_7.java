package de.xtkq.voidgen.generator.instances;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import de.xtkq.voidgen.VoidGen;
import de.xtkq.voidgen.generator.annotations.VoidChunkGenInfo;
import de.xtkq.voidgen.generator.interfaces.ChunkGen2D;
import de.xtkq.voidgen.generator.settings.ChunkGenSettings;
import org.apache.commons.lang.StringUtils;
import org.bukkit.World;

import java.util.Objects;
import java.util.Random;

@VoidChunkGenInfo(versions = {"1.7.2", "1.7.5", "1.7.8", "1.7.10", "1.8", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7"})
public class VoidChunkGen_1_7 extends ChunkGen2D {

    public VoidChunkGen_1_7(String paramIdentifier) {
        Gson gson = new Gson();

        if (StringUtils.isBlank(paramIdentifier)) {
            this.chunkGenSettings = new ChunkGenSettings();
            VoidGen.getVoidGen().getLogger().info("Generator settings have not been set. Using default values:");
        } else {
            try {
                this.chunkGenSettings = gson.fromJson(paramIdentifier, ChunkGenSettings.class);
            } catch (JsonSyntaxException jse) {
                this.chunkGenSettings = new ChunkGenSettings();
                VoidGen.getVoidGen().getLogger().info("Generator settings \"" + paramIdentifier + "\" syntax is not valid. Using default values:");
            }
        }
        // Posting the currently used chunkGenSettings to console.
        VoidGen.getVoidGen().getLogger().info(gson.toJson(chunkGenSettings));
    }

    public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid paramBiomeGrid){
        byte[][] result = new byte[world.getMaxHeight() / 16][];
        if (Objects.nonNull(this.chunkGenSettings.getBiome())) {
            this.setBiomeGrid(paramBiomeGrid, null);
        }
        this.placeBedrock(result, chunkX, chunkZ);

        return result;
    }


}
