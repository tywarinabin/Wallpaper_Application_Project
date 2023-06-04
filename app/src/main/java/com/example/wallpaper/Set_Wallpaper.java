package com.example.wallpaper;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class Set_Wallpaper extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    Button set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        set =  findViewById(R.id.setwallppr);
        imageView = findViewById(R.id.finalimage);
        intent = getIntent();

        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "Set Wallpaper Succesfully", Toast.LENGTH_SHORT).show();
                }
                catch (IOException x){
                    x.printStackTrace();
                }
            }
        });
    }
}