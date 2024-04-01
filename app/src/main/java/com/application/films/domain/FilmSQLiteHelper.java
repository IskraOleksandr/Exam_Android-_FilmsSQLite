package com.application.films.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.application.films.FilmApp;

public class FilmSQLiteHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;


    public FilmSQLiteHelper(){
        super (FilmApp.getApp(), "FilmDatabaseV1", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE genre (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        db.execSQL("CREATE TABLE films (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, director TEXT," +
                "year INTEGER, id_genre INTEGER, FOREIGN KEY (id_genre) REFERENCES genre(id))");

        db.execSQL("INSERT INTO genre (name) VALUES ('Фантастика'),('Ужасы'),('Детективы'),('Триллеры'),('Комедии'),('18+')");

        db.execSQL("INSERT INTO films (id_genre, title, director, year) VALUES "+
                "(1, 'Звездные войны. Эпизод I: Скрытая угроза', 'Джордж Лукас', 1999)," +
                "(1, 'Звездные войны. Эпизод II: Атака клонов', 'Джордж Лукас', 2002)," +
                "(1, 'Звездные войны. Эпизод III: Месть ситхов', 'Дэйв Филони', 2005)," +
                "(1, 'Звездные войны. Эпизод IV: Новая надежда', 'Джордж Лукас', 1977)," +
                "(1, 'Звездные войны. Эпизод V: Империя наносит ответный удар', 'Ирвин Кершнер', 1980)," +
                "(1, 'Звездные войны. Эпизод VI: Возвращение джедая', 'Ричард Маркуанд', 1983)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1 && newVersion==2){
            db.execSQL("INSERT INTO films (id_genre, title,director, year) VALUES "+
                    "(1, 'Звездные войны. Эпизод VII: Пробуждение силы','Джей Джей Абрамс', 2015)," +
                    "(1, 'Звездные войны. Эпизод VIII: Последние джедаи','Райан Джонсон', 2017)," +
                    "(1, 'Звездные войны. Эпизод IX: Скайуокер. Восход','Джей Джей Абрамс', 2019)");
        }
    }
}
