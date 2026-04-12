package com.example.pizzarecipes.ui;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pizzarecipes.R;
import com.example.pizzarecipes.adapter.PizzaAdapter;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.service.ProduitService;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;

public class ListPizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_list_pizza);

        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Nos Recettes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvPizzas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        List<Produit> pizzas = ProduitService.getInstance().findAll();
        PizzaAdapter adapter = new PizzaAdapter(this, pizzas);
        
        // Optimisations de performance pour un défilement fluide
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}