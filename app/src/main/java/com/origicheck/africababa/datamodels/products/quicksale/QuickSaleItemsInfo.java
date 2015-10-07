package com.origicheck.africababa.datamodels.products.quicksale;

/**
 * Created by victor on 10/7/2015.
 */
public class QuickSaleItemsInfo {

    private int quickSaleId;
    private int productId;
    private long timeAddded;

    public QuickSaleItemsInfo(int quickSaleId, int productId, long timeAdded) {
        this.quickSaleId = quickSaleId;
        this.productId = productId;
        this.timeAddded = timeAdded;
    }

    public int getQuickSaleId() {
        return quickSaleId;
    }

    public int getProductId() {
        return productId;
    }

    public long getTimeAdded() {
        return timeAddded;
    }
}
