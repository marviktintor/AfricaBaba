package com.origicheck.africababa.sync.worker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.origicheck.africababa.controller.utils.Utils;
import com.origicheck.africababa.prefs.types.keys.PrefKey;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by victor on 10/17/2015.
 */
public class SyncExecutorThread extends Thread {


    // Actions
    public static final String ACTION = "action";
    public static final String ACTION_INSERT = "insert";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_QUERY = "query";
    public static final String ACTION_UPDATE = "update";

    // Intents:
    public static final String INTENT = "intent";

    // Clients
    public static final String CLIENT = "client";
    public static final String CLIENT_MOBILE_ANDROID = "android";

    // User
    public static final String USER_ID = "user_id";

    // Transaction host
    public static final String TRANSACTION_HOST = "transaction_host";

    // host
    public static final String TRANSACTION_HOST_BUYERS = "host_buyers";
    public static final String TRANSACTION_HOST_CATALOGUE = "host_catalogue";
    public static final String TRANSACTION_HOST_CATEGORY = "host_category";
    public static final String TRANSACTION_HOST_CERTIFICATES = "host_certificates";
    public static final String TRANSACTION_HOST_FILES = "host_files";
    public static final String TRANSACTION_HOST_GROUPS = "host_groups";
    public static final String TRANSACTION_HOST_LOCATIONS = "host_locations";
    public static final String TRANSACTION_HOST_MESSAGES = "host_messages";
    public static final String TRANSACTION_HOST_ORDERS = "host_orders";
    public static final String TRANSACTION_HOST_PRODUCTS = "host_products";
    public static final String TRANSACTION_HOST_QUICK_SALE = "host_quick_sale";
    public static final String TRANSACTION_HOST_QUOTES = "host_quotes";
    public static final String TRANSACTION_HOST_STORES = "host_stores";
    public static final String TRANSACTION_HOST_SUPPLIERS = "host_suppliers";
    public static final String TRANSACTION_HOST_USERS = "host_users";

    // Insert Intents
    public static final String INTENT_INSERT_BUYERS = "insert_buyers";
    public static final String INTENT_INSERT_CATALOGUE = "insert_catalogue";
    public static final String INTENT_INSERT_CATEGORY = "insert_category";
    public static final String INTENT_INSERT_CERTIFICATES = "insert_certificates";
    public static final String INTENT_INSERT_FILES = "insert_files";
    public static final String INTENT_INSERT_GROUPS = "insert_groups";
    public static final String INTENT_INSERT_LOCATIONS = "insert_locations";
    public static final String INTENT_INSERT_MESSAGES = "insert_messages";
    public static final String INTENT_INSERT_ORDERS_SENT = "insert_orders_sent";
    public static final String INTENT_INSERT_ORDERS_RECEIVED = "insert_orders_received";
    public static final String INTENT_INSERT_PRODUCTS = "insert_products";
    public static final String INTENT_INSERT_QUICK_SALE = "insert_quick_sale";
    public static final String INTENT_INSERT_QUOTES = "insert_quotes";
    public static final String INTENT_INSERT_STORES = "insert_stores";
    public static final String INTENT_INSERT_SUPPLIERS = "insert_suppliers";
    public static final String INTENT_INSERT_USERS = "insert_users";
    public static final String INTENT_INSERT_USERS_LOGIN = "insert_users_login";
    public static final String INTENT_INSERT_USERS_SIGNUP = "insert_users_signup";

    // Query Intents
    public static final String INTENT_QUERY_BUYERS = "query_buyers";
    public static final String INTENT_QUERY_CATALOGUE = "query_catalogue";
    public static final String INTENT_QUERY_CATEGORY = "query_category";
    public static final String INTENT_QUERY_CERTIFICATES = "query_certificates";
    public static final String INTENT_QUERY_FILES = "query_files";
    public static final String INTENT_QUERY_GROUPS = "query_groups";
    public static final String INTENT_QUERY_LOCATIONS = "query_locations";
    public static final String INTENT_QUERY_MESSAGES = "query_messages";
    public static final String INTENT_QUERY_ORDERS_SENT = "query_orders_sent";
    public static final String INTENT_QUERY_ORDERS_RECEIVED = "query_orders_received";
    public static final String INTENT_QUERY_PRODUCTS = "query_products";
    public static final String INTENT_QUERY_QUICK_SALE = "query_quick_sale";
    public static final String INTENT_QUERY_QUOTES = "query_quotes";
    public static final String INTENT_QUERY_STORES = "query_stores";
    public static final String INTENT_QUERY_SUPPLIERS = "query_suppliers";
    public static final String INTENT_QUERY_USERS = "query_users";
    public static final String INTENT_QUERY_USERS_LOGIN = "query_users_login";
    public static final String INTENT_QUERY_USERS_SIGNUP = "query_users_signup";

