package com.application.films;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FilmAddActivity extends AppCompatActivity {

    private static GenreAdapter genreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_film_add);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }

        Spinner spinner = ((Spinner) findViewById(R.id.spinner));
        spinner.setAdapter(genreAdapter);

    }

    public void onClickSave(View v) {

        EditText filmNameText = findViewById(R.id.filmName);
        String filmName = filmNameText.getText().toString();

        EditText directorText = findViewById(R.id.director);

        EditText yearText = findViewById(R.id.filmYear);
        int year = Integer.parseInt(yearText.getText().toString());

        Spinner spinner = findViewById(R.id.spinner);
        String selected = spinner.getSelectedItem().toString();
        Genre genre = ((Genre)spinner.getSelectedItem());

        Toast.makeText(this, filmName+"/"+ yearText.getText()+"/"+ genre.get_name(), Toast.LENGTH_LONG).show();

        FilmAdapter filmAdapter = MainActivity.getAdapter();//new FilmAdapter()не коректно робить
        filmAdapter.addItem(new Film(0, filmName, genre.get_name(), year));
       /* Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/

        finish();//
//        GenreAdapter genreAdapter = MainActivity.getGenreAdapter();
//        genreAdapter.addItem(new Genre(0,"Аниме"));

//        FilmAdapter filmAdapter = MainActivity.getAdapter();//new FilmAdapter()
//        filmAdapter.addItem(new Film(0, title, "Фантастика", 2014));
//        finish();
    }



}