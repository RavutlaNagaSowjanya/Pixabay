package com.example.acer.pixabay1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ProgressBar p;
    RecyclerView r;
    String imageurl = "https://pixabay.com/api/?key=10860770-e807ec9f8c4c5227c982f32c1&q=";
    ArrayList<Datastore> datastore = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p = findViewById(R.id.progress);
        r = findViewById(R.id.recycler);
        r.setLayoutManager(new GridLayoutManager(this,2));
        r.setAdapter(new MyAdapter(this,datastore));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.flowers:
                new Task().execute(imageurl + "flowers");
                break;
            case R.id.animals:
                new Task().execute(imageurl + "animals");
                break;
            case R.id.novels:
                new Task().execute(imageurl + "novels");
                break;
            case R.id.food:
                new Task().execute(imageurl + "actors");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class Task extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    return scanner.next();
                } else
                    return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            p.setVisibility(View.GONE);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("imageurl",jsonObject.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject get = jsonArray.getJSONObject(i);
                        String view = get.getString("views");
                        Log.i("views",view.toString());
                        String comments = get.getString("comments");
                        Log.i("comments",comments.toString());
                        String like = get.getString("likes");
                        Log.i("likes",like.toString());
                        String tag = get.getString("tags");
                        Log.i("tags",tag.toString());
                        String im = get.optString("userImageURL");
                        Log.i("userImageURL",im.toString());
                        datastore.add(new Datastore(im, like, view, comments, tag));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
