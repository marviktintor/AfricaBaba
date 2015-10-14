package com.origicheck.africababa.sync.services.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.origicheck.africababa.sync.engines.authengine.SyncAuthenticator;

/**
 * Created by victor on 10/14/2015.
 */
public class AuthService extends Service {

    private SyncAuthenticator syncAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        syncAuthenticator = new SyncAuthenticator(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAuthenticator.getIBinder();
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
