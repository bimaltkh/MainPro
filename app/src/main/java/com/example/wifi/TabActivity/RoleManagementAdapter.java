package com.example.wifi.TabActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wifi.R;

import java.util.ArrayList;

public class RoleManagementAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RoleManagementData> arrayList;
    private TextView role,date,status;
    private Button edit,delete;

    ListView listView;
    public RoleManagementAdapter(Context context, ArrayList<RoleManagementData> arrayList) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.rolemanage_row, parent, false);

        role = convertView.findViewById(R.id.rolename);
        date =convertView.findViewById(R.id.expirydate);
        status=convertView.findViewById(R.id.status);
        edit=convertView.findViewById(R.id.editbutton);
        delete=convertView.findViewById(R.id.deletebutton);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string=arrayList.get(position).toString();
                Toast.makeText(parent.getContext(),string, Toast.LENGTH_SHORT).show();
                RelativeLayout lv=(RelativeLayout) view.getParent();
                role= lv.findViewById(R.id.rolename);
                String text = role.getText().toString();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(context, EditUser.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/


            }
        });

        role.setText(arrayList.get(position).getRole());
        date.setText(arrayList.get(position).getDate());
        status.setText(arrayList.get(position).getStatus());
       



        return convertView;
    }

}
