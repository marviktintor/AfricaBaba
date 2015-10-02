package com.origicheck.africababa.datamodels.products.groups;

/**
 * Created by victor on 10/2/2015.
 */
public class ProductGroupsInfo {
    private int productGroupId;
    private String productGroup;
    private int productCategoryId;
    private long timeAdded;

    public ProductGroupsInfo(int productGroupId, String productGroup, int productCategoryId, long timeAdded) {
        this.productGroupId = productGroupId;
        this.productGroup = productGroup;
        this.productCategoryId = productCategoryId;
        this.timeAdded = timeAdded;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}
