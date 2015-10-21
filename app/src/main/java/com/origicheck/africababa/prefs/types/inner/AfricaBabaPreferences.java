package com.origicheck.africababa.prefs.types.inner;

/**
 * Created by victor on 10/21/2015.
 */
public interface AfricaBabaPreferences {


    int getAccountType();

    void setAccountType(int accountType);

    long getAccountCreated();

    void setAccountCreated(long accountCreated);

    float getAccountPayment();

    void setAccountPayment(float accountPayment);

    float getAccountCost();

    void setAccountCost(float accountCost);

    float getPaymentCredit();

    void setPaymentCredit(float paymentCredit);

    float getPaymentDebit();

    void setPaymentDebit(float paymentDebit);

    int getStoresCount();

    void setStoresCount(int storesCount);
}
