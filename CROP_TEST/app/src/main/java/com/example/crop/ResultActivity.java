package com.example.crop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ResultActivity extends AppCompatActivity {

    TextView redValueView;
    TextView greenValueView;
    TextView blueValueView;
    ImageView cropImg;

    int redValue = 0;
    int greenValue = 0;
    int blueValue = 0;

    String uri_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // add back arrow
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        redValueView = findViewById(R.id.red_value);
        greenValueView = findViewById(R.id.green_value);
        blueValueView = findViewById(R.id.blue_value);
        cropImg = findViewById(R.id.resultView);

        Intent intent = getIntent();

        uri_str = intent.getStringExtra("uri_str");

        try {
            rgbCalculation(uri_str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cropImg.setImageURI(Uri.parse(uri_str));

        redValueView.setText(redValue+"");
        greenValueView.setText(greenValue+"");
        blueValueView.setText(blueValue+"");

    }

    private void rgbCalculation(String imgUri) throws IOException {
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
        redValue =  (redColors/pixelCount);
        greenValue = (greenColors/pixelCount);
        blueValue = (blueColors/pixelCount);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}