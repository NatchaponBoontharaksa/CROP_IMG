package com.example.crop;

import java.io.Serializable;

public class image_data implements Serializable {

    private String imgURL;
    private String FileName;

    public image_data(String imgURL, String FileName) {
        this.imgURL = imgURL;
        this.FileName = FileName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}
