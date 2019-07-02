package com.pari.cakecollections.services;

import android.content.Context;

import com.pari.cakecollections.model.CakeDetail;

import java.util.ArrayList;
import java.util.List;

public class Presnter implements GetResponseDataContract.Presenter,GetResponseDataContract.onGetResponseListener {
    private GetResponseDataContract.View responseDataView;
    private Intractor intractor;


    public Presnter(GetResponseDataContract.View getDataView){
        this.responseDataView = getDataView;
        intractor = new Intractor(this);
    }

    @Override
    public void getDataFromAPI(Context context, String url) {
        intractor.initNetowkCall(context,url);
    }

    @Override
    public void onRefreshView(Context context, String url) {
        intractor.initNetowkCall(context,url);

    }

    @Override
    public void onDestroy() {
        responseDataView = null;
    }

    @Override
    public void onSuccess(String msg, ArrayList<CakeDetail> cakes) {
        responseDataView.onGetResponseDataSuccess(msg,cakes);
    }

    @Override
    public void onFailure(String msg) {
        responseDataView.onGetResponseDataFailure(msg);
    }
}
