package com.example.crop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CropListActivity extends AppCompatActivity {

    private static final String TAG = "CropListActivity";

    ListView mCropList;
    cropListAdapter cropAdapter;
    ArrayList<String> cropURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_list);

        // add back arrow
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mCropList = findViewById(R.id.croplist);

        Intent cropIntent = getIntent();

        cropURL = cropIntent.getStringArrayListExtra("cropList");

        if(cropURL != null){
            for (String url: cropURL) {
                Log.d(TAG, url);
            }
            cropAdapter = new cropListAdapter(this, R.layout.crop_list, cropURL);
        }

        mCropList.setAdapter(cropAdapter);

        mCropList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle cropUribundle = new Bundle();
                cropUribundle.putString("uri_str", cropURL.get(position));
                Intent resultIntent = new Intent(CropListActivity.this, ResultActivity.class);
                resultIntent.putExtras(cropUribundle);
                startActivity(resultIntent);
            }
        });

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