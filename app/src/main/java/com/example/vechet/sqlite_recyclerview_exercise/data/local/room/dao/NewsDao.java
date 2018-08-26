package com.example.vechet.sqlite_recyclerview_exercise.data.local.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.vechet.sqlite_recyclerview_exercise.entity.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAllNews();

    @Insert
    void addNews(News news);
    @Update
    void updateNews(News news);
    @Delete
    void deleteNews(News news);

    @Query("DELETE FROM news WHERE id = :id")
    void deleteNewsById(int id);

    @Query("DELETE FROM news WHERE title = :title")
    void deleteNewsByTitle(String title);

    @Query("SELECT id FROM news")
    List<News> getNewsId();
}
