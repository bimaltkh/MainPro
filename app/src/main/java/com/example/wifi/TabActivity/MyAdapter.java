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

import com.example.wifi.R;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.getColor;
import static androidx.core.content.ContextCompat.startActivity;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MyData> arrayList;
    Button button;
    private TextView name,livetext,alarm1,alarm2,alarm3;
    ListView listView;
    public MyAdapter(Context context, ArrayList<MyData> arrayList) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.listview3, parent, false);

        name = convertView.findViewById(R.id.rolename);
        button=convertView.findViewById(R.id.editbutton);
        livetext=convertView.findViewById(R.id.livetext);
        alarm1=convertView.findViewById(R.id.alarm1);
        alarm2=convertView.findViewById(R.id.alarm2);
        alarm3=convertView.findViewById(R.id.alarm3);


        /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string=arrayList.get(position).toString();
                Toast.makeText(parent.getContext(), string, Toast.LENGTH_SHORT).show();

            }
        });*/
        name.setText(arrayList.get(position).getName());
        String RED="RED";
        String GREEN="GREEN";
        String ORANGE="ORANGE";
        String YELLOW="YELLOW";
        for(int i=0;i < arrayList.get(position).getColor().length(); i++) {
            if (arrayList.get(position).getColor().equals(RED)) {
                livetext.setBackground(ContextCompat.getDrawable(context,R.drawable.bcgk));
            } else {
                if (arrayList.get(position).getColor().equals(GREEN)) {
                    livetext.setBackground(ContextCompat.getDrawable(context, R.drawable.bcgk2));
                }
            }
        }
                          for(int i=0;i<arrayList.get(position).getAlarm1().length(); i++){
            alarm1.setBackground(ContextCompat.getDrawable(context,R.drawable.alarm1draw));
            alarm1.setText(arrayList.get(position).getAlarm1());

        }
        for(int i=0;i<arrayList.get(position).getAlarm2().length(); i++){
            alarm2.setBackground(ContextCompat.getDrawable(context,R.drawable.alarm2draw));
            alarm2.setText(arrayList.get(position).getAlarm2());

        }
        for(int i=0;i<arrayList.get(position).getAlarm3().length(); i++){
            alarm3.setBackground(ContextCompat.getDrawable(context,R.drawable.alarm3draw));
            alarm3.setText(arrayList.get(position).getAlarm3());

        }


name.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                RelativeLayout lv=(RelativeLayout) v.getParent();
                name = lv.findViewById(R.id.rolename);
                String text = name.getText().toString();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, sitelist.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


                //  Toast.makeText(context, " "+position, Toast.LENGTH_SHORT).show();
            }


        });
        return convertView;
    }



}
