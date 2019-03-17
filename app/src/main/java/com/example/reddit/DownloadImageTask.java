package com.example.reddit;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

class DownloadImageTask extends AsyncTask<String, Void, ImageView> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }


    @Override
    protected ImageView doInBackground(String... params) {

        System.out.println("doInBackground");



        return bmImage;
    }

    @Override
    protected void onPostExecute(ImageView view) {

        System.out.println("ImageView " + view);

    }
}