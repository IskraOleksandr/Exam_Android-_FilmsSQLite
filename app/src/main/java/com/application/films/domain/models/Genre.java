package com.application.films.domain.models;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class Genre {

    private int _id;
    private String _name;

    public Genre(int id, String name) {
        _id = id;
        _name = name;
    }

    @SuppressLint("Range")
    public Genre(Cursor cursor){

        _id = cursor.getInt(cursor.getColumnIndex("id"));
        _name = cursor.getString(cursor.getColumnIndex("name"));;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }
}
