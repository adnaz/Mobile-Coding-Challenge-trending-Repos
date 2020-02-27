package com.example.trendingrepos.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient  {
    public static final String BASE_URL = "https://api.github.com/search/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

    //

    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(instance==null){
            instance=new RetrofitClient();
        }
        return instance;
    }
    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
