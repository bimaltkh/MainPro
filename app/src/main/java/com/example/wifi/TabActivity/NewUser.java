package com.example.wifi.TabActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.MainActivity;
import com.example.wifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] role = {"Role1","Role2","Role3","Role4"};
    String[] supervisor = {"Supervisor1","Supervisor2","Supervisor3","Supervisor4"};
    EditText name,emailid,address,mobileno,password;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getSupportActionBar().setTitle("Add User");
        name=findViewById(R.id.name);
        emailid=findViewById(R.id.email);
        address=findViewById(R.id.address);
        mobileno=findViewById(R.id.mobile);
        password=findViewById(R.id.password);
        create=findViewById(R.id.createbutton);
        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        final Spinner spin1 = (Spinner) findViewById(R.id.spinner2);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) NewUser.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(NewUser.this,android.R.layout.simple_spinner_item,role);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        spin1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) NewUser.this);

        ArrayAdapter ab = new ArrayAdapter(NewUser.this,android.R.layout.simple_spinner_item,supervisor);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(ab);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!(name.getText().toString().isEmpty() || emailid.getText().toString().isEmpty() || address.getText().toString().isEmpty() ||
                mobileno.getText().toString().isEmpty() || password.getText().toString().isEmpty())) {
                    final String spi=spin.getSelectedItem().toString();
                    final String spi2=spin1.getSelectedItem().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mature-railroads.000webhostapp.com/adduser.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server

                            Toast.makeText(NewUser.this, response, Toast.LENGTH_LONG).show();
                            Log.d("adg" ,response);
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json_obj = jsonArray.getJSONObject(i);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                        }

                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
//Adding parameters to request

                    params.put("name",name.getText().toString());
                    params.put("email_id",emailid.getText().toString());
                    params.put("address",address.getText().toString());
                    params.put("role",spi);
                    params.put("supervisor",spi2);
                    params.put("mobileno",mobileno.getText().toString());
                    params.put("password",password.getText().toString());


//returning parameter
                    return params;
                }
            };

//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(NewUser.this);
            requestQueue.add(stringRequest);
                    Intent nn= new Intent(NewUser.this,UserManagement.class);
                    startActivity(nn);
                    finish();

        }
else
        {
            Toast.makeText(NewUser.this, "enter values correctly", Toast.LENGTH_LONG).show();

        }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ss= new Intent(NewUser.this,UserManagement.class);
        startActivity(ss);
    }
}





