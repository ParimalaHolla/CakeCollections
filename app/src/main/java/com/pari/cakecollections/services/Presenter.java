package com.pari.cakecollections.services;

import android.content.Context;

import com.pari.cakecollections.model.CakeDetail;

import java.util.ArrayList;

public class Presenter implements GetResponseDataContract.Presenter,GetResponseDataContract.onGetResponseListener {
    private GetResponseDataContract.View responseDataView;
    private Intractor intractor;


    public Presenter(GetResponseDataContract.View getDataView){
        this.responseDataView = getDataView;
        intractor = new Intractor(this);
    }

    @Override
    public void getDataFromAPI(Context context, String url) {
        if (responseDataView != null){
            responseDataView.showProgress();
        }
        intractor.initNetworkCall(context,url);
    }

    @Override
    public void onRefreshView(Context context, String url) {
        if (responseDataView != null){
            responseDataView.showProgress();
        }
        intractor.initNetworkCall(context,url);

    }

    @Override
    public void onDestroy() {
        responseDataView = null;
    }

    @Override
    public void onSuccess(String msg, ArrayList<CakeDetail> cakes) {
        if (responseDataView != null) {
            responseDataView.onGetResponseDataSuccess(msg, cakes);
            responseDataView.hideProgress();
        }
    }

    @Override
    public void onFailure(String msg) {
        responseDataView.onGetResponseDataFailure(msg);
        responseDataView.hideProgress();
    }
}
