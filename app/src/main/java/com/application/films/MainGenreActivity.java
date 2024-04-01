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

import com.application.films.adapters.GenreAdapter;
import com.application.films.domain.models.Genre;

public class MainGenreActivity extends AppCompatActivity {
    private static GenreAdapter genreAdapter;
    MainGenreActivity activity;
    ListView listView;
    public static GenreAdapter getGenreAdapter() {
        return genreAdapter;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_genre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (genreAdapter == null) {
            genreAdapter = new GenreAdapter();
        }

        listView = ((ListView) findViewById(R.id.lv_genres));
        genreAdapter.getAllItem();
        listView.setAdapter(genreAdapter);

        activity = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(activity, GenreActivity.class);
                Genre genre = ((Genre) parent.getItemAtPosition(position));
                intent.putExtra("id", genre.get_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, getResources().getString(R.string.add_film));
        menu.add(0, 2, 2, getResources().getString(R.string.add_genre));
        menu.add(0, 3, 3, getResources().getString(R.string.all_films));

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
                intent = new Intent(activity, MainFilmActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }
}