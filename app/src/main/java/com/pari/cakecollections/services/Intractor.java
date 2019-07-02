package com.pari.cakecollections.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pari.cakecollections.model.AllCakeList;
import com.pari.cakecollections.model.CakeDetail;
import com.pari.cakecollections.model.CakeList;
import com.pari.cakecollections.view.ListOfCakeActivty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Intractor implements GetResponseDataContract.Interactor {
    private GetResponseDataContract.onGetResponseListener mGetResponseListener;
    ArrayList<CakeDetail> allCakes = new ArrayList<>();
    List<String> allCakesName = new ArrayList<>();
    List<String> allCakesImage = new ArrayList<>();
    List<String> allCakesDesc = new ArrayList<>();
    List<CakeDetail> names ;
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
                Collections.sort(responseData,CakeDetail.BY_NAME_ALPHABETICAL);
                Log.v("Response",responseData.toString());
                for (int i=0;i<responseData.size();i++){
                    CakeDetail cake = responseData.get(i);

                    allCakes.add(cake);

                }

                  HashSet<CakeDetail> hashSet = new HashSet<>();
                  hashSet.addAll(allCakes);
                  allCakes.clear();
                  allCakes.addAll(hashSet);
               Collections.sort(allCakes, CakeDetail.BY_NAME_ALPHABETICAL);




                Log.d("Data","Refreshed");
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
