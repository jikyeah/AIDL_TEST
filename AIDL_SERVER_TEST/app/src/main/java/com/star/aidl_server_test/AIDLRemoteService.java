package com.star.aidl_server_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by linzenos on 2017/2/26.
 */

public class AIDLRemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private final GetAppDetailRequest.Stub mBinder = new GetAppDetailRequest.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String testMethod() throws RemoteException {
            return "testMethod:YES";
        }


    };
}
