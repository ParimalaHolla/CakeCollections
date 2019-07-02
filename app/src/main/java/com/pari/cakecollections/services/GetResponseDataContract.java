package com.pari.cakecollections.services;

import android.content.Context;

import com.pari.cakecollections.model.CakeDetail;

import java.util.ArrayList;

public interface GetResponseDataContract {

    interface View{
        void onGetResponseDataSuccess(String msg, ArrayList<CakeDetail> cakes);
        void onGetResponseDataFailure(String msg);
        void showProgress();
        void hideProgress();
    }

    interface Presenter{
        void onDestroy();
        void getDataFromAPI(Context context,String url);
        void onRefreshView(Context context,String url);

    }

    interface Interactor{
        void initNetowkCall(Context context,String url);
    }

    interface onGetResponseListener{
        void onSuccess(String msg,ArrayList<CakeDetail> cakes);
        void onFailure(String msg);
    }

   }

