package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.TabActivity.CustDeviceList;
import com.example.wifi.TabActivity.DeviceListhome;
import com.example.wifi.TabActivity.TabMainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
    Button login;
    EditText name, pass;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login = findViewById(R.id.logbutton);
        name = findViewById(R.id.usernameET);
        pass = findViewById(R.id.passwordET);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.buttonanim);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.VISIBLE);
                login.startAnimation(animation);
                if(!(name.getText().toString().isEmpty())) {
                    if (pass.getText().toString().isEmpty()){
                        pass.setError( "password is required!" );
                    }else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://mature-railroads.000webhostapp.com/passcheck.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
  //If we are getting success from server
        Toast.makeText(LoginPage.this, response, Toast.LENGTH_LONG).show();
        if(response.equals("success")) {


            Intent iso = new Intent(getApplicationContext(),DeviceListhome.class);
            startActivity(iso);
        }

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

                                params.put("name", name.getText().toString());
                                params.put("password", pass.getText().toString());

//returning parameter
                                return params;
                            }
                        };
                        //Adding the string request to the queue
                        RequestQueue requestQueue = Volley.newRequestQueue(LoginPage.this);
                        requestQueue.add(stringRequest);
                    }
                }else {
                    name.setError("username is required");
                }

            }

        });
    }
}
