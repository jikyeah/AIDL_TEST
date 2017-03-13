package com.star.aidl_server_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by linzenos on 14-7-8.
 */
public class LTestService extends Service {
    @Override
    public void onCreate() {
        Log.d("impp", "LTestService oncreate...");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("impp", "LTestService onStartCommand..." + " " + startId + " " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }

    private LTestInterface.Stub ibinder = new LTestInterface.Stub() {
        @Override
        public String getServiceName() throws RemoteException {
            return "this is getServiceName";//+LTestService.class.getName();
        }
    };

}
