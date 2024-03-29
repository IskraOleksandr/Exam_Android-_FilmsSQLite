package com.application.films;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.application.films.adapters.FilmAdapter;
import com.application.films.adapters.GenreAdapter;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;

public class FilmEdit_DelActivity extends AppCompatActivity {
    private static GenreAdapter genreAdapter;
    private FilmAdapter filmAdapter;
    //    private Spinner spinner;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_film_edit_del);
  /*      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        filmAdapter = MainActivity.getAdapter();
        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }
        Spinner spinner = ((Spinner) findViewById(R.id.spinner));
        spinner.setAdapter(genreAdapter);

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            id = arguments.getInt("id");
            Toast.makeText(this, id + "/", Toast.LENGTH_LONG).show();
        }
        //получаем элемент по id
        if (id != 0) {
            Film film = (Film) filmAdapter.getItemById(id);
            ((EditText) findViewById(R.id.filmName)).setText(film.get_title());
            ((EditText) findViewById(R.id.filmYear)).setText(film.get_year() + "");

//            spinner.set();
        }
        /*Android Studio Java  spinner как восстановить конкретный жанр фильма в первую позицию spinner*/



    }

    public void onClickSave(View v) {

        EditText filmNameText = findViewById(R.id.filmName);
        String filmName = filmNameText.getText().toString();

        EditText directorText = findViewById(R.id.director);

        EditText yearText = findViewById(R.id.filmYear);
        int year = Integer.parseInt(yearText.getText().toString());

        Spinner spinner = findViewById(R.id.spinner);
        String selected = spinner.getSelectedItem().toString();
        Genre genre = ((Genre) spinner.getSelectedItem());

        Toast.makeText(this, id+"/"+filmName + "/" + yearText.getText() + "/" + genre.get_name(), Toast.LENGTH_LONG).show();

        FilmAdapter filmAdapter = MainActivity.getAdapter();//не коректно робить
        filmAdapter.updateItem(new Film(id, filmName, genre.get_name(), year));//
        finish();
//        GenreAdapter genreAdapter = MainActivity.getGenreAdapter();
//        genreAdapter.addItem(new Genre(0,"Аниме"));
    }

    public void onClickDel(View v){
        FilmAdapter filmAdapter = MainActivity.getAdapter();//не коректно робить
        filmAdapter.delleteItemById(id);
        finish();
    }
}