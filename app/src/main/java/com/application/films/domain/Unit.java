package com.application.films.domain;

import com.application.films.domain.dao.FilmDao;
import com.application.films.domain.dao.GenreDao;

public class Unit {

    private static Unit _instance = new Unit();

    public static FilmSQLiteHelper getHelper(){

        return _instance._helper;
    }
    public static FilmDao getFilmDao(){

        return _instance._filmDao;
    }
    public static GenreDao getGenreDao(){

        return _instance._genreDao;
    }


    FilmSQLiteHelper _helper;
    FilmDao _filmDao;
    GenreDao _genreDao;

    private Unit(){

        _helper = new FilmSQLiteHelper();
        _filmDao = new FilmDao(_helper);
        _genreDao = new GenreDao(_helper);
    }


}
