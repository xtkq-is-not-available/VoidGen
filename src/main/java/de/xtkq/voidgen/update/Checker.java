package de.xtkq.voidgen.update;

import org.bukkit.plugin.java.JavaPlugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

public class Checker {
    private static final String API_URL = "https://api.spigotmc.org/legacy/update.php?resource=%d";
    private static final String RESOURCE_URL = "https://www.spigotmc.org/resources/%s/";
    private final static int PLUGIN_ID = 25391;

    private static boolean updateAvailable = false;
    private static String latestVersion;
    private final JavaPlugin plugin;

    public Checker(JavaPlugin paramPlugin) {
        this.plugin = paramPlugin;
        latestVersion = paramPlugin.getDescription().getVersion();
    }

    /**
     * Returns the URL under which the plugin can be found on the spigot forums.
     *
     * @return The URL for the given plugin.
     */
    public static String getResourceURL() {
        return String.format(RESOURCE_URL, PLUGIN_ID);
    }

    /**
     * Returns true if an update for this resource was found on the spigot forums. Otherwise false.
     *
     * @return True if an update is available, False otherwise.
     */
    public static boolean isUpdateAvailable() {
        return updateAvailable;
    }

    /**
     * Retrieve the latest version of this plugin.
     *
     * @return the latest version on the spigot forums. Or if no connection could be established, the local version.
     */
    public static String getLatestVersion() {
        return latestVersion;
    }

    public void checkForUpdates() {
        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {
            String serverVersion;
            try {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format(API_URL, PLUGIN_ID))
                        .openConnection();
                serverVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            } catch (Exception npe) {
                // Failed to check SpigotMc.org for Updates, since no connection could be established.
                return;
            }
            if (isUpdateAvailable(this.plugin.getDescription().getVersion(), serverVersion)) {
                plugin.getLogger().log(Level.INFO, String.format("%s v.%s is available here: %s", this.plugin.getName(), serverVersion, getResourceURL()));
                latestVersion = serverVersion;
                updateAvailable = true;
            } else {
                updateAvailable = false;
            }
        }, 3000L, 180000L);
    }

    /**
     * Returns True if the provided Version is higher than the local plugin version. Also returns False if the versions are equal.
     *
     * @param paramServerVersion the plugin version which was retrieved from the spigot forums.
     * @param paramLocalVersion  the local plugin version.
     * @return True if the version on the forums is newer than the local version. False otherwise.
     */
    private boolean isUpdateAvailable(String paramLocalVersion, String paramServerVersion) {
        String[] localVersion = paramLocalVersion.split("\\.");
        String[] serverVersion = paramServerVersion.split("\\.");
        int result;
        int i = 0;
        while (i < localVersion.length && i < serverVersion.length && localVersion[i].equals(serverVersion[i])) {
            i++;
        }
        if (i < localVersion.length && i < serverVersion.length) {
            int diff = Integer.valueOf(localVersion[i]).compareTo(Integer.valueOf(serverVersion[i]));
            result = Integer.signum(diff);
        } else {
            result = Integer.signum(localVersion.length - serverVersion.length);
        }
        return result == -1;
    }
}

