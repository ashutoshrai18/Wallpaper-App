 package com.ashutosh.wallpapertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashutosh.wallpapertest.adapter.CategoryList_Adapter;
import com.ashutosh.wallpapertest.adapter.Wallpaper_Adapter;
import com.ashutosh.wallpapertest.model.CategoryModelList;
import com.ashutosh.wallpapertest.model.Model;
import com.ashutosh.wallpapertest.model.Wallpaper_Model;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHori, recyclerViewCategoryList;
    Wallpaper_Adapter wallpaperAdapter;
    CategoryList_Adapter categoryListAdapter;
    List<Model> wallpaperModelList;
    List<CategoryModelList> categoryModelLists;
    int page_no = 1;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    TextView moreButton;

    public ArrayList<Model> globalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        API api = new API(this);

        recyclerViewHori = findViewById(R.id.recyclerView);
        moreButton = findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AllWallpaperActivity.class);
                startActivity(i);
//                i.putExtra("item", wallpaperModelList);
            }
        });
        wallpaperModelList = new ArrayList<>();
        wallpaperAdapter = new Wallpaper_Adapter(this, wallpaperModelList, true);
        recyclerViewHori.setAdapter(wallpaperAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHori.setLayoutManager(layoutManager);

        recyclerViewCategoryList = findViewById(R.id.categoryList);
        buildCategories();
        categoryListAdapter = new CategoryList_Adapter(categoryModelLists, this);
        recyclerViewCategoryList.setAdapter(categoryListAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewCategoryList.setLayoutManager(gridLayoutManager);


        recyclerViewHori.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


        api.fetchWallpapers(null, new API.OnWallpapersResponseListener() {
            @Override
            public void onResponse(ArrayList<Model> models) throws JSONException {
                wallpaperModelList.addAll(models);
                wallpaperAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {
                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void buildCategories() {
        categoryModelLists = new ArrayList<>();
        categoryModelLists.add(new CategoryModelList("Bike", "https://cdn.pixabay.com/photo/2016/04/07/06/53/bmw-1313343_960_720.jpg"));
        categoryModelLists.add(new CategoryModelList("Car", "https://cdn.pixabay.com/photo/2016/11/22/23/44/porsche-1851246_960_720.jpg"));
        categoryModelLists.add(new CategoryModelList("Nature", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg"));
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
