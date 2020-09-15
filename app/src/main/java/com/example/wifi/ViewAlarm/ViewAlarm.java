package com.example.wifi.ViewAlarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleService;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.R;
import com.example.wifi.TabActivity.UserManageAdapter;
import com.example.wifi.TabActivity.UserManagement;
import com.example.wifi.TabActivity.UserManagementData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.wifi.TabActivity.Tab2.removeSimpleProgressDialog;

public class ViewAlarm extends AppCompatActivity {
    ListView listViewalarm;
    ArrayList<ViewAlarmData> arrayList = new ArrayList<>();
    ViewAlarmAdapter viewAlarmAdapter;
String  URLstring="https://mature-railroads.000webhostapp.com/alarmlist.JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alarm);
        listViewalarm=findViewById(R.id.viewalarm);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);


                        try {
                            removeSimpleProgressDialog();

           /*                 JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){

                                JSONArray dataArray2  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray2.length(); i++) {

                                    JSONObject dataobj = dataArray2.getJSONObject(i);

                                    UserManagementData userManagementData=new UserManagementData();
                                    userManagementData.setName(dataobj.getString("name"));
                                    userManagementData.setRole(dataobj.getString("role"));
                                    arrayList.add(userManagementData);
                                    userManageAdapter  = new UserManageAdapter(UserManagement.this,arrayList);

                                    listView.setAdapter(userManageAdapter);

                               }

                      }*/
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length() ; i++) {
ViewAlarmData viewAlarmData=new ViewAlarmData();
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                viewAlarmData.setAlarmname (jsonObject.getString("name"));
                                arrayList.add(viewAlarmData);
                                viewAlarmAdapter  = new ViewAlarmAdapter(ViewAlarm.this,arrayList);

                                listViewalarm.setAdapter(viewAlarmAdapter);
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
                        Toast.makeText(ViewAlarm.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(ViewAlarm.this);

        requestQueue.add(stringRequest);

    }
}