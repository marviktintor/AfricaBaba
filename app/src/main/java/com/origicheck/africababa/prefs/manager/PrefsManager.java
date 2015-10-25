package com.origicheck.africababa.prefs.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.origicheck.africababa.prefs.types.declaration.IPreferences;
import com.origicheck.africababa.prefs.types.keys.PrefKey;

/**
 * Created by victor on 10/21/2015.
 */
public class PrefsManager implements IPreferences {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PrefsManager(@NonNull Context context) {
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
        if (editor == null) {
            editor = getSharedPreferences().edit();
        }
        return editor;
    }

    private <T> void commit(String preference, T typeOf) {


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
    public boolean isLoggedIn() {
        return read(PrefKey.LOGGED_IN, Boolean.class, false);
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        commit(PrefKey.LOGGED_IN, loggedIn);
    }

    @Nullable
    @Override
    public String getUsername() {
        return read(PrefKey.USERNAME, String.class, getDisplayName());
    }

    @Override
    public void setUsername(String username) {
        commit(PrefKey.USERNAME, username);
    }

    @Nullable
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

    @NonNull
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

    @NonNull
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

    @NonNull
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
        return read(PrefKey.USER_ID, Integer.class, -1);
    }

    @Override
    public void setUserId(int userId) {
        commit(PrefKey.USER_ID, userId);
    }

    @Override
    public int getBuyerId() {
        return read(PrefKey.BUYER_ID, Integer.class, getUserId());
    }

    @Override
    public void setBuyerId(int buyerId) {
        commit(PrefKey.BUYER_ID, buyerId);
    }

    @Override
    public int getSellerId() {
        return read(PrefKey.SELLER_ID, Integer.class, getUserId());
    }

    @Override
    public void setSellerId(int sellerId) {
        commit(PrefKey.SELLER_ID, sellerId);
    }

    @Override
    public int getSupplierId() {
        return getSellerId();
    }

    @Override
    public void setSupplierId(int supplierId) {
        setSellerId(supplierId);
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return read(PrefKey.DISPLAY_NAME, String.class, null);
    }

    @Override
    public void setDisplayName(String displayName) {
        commit(PrefKey.DISPLAY_NAME, displayName);
    }

    @Override
    public int getDisplayAvatar() {
        return read(PrefKey.DISPLAY_AVATAR, Integer.class, PrefKey.DISPLAY_AVATAR_NONE);
    }

    @Override
    public void setDisplayAvatar(int displayAvatar) {
        commit(PrefKey.DISPLAY_AVATAR, displayAvatar);
    }

    @Override
    public String getProfilePicUri() {
        return read(PrefKey.LOCAL_AVATAR, String.class, null);
    }

    @Override
    public void setProfilePicUri(String profilePicUri) {
        commit(PrefKey.LOCAL_AVATAR, profilePicUri);
    }

    @Nullable
    @Override
    public String getContactEmail() {
        return read(PrefKey.CONTACT_EMAIL, String.class, null);
    }

    @Override
    public void setContactEmail(String contactEmail) {
        commit(PrefKey.CONTACT_EMAIL, contactEmail);
    }

    @Nullable
    @Override
    public String getContactPhone() {
        return read(PrefKey.CONTACT_PHONE, String.class, null);
    }

    @Override
    public void setContactPhone(String contactPhone) {
        commit(PrefKey.CONTACT_PHONE, contactPhone);
    }

    @Nullable
    @Override
    public String getWebsite() {
        return read(PrefKey.WEBSITE, String.class, null);
    }

    @Override
    public void setWebsite(String website) {
        commit(PrefKey.WEBSITE, website);
    }

    public void clearPreferences() { getEditor().clear().commit();}


}
