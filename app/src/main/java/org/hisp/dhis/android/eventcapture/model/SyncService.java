package org.hisp.dhis.android.eventcapture.model;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static SyncAdapter sSyncAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        /* Create syncAdapter as a singleton. */
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true, true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}