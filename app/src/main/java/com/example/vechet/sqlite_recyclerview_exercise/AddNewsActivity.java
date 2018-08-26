package com.example.vechet.sqlite_recyclerview_exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.vechet.sqlite_recyclerview_exercise.callback.OnItemClickListener;
import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.dao.NewsDao;
import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.db.NewsDatabase;
import com.example.vechet.sqlite_recyclerview_exercise.entity.News;

public class AddNewsActivity extends AppCompatActivity {

    EditText etEnterTitle, etEnterSubTitle, etEnterLinkImage;
    Button btnSaveNewNews;
    NewsDao newsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        etEnterLinkImage = findViewById(R.id.etEnterLinkImage);
        etEnterSubTitle = findViewById(R.id.etEnterSubTitle);
        etEnterTitle = findViewById(R.id.etEnterTitle);
        btnSaveNewNews = findViewById(R.id.btnSaveNewNews);
        newsDao = NewsDatabase.getDatabase(this).getNewsDao();

        btnSaveNewNews.setOnClickListener(v->{
            News news = new News();
            news.title = etEnterTitle.getText().toString();
            news.subTitle = etEnterSubTitle.getText().toString();
            news.imageThump = etEnterLinkImage.getText().toString();

            Intent in = new Intent();
            in.putExtra("addNews", news);
            setResult(RESULT_OK, in);
            finish();
        });
    }
}
