# Keep your main class
-keep class * extends org.bukkit.plugin.java.JavaPlugin {
    public void onEnable();
	public void onDisable();
}

# Keep event handlers
-keep,allowobfuscation class * extends org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler <methods>;
}

-dontshrink
-dontoptimize

-keepattributes !LocalVariableTable,!LocalVariableTypeTable,Exceptions,InnerClasses,Signature,Deprecated,LineNumberTable,*Annotation*,EnclosingMethod