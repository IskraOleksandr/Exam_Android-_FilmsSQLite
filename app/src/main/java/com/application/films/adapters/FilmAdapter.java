package com.application.films.adapters;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.films.MainFilmActivity;
import com.application.films.R;
import com.application.films.domain.Unit;
import com.application.films.domain.dao.FilmDao;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;

public class FilmAdapter extends BaseAdapter {

    FilmDao _dao = Unit.getFilmDao();

    public void getAllItem(){
        _dao.getAllItem();
    }
    @Override
    public int getCount() {
        return _dao.getItemCount();
    }

    @Override
    public Object getItem(int pos) {
        return _dao.getItem(pos);
    }

    @Override
    public long getItemId(int pos) {
        return _dao.getItemId(pos);
    }

    public Object getItemById(int id) {
        return _dao.getItemById(id);
    }

    public void addItem(Film film) {
        _dao.addItem(film);
    }
    public void updateItem(Film film) {
        _dao.updateItem(film);
    }
    public void delleteItemById(int id) {
        _dao.deleteItemById(id);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.film_item, null);
        }


        Film film = (Film) getItem(pos);
        ((TextView) view.findViewById(R.id.film_title)).setText(film.get_title());
        ((TextView) view.findViewById(R.id.director)).setText(view.getResources().getString(R.string.film_director)+' '+film.get_director());
        ((TextView) view.findViewById(R.id.film_genre)).setText(view.getResources().getString(R.string.film_genre)+' '+film.get_genre());
        ((TextView) view.findViewById(R.id.film_year)).setText(view.getResources().getString(R.string.film_year)+' '+film.get_year());

        return view;
    }


}
