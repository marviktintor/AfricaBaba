package com.origicheck.africababa.controller.downloader;

import android.content.Context;

/**
 * Created by victor on 10/26/2015.
 */
public final class Downloader {

    private static Context context;

    private Downloader() {}

    public static Downloader getInstance(Context context) {
        Downloader.context = context;
        return new Downloader();
    }

    public void downloadFile(String fileUri) {

    }
}
