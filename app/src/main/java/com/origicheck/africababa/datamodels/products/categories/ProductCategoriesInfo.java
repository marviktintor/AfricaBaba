package com.origicheck.africababa.datamodels.products.categories;

/**
 * Created by victor on 10/2/2015.
 */
public class ProductCategoriesInfo {
    private int categoryId;
    private String category;
    private long timeAdded;

    public ProductCategoriesInfo(int categoryId, String category, long timeAdded) {
        this.categoryId = categoryId;
        this.category = category;
        this.timeAdded = timeAdded;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategory() {
        return category;
    }

    public long getTimeAdded() {
        return timeAdded;
    }
}
