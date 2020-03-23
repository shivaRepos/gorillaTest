package com.example.gorillatest;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://www.dropbox.com/")
               // .baseUrl("https://www.drive.google.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;


    }


}
