package com.star.aidl_test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.star.aidl_server_test.GetAppDetailRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnBindService;
    private Button btnStartMethod;

    private GetAppDetailRequest mMyAIDL;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            mMyAIDL = GetAppDetailRequest.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected");
            mMyAIDL = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnBindService = (Button) findViewById(R.id.btn_bind_service);
        btnStartMethod = (Button) findViewById(R.id.btn_start_method);
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.star.aidl_server_test.GetAppDetailRequest");
                intent.setPackage("com.star.aidl_server_test");
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        btnStartMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mMyAIDL.testMethod();
                } catch (RemoteException e) {
                    Toast.makeText(MainActivity.this, "服务被异常杀死，请重新开启。", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
