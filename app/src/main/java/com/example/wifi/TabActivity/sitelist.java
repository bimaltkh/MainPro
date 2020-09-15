package com.example.wifi.TabActivity;


import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wifi.R;

import java.util.Objects;

public class sitelist extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitelist);

        getSupportActionBar().setTitle("Edit Site");

    }
}
