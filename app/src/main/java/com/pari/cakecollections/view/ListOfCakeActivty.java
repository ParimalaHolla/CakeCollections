package com.pari.cakecollections.view;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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
import com.pari.cakecollections.services.Presenter;

import java.util.ArrayList;


public class ListOfCakeActivty extends AppCompatActivity implements GetResponseDataContract.View {
      private Presenter presenter;
      RecyclerView recyclerView;
      LinearLayoutManager linearLayoutManager;
      CakeAdapter cakeAdapter;
      private ProgressBar progressBar;
      private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cake);
        setupRecyclerview();
        initProgressBar();
        presenter = new Presenter(this);
        presenter.getDataFromAPI(getApplicationContext(),"");
        swipeContainer = findViewById(R.id.swipeContainer);

         swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 cakeAdapter.clear();
                 refreshView();

             }
         });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


}


    @Override
    public void onGetResponseDataSuccess(String msg, ArrayList<CakeDetail> cakes) {

        cakeAdapter = new CakeAdapter(cakes,recyclerItemClickListener);
        System.out.println("Adapter Data"+cakes.toString());
        recyclerView.setAdapter(cakeAdapter);
        Log.v("Sucess Status",msg);


    }

    @Override
    public void onGetResponseDataFailure(String msg) {
        Log.e("Failure Status",msg);
       // Toast.makeText(ListOfCakeActivty.this, "network failure : inform the user and possibly retry", Toast.LENGTH_SHORT).show();
     showConnectionError();

    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(CakeDetail cakeDetail) {

            Toast.makeText(ListOfCakeActivty.this,
                    cakeDetail.getTitle() +" is " + cakeDetail.getDesc(),
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

    // Destroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(this, R.string.main_error_connection, Toast.LENGTH_SHORT).show();

    }

    /* Refreshing recylerview swipe to refresh */
    private void refreshView(){
        presenter.onRefreshView(getApplicationContext(),"");
        swipeContainer.setRefreshing(false);


    }
    /* Initialization of recyclerview*/
    private void setupRecyclerview(){
        recyclerView =  findViewById(R.id.recycler_view_cake_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exist?")
                .setNegativeButton(android.R.string.no,null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListOfCakeActivty.super.onBackPressed();
                    }
                }).create().show();
    }
}