    // Update Intents
    public static final String INTENT_UPDATE_BUYERS = "update_buyers";
    public static final String INTENT_UPDATE_CATALOGUE = "update_catalogue";
    public static final String INTENT_UPDATE_CATEGORY = "update_category";
    public static final String INTENT_UPDATE_CERTIFICATES = "update_certificates";
    public static final String INTENT_UPDATE_FILES = "update_files";
    public static final String INTENT_UPDATE_GROUPS = "update_groups";
    public static final String INTENT_UPDATE_LOCATIONS = "update_locations";
    public static final String INTENT_UPDATE_MESSAGES = "update_messages";
    public static final String INTENT_UPDATE_ORDERS_SENT = "update_orders_sent";
    public static final String INTENT_UPDATE_ORDERS_RECEIVED = "update_orders_received";
    public static final String INTENT_UPDATE_PRODUCTS = "update_products";
    public static final String INTENT_UPDATE_QUICK_SALE = "update_quick_sale";
    public static final String INTENT_UPDATE_QUOTES = "update_quotes";
    public static final String INTENT_UPDATE_STORES = "update_stores";
    public static final String INTENT_UPDATE_SUPPLIERS = "update_suppliers";
    public static final String INTENT_UPDATE_USERS = "update_users";
    public static final String INTENT_UPDATE_USERS_LOGIN = "update_users_login";
    public static final String INTENT_UPDATE_USERS_SIGNUP = "update_users_signup";

    //Params

    //Authentication Params
    public static final String POST_PARAM_FILEID = "fileid";
    public static final String POST_PARAM_FULLNAME = "fullname";
    public static final String POST_PARAM_EMAIL = "email";
    public static final String POST_PARAM_PHONE = "phonenumber";
    public static final String POST_PARAM_USERNAME = "username";
    public static final String POST_PARAM_PASSWORD = "password";
    public static final String POST_ORDERS_PARAM_ORDER_ID = "orderId";
    public static final String POST_ORDERS_PARAM_PRODUCT_ID = "productId";
    public static final String POST_ORDERS_PARAM_SUPPLIER_ID = "supplierId";
    public static final String POST_ORDERS_PARAM_BUYER_ID = "buyerId";
    public static final String POST_ORDERS_PARAM_QUANTITY = "quantity";
    public static final String POST_ORDERS_PARAM_PRICE = "price";
    public static final String POST_ORDERS_PARAM_TIMESTAMP = "timestamp";
    public static final String POST_QUOTES_PARAM_QUOTE_ID = "quoteId";
    public static final String POST_QUOTES_PARAM_PRODUCT_NAME = "productName";
    public static final String POST_QUOTES_PARAM_PRODUCT_DESCRIPTION = "productDescription";
    public static final String POST_QUOTES_PARAM_PRODUCT_QUANTITY = "productQuantity";
    public static final String POST_QUOTES_PARAM_TIMESTAMP = "timestamp";
    @Nullable
    private FormData mFormData = null;
    private Utils utils;
    private Context context;

    public SyncExecutorThread(Context context, Utils utils) {
        this.context = context;
        this.utils = utils;
    }

    public Context getContext() {
        return context;
    }

    public Utils getUtils() {
        return utils;
    }

    @Override
    public void run() {
        super.run();
        if (getUtils().isNetworkConnected(true)) syncAll(false);
    }

    private int getUserId() {
        // TODO Auto-generated method stub
        return getUtils().getPrefsManager().getUserId();
    }

    @Nullable
    public FormData getFormData() {
        return mFormData;
    }

