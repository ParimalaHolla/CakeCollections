package com.pari.cakecollections.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CakeList {
  @SerializedName("cake_list")
    private ArrayList<CakeDetail> cakeList;


  public ArrayList<CakeDetail> getCakeDetailsArrayList(){
      return cakeList;
  }

  public void setCakeDetailsArrayList(ArrayList<CakeDetail> cakeDetailsArrayList){
      this.cakeList = cakeDetailsArrayList;
  }


}
