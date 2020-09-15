package com.example.wifi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;
import static android.os.ParcelFileDescriptor.MODE_APPEND;

class WifiReceiver extends BroadcastReceiver {
    WifiManager wifiManager;
    StringBuilder sb;
    ListView wifiDeviceList;
  String ssid;
  String key;

   //public  String state;
   public String pass;
    public WifiReceiver() {
    }
    public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList, String user, String pass) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
        this.ssid=user;
        this.key=pass;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //  Log.i("Receiver", "Broadcast received: " + action);



        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                sb = new StringBuilder();
                List<ScanResult> wifiList = wifiManager.getScanResults();
                ArrayList<String> deviceList = new ArrayList<>();
                for (ScanResult scanResult : wifiList) {
                    sb.append("\n").append(scanResult.SSID);//.append(" - ");//.append(scanResult.capabilities);
                    deviceList.add(scanResult.SSID);// + " - " + scanResult.capabilities);
                }
                //Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();
                ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceList.toArray());
                wifiDeviceList.setAdapter(arrayAdapter);
                    WifiConfiguration wifiConfig = new WifiConfiguration();
                    wifiConfig.SSID = String.format("\"%s\"", ssid);
                    wifiConfig.preSharedKey = String.format("\"%s\"", key);



              WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
////remember id

                int netId = wifiManager.addNetwork(wifiConfig);
             //wifiManager.disconnect();
                wifiManager.enableNetwork(netId, false);

           wifiManager.reconnect();
//


                }
            }













}



