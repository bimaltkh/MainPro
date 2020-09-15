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

public class EditUserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<EditUserData> arrayList;
    private TextView name,date;
    private Button button;

    ListView listView;
    public EditUserAdapter(Context context, ArrayList<EditUserData> arrayList) {
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
        date =convertView.findViewById(R.id.role);
        button=convertView.findViewById(R.id.editbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string=arrayList.get(position).toString();
                Toast.makeText(parent.getContext(),string, Toast.LENGTH_SHORT).show();
                RelativeLayout lv=(RelativeLayout) view.getParent();
                name = lv.findViewById(R.id.name);
                String text = name.getText().toString();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, EditUser.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

        name.setText(arrayList.get(position).getName());
    //    date.setText(arrayList.get(position).getDate());



        return convertView;
    }

}
