package com.example.vechet.sqlite_recyclerview_exercise.data.local.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.vechet.sqlite_recyclerview_exercise.data.local.room.dao.NewsDao;
import com.example.vechet.sqlite_recyclerview_exercise.entity.News;

@Database(version = 4, entities = {News.class}, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase{
    public static final String DB_NAME = "News_db";

    public abstract NewsDao getNewsDao();

    public static NewsDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, NewsDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
