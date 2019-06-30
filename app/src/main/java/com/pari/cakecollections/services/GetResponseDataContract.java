package com.pari.cakecollections.services;

import android.content.Context;
import android.os.IInterface;

import com.pari.cakecollections.model.CakeDetail;
import com.pari.cakecollections.model.CakeList;

import java.util.ArrayList;

public interface GetResponseDataContract {

    interface View{
        void onGetResponseDataSuccess(String msg, ArrayList<CakeDetail> cakes);
        void onGetResponseDataFailure(String msg);
    }

    interface Presenter{
        void getDataFromAPI(Context context,String url);
    }

    interface Interactor{
        void initNetowkCall(Context context,String url);
    }

    interface onGetResponseListener{
        void onSuccess(String msg,ArrayList<CakeDetail> cakes);
        void onFailure(String msg);
    }

}
