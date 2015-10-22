package com.origicheck.africababa.prefs.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.origicheck.africababa.prefs.types.declaration.IPreferences;
import com.origicheck.africababa.prefs.types.keys.PrefKey;

/**
 * Created by victor on 10/21/2015.
 */
public class PrefsManager implements IPreferences {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PrefsManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Context getContext() {
        return context;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    private <T> void commit(String preference, T typeOf) {
        editor = getSharedPreferences().edit();

        if (typeOf instanceof Boolean) {
            getEditor().putBoolean(preference, (Boolean) typeOf);
        }
        if (typeOf instanceof Float) {
            getEditor().putFloat(preference, (Float) typeOf);
        }
        if (typeOf instanceof Integer) {
            getEditor().putInt(preference, (Integer) typeOf);
        }
        if (typeOf instanceof Long) {
            getEditor().putLong(preference, (Long) typeOf);
        }
        if (typeOf instanceof String) {
            getEditor().putString(preference, (String) typeOf);
        }
        getEditor().commit();
    }

    private <T> T read(String preference, Class<T> componentType, T defaultValue) {


        if (componentType == Boolean.class) {
            return (T) Boolean.valueOf(getSharedPreferences().getBoolean(preference, (Boolean) defaultValue));

        }
        if (componentType == Float.class) {
            return (T) Float.valueOf(getSharedPreferences().getFloat(preference, (Float) defaultValue));
        }
        if (componentType == Integer.class) {
            return (T) Integer.valueOf(getSharedPreferences().getInt(preference, (Integer) defaultValue));
        }
        if (componentType == Long.class) {
            return (T) Long.valueOf(getSharedPreferences().getLong(preference, (Long) defaultValue));
        }
        if (componentType == String.class) {
            return (T) String.valueOf(getSharedPreferences().getString(preference, (String) defaultValue));
        }

        return null;
    }

    @Override
    public int getAccountType() {
        return 0;
    }

    @Override
    public void setAccountType(int accountType) {

    }

    @Override
    public long getAccountCreated() {
        return 0;
    }

    @Override
    public void setAccountCreated(long accountCreated) {

    }

    @Override
    public float getAccountPayment() {
        return 0;
    }

    @Override
    public void setAccountPayment(float accountPayment) {

    }

    @Override
    public float getAccountCost() {
        return 0;
    }

    @Override
    public void setAccountCost(float accountCost) {

    }

    @Override
    public float getPaymentCredit() {
        return 0;
    }

    @Override
    public void setPaymentCredit(float paymentCredit) {

    }

    @Override
    public float getPaymentDebit() {
        return 0;
    }

    @Override
    public void setPaymentDebit(float paymentDebit) {

    }

    @Override
    public int getStoresCount() {
        return 0;
    }

    @Override
    public void setStoresCount(int storesCount) {

    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        commit(PrefKey.LOGGED_IN, loggedIn);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        commit(PrefKey.LOGGED_IN, loggedIn);
    }

    @Override
    public boolean isLoggedIn() {
        return read(PrefKey.LOGGED_IN, Boolean.TYPE, false);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public void setSessionId(String sessionId) {

    }

    @Override
    public long getLastExpires() {
        return 0;
    }

    @Override
    public void setLastExpires(long lastExpires) {

    }

    @Override
    public long getExpiresToken() {
        return 0;
    }

    @Override
    public void setExpiresToken(long expiresToken) {

    }

    @Override
    public int getThemeColor() {
        return 0;
    }

    @Override
    public void setThemeColor(int themeColor) {

    }

    @Override
    public String[] getThemeColors() {
        return new String[0];
    }

    @Override
    public void setThemeColors(String[] themeColors) {

    }

    @Override
    public float getFontSize() {
        return 0;
    }

    @Override
    public void setFontSize(float fontSize) {

    }

    @Override
    public int getTextColor() {
        return 0;
    }

    @Override
    public void setTextColor(int textColor) {

    }

    @Override
    public String[] getTextColors() {
        return new String[0];
    }

    @Override
    public void setTextColors(String[] textColors) {

    }

    @Override
    public int getTextStyle() {
        return 0;
    }

    @Override
    public void setTextStyle(int textStyle) {

    }

    @Override
    public String[] getTextStyles() {
        return new String[0];
    }

    @Override
    public void setTextStyles(String[] textStyles) {

    }

    @Override
    public long getLastLogin() {
        return 0;
    }

    @Override
    public void setLastLogin(long lastLogin) {

    }

    @Override
    public long getFirstLogin() {
        return 0;
    }

    @Override
    public void setFirstLogin(long firstLogin) {

    }

    @Override
    public long getLongestOnline() {
        return 0;
    }

    @Override
    public void setLongestOnline(long longestOnline) {

    }

    @Override
    public long getLastOnline() {
        return 0;
    }

    @Override
    public void setLastOnline(long lastOnline) {

    }

    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public void setUserId(int userId) {

    }

    @Override
    public int getBuyerId() {
        return 0;
    }

    @Override
    public void setBuyerId(int buyerId) {

    }

    @Override
    public int getSellerId() {
        return 0;
    }

    @Override
    public void setSellerId(int sellerId) {

    }

    @Override
    public int getSupplierId() {
        return 0;
    }

    @Override
    public void setSupplierId(int supplierId) {

    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(String displayName) {

    }

    @Override
    public String getContactEmail() {
        return null;
    }

    @Override
    public void setContactEmail(String contactEmail) {

    }

    @Override
    public String getContactPhone() {
        return null;
    }

    @Override
    public void setContactPhone(String contactPhone) {

    }

    @Override
    public String getWebsite() {
        return null;
    }

    @Override
    public void setWebsite(String website) {

    }
}
