package com.origicheck.africababa.database.tables;

import android.net.Uri;

/**
 * Created by victor on 8/24/2015.
 */
public class Tables {

    public static class TblBuyers {
        public static final String COL_BUYER_ID = "buyer_id";
        public static final String COL_USER_ID = "user_id";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_buyers";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_BUYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_ID + " integer NOT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblCatalogue {
        public static final String COL_ID_SALE = "id_sale";
        public static final String COL_PRODUCT_ID = "product_id";
        public static final String COL_QUANTITY = "quantity";
        public static final String COL_AVAILABLE = "available";
        public static final String COL_STORE_ID = "store_id";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_catalogue";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_ID_SALE + " integer NOT NULL," + COL_PRODUCT_ID + " integer NOT NULL," + COL_QUANTITY + " integer NOT NULL," + COL_AVAILABLE + " integer NOT NULL," + COL_STORE_ID + " integer NOT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblCategory {
        public static final String COL_CAT_ID = "cat_id";
        public static final String COL_CAT_NAME = "cat_name";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_category";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_CAT_NAME + " varchar DEFAULT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblCertificate {
        public static final String COL_CERTIFICATE_ID = "certificate_id";
        public static final String COL_CERTIFICATE_TYPE = "certificate_type";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_certificate";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_CERTIFICATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_CERTIFICATE_TYPE + " varchar DEFAULT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblFiles {
        public static final String COL_FILE_ID = "file_id";
        public static final String COL_FILE_URL_NAME = "file_url_name";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_files";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_FILE_ID + " integer NOT NULL DEFAULT '0'," + COL_FILE_URL_NAME + " varchar DEFAULT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblGroup {
        public static final String COL_GROUP_ID = "group_id";
        public static final String COL_GROUP_NAME = "group_name";
        public static final String COL_CAT_ID = "cat_id";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_group";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_GROUP_NAME + " varchar DEFAULT NULL," + COL_CAT_ID + " integer," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblLocation {
        public static final String COL_LOCATION_ID = "location_id";
        public static final String COL_ADDRESS = "address";
        public static final String COL_LOCATION_LAT = "location_lat";
        public static final String COL_LOCATION_LONG = "location_long";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_location";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_ADDRESS + " varchar DEFAULT NULL," + COL_LOCATION_LAT + " varchar DEFAULT NULL," + COL_LOCATION_LONG + " varchar DEFAULT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblMessages {
        public static final String COL_MESSAGE_ID = "message_id";
        public static final String COL_SUPPLIER_ID = "supplier_id";
        public static final String COL_TIME_ADDED = "time_added";
        public static final String COL_MESSAGE = "message";
        public static final String COL_FILE_ID = "file_id";

        public static final String TABLE_NAME = "tbl_messages";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SUPPLIER_ID + " integer," + COL_TIME_ADDED + " long," + COL_MESSAGE + " varchar DEFAULT NULL," + COL_FILE_ID + " integer" + ");";
    }

    public static class TblOrder {
        public static final String COL_ORDER_ID = "order_id";
        public static final String COL_PRODUCT_ID = "product_id";
        public static final String COL_SUPPLIER_ID = "supplier_id";
        public static final String COL_BUYER_ID = "buyer_id";
        public static final String COL_TIME_ADDED = "time_added";
        public static final String COL_QUANTITY = "quantity";
        public static final String COL_PRICE = "price";

        public static final String TABLE_NAME = "tbl_order";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_PRODUCT_ID + " integer," + COL_SUPPLIER_ID + " integer," + COL_BUYER_ID + " integer," + COL_TIME_ADDED + " long," + COL_QUANTITY + " integer," + COL_PRICE + " integer" + ");";
    }

    public static class TblProducts {
        public static final String COL_PRODUCT_ID = "product_id";
        public static final String COL_PRODUCT_DESCRIPTION = "product_desc";
        public static final String COL_PRODUCT_NAME = "product_name";
        public static final String COL_TIME_ADDED = "time_added";
        public static final String COL_SUPPLIER_ID = "supplier_id";
        public static final String COL_STORE_ID = "store_id";
        public static final String COL_QUICK_SALE_ID = "quick_sale_id";
        public static final String COL_MOQ = "MOQ";
        public static final String COL_GROUP_ID = "group_id";
        public static final String COL_FILE_ID = "file_id";
        public static final String COL_PRICE = "price";

        public static final String TABLE_NAME = "tbl_products";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_PRODUCT_NAME + " varchar DEFAULT NULL," + COL_PRODUCT_DESCRIPTION + " varchar DEFAULT NULL," + COL_TIME_ADDED + " long," + COL_SUPPLIER_ID + " integer," + COL_STORE_ID + " integer," + COL_QUICK_SALE_ID + " integer," + COL_MOQ + " integer," + COL_GROUP_ID + " integer," + COL_FILE_ID + " varchar(251) DEFAULT NULL," + COL_PRICE + " varchar" + ");";
    }

    public static class TblQuicksale {
        public static final String COL_QUICK_SALE_ID = "quick_sale_id";
        public static final String COL_TIME_ADDED = "time_added";
        public static final String COL_PRODUCT_ID = "product_id";

        public static final String TABLE_NAME = "tbl_quick_sale";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_QUICK_SALE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TIME_ADDED + " long," + COL_PRODUCT_ID + " integer" + ");";
    }

    public static class TblQuotes {
        public static final String COL_QUOTE_ID = "Quote_id";
        public static final String COL_PRODUCT_NAME = "product_name";
        public static final String COL_PRODUCT_ID = "product_id";
        public static final String COL_PRODUCT_DESCRIPTION = "product_description";
        public static final String COL_PRODUCT_QUANTITY = "product_quantity";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_quotes";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_QUOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_PRODUCT_NAME + " varchar DEFAULT NULL," + COL_PRODUCT_ID + " integer," + COL_PRODUCT_DESCRIPTION + " varchar DEFAULT NULL," + COL_PRODUCT_QUANTITY + " integer," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblStore {
        public static final String COL_STORE_ID = "store_id";
        public static final String COL_PRODUCTS_ID = "products_id";
        public static final String COL_QUANTITY = "quantity";
        public static final String COL_LOCATION_ID = "location_id";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_store";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_PRODUCTS_ID + " integer," + COL_QUANTITY + " integer," + COL_LOCATION_ID + " integer," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblSuppliers {
        public static final String COL_SUPPLIER_ID = "supplier_id";
        public static final String COL_USER_ID = "user_id";
        public static final String COL_CERTIFICATE_ID = "certificate_id";
        public static final String COL_LOCATION_ID = "location_id";
        public static final String COL_SUPPLIER_TYPE = "Supplier_type";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_suppliers";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_ID + " integer NOT NULL," + COL_CERTIFICATE_ID + " integer NOT NULL," + COL_LOCATION_ID + " integer NOT NULL," + COL_SUPPLIER_TYPE + " integer NOT NULL," + COL_TIME_ADDED + " long" + ");";
    }

    public static class TblUsers {
        public static final String COL_USER_ID = "user_id";
        public static final String COL_FILE_ID = "file_id";
        public static final String COL_FULL_NAME = "full_name";
        public static final String COL_USER_PHONE_NUMBER = "user_phone_number";
        public static final String COL_USER_EMAIL = "user_email";
        public static final String COL_USERNAME = "username";
        public static final String COL_USER_PASSWORD = "user_password";
        public static final String COL_TIME_ADDED = "time_added";

        public static final String TABLE_NAME = "tbl_users";

        public static final Uri CONTENT_URI = Uri.parse("content://com.origicheck.africababa.database.provider.DataProvider/" + TABLE_NAME);

        public static final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_FILE_ID + " integer NOT NULL," + COL_FULL_NAME + " varchar NOT NULL," + COL_USER_PHONE_NUMBER + " varchar NOT NULL," + COL_USER_EMAIL + " varchar NOT NULL," + COL_USERNAME + " varchar NOT NULL," + COL_USER_PASSWORD + " varchar NOT NULL," + COL_TIME_ADDED + " long" + ");";
    }



}
