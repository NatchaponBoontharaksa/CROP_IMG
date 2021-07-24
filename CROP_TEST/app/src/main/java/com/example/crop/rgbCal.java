package com.example.crop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

        Bitmap bitmap = bImage; //assign your bitmap here
        int redColors = 0;
        int greenColors = 0;
        int blueColors = 0;
        int pixelCount = 0;

        for (int y = 0; y < bitmap.getHeight(); y++)
        {
            for (int x = 0; x < bitmap.getWidth(); x++)
            {
                int c = bitmap.getPixel(x, y);
                pixelCount++;
                redColors += Color.red(c);
                greenColors += Color.green(c);
                blueColors += Color.blue(c);
            }
        }
// calculate average of bitmap r,g,b values
        int red = (redColors/pixelCount);
        int green = (greenColors/pixelCount);
        int blue = (blueColors/pixelCount);


    }

}
