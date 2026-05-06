package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.starsgallery.R;
import com.example.starsgallery.adapter.StarAdapter;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.services.StarService;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> starsOriginales;
    private List<Star> starsAffichees;
    private StarAdapter monAdapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Configuration de la Toolbar Rouge
        Toolbar toolbar = findViewById(R.id.star_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Stars");
        }

        // Récupération des données
        starsOriginales = StarService.get().getAll();
        starsAffichees = new ArrayList<>(starsOriginales);

        // Configuration du RecyclerView
        rv = findViewById(R.id.recycler_stars);
        monAdapter = new StarAdapter(this, starsAffichees);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(monAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // Configuration de la Recherche
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }
            @Override
            public boolean onQueryTextChange(String newText) {
                filtrer(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Logique du bouton Partager
        if (item.getItemId() == R.id.app_bar_share) {
            String message = "Découvre ma galerie de stars sur l'app StarsGallery !";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(shareIntent, "Partager via"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filtrer(String texte) {
        starsAffichees.clear();
        for (Star s : starsOriginales) {
            if (s.getName().toLowerCase().contains(texte.toLowerCase())) {
                starsAffichees.add(s);
            }
        }
        monAdapter.notifyDataSetChanged();
    }
}
