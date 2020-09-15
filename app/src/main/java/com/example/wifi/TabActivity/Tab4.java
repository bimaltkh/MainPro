package com.example.wifi.TabActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.wifi.R;

public class Tab4 extends ListFragment implements AdapterView.OnItemClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Tab4items, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //  Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        String name=(String) getListView().getItemAtPosition(position);
        Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
        if(name.equals("Set1")){
            Intent ss=new Intent(getActivity(),Tab4Set1.class);
            startActivity(ss);
        }
        if(name.equals("Set2")){
            Intent ss=new Intent(getActivity(),Tab4Set2.class);
            startActivity(ss);
        }
        if(name.equals("Set3")){
            Intent ss=new Intent(getActivity(),Tab4Set3.class);
            startActivity(ss);
        }
        if(name.equals("Set4")){
            Intent ss=new Intent(getActivity(),Tab4Set4.class);
            startActivity(ss);
        }
        if(name.equals("Set5")){
            Intent ss=new Intent(getActivity(),Tab4Set5.class);
            startActivity(ss);
        }
    }



}
