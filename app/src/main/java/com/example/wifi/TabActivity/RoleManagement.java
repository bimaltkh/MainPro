package com.example.wifi.TabActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.wifi.TabActivity.Tab2.removeSimpleProgressDialog;

public class RoleManagement extends AppCompatActivity {
    Button addrole;
    ArrayList<RoleManagementData> arrayList = new ArrayList<>();
    RoleManagementAdapter roleManageAdapter;
    ListView listView;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    String URLstring="https://mature-railroads.000webhostapp.com/retrievedatarole.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_management);
        getSupportActionBar().setTitle("Role Management");
        addrole=findViewById(R.id.addrole);
        addrole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss=new Intent(RoleManagement.this, NewRole.class);
                startActivity(ss);
            }
        });

        listView=findViewById(R.id.list_role);
        /*
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        Toast.makeText(RoleManagement.this,date,Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);


                        try {
                            removeSimpleProgressDialog();

           *//*                 JSONObject obj = new JSONObject(response);
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

                      }*//*
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                RoleManagementData roleManagementData=new RoleManagementData();
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                roleManagementData.setRole (jsonObject.getString("name"));
                                roleManagementData.setDate(jsonObject.getString("expdate"));
                                String expdate=(jsonObject.getString("expdate"));
                                arrayList.add(roleManagementData);
                                try{

                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


                                    String str1 =date;
                                    Date date1 = formatter.parse(str1);

                                    String str2 = expdate;
                                    Date date2 = formatter.parse(str2);

                                    if (date1.compareTo(date2)<0)
                                    {
                                        System.out.println("date2 is Greater than my date1");
                                        roleManagementData.setStatus("Active");
                                    }else {
                                        roleManagementData.setStatus("Expired");
                                    }

                                }catch (ParseException e1){
                                    e1.printStackTrace();
                                }
                                roleManageAdapter  = new RoleManagementAdapter(RoleManagement.this,arrayList);

                                listView.setAdapter(roleManageAdapter);
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
                        Toast.makeText(RoleManagement.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(RoleManagement.this);

        requestQueue.add(stringRequest);*/

RoleManagementData roleManagementData=new RoleManagementData();
String ss="hdhd";
String dd="adkfjdf";
String si="asdasdf";
       /* Role role= new Role();
        roleManagementData.setRole(role.rolename="aaaa");
        roleManagementData.setDate(role.expirydate="adafd");*/
        roleManagementData.setStatus(si);
        arrayList.add(roleManagementData);
        roleManageAdapter  = new RoleManagementAdapter(RoleManagement.this,arrayList);
        listView.setAdapter(roleManageAdapter);

    }
}