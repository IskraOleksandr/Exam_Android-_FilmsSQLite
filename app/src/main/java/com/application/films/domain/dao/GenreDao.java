package com.application.films.domain.dao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.application.films.domain.FilmSQLiteHelper;
import com.application.films.domain.models.Genre;

public class GenreDao  implements IDao<Genre> {

    FilmSQLiteHelper _helper;
    SQLiteDatabase _database;
    Cursor _cursor;

    public GenreDao(FilmSQLiteHelper _helper) {
        /*_helper = _helper;
        _database = _helper.getReadableDatabase();*/
        this._helper = _helper;
        _database = this._helper.getReadableDatabase();
        _cursor = _database.rawQuery("SELECT id, name FROM genre", new String[]{});
    }
    public void getAllItem(){
        _cursor = _database.rawQuery("SELECT id, name FROM genre", new String[]{});
    }
    @Override
    public int getItemCount() {
        return _cursor.getCount();
    }

    @Override
    public Genre getItem(int pos) {
        _cursor.moveToPosition(pos);
        return new Genre(_cursor);
    }

    @SuppressLint("Range")
    @Override
    public int getItemId(int pos) {
        _cursor.moveToPosition(pos);
        return _cursor.getInt(_cursor.getColumnIndex("id"));
    }

    @Override
    public Genre getItemById(int id) {

        Cursor cursor = _helper.getReadableDatabase().rawQuery(
                "SELECT id, name FROM genre WHERE id=?",
                new String[]{Integer.toString(id)}
        );
        if(cursor.getCount() > 0 && cursor.moveToFirst()){

            return new Genre(cursor);
        }
        return null;
    }

    @Override
    public void addItem(Genre genre) {
        _helper.getWritableDatabase().execSQL(
                "INSERT INTO genre (name) VALUES (?)",
                new String[]{genre.get_name()});
    }


    public void updateItem(Genre genre) {
        _helper.getWritableDatabase().execSQL(
                "Update genre set name = ? Where id= ?",
                new String[]{genre.get_name(), Integer.toString(genre.get_id())});
    }

    public void deleteItemById(int id){
        _helper.getWritableDatabase().execSQL(
                "Delete from genre WHERE id = ?",
                new String[]{Integer.toString(id)});
    }
}