    public void serverSync(@NonNull String transactionHost, @NonNull String commitIntent, @NonNull String formData) throws Exception {
        try {

            URL url = new URL(PrefKey.SERVER_URL + "/worker.php");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(formData);
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String dataStream = "";
            while ((dataStream = bufferedReader.readLine()) != null) {
                stringBuffer.append(dataStream);
            }

            Log.d("DATA_STREAM", stringBuffer.toString());

            getUtils().getTransactionsManager().consumeSyncData(transactionHost, commitIntent, stringBuffer.toString());

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            printError("MalformedURLException -> " + e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            printError("IOException -> " + e.getMessage());

        }

    }

    private void sync(String action, String commitIntent, String transactionHost,
                      @NonNull FormData formData) {
        if (getFormData() == null) {
            printError("Form data is null");
        }
        Log.i("USER_ID", "" + getUserId());
        formData.appendFormData(USER_ID, "" + getUserId());
        formData.appendFormData(ACTION, action);
        formData.appendFormData(CLIENT, CLIENT_MOBILE_ANDROID);
        formData.appendFormData(INTENT, commitIntent);
        formData.appendFormData(TRANSACTION_HOST, transactionHost);

        if (formData == null || formData.getFormDataBuilder().equals("")) {
            printError("Could not build Form data to send");

        }
        syncFormData(transactionHost, commitIntent, formData.getData());
    }

    private void syncFormData(final String transactionHost, @NonNull final String commitIntent, @NonNull final String formData) {
        // TODO Auto-generated method stub

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSync(transactionHost, commitIntent, formData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void syncBuyers() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_BUYERS;
        String transactionHost = TRANSACTION_HOST_BUYERS;

        mFormData = new FormData();
        mFormData.startBuildingFormData();
        mFormData.appendFormData("nickname", "victor_mwenda");
        sync(action, intent, transactionHost, mFormData);
    }

    public void syncCatalogue() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_CATALOGUE;
        String transactionHost = TRANSACTION_HOST_CATALOGUE;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncCategory() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_CATEGORY;
        String transactionHost = TRANSACTION_HOST_CATEGORY;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncCertificate() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_CERTIFICATES;
        String transactionHost = TRANSACTION_HOST_CERTIFICATES;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncFiles() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_FILES;
        String transactionHost = TRANSACTION_HOST_FILES;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncGroups() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_GROUPS;
        String transactionHost = TRANSACTION_HOST_GROUPS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncLocations() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_LOCATIONS;
        String transactionHost = TRANSACTION_HOST_LOCATIONS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncMessages() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_MESSAGES;
        String transactionHost = TRANSACTION_HOST_MESSAGES;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncOrdrersReceived() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_ORDERS_RECEIVED;
        String transactionHost = TRANSACTION_HOST_ORDERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncOrdrersSent() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_ORDERS_SENT;
        String transactionHost = TRANSACTION_HOST_ORDERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncProducts() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_PRODUCTS;
        String transactionHost = TRANSACTION_HOST_PRODUCTS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncQuickSales() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_QUICK_SALE;
        String transactionHost = TRANSACTION_HOST_QUICK_SALE;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncQuotes() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_QUOTES;
        String transactionHost = TRANSACTION_HOST_QUOTES;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncStores() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_STORES;
        String transactionHost = TRANSACTION_HOST_STORES;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncSuppliers() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_SUPPLIERS;
        String transactionHost = TRANSACTION_HOST_SUPPLIERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void syncUsers() {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_USERS;
        String transactionHost = TRANSACTION_HOST_USERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        sync(action, intent, transactionHost, mFormData);
    }

    public void loginUser(String username, String password) {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_USERS_LOGIN;
        String transactionHost = TRANSACTION_HOST_USERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        mFormData.appendFormData(POST_PARAM_USERNAME, username);
        mFormData.appendFormData(POST_PARAM_PASSWORD, password);

        sync(action, intent, transactionHost, mFormData);
    }

    public void signupUsers(String fullname, String email, String phonenumber,
                            String username, String password) {
        String action = ACTION_QUERY;
        String intent = INTENT_QUERY_USERS_SIGNUP;
        String transactionHost = TRANSACTION_HOST_USERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();

        mFormData.appendFormData(POST_PARAM_FILEID, "" + getUtils().getPrefsManager().getDisplayAvatar());
        mFormData.appendFormData(POST_PARAM_FULLNAME, fullname);
        mFormData.appendFormData(POST_PARAM_EMAIL, email);
        mFormData.appendFormData(POST_PARAM_PHONE, phonenumber);
        mFormData.appendFormData(POST_PARAM_USERNAME, username);
        mFormData.appendFormData(POST_PARAM_PASSWORD, password);

        sync(action, intent, transactionHost, mFormData);

    }

    public void syncAll() {
        printData("Syncing Buyers");
        syncBuyers();

        printData("Syncing Catalogue");
        syncCatalogue();

        printData("Syncing Category");
        syncCategory();

        printData("Syncing Certificate");
        syncCertificate();

        printData("Syncing Files");
        syncFiles();

        printData("Syncing Groups");
        syncGroups();

        printData("Syncing Locations");
        syncLocations();

        printData("Syncing Messages");
        syncMessages();

        printData("Syncing Orders Received");
        syncOrdrersReceived();

        printData("Syncing Orders Sent");
        syncOrdrersSent();

        printData("Syncing Products");
        syncProducts();

        printData("Syncing Quick Sales");
        syncQuickSales();

        printData("Syncing Quotes");
        syncQuotes();

        printData("Syncing Stores");
        syncStores();

        printData("Syncing Suppliers");
        syncSuppliers();


    }

    public void syncAll(boolean syncUsers) {

        if (syncUsers) {
            printData("Syncing Users");
            syncUsers();
        }
        syncAll();

    }

    private <T> void printError(String data) {
        // TODO Auto-generated method stub
        Log.e("SYNC_EXECUTOR", data);
    }

    private <T> void printData(String data) {
        // TODO Auto-generated method stub
        Log.d("SYNC_EXECUTOR", data);
    }

    public void serverSyncUpdateOrders(int orderId, int productId, int supplierId, int buyerId, int quantity, float price, String timestamp) {
        serverSyncOrders(ACTION_UPDATE, INTENT_UPDATE_ORDERS_SENT, orderId, productId, supplierId, buyerId, quantity, price, timestamp);
    }

    public void serverSyncInsertOrders(int orderId, int productId, int supplierId, int buyerId, int quantity, float price, String timestamp) {
        serverSyncOrders(ACTION_INSERT, INTENT_UPDATE_ORDERS_SENT, orderId, productId, supplierId, buyerId, quantity, price, timestamp);
    }

    public void serverSyncOrders(String action, String intent, int orderId, int productId, int supplierId, int buyerId, int quantity, float price, String timestamp) {
        String transactionHost = TRANSACTION_HOST_ORDERS;
        mFormData = new FormData();
        mFormData.startBuildingFormData();


        mFormData.appendFormData(POST_ORDERS_PARAM_ORDER_ID, "" + orderId);
        mFormData.appendFormData(POST_ORDERS_PARAM_PRODUCT_ID, "" + productId);
        mFormData.appendFormData(POST_ORDERS_PARAM_SUPPLIER_ID, "" + supplierId);
        mFormData.appendFormData(POST_ORDERS_PARAM_BUYER_ID, "" + buyerId);
        mFormData.appendFormData(POST_ORDERS_PARAM_QUANTITY, "" + quantity);
        mFormData.appendFormData(POST_ORDERS_PARAM_PRICE, "" + price);
        mFormData.appendFormData(POST_ORDERS_PARAM_TIMESTAMP, timestamp);

        sync(action, intent, transactionHost, mFormData);
    }

    public void serverSyncInsertQuotes(int quoteId, String productName, String productDescription, String productQuantity, String timestamp) {
        serverSyncQuotes(ACTION_INSERT, INTENT_INSERT_QUOTES, quoteId, productName, productDescription, productQuantity, timestamp);
    }

    public void serverSyncUpdateQuotes(int quoteId, String productName, String productDescription, String productQuantity, String timestamp) {
        serverSyncQuotes(ACTION_UPDATE, INTENT_UPDATE_QUOTES, quoteId, productName, productDescription, productQuantity, timestamp);
    }

    public void serverSyncQuotes(String action, String intent, int quoteId, String productName, String productDescription, String productQuantity, String timestamp) {
        String transactionHost = TRANSACTION_HOST_QUOTES;
        mFormData = new FormData();
        mFormData.startBuildingFormData();
        mFormData.appendFormData(POST_QUOTES_PARAM_QUOTE_ID, "" + quoteId);
        mFormData.appendFormData(POST_QUOTES_PARAM_PRODUCT_NAME, productName);
        mFormData.appendFormData(POST_QUOTES_PARAM_PRODUCT_DESCRIPTION, productDescription);
        mFormData.appendFormData(POST_QUOTES_PARAM_PRODUCT_QUANTITY, productQuantity);
        mFormData.appendFormData(POST_QUOTES_PARAM_TIMESTAMP, timestamp);

        sync(action, intent, transactionHost, mFormData);
    }
}
