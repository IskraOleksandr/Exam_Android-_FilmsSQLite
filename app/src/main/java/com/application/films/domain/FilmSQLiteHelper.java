package com.application.films.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.application.films.FilmApp;

public class FilmSQLiteHelper extends SQLiteOpenHelper {
    private static /*final*/ int DATABASE_VERSION = 6;


    public FilmSQLiteHelper(){
        super (FilmApp.getApp(), "FilmDatabase", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE genre (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        db.execSQL("CREATE TABLE films (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, " +
                "year INTEGER, id_genre INTEGER, FOREIGN KEY (id_genre) REFERENCES genre(id))");

        db.execSQL("INSERT INTO genre (name) VALUES ('Фантастика'),('Ужасы'),('Детективы'),('Триллеры'),('Комедии'),('18+')");

        db.execSQL("INSERT INTO films (id_genre, title, year) VALUES "+
                "(1, 'Звездные войны. Эпизод I: Скрытая угроза', 1999)," +
                "(1, 'Звездные войны. Эпизод II: Атака клонов', 2002)," +
                "(1, 'Звездные войны. Эпизод III: Месть ситхов', 2005)," +
                "(1, 'Звездные войны. Эпизод IV: Новая надежда', 1977)," +
                "(1, 'Звездные войны. Эпизод V: Империя наносит ответный удар', 1980)," +
                "(1, 'Звездные войны. Эпизод V: Возвращение джедая', 1983)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion < newVersion){//oldVersion==2 && newVersion==3
            db.execSQL("INSERT INTO films (id_genre, title, year) VALUES "+
                    "(1, 'Звездные войны. Эпизод VII: Пробуждение силы', 2015)," +
                    "(1, 'Звездные войны. Эпизод VIII: Последние джедаи', 2017)," +
                    "(1, 'Звездные войны. Эпизод IX: Скайуокер. Восход', 2019)," +
                    "(1, 'Звездные войны. Эпизод X: Новый орден джедаев', 2026)");
            Log.d("onUpgrade", "onUpgrade");
        }
    }
}
