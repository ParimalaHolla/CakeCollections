package com.pari.cakecollections.model;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class CakeDetail {
    @SerializedName("title")
    private String title;
    @SerializedName("desc")
    private String desc;
    @SerializedName("image")
    private String image;




    public CakeDetail(String title, String desc, String image){
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
    public static final Comparator<CakeDetail> BY_NAME_ALPHABETICAL = new Comparator<CakeDetail>() {
        @Override
        public int compare(CakeDetail cake1, CakeDetail cake2) {
            return cake1.getTitle().compareTo(cake2.getTitle());
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
