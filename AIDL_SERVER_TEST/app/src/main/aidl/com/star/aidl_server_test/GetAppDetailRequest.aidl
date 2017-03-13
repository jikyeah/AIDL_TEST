// GetAppDetailRequest.aidl.aidl
package com.star.aidl_server_test;

// Declare any non-default types here with import statements

interface GetAppDetailRequest {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String testMethod();
}
