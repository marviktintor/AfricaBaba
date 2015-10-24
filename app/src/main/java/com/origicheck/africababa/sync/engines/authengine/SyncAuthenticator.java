package com.origicheck.africababa.sync.engines.authengine;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.origicheck.africababa.activities.ActivityMain;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.controller.utils.Utils;

/**
 * Created by victor on 10/14/2015.
 */
public class SyncAuthenticator extends AbstractAccountAuthenticator {
    private Utils utils;

    public SyncAuthenticator(Context context) {
        super(context);
        initAll(context);
    }

    @Nullable
    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Nullable
    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {

        getUtils().startActivity(Intents.ACTION_AUTHENTICATE, ActivityMain.class, Intent.FLAG_ACTIVITY_NEW_TASK);
        return null;
    }

    @Nullable
    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Nullable
    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Nullable
    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Nullable
    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Nullable
    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }

    private void initAll(Context context) {
        utils = new Utils(context);
    }

    public Utils getUtils() {
        return utils;
    }
}
