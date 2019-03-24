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
    private String sub;
    private String author;

    public Post(String title, String shortdesc, String image, String sub, String author) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
        this.sub = "r/" + sub;
        this.author = "u/" + author;
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

    public String getSub() { return sub; }

    public String getAuthor() {
        return author;
    }
}