package com.application.films;

import android.content.Intent;
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
import com.application.films.domain.models.Genre;

public class GenreEdit_DelActivity extends AppCompatActivity {
    private static GenreAdapter genreAdapter;
    private FilmAdapter filmAdapter;
    //    private Spinner spinner;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genre_edit_del);
  /*      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        genreAdapter = MainGenreActivity.getGenreAdapter();
        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }

        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            id = arguments.getInt("id");
            Toast.makeText(this, id + "/", Toast.LENGTH_LONG).show();
        }

        if (id != 0) {//получаем элемент по id
            Genre genre = (Genre) genreAdapter.getItemById(id);
            ((EditText) findViewById(R.id.filmName)).setText(genre.get_name());
        }
        /*Android Studio Java  spinner как восстановить конкретный жанр фильма в первую позицию spinner*/

    }

    public void onClickSave(View v) {

        EditText filmNameText = findViewById(R.id.filmName);
        String filmName = filmNameText.getText().toString();


        Toast.makeText(this, filmName, Toast.LENGTH_LONG).show();

        GenreAdapter genreAdapter = MainGenreActivity.getGenreAdapter();//не коректно робить
        genreAdapter.updateItem(new Genre(id, filmName));
        finish();
    }

    public void onClickDel(View v){
        GenreAdapter genreAdapter = MainActivity.getGenreAdapter();//не коректно робить
        genreAdapter.delleteItemById(id);
        finish();
    }
}