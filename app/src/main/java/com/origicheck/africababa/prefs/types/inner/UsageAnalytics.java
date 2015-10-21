package com.origicheck.africababa.prefs.types.inner;

/**
 * Created by victor on 10/21/2015.
 */
public interface UsageAnalytics {

    long getLastLogin();

    void setLastLogin(long lastLogin);

    long getFirstLogin();

    void setFirstLogin(long firstLogin);

    long getLongestOnline();

    void setLongestOnline(long longestOnline);

    long getLastOnline();

    void setLastOnline(long lastOnline);
}
