package com.application.films;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.application.films.adapters.FilmAdapter;
import com.application.films.adapters.GenreAdapter;
import com.application.films.domain.models.Film;
import com.application.films.domain.models.Genre;

public class GenreAddActivity extends AppCompatActivity {

    private static GenreAdapter genreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genre_add);
  /*      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }

    }

    public void onClickSave(View v) {

        EditText genreNameText = findViewById(R.id.filmName);
        String genreName = genreNameText.getText().toString();
        Toast.makeText(this, genreName, Toast.LENGTH_LONG).show();

        GenreAdapter filmAdapter = MainActivity.getGenreAdapter();//new FilmAdapter()не коректно робить
        filmAdapter.addItem(new Genre(0, genreName));
        finish();//
    }



}