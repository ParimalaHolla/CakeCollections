package com.pari.cakecollections.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pari.cakecollections.R;
import com.pari.cakecollections.adapter.CakeAdapter;
import com.pari.cakecollections.model.CakeDetail;
import com.pari.cakecollections.services.GetResponseDataContract;
import com.pari.cakecollections.services.Presnter;

import java.util.ArrayList;

public class ListOfCakeActivty extends AppCompatActivity implements GetResponseDataContract.View {
      private Presnter presenter;
      RecyclerView recyclerView;
      LinearLayoutManager linearLayoutManager;
      CakeAdapter cakeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cake);
        presenter = new Presnter(this);
        presenter.getDataFromAPI(getApplicationContext(),"");

        recyclerView =  findViewById(R.id.recycler_view_cake_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(this);
         linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


    }

    @Override
    public void onGetResponseDataSuccess(String msg, ArrayList<CakeDetail> cakes) {
        cakeAdapter = new CakeAdapter(getApplicationContext(),cakes);
        System.out.println("Adapter Data"+cakes.toString());
        recyclerView.setAdapter(cakeAdapter);
        Log.d("Sucess Status",msg);


    }

    @Override
    public void onGetResponseDataFailure(String msg) {
        Log.d("Failure Status",msg);

    }
}
