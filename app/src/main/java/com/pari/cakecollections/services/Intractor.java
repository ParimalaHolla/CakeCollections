package com.pari.cakecollections.services;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pari.cakecollections.model.AllCakeList;
import com.pari.cakecollections.model.CakeDetail;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Intractor implements GetResponseDataContract.Interactor {
    private GetResponseDataContract.onGetResponseListener mGetResponseListener;
    ArrayList<CakeDetail> allCakes = new ArrayList<>();

    public Intractor(GetResponseDataContract.onGetResponseListener mGetResponseListener){
        this.mGetResponseListener = mGetResponseListener;
    }

    @Override
    public void initNetowkCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final AllCakeList cakeList = retrofit.create(AllCakeList.class);
        retrofit2.Call<List<CakeDetail>> call = cakeList.getCakeList();
        call.enqueue(new retrofit2.Callback<List<CakeDetail>>() {
            @Override
            public void onResponse(Call<List<CakeDetail>> call, Response<List<CakeDetail>> response) {
                List<CakeDetail> responseData = response.body();
                for (int i=0;i<responseData.size();i++){
                    CakeDetail cake = responseData.get(i);
                    allCakes.add(cake);
                }
                HashSet<CakeDetail> hashSet = new HashSet<>();
                  hashSet.addAll(allCakes);
                  allCakes.clear();
                  allCakes.addAll(hashSet);
               Collections.sort(allCakes, CakeDetail.BY_NAME_ALPHABETICAL);
               Log.v("Successful","Refreshed");
                mGetResponseListener.onSuccess("list size :"+allCakes.size(),allCakes);
            }

            @Override
            public void onFailure(Call<List<CakeDetail>> call, Throwable t) {
                Log.v("Error",t.getMessage());
                mGetResponseListener.onFailure(t.getMessage());
            }
        });
    }



}
