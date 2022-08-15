package com.myapplicationdev.android.l13mrtlrtfaresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.*;

public class MainActivity extends AppCompatActivity {
    ListView lvFares;
    AsyncHttpClient client;
    ArrayAdapter aaFares;
    Button btnFilter, btnRefresh;
    Spinner spnFare;

    ArrayList<Fares> alFares;
    CustomAdapter caFare;
    int filterFare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFares = findViewById(R.id.lvFares);
        client = new AsyncHttpClient();
        btnFilter = findViewById(R.id.btnFilter);
        btnRefresh = findViewById(R.id.btnRefresh);
        spnFare = findViewById(R.id.spnFare);
        alFares = new ArrayList<Fares>();
        caFare = new CustomAdapter(MainActivity.this, R.layout.row, alFares);


        spnFare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        filterFare = 170;
                        break;
                    case 1:
                        filterFare = 190;
                        break;
                    case 2:
                        filterFare = 210;
                        break;
                    case 3:
                        filterFare = 230;
                        break;
                    case 4:
                        filterFare = 250;
                        break;
                    case 5:
                        filterFare = 260;
                        break;
                    case 6:
                        filterFare = 270;
                        break;
                    case 7:
                        filterFare = 280;
                        break;
                    default:
                        filterFare = 0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alFares.clear();
                client.get("https://data.gov.sg/api/action/datastore_search?resource_id=663fe7b6-23c2-4a40-b77a-a2fa2114beff&limit=30", new JsonHttpResponseHandler() {
                    String type;
                    String time;
                    String distance;
                    int fare;

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONObject jsonArrResult = response.getJSONObject("result");
                            JSONArray jsonArrRecords = jsonArrResult.getJSONArray("records");
                            for (int i = 0; i < jsonArrRecords.length(); i++) {
                                JSONObject jsonObjRecords = jsonArrRecords.getJSONObject(i);
                                type = jsonObjRecords.getString("fare_type");
                                time = jsonObjRecords.getString("applicable_time");
                                distance = jsonObjRecords.getString("distance");
                                fare = jsonObjRecords.getInt("fare_per_ride");
                                if (fare == filterFare) {
                                    Fares fares = new Fares(type, time, distance, fare);
                                    alFares.add(fares);
                                }
                            }
                        } catch (JSONException e) {
                        }
                        lvFares.setAdapter(caFare);

                    }
                });
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alFares.clear();
                client.get("https://data.gov.sg/api/action/datastore_search?resource_id=663fe7b6-23c2-4a40-b77a-a2fa2114beff&limit=30", new JsonHttpResponseHandler() {
                    String type;
                    String time;
                    String distance;
                    int fare;

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONObject jsonArrResult = response.getJSONObject("result");
                            JSONArray jsonArrRecords = jsonArrResult.getJSONArray("records");
                            for (int i = 0; i < jsonArrRecords.length(); i++) {
                                JSONObject jsonObjRecords = jsonArrRecords.getJSONObject(i);
                                type = jsonObjRecords.getString("fare_type");
                                time = jsonObjRecords.getString("applicable_time");
                                distance = jsonObjRecords.getString("distance");
                                fare = jsonObjRecords.getInt("fare_per_ride");
                                Fares fares = new Fares(type, time, distance, fare);
                                alFares.add(fares);
                            }
                        } catch (JSONException e) {
                        }
                        lvFares.setAdapter(caFare);

                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=663fe7b6-23c2-4a40-b77a-a2fa2114beff&limit=30", new JsonHttpResponseHandler() {
            String type;
            String time;
            String distance;
            int fare;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject jsonArrResult = response.getJSONObject("result");
                    JSONArray jsonArrRecords = jsonArrResult.getJSONArray("records");
                    for (int i = 0; i < jsonArrRecords.length(); i++) {
                        JSONObject jsonObjRecords = jsonArrRecords.getJSONObject(i);
                        type = jsonObjRecords.getString("fare_type");
                        time = jsonObjRecords.getString("applicable_time");
                        distance = jsonObjRecords.getString("distance");
                        fare = jsonObjRecords.getInt("fare_per_ride");
                        Fares fares = new Fares(type, time, distance, fare);
                        alFares.add(fares);
                    }
                } catch (JSONException e) {
                }
                lvFares.setAdapter(caFare);

            }
        });
    }
}