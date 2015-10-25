package com.origicheck.africababa.prefs.types.declaration;

import com.origicheck.africababa.prefs.types.inner.AfricaBabaPreferences;
import com.origicheck.africababa.prefs.types.inner.AuthenticationSettings;
import com.origicheck.africababa.prefs.types.inner.UIPreferences;
import com.origicheck.africababa.prefs.types.inner.UsageAnalytics;
import com.origicheck.africababa.prefs.types.inner.UserSettings;

/**
 * Created by victor on 10/21/2015.
 */
public interface IPreferences extends AfricaBabaPreferences, AuthenticationSettings, UIPreferences, UsageAnalytics, UserSettings {
    @Override
    int getAccountType();

    @Override
    void setAccountType(int accountType);

    @Override
    long getAccountCreated();

    @Override
    void setAccountCreated(long accountCreated);

    @Override
    float getAccountPayment();

    @Override
    void setAccountPayment(float accountPayment);

    @Override
    float getAccountCost();

    @Override
    void setAccountCost(float accountCost);

    @Override
    float getPaymentCredit();

    @Override
    void setPaymentCredit(float paymentCredit);

    @Override
    float getPaymentDebit();

    @Override
    void setPaymentDebit(float paymentDebit);

    @Override
    int getStoresCount();

    @Override
    void setStoresCount(int storesCount);

    @Override
    boolean isLoggedIn();

    @Override
    void setLoggedIn(boolean loggedIn);

    @Override
    String getUsername();

    @Override
    void setUsername(String username);

    @Override
    String getSessionId();

    @Override
    void setSessionId(String sessionId);

    @Override
    long getLastExpires();

    @Override
    void setLastExpires(long lastExpires);

    @Override
    long getExpiresToken();

    @Override
    void setExpiresToken(long expiresToken);

    @Override
    int getThemeColor();

    @Override
    void setThemeColor(int themeColor);

    @Override
    String[] getThemeColors();

    @Override
    void setThemeColors(String[] themeColors);

    @Override
    float getFontSize();

    @Override
    void setFontSize(float fontSize);

    @Override
    int getTextColor();

    @Override
    void setTextColor(int textColor);

    @Override
    String[] getTextColors();

    @Override
    void setTextColors(String[] textColors);

    @Override
    int getTextStyle();

    @Override
    void setTextStyle(int textStyle);

    @Override
    String[] getTextStyles();

    @Override
    void setTextStyles(String[] textStyles);

    @Override
    long getLastLogin();

    @Override
    void setLastLogin(long lastLogin);

    @Override
    long getFirstLogin();

    @Override
    void setFirstLogin(long firstLogin);

    @Override
    long getLongestOnline();

    @Override
    void setLongestOnline(long longestOnline);

    @Override
    long getLastOnline();

    @Override
    void setLastOnline(long lastOnline);

    @Override
    int getUserId();

    @Override
    void setUserId(int userId);

    @Override
    int getBuyerId();

    @Override
    void setBuyerId(int buyerId);

    @Override
    int getSellerId();

    @Override
    void setSellerId(int sellerId);

    @Override
    int getSupplierId();

    @Override
    void setSupplierId(int supplierId);

    @Override
    String getDisplayName();

    @Override
    void setDisplayName(String displayName);

    @Override
    int getDisplayAvatar();

    @Override
    void setDisplayAvatar(int displayAvatar);

    @Override
    String getProfilePicUri();

    @Override
    void setProfilePicUri(String profilePicUri);

    @Override
    String getContactEmail();

    @Override
    void setContactEmail(String contactEmail);

    @Override
    String getContactPhone();

    @Override
    void setContactPhone(String contactPhone);

    @Override
    String getWebsite();

    @Override
    void setWebsite(String website);
}
