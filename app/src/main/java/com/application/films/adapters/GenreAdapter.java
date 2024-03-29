package com.application.films.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.films.R;
import com.application.films.domain.Unit;
import com.application.films.domain.dao.GenreDao;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;

public class GenreAdapter extends BaseAdapter {

    GenreDao _dao = Unit.getGenreDao();

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

    public void addItem(Genre genre){
        Log.d("addItem", "item added");
        _dao.addItem(genre);
    }
    public void updateItem(Genre genre){
        Log.d("addItem", "item added");
        _dao.updateItem(genre);
    }
    public void delleteItemById(int id) {
        _dao.deleteItemById(id);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        if(view == null){

            LayoutInflater inflater  = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.genre_item, null);
        }

        Genre genre = (Genre)getItem(pos);
        ((TextView) view.findViewById(R.id.genre_name)).setText(genre.get_name());

        return view;
    }
}
