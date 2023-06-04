package com.example.wallpaper;

import java.util.ArrayList;

public class SearchModel {
    private ArrayList<ImageModel> images = new ArrayList<>();

    public SearchModel(ArrayList<ImageModel> images) {
        this.images = images;
    }

    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }
}
