package com.ashutosh.wallpapertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import com.ashutosh.wallpapertest.model.Wallpaper_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerViewCategoryList;
    Wallpaper_Adapter wallpaperAdapter;
    CategoryList_Adapter categoryListAdapter;
    List<Model> wallpaperModelList;
    List<CategoryModelList> categoryModelLists;
    int page_no = 1;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    String url = "https://pixabay.com/api/?key=21200728-66e656db38c1b714199b8e589";
    public ArrayList<Model> globalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        563492ad6f917000010000018a0779f0bdec43aa96c50def121a2830
        recyclerView = findViewById(R.id.recyclerView);
        wallpaperModelList = new ArrayList<>();
        wallpaperAdapter = new Wallpaper_Adapter(this, wallpaperModelList);
        recyclerView.setAdapter(wallpaperAdapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        globalList = new ArrayList<>();
//  todo CategoryModelList
        recyclerViewCategoryList = findViewById(R.id.categoryList);
        categoryModelLists = new ArrayList<>();
        categoryListAdapter = new CategoryList_Adapter(categoryModelLists, this);
        recyclerViewCategoryList.setAdapter(categoryListAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);
//        ApiHelper.getMoreImages(this, 1, list -> {
//            globalList.addAll(list);
//        });
//    }

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
        });


//        fetchWallpaper();
        fetchData();
        carsCategoryData();
        categoryData();
        superHeroCategoryData();
        bikeCategoryData();
    }


    public void categoryData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String PAGE_URL = url + "&q=bike&image_type=portrait + photo&pretty=true";
        StringRequest requestTags = new StringRequest(Request.Method.GET, PAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("hits");
                        JSONObject eachPhoto = array.getJSONObject(0);
                        CategoryModelList model = new CategoryModelList(eachPhoto.getInt("id"), eachPhoto.getString("largeImageURL"), eachPhoto.getString("tags"), eachPhoto.getString("largeImageURL"), PAGE_URL);
                        categoryModelLists.add(model);
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
          

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e + "Json Error", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error + "Volley Error", Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(requestTags);

    }

    public void carsCategoryData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String PAGE_URL = url + "&q=cars&image_type=photo&pretty=true";
        StringRequest requestTags = new StringRequest(Request.Method.GET, PAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("hits");
                    JSONObject eachPhoto = array.getJSONObject(0);
                    CategoryModelList model = new CategoryModelList(eachPhoto.getInt("id"), eachPhoto.getString("previewURL"), eachPhoto.getString("tags"), eachPhoto.getString("largeImageURL"), PAGE_URL);
                    categoryModelLists.add(model);
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e + "Json Error", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error + "Volley Error", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(requestTags);

    }

    public void superHeroCategoryData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String PAGE_URL = url + "&q=superHero&image_type=photo&pretty=true";
        StringRequest requestTags = new StringRequest(Request.Method.GET, PAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("hits");
                    JSONObject eachPhoto = array.getJSONObject(0);
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    CategoryModelList model = new CategoryModelList(eachPhoto.getInt("id"), eachPhoto.getString("previewURL"), eachPhoto.getString("tags"), eachPhoto.getString("largeImageURL"), PAGE_URL);
                    categoryModelLists.add(model);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e + "Json Error", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error + "Volley Error", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(requestTags);

    }

    public void bikeCategoryData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String PAGE_URL = url + "&q=animal&image_type=photo&pretty=true";
        StringRequest requestTags = new StringRequest(Request.Method.GET, PAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("hits");
                    JSONObject eachPhoto = array.getJSONObject(0);
                    CategoryModelList model = new CategoryModelList(eachPhoto.getInt("id"), eachPhoto.getString("previewURL"), eachPhoto.getString("tags"), eachPhoto.getString("largeImageURL"), PAGE_URL);
                    categoryModelLists.add(model);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e + "Json Error", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error + "Volley Error", Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(requestTags);

    }

    public void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("hits");
//                    Toast.makeText(MainActivity.this, array.toString() + "", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, "NormalList", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject eachPhoto = array.getJSONObject(i);
                        String imageTags = eachPhoto.getString("tags");
//                        Toast.makeText(MainActivity.this, eachPhoto.getString("largeImageURL"), Toast.LENGTH_SHORT).show();
                        Model model = new Model(eachPhoto.getInt("id"), eachPhoto.getString("previewURL"), eachPhoto.getString("largeImageURL"));
                        wallpaperModelList.add(model);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e + "Json Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error + "Volley Error", Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(request);
    }
}

//    public void fetchWallpaper() {
//        StringRequest request = new StringRequest(Request.Method.GET, url,
//                response -> {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONArray jsonArray = jsonObject.getJSONArray("photos");
//                        int length = jsonArray.length();
//
//                        for (int i = 0; i < length; i++) {
//                            JSONObject object = jsonArray.getJSONObject(i);
//                            int id = object.getInt("id");
//
//                            JSONObject objectImages = object.getJSONObject("src");
//
//
//                            String originalUrl = objectImages.getString("original");
//                            String mediumUrl = objectImages.getString("medium");
//
//                            Wallpaper_Model wallpaper_model = new Wallpaper_Model(id, originalUrl, mediumUrl);
//                            wallpaperModelList.add(wallpaper_model);
//
//                        }
//
//                        wallpaperAdapter.notifyDataSetChanged();
//                        page_no++;
//                    } catch (JSONException e) {
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("Authorization", "563492ad6f917000010000018a0779f0bdec43aa96c50def121a2830");
//                return params;
//            }
//        };
////        21200728-66e656db38c1b714199b8e589
//
////        https://pixabay.com/api/?key=21200728-66e656db38c1b714199b8e589&q=yellow+flowers&image_type=photo&pretty=true
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(request);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.item_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////        Toast.makeText(MainActivity.this, "dfsfssf", Toast.LENGTH_SHORT).show();
////
////        if (item.getItemId() == R.id.nav_search){
////              SearchView searchView = findViewById(R.id.searchView);
////              searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////
////                  @Override
////                  public boolean onQueryTextSubmit(String query) {
////                      Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
////
////                      url = "https://api.pexels.com/v1/search/?page=" + page_no + "&per_page=1&query=" + query;
////                      wallpaperModelList.clear();
////                      fetchWallpaper();
////                      return true;
////                  }
////
////                  @Override
////                  public boolean onQueryTextChange(String newText) {
////                      Toast.makeText(MainActivity.this, "ddddddddddd", Toast.LENGTH_SHORT).show();
////
////                      return false;
////                  }
////              });
////          }
//
//
//        if (item.getItemId() == R.id.nav_search) {
//
//
//            AlertDialog.Builder alert = new AlertDialog.Builder(this);
//            final EditText editText = new EditText(this);
//            editText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String query = editText.getText().toString().toLowerCase();
//                    url = "https://api.pexels.com/v1/search/?page=" + page_no + "&per_page=1&query=" + query;
//                    wallpaperModelList.clear();
//                    fetchWallpaper();
//                }
//            });
////            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
////            alert.setMessage("Enter Category e.g. Nature");
////            alert.setTitle("Search Wallpaper");
////            alert.setView(editText);
////            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                @Override
////                public void onClick(DialogInterface dialog, int which) {
////                    String query = editText.getText().toString().toLowerCase();
////                    url = "https://api.pexels.com/v1/search/?page="+ page_no +"&per_page=1&query=" + query;
////                    wallpaperModelList.clear();
////                    fetchWallpaper();
////                }
////            });
////            alert.show();
////        }
//            return super.onOptionsItemSelected(item);
//        }
//    }
