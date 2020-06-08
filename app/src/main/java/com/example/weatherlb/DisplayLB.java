package com.example.weatherlb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DisplayLB extends AppCompatActivity {
    TextView t1, t2, t3, t4,t5;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_l_b);

        t1 = findViewById(R.id.ttemp);
        t2 = findViewById(R.id.tcity);
        t3 = findViewById(R.id.tdesc);
        t4 = findViewById(R.id.tdate);




        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q=" + Temp.selected +"&appid=240c162dbe599cc9bc44041e5c74ea32";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject json = new JSONObject(response);
                            JSONArray weather_array = json.getJSONArray("weather");

                            JSONObject json_weather = weather_array.getJSONObject(0);
//                            JSONObject json_icon = json.getJSONObject("icon");
//                            Log.e("hi","hi");
//                            String icon = json_icon.getString("icon");
//                            String imageUri = "http://openweathermp.org/img/w/" + icon + ".png";
//                            Log.e("Image",imageUri);
//                            ImageView img = (ImageView) findViewById(R.id.imgicon);
//                            Picasso.get().load(imageUri).into(img);
//
////                            JSONObject json_icon = json.getJSONObject("icon");
////                            String icon = json_icon.getString("icon");
////                            String iconUrl = "http://openweathermp.org/img/w/" + json_icon + ".png";
////
////                            Picasso.get().load(iconUrl).into(img);

                            JSONObject json_main = json.getJSONObject("main");
                            String str_temp = json_main.getString("temp");

                            t3.setText(json_weather.getString("main"));
                            t2.setText(Temp.selected);

                            Calendar calendar= Calendar.getInstance();
                            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-YYYY\nhh:mm:ss a");
                            String datenow =sdf.format(calendar.getTime());
                            t4.setText(datenow);

                            double tint= Double.parseDouble(str_temp);
                            double centi= (tint-273);
                            centi= Math.round(centi);
                            int i= (int)centi;
                            t1.setText((String.valueOf(i)) + " °C");

                        }catch(Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t2.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    public void floating(View view) {
        Intent mainIntent = new Intent(DisplayLB.this,Locations.class);
        startActivity(mainIntent);
    }
}