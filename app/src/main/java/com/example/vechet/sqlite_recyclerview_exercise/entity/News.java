package com.example.vechet.sqlite_recyclerview_exercise.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "news")
public class News implements Serializable{
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    @ColumnInfo(name = "sub_title")
    public String subTitle;
    @ColumnInfo(name = "image_thump")
    public String imageThump;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", imageThump='" + imageThump + '\'' +
                '}';
    }
}
