package com.example.wallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtilities {
    public static Retrofit retrofit = null;
    public static final String API = "3otKYz1vTmWjcCcX7AMullKFDf6jvvUUd4xZMRQPUoCIAYkohQsg6e5a";

    public  static ApiInterface getApiInterface(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
