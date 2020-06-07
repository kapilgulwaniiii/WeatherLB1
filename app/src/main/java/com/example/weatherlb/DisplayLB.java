package com.example.weatherlb;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class DisplayLB extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_l_b);

    t1 =findViewById(R.id.ttemp);
    t2 =findViewById(R.id.tcity);
    t3 =findViewById(R.id.tdesc);
    t4 =findViewById(R.id.tdate);

    find_weather();
    }
    public void find_weather(){

    }

}