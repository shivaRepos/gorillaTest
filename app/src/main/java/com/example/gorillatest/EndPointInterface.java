package com.example.gorillatest;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPointInterface {


    @GET("s/1hh8vh7whv6cjme/list1.json")
    Call<ApiResponse> getData(@Query("dl") int i);

    @GET("s/n4i57r22rdx89cw/list2.json?")
    Call<ArrayList<MapData>> getData2(@Query("dl") int i);



}
