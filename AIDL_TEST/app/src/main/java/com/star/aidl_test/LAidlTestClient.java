package com.star.aidl_test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.star.aidl_server_test.LTestInterface;

public class LAidlTestClient extends Activity implements OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button btn = null;
    private Button btn1 = null;
    private Button btn2 = null;
    private Button btn3 = null;
    private Button btn4 = null;
    private TextView text = null;
    private LTestInterface lTestInterface = null;

    private boolean isBind = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn = (Button) findViewById(R.id.Button01);
        btn1 = (Button) findViewById(R.id.Button02);
        btn2 = (Button) findViewById(R.id.Button03);
        btn3 = (Button) findViewById(R.id.Button04);
        btn4 = (Button) findViewById(R.id.Button05);
        text = (TextView) findViewById(R.id.TextView01);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        int viewId = v.getId();
        try {
            if (viewId == btn.getId()) {
                Intent intent = new Intent();
                intent.setAction("com.star.aidl_server_test.LTestInterface");
                intent.setPackage("com.star.aidl_server_test");

                isBind = bindService(intent, connection, BIND_AUTO_CREATE);

            } else if (viewId == btn1.getId()) {
                if (lTestInterface == null) {
                    text.setText("lTestInterface is null");
                } else {
                    text.setText("lTestInterface:" + lTestInterface.getServiceName());
                }
            } else if (viewId == btn2.getId()) {

            } else if (viewId == btn3.getId()) {

            } else if (viewId == btn4.getId()) {

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            lTestInterface = LTestInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();


    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        if (connection != null && isBind) {
            unbindService(connection);
            isBind = false;
        }
    }
}