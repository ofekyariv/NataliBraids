package com.example.natalibraids;

import android.graphics.Bitmap;

public class BraidsKinds {
    private boolean like;
    private String title;
    private String subTitle;
    private Bitmap bitmap;
    public BraidsKinds(boolean like, String title, String subTitle, Bitmap bitmap)
    {
        this.like=like;
        this.title = title;
        this.subTitle = subTitle;
        this.bitmap = bitmap;
    }

    public boolean getLike() {
        return like;
    }
    public void setLike(boolean like) {
        this.like=like;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {

        this.title = title;
    }
    public String getSubTitle() {

        return subTitle;
    }
    public void setSubTitle(String subTitle) {

        this.subTitle = subTitle;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
