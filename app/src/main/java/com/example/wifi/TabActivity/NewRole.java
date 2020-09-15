package com.example.wifi.TabActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewRole extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatePickerDialog picker;
    String[] role = {"Role1","Role2","Role3","Role4"};
    EditText rolename,expirydate;
    Spinner parentrole;
    Button create;
    CheckBox viewsitelist,
    addsite,
    viewsitedetails,
    editsite,
    deletesite,
    viewsiteparams,
    viewalarms,
    viewalarmshistory,
    viewsitesettings,
    initializesettings,
    viewsiteidrequests,
    editsitesettings,
  controlsection,
    sendOTP,
    energylevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrole);
        //button
        create=findViewById(R.id.create);
        //edittext
        rolename=findViewById(R.id.rolenameaddrole);
        expirydate=findViewById(R.id.expirydateaddrole);
        expirydate.setInputType(InputType.TYPE_NULL);
        //spinner
        parentrole=findViewById(R.id.parentspiner);
        //checkboxes
        viewsitelist=findViewById(R.id.viewsite);
        addsite=findViewById(R.id.addsite);
        viewsitedetails=findViewById(R.id.viewsitedetail);
        editsite=findViewById(R.id.editsite);
        deletesite=findViewById(R.id.deletesite);
        viewsiteparams=findViewById(R.id.viewsiteparams);
        viewalarms=findViewById(R.id.viewalarm);
        viewalarmshistory=findViewById(R.id.alarmhistory);
        viewsitesettings=findViewById(R.id.viewsitesetting);
        initializesettings=findViewById(R.id.initializesettings);
        viewsiteidrequests=findViewById(R.id.viewSiteidRequest);
        editsitesettings=findViewById(R.id.editsitesetting);
        controlsection=findViewById(R.id.controlsection);
        sendOTP=findViewById(R.id.sendotp);
        energylevels=findViewById(R.id.energylevels);
        parentrole.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) NewRole.this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(NewRole.this,android.R.layout.simple_spinner_item,role);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        parentrole.setAdapter(aa);
        expirydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(NewRole.this,
                        new DatePickerDialog.OnDateSetListener() {
                       @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                             expirydate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

 create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    /*    String msg="";

        // Concatenation of the checked options in if

        // isChecked() is used to check whether
        // the CheckBox is in true state or not.

        if(viewsitelist.isChecked())
            msg = msg + " Painting ";
        if(addsite.isChecked())
            msg = msg + " Reading ";
        if(viewsitedetails.isChecked())
            msg = msg + " Singing ";
        if(viewsitelist.isChecked())
            msg = msg + " Cooking ";
     //   viewsitelist.setVisibility(View.INVISIBLE);
        viewsitelist.setEnabled(false);

        // Toast is created to display the
        // message using show() method.
        Toast.makeText(NewRole.this, msg + "are selected",
                Toast.LENGTH_LONG).show();
*/
        if(!( rolename.getText().toString().isEmpty() || expirydate.getText().toString().isEmpty())) {
            final String spin=parentrole.getSelectedItem().toString();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mature-railroads.000webhostapp.com/addrole.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server

                            Toast.makeText(NewRole.this,response,Toast.LENGTH_SHORT).show();
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

                    params.put("rolename",rolename.getText().toString());
                    params.put("expdate",expirydate.getText().toString());
                    params.put("role",spin);


//returning parameter
                    return params;
                }
            };

//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(NewRole.this);
            requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(NewRole.this, "enter values correctly", Toast.LENGTH_LONG).show();

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