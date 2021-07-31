package de.xtkq.voidgen.events;

import org.bukkit.plugin.java.JavaPlugin;

public class EventManager {

    private final JavaPlugin plugin;
    private PlayerLoginListener playerLogin;

    public EventManager(JavaPlugin paramPlugin) {
        this.plugin = paramPlugin;
    }

    public void initialize() {
        this.playerLogin = new PlayerLoginListener(this.plugin);
    }

    public void terminate() {
        if (this.playerLogin != null) {
            this.playerLogin.terminate();
        }
    }
}

