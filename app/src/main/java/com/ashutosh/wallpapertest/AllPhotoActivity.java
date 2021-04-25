package com.ashutosh.wallpapertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashutosh.wallpapertest.adapter.CategoryList_Adapter;
import com.ashutosh.wallpapertest.adapter.Wallpaper_Adapter;
import com.ashutosh.wallpapertest.model.CategoryModelList;
import com.ashutosh.wallpapertest.model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllPhotoActivity extends AppCompatActivity {
    String PAGE_URL, URL;
    RecyclerView recyclerView;
    Wallpaper_Adapter wallpaperAdapter;
    List<Model> modelList;
    Boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_photo);

        Intent intent = getIntent();
        PAGE_URL = intent.getStringExtra("PAGE_URL");

        URL = PAGE_URL + "&per_page=1";

        recyclerView = findViewById(R.id.recyclerView);
        modelList = new ArrayList<>();
        wallpaperAdapter = new Wallpaper_Adapter(this, modelList);
        recyclerView.setAdapter(wallpaperAdapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }


//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                currentItems = gridLayoutManager.getChildCount();
//                totalItems = gridLayoutManager.getItemCount();
//                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();
//                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
//                    isScrolling = false;
////                    fetchWallpaper();
//                    fetchData();
//
//                }
//            }
        });        fetchData();
    }

    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("hits");
//                    Toast.makeText(MainActivity.this, array.toString() + "", Toast.LENGTH_SHORT).show();
                    Toast.makeText(AllPhotoActivity.this, "NormalList", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject eachPhoto = array.getJSONObject(i);
                        String imageTags = eachPhoto.getString("tags");
//                        Toast.makeText(MainActivity.this, eachPhoto.getString("largeImageURL"), Toast.LENGTH_SHORT).show();
                        Model model = new Model(eachPhoto.getInt("id"), eachPhoto.getString("previewURL"), eachPhoto.getString("largeImageURL"));
                        modelList.add(model);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}