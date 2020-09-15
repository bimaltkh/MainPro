package com.example.wifi.ViewAlarm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.R;
import com.example.wifi.TabActivity.EditUser;
import com.example.wifi.TabActivity.UserDetails;
import com.example.wifi.TabActivity.UserManageAdapter;
import com.example.wifi.TabActivity.UserManagementData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewAlarmAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ViewAlarmData> arrayList;
    TextView name;
    public ViewAlarmAdapter(Context context,ArrayList<ViewAlarmData>arrayList){
        this.context=context;
        this.arrayList=arrayList;

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

        convertView = LayoutInflater.from(context).inflate(R.layout.alarmrow, parent, false);

        name = convertView.findViewById(R.id.name);

        final String ss=arrayList.get(position).toString();




        name.setText(arrayList.get(position).getAlarmname());





        return convertView;
    }
}
