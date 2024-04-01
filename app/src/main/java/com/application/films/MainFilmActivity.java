package com.application.films;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.application.films.adapters.FilmAdapter;
import com.application.films.adapters.GenreAdapter;
import com.application.films.domain.models.Film;

public class MainFilmActivity extends AppCompatActivity {
    private static FilmAdapter filmAdapter;
    MainFilmActivity activity;
    ListView listView;
    public static FilmAdapter getAdapter() {
        return filmAdapter;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (filmAdapter == null) {
            filmAdapter = new FilmAdapter();
        }

        listView = ((ListView) findViewById(R.id.lv_films));
        filmAdapter.getAllItem();
        listView.setAdapter(filmAdapter);

        activity = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(activity, FilmActivity.class);
                Film film = ((Film) parent.getItemAtPosition(position));
                intent.putExtra("id", film.get_id());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Add film");
        menu.add(0, 2, 2, "Add genre");
        menu.add(0, 3, 3, "Show all genre");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        activity = this;
        switch (item.getItemId()) {
            case 1:
                intent = new Intent(activity, FilmActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(activity, GenreActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(activity, MainGenreActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return super.onOptionsItemSelected(item);
    }

}