package com.example.wifi.TabActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.wifi.TabActivity.Tab2.removeSimpleProgressDialog;

public class UserManagement extends AppCompatActivity {
    ListView listView;
    String itemValue;
    Button button;
    ArrayList<UserManagementData> arrayList = new ArrayList<>();
    UserManageAdapter userManageAdapter;
    Button addbutton;
    String URLstring="https://mature-railroads.000webhostapp.com/retrievedata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
        listView = findViewById(R.id.system_users_listview);

        addbutton=findViewById(R.id.button2);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss=new Intent(UserManagement.this,NewUser.class);
                startActivity(ss);
                finish();
            }
        });
        getSupportActionBar().setTitle("User Management");
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
                                UserManagementData userManagementData=new UserManagementData();
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                userManagementData.setName (jsonObject.getString("name"));
                                userManagementData.setRole(jsonObject.getString("role"));
                                arrayList.add(userManagementData);
                                userManageAdapter  = new UserManageAdapter(UserManagement.this,arrayList);

                                listView.setAdapter(userManageAdapter);
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
                        Toast.makeText(UserManagement.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(UserManagement.this);

        requestQueue.add(stringRequest);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ss=new Intent(UserManagement.this,DeviceListhome.class);
        startActivity(ss);
    }
}
