package com.example.wifi.TabActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserManagementData> arrayList;
  private TextView name,roles;
  private Button button,delete;

    ListView listView;
    public UserManageAdapter(Context context, ArrayList<UserManagementData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {

        return position;
    }
    @Override
    public long getItemId(final int position) {

        return position;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.usermanage_row, parent, false);

        name = convertView.findViewById(R.id.name);
        roles =convertView.findViewById(R.id.role);
        button=convertView.findViewById(R.id.editbutton);
        delete=convertView.findViewById(R.id.deletebutton);
        final String ss=arrayList.get(position).toString();

          button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string=arrayList.get(position).toString();
                Toast.makeText(parent.getContext(),string, Toast.LENGTH_SHORT).show();
                RelativeLayout lv=(RelativeLayout) view.getParent();
                name = lv.findViewById(R.id.name);
                final String text = name.getText().toString();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mature-railroads.000webhostapp.com/displayuser.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server


                                Intent ss=new Intent(context,EditUser.class);
                                ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(ss);
                                Intent intent = new Intent(context,EditUser.class);
                                intent.putExtra("message",response);
                                context.startActivity(intent);

                                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
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

                        params.put("name1",text);


//returning parameter
                        return params;
                    }
                };

//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);



            }
        });
          delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String string=arrayList.get(position).toString();
                  Toast.makeText(parent.getContext(),string, Toast.LENGTH_SHORT).show();
                  RelativeLayout lv=(RelativeLayout) v.getParent();
                  name = lv.findViewById(R.id.name);
                  final String text = name.getText().toString();
                  StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mature-railroads.000webhostapp.com/deleteuser.php",
                          new Response.Listener<String>() {
                              @Override
                              public void onResponse(String response) {
//If we are getting success from server

                                  Toast.makeText(context, response, Toast.LENGTH_LONG).show();
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


                          params.put("name1",text);


//returning parameter
                          return params;
                      }
                  };

//Adding the string request to the queue
                  RequestQueue requestQueue = Volley.newRequestQueue(context);
                  requestQueue.add(stringRequest);
arrayList.remove(position);
              notifyDataSetChanged();
              }
          });


        name.setText(arrayList.get(position).getName());
        roles.setText(arrayList.get(position).getRole());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=arrayList.get(position).toString();
                Toast.makeText(parent.getContext(),string, Toast.LENGTH_SHORT).show();
                RelativeLayout lv=(RelativeLayout) v.getParent();
                name = lv.findViewById(R.id.name);
                final String text = name.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mature-railroads.000webhostapp.com/displayuser.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server


                                Intent ss=new Intent(context,UserDetails.class);
                                ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(ss);
                                Intent intent = new Intent(context,UserDetails.class);
                                intent.putExtra("message",response);
                                context.startActivity(intent);

                                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
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

                        params.put("name1",text);


//returning parameter
                        return params;
                    }
                };

//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

            }
        });



        return convertView;
    }


}
