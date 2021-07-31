# Keep your main class
-keep public class de.xtkq.voidgen.VoidGen {
    public void onEnable();
	public void onDisable();
}

-keepclassmembernames class de.xtkq.voidgen.settings.Configuration {
    *** checkForUpdates;
}

-keepclassmembernames class de.xtkq.voidgen.generator.settings.ChunkGenSettings{
    *** biome;
    *** caves;
    *** decoration;
    *** mobs;
    *** structures;
}

# Keep event handlers
-keep,allowobfuscation class * extends org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler <methods>;
}

-dontshrink
-dontoptimize
# Some attributes that you'll need to keep (to be honest I'm not sure which ones really need to be kept here, but this is what works for me)
-keepattributes !LocalVariableTable,!LocalVariableTypeTable,Exceptions,InnerClasses,Signature,Deprecated,LineNumberTable,*Annotation*,EnclosingMethod