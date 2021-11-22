package de.xtkq.voidgen.settings;

import com.google.gson.annotations.SerializedName;

public class Configuration {

    @SerializedName("checkForUpdates")
    private boolean checkForUpdates = true;

    public boolean getCheckForUpdates() {
        return this.checkForUpdates;
    }
}
