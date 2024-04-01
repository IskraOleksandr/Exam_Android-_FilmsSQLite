package com.application.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.application.films.adapters.FilmAdapter;
import com.application.films.adapters.GenreAdapter;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;


public class FilmActivity extends AppCompatActivity {
    private static GenreAdapter genreAdapter;
    private FilmAdapter filmAdapter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_film);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        filmAdapter = MainFilmActivity.getAdapter();
        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }

        Spinner spinner = ((Spinner) findViewById(R.id.spinner));
        spinner.setAdapter(genreAdapter);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            id = arguments.getInt("id");//получаем элемент по id
        }else {
            ((Button)findViewById(R.id.btnDel)).setVisibility(View.INVISIBLE);
            ((TextView)findViewById(R.id.textView)).setText(getString(R.string.activ_title_film_ad));
            id = 0;
        }

        if (id != 0) {
            ((TextView)findViewById(R.id.textView)).setText(getString(R.string.activ_title_film_ed));
            ((Button)findViewById(R.id.btnDel)).setVisibility(View.VISIBLE);
            Film film = (Film) filmAdapter.getItemById(id);
            ((EditText) findViewById(R.id.filmName)).setText(film.get_title());
            ((EditText) findViewById(R.id.filmYear)).setText(film.get_year() + "");
//            spinner.set();
        }
    }

    public void onClick(View v) {
        FilmAdapter filmAdapter = MainFilmActivity.getAdapter();
        if (v.getId()==R.id.btn) {
            String filmName = ((EditText) findViewById(R.id.filmName)).getText().toString();
            String director = ((EditText) findViewById(R.id.director)).getText().toString();
            int year = Integer.parseInt(((EditText) findViewById(R.id.filmYear)).getText().toString());

            Genre genre = ((Genre) ((Spinner) findViewById(R.id.spinner)).getSelectedItem());

            if (id > 0) {
                filmAdapter.updateItem(new Film(id, filmName, genre.get_name(), year));
            } else {
                filmAdapter.addItem(new Film(id, filmName, genre.get_name(), year));
            }
        }else if (v.getId() == R.id.btnDel){
            filmAdapter.delleteItemById(id);
        }
        Intent intent = new Intent(this, MainFilmActivity.class);
        startActivity(intent);
    }
}