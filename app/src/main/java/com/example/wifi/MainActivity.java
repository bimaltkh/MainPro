package com.example.wifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.OnLifecycleEvent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wifi.TabActivity.TabMainActivity;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  final int MY_PERMISSIONS_ACCESS_FINE_LOCATION =1 ;
    ListView wifiLi;
    WifiManager wifiManager;
    private final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1;
    WifiReceiver receiverWifi;
    public String value;
    public String itemValue;
    String username = " ";
    String pass = " ";
    String wifiID=" ";
    IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
        SharedPreferences shared = this.getPreferences(Context.MODE_PRIVATE);

        wifiID = shared.getString("id", "fjfifjwjfeifjiwo");

        //  Toast.makeText(MainActivity.this,wifiID, Toast.LENGTH_SHORT).show();
        wifiLi = findViewById(R.id.wifiList);

//turning wifi & location on or off ,asking permission

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!(wifiManager.isWifiEnabled())) {
            Toast.makeText(getApplicationContext(), "Turning WiFi ON...", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
        }
        wifiManager.startScan();
//getting connection details


//
//     WifiInfo wifiInfo = null;
//     if (wifiManager != null)
//         wifiInfo = wifiManager.getConnectionInfo();
//     String ssid = null;
//     if (wifiInfo != null)
//         ssid = wifiInfo.getSSID();
//     Toast.makeText(MainActivity.this,ssid,Toast.LENGTH_SHORT).show();

        //  checking if wifi connected previously

        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if (mWifi.isConnected()) {
            for (WifiConfiguration i : list) {
                if (i.SSID != null && i.SSID.equals("\"" + wifiID + "\"")) {
                    //wifiManager.disconnect();
                    wifiManager.enableNetwork(i.networkId, true);
                    // wifiManager.reconnect();
                    Toast.makeText(MainActivity.this, "good", Toast.LENGTH_SHORT).show();
                    Intent hol = new Intent(MainActivity.this, LoginPage.class);
                    startActivity(hol);
                    break;
                }
            }
        } else if (!(mWifi.isConnected())) {
            Toast.makeText(MainActivity.this, "Wifi not Connected", Toast.LENGTH_SHORT).show();
            for (WifiConfiguration i : list) {
                if (i.SSID != null && i.SSID.equals("\"" + wifiID + "\"")) {
                    //  wifiManager.disconnect();
                    wifiManager.enableNetwork(i.networkId, true);
                   // wifiManager.reconnect();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                            if (mWifi.isConnected()) {
                                Toast.makeText(MainActivity.this, "inside con", Toast.LENGTH_SHORT).show();


                                Intent ss = new Intent(MainActivity.this, LoginPage.class);
                                startActivity(ss);
                                finish();
                            }

                        }
                    }, 7000);

                }
            }
        }

//                Intent hh = new Intent(MainActivity.this,TabMainActivity.class);
//                //  Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
//                startActivity(hh);
//           Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//
//                                        if (mWifi.isConnected()) {
//                                            Toast.makeText(MainActivity.this, "inside con", Toast.LENGTH_SHORT).show();
//
//
//                                    Intent ss = new Intent(MainActivity.this, TabMainActivity.class);
//                                            startActivity(ss);
//                                            finish();
//                                        }
//
//                                    }
//                                }
//
//
//                    , 7000);
//

            //hh();
            wifiLi.setOnItemClickListener(listClick);

            receiverWifi = new WifiReceiver(wifiManager, wifiLi, username, pass);
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
            registerReceiver(receiverWifi, intentFilter);


        }


    // ConnectivityManager connManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    // final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    // ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    // NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        @Override
        protected void onStart () {
            super.onStart();

        }




        private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                 NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


                        itemValue = (String) wifiLi.getItemAtPosition(position);
                        username = itemValue;
//                  if (mWifi.isConnected() && username.equals(wifiID)) {
//
//                  }




                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                        alert.setTitle(itemValue);
                        alert.setMessage("Enter the password");

// Set an EditText view to get user input
                        final EditText input = new EditText(MainActivity.this);
                        alert.setView(input);
                        //set button


                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        value = input.getText().toString();
                        pass = value;
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();


                final  WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                        receiverWifi = new WifiReceiver(wifiManager, wifiLi, username, pass);
//                            IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                        registerReceiver(receiverWifi, intentFilter);


                        wifiManager.startScan();


                        wifiID = itemValue;

                        Toast.makeText(MainActivity.this, wifiID, Toast.LENGTH_SHORT).show();

                        final SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("id", wifiID);
                        editor.apply();
//
                         Handler handler = new Handler();
                         handler.postDelayed(new Runnable() {
                            @Override
                                 public void run() {
                                     ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                     NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                                     //   WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//                                WifiInfo wifiInfo = null;
//                                        if (wifiManager != null)
//                                            wifiInfo = wifiManager.getConnectionInfo();
//                                        String ssid = null;
//                                        if (wifiInfo != null)
//                                            ssid = wifiInfo.getSSID();

                                     if (mWifi.isConnected()) {

                                         {
                                             Intent ff = new Intent(MainActivity.this, LoginPage.class);
                                             startActivity(ff);
                                         }
                                     }else {


                                     if    (!(mWifi.isConnected())) {


                                             Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                                         }

                                     }
                            }

                        }, 7000);


                        // WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


//

//Toast.makeText(MainActivity.this,loid,Toast.LENGTH_SHORT).show();


                        // sharedPref = getSharedPreferences("mypref", MODE_PRIVATE);
                        //  editor.putString("id", "\"" + itemValue + "\"");
                        //  editor.commit();


////


                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

                 //   } else {
//                        WifiManager wifiManag = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//                        WifiInfo wifiInfo = null;
//                        if (wifiManag != null)
//                            wifiInfo = wifiManager.getConnectionInfo();
//                        String ssid = null;
//                        if (wifiInfo != null)
//                            ssid = wifiInfo.getSSID();
//                        if (ssid.equals(loid)) {
//                            Intent ffc = new Intent(MainActivity.this, homepage.class);
//                            startActivity(ffc);

                        }


                  //  }

            ///    }
//
           // }


        };



        @Override
        protected void onPostResume () {
            super.onPostResume();
//            receiverWifi = new WifiReceiver(wifiManager, wifiLi, username, pass);
//            IntentFilter intentFilter = new IntentFilter();
//            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
//            registerReceiver(receiverWifi, intentFilter);
            // getWifi();
        }


//       private void getWifi() {
//           ConnectivityManager connManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

//           NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
//           boolean isConnected = activeNetwork != null &&
//                   activeNetwork.isConnectedOrConnecting();
//           if(isConnected) {
//               if (true) {


//                   Toast.makeText(MainActivity.this, "connrc", Toast.LENGTH_SHORT).show();
//               }
//           }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //Toast.makeText(MainActivity.this, "version> = marshmallow", Toast.LENGTH_SHORT).show();
//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED) {
//              //  Toast.makeText(MainActivity.this, "location turned off", Toast.LENGTH_SHORT).show();
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                        MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
//            } else {
//                //Toast.makeText(MainActivity.this, "location turned on", Toast.LENGTH_SHORT).show();
//                wifiManager.startScan();
//            }
//        } else {
//            Toast.makeText(MainActivity.this, "scanning", Toast.LENGTH_SHORT).show();
//            wifiManager.startScan();
//
 //       }



        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case MY_PERMISSIONS_ACCESS_COARSE_LOCATION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "permission granted", Toast.LENGTH_SHORT).show();
                        wifiManager.startScan();
                    } else {
                        Toast.makeText(MainActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }

        }

    }






