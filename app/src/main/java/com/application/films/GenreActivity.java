package com.application.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.application.films.adapters.GenreAdapter;
import com.application.films.domain.models.Genre;

public class GenreActivity extends AppCompatActivity {
    private static GenreAdapter genreAdapter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            id = arguments.getInt("id");
        }else {
            id = 0;
            ((Button)findViewById(R.id.btnDel)).setVisibility(View.INVISIBLE);
        }

        if (id != 0) {//получаем элемент по id
            Genre genre = (Genre) genreAdapter.getItemById(id);
            ((EditText) findViewById(R.id.filmName)).setText(genre.get_name());
        }
    }

    public void onClick(View v) {
        GenreAdapter genreAdapter = MainGenreActivity.getGenreAdapter();//не коректно робить
        if (v.getId() == R.id.btn) {
            EditText filmNameText = findViewById(R.id.filmName);
            String filmName = filmNameText.getText().toString();
            if (id > 0) {
                genreAdapter.updateItem(new Genre(id, filmName));
            }else {
                genreAdapter.addItem(new Genre(id, filmName));
            }
        } else if (v.getId() == R.id.btnDel) {
            genreAdapter.delleteItemById(id);
        }
        Intent intent = new Intent(this, MainGenreActivity.class);
        startActivity(intent);
    }
}