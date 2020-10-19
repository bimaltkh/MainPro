package com.example.wifi.TabActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.DeviceList;
import com.example.wifi.R;
import com.example.wifi.RoleDatabaseRoom.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.wifi.TabActivity.Tab2.removeSimpleProgressDialog;

public class DeviceListhome extends AppCompatActivity {

    ListView listView;
    String itemValue;
    Button addsite;
    ArrayList<MyData> arrayList = new ArrayList<>();
    MyAdapter adapter;

    String URLstring = "https://mature-railroads.000webhostapp.com/jsondevicelist.JSON";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_listhome);
        listView = findViewById(R.id.mobile_list2);
         addsite=findViewById(R.id.addsite);
         addsite.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent ss=new Intent(DeviceListhome.this,NewSite.class);
                 startActivity(ss);
             }
         });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);


                        try {
                            removeSimpleProgressDialog();

                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("status").equals("true")) {


                                JSONArray dataArray2 = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray2.length(); i++) {


                                    JSONObject dataobj = dataArray2.getJSONObject(i);

                                    MyData myData = new MyData();
                                    myData.setName(dataobj.getString("name"));
                                    myData.setColor(dataobj.getString("color"));
                                    myData.setAlarm1(dataobj.getString("alarm1"));
                                    myData.setAlarm2(dataobj.getString("alarm2"));
                                    myData.setAlarm3(dataobj.getString("alarm3"));
                                    arrayList.add(myData);
                                    adapter = new MyAdapter(DeviceListhome.this, arrayList);
                                    listView.setAdapter(adapter);

                                }

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
                        Toast.makeText(DeviceListhome.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(DeviceListhome.this);

        requestQueue.add(stringRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                itemValue = arrayList.get(position).getName();
                Toast.makeText(DeviceListhome.this, itemValue, Toast.LENGTH_SHORT).show();


                SingletonSession.Instance().setUsername(itemValue);

                Intent iso = new Intent(getApplicationContext(), TabMainActivity.class);
                startActivity(iso);
                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.devicelist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.system_roles:
                Intent ss = new Intent(DeviceListhome.this, MainActivity.class);
                startActivity(ss);
                break;
            case R.id.system_users:
                Intent tt = new Intent(DeviceListhome.this, UserManagement.class);
                startActivity(tt);
                break;
            // do your code
            default:

        }
        return super.onOptionsItemSelected(item);

    }
}
