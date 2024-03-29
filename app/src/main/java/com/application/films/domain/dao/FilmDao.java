package com.application.films.domain.dao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.application.films.domain.FilmSQLiteHelper;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;

public class FilmDao implements IDao<Film> {

    FilmSQLiteHelper _helper;

    SQLiteDatabase _database;
    Cursor _cursor;

    public FilmDao(FilmSQLiteHelper _helper) {
        /*_helper = _helper;
        _database = _helper.getReadableDatabase();*/
        this._helper = _helper;
        _database = this._helper.getReadableDatabase();
        _cursor = _database.rawQuery(
                "SELECT films.id, title, name AS genre, year FROM films INNER JOIN genre ON genre.id = id_genre",
                new String[]{});
    }


    @Override
    public int getItemCount() {
        return _cursor.getCount();
    }

    @Override
    public Film getItem(int pos) {

        _cursor.moveToPosition(pos);
        return new Film(_cursor);
    }

    @SuppressLint("Range")
    @Override
    public int getItemId(int pos) {

        _cursor.moveToPosition(pos);
        return _cursor.getInt(_cursor.getColumnIndex("id"));
    }

    @Override
    public Film getItemById(int id) {
        _helper = new FilmSQLiteHelper();//если не сделать будет ошибка
        Cursor cursor = _helper.getReadableDatabase().rawQuery(
                "SELECT films.id, title, name AS genre, year FROM films INNER JOIN genre ON genre.id = id_genre WHERE films.id=?",
                new String[]{Integer.toString(id)}
        );
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {

            return new Film(cursor);
        }
        return null;
    }

    @Override
    public void addItem(Film film) {
        //Log.d("addItem", "addItem");
        //_helper = new FilmSQLiteHelper();если не сделать будет ошибка
        _helper.getWritableDatabase().execSQL(
                "INSERT INTO films (title, year, id_genre) VALUES (?,?, " +
                        "(SELECT id from genre WHERE name = ?))",
                new String[]{film.get_title(), Integer.toString(film.get_year()), film.get_genre()});
    }

    public void deleteItemById(int id){
        //_helper = new FilmSQLiteHelper();если не сделать будет ошибка
        _helper.getWritableDatabase().execSQL(
                "Delete from films WHERE id = ?",
                new String[]{Integer.toString(id)});
    }

    public void updateItem(Film film) {
        Log.d("updateItem", "updateItem");
        //_helper = new FilmSQLiteHelper();если не сделать будет ошибка
        _helper.getWritableDatabase().execSQL(
                "Update films set title = ?, year = ?, id_genre=" + "(SELECT id from genre WHERE name = ?)"
                +"WHERE id = ?",
                new String[]{film.get_title(), Integer.toString(film.get_year()), film.get_genre(), Integer.toString(film.get_id())});
    }
}
