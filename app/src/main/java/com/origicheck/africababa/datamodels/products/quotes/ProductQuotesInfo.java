package com.origicheck.africababa.datamodels.products.quotes;

/**
 * Created by victor on 10/13/2015.
 */
public class ProductQuotesInfo {

    private int quoteId;
    private String productName;
    private String productDescription;
    private int productQuantity;
    private long timeAdded;

    public ProductQuotesInfo(int quoteId, String productName, String productDescription, int productQuantity, long timeAdded) {
        this.quoteId = quoteId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.timeAdded = timeAdded;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}
