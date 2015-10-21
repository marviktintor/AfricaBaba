package com.origicheck.africababa.prefs.types.inner;

/**
 * Created by victor on 10/21/2015.
 */
public interface ServerPreferences {


    boolean isDebug();

    void setDebug(boolean debug);

    boolean isRelease();

    void setRelease(boolean release);

    String getServerUrl();

    void setServerUrl(String serverUrl);

    String getDomain();

    void setDomain(String domain);

    String getProtocol();

    void setProtocol(String protocol);

    boolean isUsingSSL();

    void setUsingSSL(boolean usingSSL);

    String getSyncerUrl();

    void setSyncerUrl(String syncerUrl);
}
