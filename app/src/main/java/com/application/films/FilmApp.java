package com.application.films;

import android.app.Application;

public class FilmApp extends Application {

    static FilmApp self;

    public static FilmApp getApp() {

        return self;
    }

    public FilmApp(){

        FilmApp.self = this;
    }
}
