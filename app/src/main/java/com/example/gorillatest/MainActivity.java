package com.example.gorillatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.media.CamcorderProfile.get;


public class MainActivity extends AppCompatActivity   {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //Progress bar here -------------------
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("loading....");
        progressDialog.show();

        Call<ApiResponse> call =ApiClient.getApiClient().create(EndPointInterface.class)
                .getData(1);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            progressDialog.dismiss();
            if(response.isSuccessful() && response.body()!= null){
                UpdateRecyclerView(response.body());

            }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed due to"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void UpdateRecyclerView(ApiResponse body) {
        CustomAdapter adapter = new CustomAdapter(this,body.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }

}
