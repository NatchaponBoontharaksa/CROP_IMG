package com.example.crop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQ_CODE = 42;
    private static final int GALLERY_REQ_CODE = 44;

    private static final String TAG = "MainActivity";

    String cameraPermission[];
    String storagePermission[];

    image_data tmp_crop_img;


    Uri imageUri;
    ImageView Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mList_btn = findViewById(R.id.list_btn);
        ImageButton mCamera_btn = findViewById(R.id.camera_btn);
        ImageButton mGallery_btn = findViewById(R.id.gallery_btn);
        ImageButton mCrop_btn = findViewById(R.id.crop_btn);
        Img = findViewById(R.id.imageView);

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (!checkCameraPermission()) {
            requestCameraPermission();
        }else {

        }

        mList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        mGallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });

        mCamera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openCamera();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mCrop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    startActivity();
                }
                else {
                    Toast.makeText(MainActivity.this, "PLEASE ADD IMAGE", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void openCamera() throws IOException {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQ_CODE);

    }

    private void pickFromGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/*");
        startActivityForResult(gallery, GALLERY_REQ_CODE);
    }

    private void startActivity(){
        CropImage.activity(imageUri).start(MainActivity.this);
    }

    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQ_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                tmp_crop_img = new image_data(imageUri.toString(), resultUri.toString(), "image");

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("imgObj", tmp_crop_img);
                startActivity(intent);
//                Img.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        } else if (requestCode == GALLERY_REQ_CODE && resultCode == Activity.RESULT_OK){
            Uri resultUri;
            resultUri = data.getData();
            imageUri = resultUri;
            Img.setImageURI(imageUri);
//            CropImage.activity(imageUri).start(this);
        } else if (requestCode == CAMERA_REQ_CODE && resultCode == Activity.RESULT_OK){
//            Uri resultUri;
//            resultUri = data.getData();
            Img.setImageURI(imageUri);
        }
    }
}