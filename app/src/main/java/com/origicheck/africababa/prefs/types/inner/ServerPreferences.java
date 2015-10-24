package com.origicheck.africababa.prefs.types.inner;

import android.support.annotation.NonNull;

/**
 * Created by victor on 10/21/2015.
 */
public interface ServerPreferences {


    boolean isDebug();

    void setDebug(boolean debug);

    boolean isRelease();

    void setRelease(boolean release);

    @NonNull
    String getServerUrl();

    void setServerUrl(String serverUrl);

    @NonNull
    String getDomain();

    void setDomain(String domain);

    @NonNull
    String getProtocol();

    void setProtocol(String protocol);

    boolean isUsingSSL();

    void setUsingSSL(boolean usingSSL);

    @NonNull
    String getSyncerUrl();

    void setSyncerUrl(String syncerUrl);
}
