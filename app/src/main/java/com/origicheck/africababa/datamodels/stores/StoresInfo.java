package com.origicheck.africababa.datamodels.stores;

/**
 * Created by victor on 10/2/2015.
 */
public class StoresInfo {
    private int storeId;
    private String store;
    private int locationId;
    private int productId;
    private int quantity;
    private long timeAdded;

    public StoresInfo(int storeId, String store, int locationId, int productId, int quantity, long timeAdded) {
        this.storeId = storeId;
        this.store = store;
        this.locationId = locationId;
        this.productId = productId;
        this.quantity = quantity;
        this.timeAdded = timeAdded;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStore() {
        return store;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}
