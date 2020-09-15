package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class ModePage extends AppCompatActivity {
    RadioButton on,off,manual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_page);
        on=findViewById(R.id.onButton);
        off=findViewById(R.id.offButton);
        manual=findViewById(R.id.manualButton);

    }
}
