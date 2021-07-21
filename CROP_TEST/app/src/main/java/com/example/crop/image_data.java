package com.example.crop;

import java.io.Serializable;

public class image_data implements Serializable {

    private String imgURL;
    private String[] imgCropURL;
    private String FileName;

    public image_data(String imgURL, String imgCropURL, String FileName) {
        this.imgURL = imgURL;
        this.imgCropURL = new String[]{imgCropURL};
        this.FileName = FileName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String URL) {
        this.imgURL = URL;
    }

    public String getImgCropURL(int index) {
        return imgCropURL[index];
    }

    public void setImgCropURL(String URL) {
        this.imgCropURL[imgCropURL.length] = URL;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}
