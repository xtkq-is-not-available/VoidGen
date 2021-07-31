package de.xtkq.voidgen;

import de.xtkq.voidgen.generator.annotations.VoidChunkGenInfo;
import de.xtkq.voidgen.events.EventManager;
import de.xtkq.voidgen.generator.instances.VoidChunkGen_1_15;
import de.xtkq.voidgen.generator.instances.VoidChunkGen_1_17;
import de.xtkq.voidgen.generator.instances.VoidChunkGen_1_7;
import de.xtkq.voidgen.generator.instances.VoidChunkGen_1_8_8;
import de.xtkq.voidgen.settings.SettingsManager;
import de.xtkq.voidgen.update.Checker;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class VoidGen extends JavaPlugin {

    private static VoidGen voidGen;
    private VoidGenType voidGenType;
    private EventManager eventManager;

    public static VoidGen getVoidGen() {
        return voidGen;
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        switch (this.voidGenType) {
            case v1_7:
                return new VoidChunkGen_1_7(id);
            case v1_8_8:
                return new VoidChunkGen_1_8_8(id);
            case v1_15:
                return new VoidChunkGen_1_15(id);
            case v1_17:
                return new VoidChunkGen_1_17(id);
            default:
                this.getLogger().severe("Ain't nobody got time for that!");
                return new VoidChunkGen_1_17(id);
        }
    }

    @Override
    public void onEnable() {
        voidGen = this;
        this.voidGenType = this.setupVoidChunkGen();
        this.getLogger().info("Using VoidChunkGen: " + this.voidGenType.name());

        SettingsManager settingsManager = new SettingsManager(this);
        Checker checker = new Checker(this);
        this.eventManager = new EventManager(this);

        if (settingsManager.getConfiguration().getCheckForUpdates()) {
            checker.checkForUpdates();
            this.eventManager.initialize();
        }
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
        this.eventManager.terminate();
        voidGen = null;
    }

    private VoidGenType setupVoidChunkGen() {
        VoidChunkGenInfo annotation;
        String bukkitVersion = Bukkit.getBukkitVersion().split("-")[0];

        annotation = VoidChunkGen_1_7.class.getAnnotation(VoidChunkGenInfo.class);
        if (Arrays.asList(annotation.versions()).contains(bukkitVersion)) {
            return VoidGenType.v1_7;
        }

        annotation = VoidChunkGen_1_8_8.class.getAnnotation(VoidChunkGenInfo.class);
        if (Arrays.asList(annotation.versions()).contains(bukkitVersion)) {
            return VoidGenType.v1_8_8;
        }

        annotation = VoidChunkGen_1_15.class.getAnnotation(VoidChunkGenInfo.class);
        if (Arrays.asList(annotation.versions()).contains(bukkitVersion)) {
            return VoidGenType.v1_15;
        }

        annotation = VoidChunkGen_1_17.class.getAnnotation(VoidChunkGenInfo.class);
        if (Arrays.asList(annotation.versions()).contains(bukkitVersion)) {
            return VoidGenType.v1_17;
        }
        return VoidGenType.v1_17;
    }
}
