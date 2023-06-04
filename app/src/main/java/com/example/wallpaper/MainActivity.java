package com.example.wallpaper;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature,mbus,mcar,mtrain,mtrending;
    EditText editText;
    ImageButton search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        mbus = findViewById(R.id.bus);
        mcar = findViewById(R.id.car);
        mtrain = findViewById(R.id.train);
        mtrending = findViewById(R.id.trending);
        mnature = findViewById(R.id.nature);
        search = findViewById(R.id.search);
        editText = findViewById(R.id.editText);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findPhotos();









        mnature.setOnClickListener(v -> {
            String query = "nature";
            getsearchImage(query);
        });

        mbus.setOnClickListener(v -> {
            String query = "bus";
            getsearchImage(query);
        });


        mtrain.setOnClickListener(v -> {
            String query = "train";
            getsearchImage(query);
        });

        mtrending.setOnClickListener(v -> findPhotos());


        mcar.setOnClickListener(v -> {
            String query = "car";
            getsearchImage(query);
        });


        search.setOnClickListener(v -> {
            String query = editText.getText().toString().trim().toLowerCase();
            if(query.isEmpty()){
                Toast.makeText(MainActivity.this, "Enter Something to Search", Toast.LENGTH_SHORT).show();
            }
            else {
                getsearchImage(query);
            }
        });

    }


    private void getsearchImage(String query) {
        APIUtilities.getApiInterface().getSearchImage(query,1,50).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getImages());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error Occured to get photo from API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
    private void findPhotos() {
        APIUtilities.getApiInterface().getImages(2,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getImages());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "Error occour to find Photo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}