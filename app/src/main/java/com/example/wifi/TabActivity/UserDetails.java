package com.example.wifi.TabActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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

import static com.example.wifi.TabActivity.Tab2.removeSimpleProgressDialog;

public class UserDetails extends AppCompatActivity {
    EditText name, email, address, role, supervisor, mobile, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        role = findViewById(R.id.role);
        supervisor = findViewById(R.id.supervisor);
        mobile = findViewById(R.id.mobile);
        pass = findViewById(R.id.password);
      /*  StringRequest stringRequest = new StringRequest(Request.Method.GET, message,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

*/
        try {
            removeSimpleProgressDialog();


            JSONArray jsonArray = new JSONArray(message);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                name.setText(jsonObject.getString("name"));
                email.setText(jsonObject.getString("email"));
                address.setText(jsonObject.getString("address"));
                role.setText(jsonObject.getString("role"));
                supervisor.setText(jsonObject.getString("supervisor"));
                mobile.setText(jsonObject.getString("mobileno"));
                pass.setText(jsonObject.getString("password"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            /*        }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(UserDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/

        // request queue
     /*   RequestQueue requestQueue = Volley.newRequestQueue(UserDetails.this);

        requestQueue.add(stringRequest);
    }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ss=new Intent(UserDetails.this,UserManagement.class);
        startActivity(ss);
        finish();
    }
}