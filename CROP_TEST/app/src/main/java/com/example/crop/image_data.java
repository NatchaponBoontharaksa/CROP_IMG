package com.example.crop;

import java.io.Serializable;
import java.util.ArrayList;

public class image_data implements Serializable {

    private String imgURL;
    private ArrayList<String> imgCropURL;
    private String FileName;

    public image_data(String imgURL, String FileName) {
        this.imgURL = imgURL;
        this.imgCropURL = new ArrayList<>();
        this.FileName = FileName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String URL) {
        this.imgURL = URL;
    }

    public ArrayList<String> getImgCropURL() {
        return imgCropURL;
    }

    public void setImgCropURL(String URL) {
        this.imgCropURL.add(URL);
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}
