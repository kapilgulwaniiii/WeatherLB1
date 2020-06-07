package com.example.weatherlb;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_l_b);

        t1 = findViewById(R.id.ttemp);
        t2 = findViewById(R.id.tcity);
        t3 = findViewById(R.id.tdesc);
        t4 = findViewById(R.id.tdate);
        t5 = findViewById(R.id.ttime);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q=pune,india&appid=240c162dbe599cc9bc44041e5c74ea32";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject json = new JSONObject(response);
                            JSONArray weather_array = json.getJSONArray("weather");
                            JSONObject json_weather = weather_array.getJSONObject(0);
                            //String str_main =json_weather.getJSONArray("main");
                            JSONObject json_main = json.getJSONObject("main");
                            String str_temp = json_main.getString("temp");
                            //String str_name = json_main.getString("name");
                            t1.setText("Temprature: " + str_temp);
                            //t2.setText("Response is: " + str_name);
                            t3.setText("Weather: " + json_weather.getString("main"));

                            Calendar calendar= Calendar.getInstance();
                            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-YYYY\nhh mm ss a");
                            String datenow =sdf.format(calendar.getTime());
                            t4.setText(datenow);


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
}