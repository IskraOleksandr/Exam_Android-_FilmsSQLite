package com.application.films.domain.models;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class Film {

    private int _id;
    private String _title;
//    private String _director;
    private String _genre;
    private int _year;

    public Film(int id, String title, String genre, int year) {

        _id = id;
        _title = title;
        _genre = genre;
        _year = year;
    }
    @SuppressLint("Range")
    public Film(Cursor cursor){

        _id = cursor.getInt(cursor.getColumnIndex("id"));
        _title = cursor.getString(cursor.getColumnIndex("title"));
        _year = cursor.getInt(cursor.getColumnIndex("year"));
        _genre = cursor.getString(cursor.getColumnIndex("genre"));;
    }

    public int get_id() {
        return _id;
    }

    public String get_title() {
        return _title;
    }

    public String get_genre() {
        return _genre;
    }

    public int get_year() {
        return _year;
    }
}
