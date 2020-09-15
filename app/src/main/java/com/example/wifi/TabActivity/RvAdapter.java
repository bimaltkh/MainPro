package com.example.wifi.TabActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wifi.R;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder>  {
    private LayoutInflater inflater;
    private ArrayList<DataModel> dataModelArrayList;


    public RvAdapter(Context ctx, ArrayList<DataModel> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }
    @Override
    public RvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.tab1sample, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RvAdapter.MyViewHolder holder, int position) {
        int itemNumber = position+1;


        holder.Rvaluetext1.setText(dataModelArrayList.get(position).getR1Value()+ "V");
        holder.Rvaluetext2.setText(dataModelArrayList.get(position).getR2Value()+"v");
        holder.Yvaluetext1.setText(dataModelArrayList.get(position).getY1Value()+"v");
        holder.Yvaluetext2.setText(dataModelArrayList.get(position).getY2Value()+"v");
        holder.Bvaluetext1.setText(dataModelArrayList.get(position).getB1Value()+"v");
        holder.Bvaluetext2.setText(dataModelArrayList.get(position).getB2Value()+"v");
        holder.IDDevice.setText(dataModelArrayList.get(position).getIDDevice());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Rvaluetext1,Rvaluetext2,Yvaluetext1,Yvaluetext2,Bvaluetext1,Bvaluetext2,IDDevice;

        public MyViewHolder(View itemView) {
            super(itemView);

            Rvaluetext1 =itemView.findViewById(R.id.R1textv);
            Rvaluetext2 =itemView.findViewById(R.id.R2textv);
            Yvaluetext1=itemView.findViewById(R.id.Y1textv);
            Yvaluetext2=itemView.findViewById(R.id.Y2textv);
            Bvaluetext1=itemView.findViewById(R.id.B1textv);
            Bvaluetext2=itemView.findViewById(R.id.B2textv);
            IDDevice=itemView.findViewById(R.id.textView28);


        }

    }

}
