package com.example.vechet.sqlite_recyclerview_exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vechet.sqlite_recyclerview_exercise.adapter.NewsAdapter;
import com.example.vechet.sqlite_recyclerview_exercise.callback.OnItemClickListener;
import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.dao.NewsDao;
import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.db.NewsDatabase;
import com.example.vechet.sqlite_recyclerview_exercise.entity.News;
import com.example.vechet.sqlite_recyclerview_exercise.util.ToastMessase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    RecyclerView recyclerView;
    NewsDao newsDao;
    NewsAdapter adapter;
    public static final int ADD_NEWS = 1;
    ArrayList<News> news = new ArrayList<>();
    public static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SQLite RE");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsDao = NewsDatabase.getDatabase(this).getNewsDao();
        adapter = new NewsAdapter(this,news);

        adapter.setClickListener(this);
        adapter.setNews(newsDao.getAllNews());
        recyclerView.setAdapter(adapter);
        Log.e(TAG, "onCreate: " + newsDao.getAllNews() );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearchCollap);
        SearchView searchView = (SearchView)menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdd:
                Intent in = new Intent(this, AddNewsActivity.class);
                startActivityForResult(in, ADD_NEWS);

                break;
            case R.id.menuSearchCollap:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NEWS && resultCode == RESULT_OK){
            News news1 = (News)data.getSerializableExtra("addNews");
            Log.e(TAG, "onActivityResult: " + news1);
            newsDao.addNews(news1);
            adapter.addNews(news1);
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void OnDeleteNews(int position) {
        News newss = news.get(position);
        int id = newss.id;
//        getId.id = position;
        ToastMessase.showMessage(getApplicationContext(), Integer.toString(id));
      //  newsDao.deleteNews(getId);
    }

}
