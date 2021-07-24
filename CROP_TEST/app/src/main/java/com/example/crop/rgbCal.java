package com.example.crop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class rgbCal {

    public void rgbCalculation(String imgUri) throws IOException {
        Uri tmpUri = Uri.parse(imgUri);
        InputStream is = null;
        BufferedInputStream bis = null;
        Bitmap bImage = null;

        URLConnection conn = new URL(imgUri).openConnection();

        conn.connect();
        is = conn.getInputStream();
        bis = new BufferedInputStream(is, 8192);
        bImage = BitmapFactory.decodeStream(bis);


    }

}
