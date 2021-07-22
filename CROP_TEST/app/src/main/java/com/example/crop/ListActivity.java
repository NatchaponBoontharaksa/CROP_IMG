package com.example.crop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    ListView mListView;
    Uri imageCropUri;

    ArrayList<image_data> imageList = new ArrayList<>();
    image_data img_Obj;
    imageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mListView = findViewById(R.id.listView);

        Intent intent = getIntent();

//        img_Obj = (image_data)intent.getSerializableExtra("imgObj");
        imageList = (ArrayList<image_data>) intent.getSerializableExtra("imageList");
        if (imageList != null) {
//            imageList.add(img_Obj);
            adapter = new imageListAdapter(this, R.layout.img_list, imageList);
        }


        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> cropURL = imageList.get(position).getImgCropURL();
                for(int cnt = 0; cnt < cropURL.size(); cnt++){
                    Log.d(TAG, cropURL.get(cnt));
                }

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