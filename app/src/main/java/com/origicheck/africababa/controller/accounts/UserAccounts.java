package com.origicheck.africababa.controller.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.origicheck.africababa.R;

/**
 * Created by victor on 10/14/2015.
 */
public class UserAccounts {
    private Context context;

    public UserAccounts(Context context) {
        this.context = context;

        if (!isAccountExists(getContext().getResources().getString(R.string.account_type))) {
            addAccount(getContext().getResources().getString(R.string.account_type));
        }
    }

    private void addAccount(String accountType) {
        AccountManager accountManager = (AccountManager) getContext().getSystemService(Context.ACCOUNT_SERVICE);
        String accountname = getContext().getResources().getString(R.string.app_name);

        Account account = new Account(accountname, accountType);

        accountManager.addAccountExplicitly(account, accountType, null);

    }

    private boolean isAccountExists(String accountType) {
        AccountManager accountManager = (AccountManager) getContext().getSystemService(Context.ACCOUNT_SERVICE);
        Account[] accounts = accountManager.getAccounts();

        for (Account account : accounts) {
            if (account.type.equals(accountType)) {
                return true;
            }
        }
        return false;
    }

    public Context getContext() {
        return context;
    }

}
