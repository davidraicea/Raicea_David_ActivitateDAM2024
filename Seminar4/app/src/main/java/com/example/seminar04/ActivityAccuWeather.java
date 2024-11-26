package com.example.seminar04;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityAccuWeather extends AppCompatActivity {
    String apiKey = "mdZ6aCBXBHSDbg6j4IuT7mA7QV34ANUg" ;
    String cheieOras;
    String min;
    String max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accu_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        Button btn = findViewById(R.id.btnApplyCity);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oras = ((EditText)findViewById(R.id.orasET)).getText().toString();
                String url = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + oras;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection con = null;
                        try{
                            URL apiUrl = new URL(url);
                            con = (HttpURLConnection) apiUrl.openConnection();
                            con.setRequestMethod("GET");
                            con.connect();

                            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            StringBuilder response = new StringBuilder();
                            String line;

                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }

                            reader.close();
                            con.disconnect();

                            JSONArray jsonArray = new JSONArray(response.toString());
                            JSONObject object = jsonArray.getJSONObject(0);
                            cheieOras = object.getString("Key");

                            TextView orasTV = findViewById(R.id.orasTV);

                            String weatherUrl = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/" +
                                    cheieOras + "?apikey=" + apiKey + "&metric=true";
                            apiUrl = new URL(weatherUrl);
                            con = (HttpURLConnection) apiUrl.openConnection();
                            con.setRequestMethod("GET");
                            con.connect();

                            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            response = new StringBuilder();
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }
                            reader.close();
                            con.disconnect();

                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONObject dailyForecasts = jsonObject.getJSONArray("DailyForecasts").getJSONObject(0);
                            JSONObject temperature = dailyForecasts.getJSONObject("Temperature");
                             min = temperature.getJSONObject("Minimum").getString("Value");
                             max = temperature.getJSONObject("Maximum").getString("Value");

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                TextView orasTV = findViewById(R.id.orasTV);
                                orasTV.setText("Cod oras: " + cheieOras + "\nTemperatura:\nMinimă: " + min + "°C\nMaximă: " + max + "°C");

                            }
                        });

                    }
                });
            }
        });


    }
}