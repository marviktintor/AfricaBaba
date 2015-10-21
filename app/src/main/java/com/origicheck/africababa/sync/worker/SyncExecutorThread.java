package com.origicheck.africababa.sync.worker;

import android.content.Context;
import android.util.Log;

import com.origicheck.africababa.controller.utils.Utils;

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
    // Query
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
    private FormData mFormData = null;
    private Utils utils;
    private Context context;

    public SyncExecutorThread(Context context) {
        this.context = context;
        initAll();
    }

    public Context getContext() {
        return context;
    }

    void initAll() {
        utils = new Utils(getContext());
    }

    public Utils getUtils() {
        return utils;
    }

    @Override
    public void run() {
        super.run();
        syncAll();
    }

    private int getUserId() {
        // TODO Auto-generated method stub
        return 1;
    }

    public FormData getFormData() {
        return mFormData;
    }

    public void serverSync(String transactionHost, String formData) throws Exception {
        try {
            URL url = new URL("http://192.168.43.47/africababa/worker.php");

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

            getUtils().getTransactionsManager().consumeSyncData(transactionHost, stringBuffer.toString());

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

    private void sync(String action, String intent, String transactionHost,
                      FormData formData) {
        if (getFormData() == null) {
            printError("Form data is null");
            return;
        }

        formData.appendFormData(USER_ID, "" + getUserId());
        formData.appendFormData(ACTION, action);
        formData.appendFormData(CLIENT, CLIENT_MOBILE_ANDROID);
        formData.appendFormData(INTENT, intent);
        formData.appendFormData(TRANSACTION_HOST, transactionHost);

        if (formData == null || formData.getFormDataBuilder().equals("")) {
            printError("Could not build Form data to send");
            return;
        }
        syncFormData(transactionHost, formData.getData());
    }

    private void syncFormData(final String transactionHost, final String formData) {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSync(transactionHost, formData);
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

        printData("Syncing Users");
        syncUsers();
    }

    private <T> void printError(String data) {
        // TODO Auto-generated method stub
        Log.i("SYNC_EXECUTOR", data);
    }

    private <T> void printData(String data) {
        // TODO Auto-generated method stub
        Log.i("SYNC_EXECUTOR", data);
    }

}
