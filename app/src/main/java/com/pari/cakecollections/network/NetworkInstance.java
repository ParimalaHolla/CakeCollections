package com.pari.cakecollections.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0\n" +
            "ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client";

    /* Retrofit Instance of object*/

    public static Retrofit getNetworkInstance(){
        if (retrofit==null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
