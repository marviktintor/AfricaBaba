package com.origicheck.africababa.sync.engines.syncengine;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.origicheck.africababa.controller.utils.Utils;
import com.origicheck.africababa.sync.worker.SyncExecutorThread;

/**
 * Created by victor on 10/14/2015.
 */
public class Syncer extends AbstractThreadedSyncAdapter {

    private SyncExecutorThread syncExecutorThread;

    public Syncer(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        syncExecutorThread = new SyncExecutorThread(context, new Utils(getContext()));
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        syncExecutorThread.start();
    }


}
