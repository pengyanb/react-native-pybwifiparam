package com.pengyanb.pybwifiparam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by Peng on 30/03/17.
 */

public class PybWifiParamModule extends ReactContextBaseJavaModule {
    public PybWifiParamModule(ReactApplicationContext reactContext){
        super(reactContext);
    }

    @Override
    public String getName() {
        return "PybWifiParam";
    }
    @ReactMethod
    public void getWifiSSID(Callback callback){
        String SSID;
        Context context = this.getReactApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfo != null && networkInfo.isConnected()){
            WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            SSID = wifiInfo.getSSID();
            if(SSID.startsWith("\"") && SSID.endsWith("\""))
                SSID = SSID.substring(1, SSID.length()-1);
            callback.invoke(null, SSID);
        }
        else{
            callback.invoke("Error: unable to retrieve SSID.", null);
        }
    }
}
