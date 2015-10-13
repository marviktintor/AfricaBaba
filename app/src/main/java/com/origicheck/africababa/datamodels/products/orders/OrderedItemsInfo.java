package com.origicheck.africababa.datamodels.products.orders;

/**
 * Created by victor on 10/8/2015.
 */
public class OrderedItemsInfo {


    private int orderId;
    private int productId;
    private int supplierId;
    private int buyerId;
    private long timeAdded;
    private int quantity;
    private float price;

    public OrderedItemsInfo(int orderId, int productId, int supplierId, int buyerId, long timeAdded, int quantity, float price) {
        this.orderId = orderId;
        this.productId = productId;
        this.supplierId = supplierId;
        this.buyerId = buyerId;
        this.timeAdded = timeAdded;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public long getTimeAdded() {
        return timeAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }
}
