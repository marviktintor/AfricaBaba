package com.origicheck.africababa.datamodels.dashboard;

import android.app.Fragment;

/**
 * Created by victor on 10/1/2015.
 */
public class DashboardInfo {
    private String itemTitle;
    private String itemDescription;
    private Fragment itemFragment;

    public DashboardInfo(String itemTitle, String itemDescription, Fragment itemFragment) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemFragment = itemFragment;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Fragment getItemFragment() {
        return itemFragment;
    }
}
