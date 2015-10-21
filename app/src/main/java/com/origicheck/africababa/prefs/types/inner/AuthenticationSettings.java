package com.origicheck.africababa.prefs.types.inner;

/**
 * Created by victor on 10/21/2015.
 */
public interface AuthenticationSettings {


    String getUsername();

    void setUsername(String username);

    String getSessionId();

    void setSessionId(String sessionId);

    long getLastExpires();

    void setLastExpires(long lastExpires);

    long getExpiresToken();

    void setExpiresToken(long expiresToken);
}
