package com.origicheck.africababa.sync.services.syncer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.origicheck.africababa.sync.engines.syncengine.Syncer;

/**
 * Created by victor on 10/14/2015.
 */
public class SyncerService extends Service {

    private Object syncLocker;
    private Syncer syncer;

    @Override
    public void onCreate() {
        super.onCreate();

        syncLocker = new Object();

        synchronized (syncLocker) {
            syncer = new Syncer(getApplicationContext(), true);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
