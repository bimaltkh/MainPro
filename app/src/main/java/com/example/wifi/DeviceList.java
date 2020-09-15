package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.TabActivity.DataModel;
import com.example.wifi.TabActivity.SingletonSession;
import com.example.wifi.TabActivity.SingletonSession2;
import com.example.wifi.TabActivity.Tab2;
import com.example.wifi.TabActivity.TabMainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.wifi.TabActivity.Tab2.removeSimpleProgressDialog;

public class DeviceList extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    String name;
    ListView listView;
    String itemValue;




    String URLstring="https://mature-railroads.000webhostapp.com/jsondevicelist.JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
         listView = findViewById(R.id.mobile_list);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("strrrrr", ">>" + response);

                                try {
                                    removeSimpleProgressDialog();

                                    JSONObject obj = new JSONObject(response);
                                    if(obj.optString("status").equals("true")){

                                        JSONArray dataArray2  = obj.getJSONArray("data");

                                        for (int i = 0; i < dataArray2.length(); i++) {

                                            DataModel playerModel = new DataModel();
                                            JSONObject dataobj = dataArray2.getJSONObject(i);

                                            name=dataobj.getString("name");


                                           items.add(name);

                                        }
                                        ArrayAdapter adapter = new ArrayAdapter<String>(DeviceList.this,R.layout.listview, items);


                                        listView.setAdapter(adapter);



                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //displaying the error in toast if occurrs
                                Toast.makeText(DeviceList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                // request queue
                RequestQueue requestQueue = Volley.newRequestQueue(DeviceList.this);

                requestQueue.add(stringRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemValue = (String) listView.getItemAtPosition(position);
      String editValue=itemValue;
              SingletonSession2.Instance().setUsername(editValue);
              finish();



    }
});


    }
        }




