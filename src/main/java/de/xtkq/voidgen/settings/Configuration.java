package de.xtkq.voidgen.settings;

import com.google.gson.annotations.SerializedName;

public class Configuration {

    @SerializedName("checkForUpdates")
    private boolean checkForUpdates = true;

//    @SerializedName("verbose")
//    private boolean verbose = true;

    public boolean getCheckForUpdates() {
        return this.checkForUpdates;
    }

//    public boolean getVerbose() {
//        return this.verbose;
//    }
}
