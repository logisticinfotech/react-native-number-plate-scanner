package com.NumberPlateScanner;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.NumberPlateScanner.base.ScannerActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class NumberPlateScannerModule extends ReactContextBaseJavaModule {

    private static String TAG="NumberPlateScanner";
    ReactApplicationContext context;

    public NumberPlateScannerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        context=reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }

    @ReactMethod
    void scanNumberPlate() {
//        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, ScannerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
