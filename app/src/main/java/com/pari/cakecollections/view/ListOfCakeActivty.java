package com.pari.cakecollections.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pari.cakecollections.R;
import com.pari.cakecollections.adapter.CakeAdapter;
import com.pari.cakecollections.model.CakeDetail;
import com.pari.cakecollections.services.GetResponseDataContract;
import com.pari.cakecollections.services.Presnter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ListOfCakeActivty extends AppCompatActivity implements GetResponseDataContract.View {
      private Presnter presenter;
      RecyclerView recyclerView;
      LinearLayoutManager linearLayoutManager;
      CakeAdapter cakeAdapter;
      private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cake);
        initProgressBar();
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

        cakeAdapter = new CakeAdapter(cakes,recyclerItemClickListener);
        System.out.println("Adapter Data"+cakes.toString());
        recyclerView.setAdapter(cakeAdapter);
        Log.d("Sucess Status",msg);


    }

    @Override
    public void onGetResponseDataFailure(String msg) {
        Log.d("Failure Status",msg);

    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(CakeDetail cakeDetail) {

            Toast.makeText(ListOfCakeActivty.this,
                    " " + cakeDetail.getDesc(),
                    Toast.LENGTH_LONG).show();

        }
    };

    /**
     * Initializing progressbar programmatically
     * */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }
}
