package com.star.aidl_server_test;

import android.os.Bundle;

/**
 * Created by linzenos on 2017/2/26.
 */

public interface ICallback {
    void onResult(Bundle result);

    void onError(String code,String desc);

}
