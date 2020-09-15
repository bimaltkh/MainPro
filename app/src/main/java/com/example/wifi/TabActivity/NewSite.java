package com.example.wifi.TabActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.wifi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewSite extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] assign = {"User1", "User2", "User3", "User4"};
    EditText siteid, serialno, sitename, location, address;
    Spinner assigned;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_site);
        siteid = findViewById(R.id.siteid);
        serialno = findViewById(R.id.serial);
        sitename = findViewById(R.id.sitename);
        location = findViewById(R.id.location);
        address = findViewById(R.id.address);
        assigned = findViewById(R.id.assigned);
        create=findViewById(R.id.createbutton);
        assigned.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) NewSite.this);
        ArrayAdapter aa = new ArrayAdapter(NewSite.this, android.R.layout.simple_spinner_item, assign);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        assigned.setAdapter(aa);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(siteid.getText().toString().isEmpty() || serialno.getText().toString().isEmpty() || sitename.getText().toString().isEmpty() ||
                location.getText().toString().isEmpty() || address.getText().toString().isEmpty() )) {
                    final String assign=assigned.getSelectedItem().toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mature-railroads.000webhostapp.com/addsite.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server

                            Toast.makeText(NewSite.this, response, Toast.LENGTH_LONG).show();
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

                    params.put("site",siteid.getText().toString());
                    params.put("serial",serialno.getText().toString());
                    params.put("sname",sitename.getText().toString());
                    params.put("location",location.getText().toString());
                    params.put("address",address.getText().toString());
                    params.put("assigned",assign);



//returning parameter
                    return params;
                }
            };

//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(NewSite.this);
            requestQueue.add(stringRequest);
            Intent nn= new Intent(NewSite.this,UserManagement.class);
            startActivity(nn);
            finish();


        }else {
            Toast.makeText(NewSite.this, "enter values correctly", Toast.LENGTH_LONG).show();

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
}