package com.application.films.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.films.R;
import com.application.films.domain.Unit;
import com.application.films.domain.dao.FilmDao;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;

public class FilmAdapter extends BaseAdapter {

    FilmDao _dao = Unit.getFilmDao();

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
        ((TextView) view.findViewById(R.id.film_genre)).setText(film.get_genre());
        ((TextView) view.findViewById(R.id.film_year)).setText(Integer.toString(film.get_year()));

        return view;
    }


}
