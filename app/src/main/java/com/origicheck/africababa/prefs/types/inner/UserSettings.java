package com.origicheck.africababa.prefs.types.inner;

/**
 * Created by victor on 10/21/2015.
 */
public interface UserSettings {


    int getUserId();

    void setUserId(int userId);

    int getBuyerId();

    void setBuyerId(int buyerId);

    int getSellerId();

    void setSellerId(int sellerId);

    int getSupplierId();

    void setSupplierId(int supplierId);

    String getDisplayName();

    void setDisplayName(String displayName);

    int getDisplayAvatar();

    void setDisplayAvatar(int displayAvatar);

    String getContactEmail();

    void setContactEmail(String contactEmail);

    String getContactPhone();

    void setContactPhone(String contactPhone);

    String getWebsite();

    void setWebsite(String website);
}
