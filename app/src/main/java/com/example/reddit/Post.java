package com.example.reddit;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class Post {
    private String title;
    private String shortdesc;
    private String image;

    public Post(String title, String shortdesc, String image) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getImage() {
        return image;
    }
}