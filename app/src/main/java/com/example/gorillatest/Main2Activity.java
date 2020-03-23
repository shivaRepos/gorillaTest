package com.example.gorillatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    String name,email;

    GraphView graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        graph = (GraphView) findViewById(R.id.graph);


        //Progress bar here -------------------
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("loading....");
        progressDialog.show();

        Call<ArrayList<MapData>> call =ApiClient.getApiClient().create(EndPointInterface.class)
                .getData2(1);
        call.enqueue(new Callback<ArrayList<MapData>>() {
            @Override
            public void onResponse(Call<ArrayList<MapData>> call, Response<ArrayList<MapData>> response) {
                progressDialog.dismiss();
                if(response.isSuccessful() && response.body()!= null){
                    UpdateRecyclerView(response.body());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<MapData>> call, Throwable t) {
                Toast.makeText(Main2Activity.this, "Failed due to"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void UpdateRecyclerView(ArrayList<MapData> body) {

        DataPoint[] dataPoint= new DataPoint[body.size()];
        for(int i=0; i<body.size(); i++){

            dataPoint[i]=new DataPoint(i, (double) body.get(i).getAmount());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoint);
        graph.addSeries(series);
    }

}
