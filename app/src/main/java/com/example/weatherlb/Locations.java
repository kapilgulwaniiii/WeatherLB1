package com.example.weatherlb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Locations extends AppCompatActivity {

    Spinner spinner;
    TextView t1,t2,t3;
    List<String> list = new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_locations);

        spinner=findViewById(R.id.spinner);
    }

    public void selecteditem(View view) {
            Temp.selected=spinner.getSelectedItem().toString();
        Intent mainIntent = new Intent(Locations.this,DisplayLB.class);
        startActivity(mainIntent);
    }

    public void add(View view) {
        t1 = findViewById(R.id.fav1);
        t2 = findViewById(R.id.fav2);
        t3 = findViewById(R.id.fav3);

    if(list.contains(spinner.getSelectedItem().toString())) {
        }
        else {
        list.add(spinner.getSelectedItem().toString());

        if (list.size() == 1) {
            t1.setText(list.get(0));
        }
        if (list.size() == 2) {
            t2.setText(list.get(1));
        }

        if (list.size() == 3) {
            t3.setText(list.get(2));
        }


    }

    }

}
