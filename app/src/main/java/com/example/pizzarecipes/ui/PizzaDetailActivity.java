package com.example.pizzarecipes.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.service.ProduitService;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class PizzaDetailActivity extends AppCompatActivity {

    private Produit produit;
    private boolean ingredientsExpanded = true;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_pizza_detail);

        long id = getIntent().getLongExtra("pizza_id", -1);
        produit = ProduitService.getInstance().findById(id);

        if (produit == null) {
            finish();
            return;
        }

        setupToolbar();
        setupContent();
        setupAnimations();
        setupClickListeners();
        animateContent();
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Animation de la toolbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(toolbar, "toolbar");
        }
    }

    private void setupContent() {
        ImageView img = findViewById(R.id.img);
        TextView title = findViewById(R.id.title);
        Chip chipTime = findViewById(R.id.chipTime);
        Chip chipPrice = findViewById(R.id.chipPrice);
        TextView ingredients = findViewById(R.id.ingredients);
        TextView desc = findViewById(R.id.desc);
        TextView steps = findViewById(R.id.steps);

        img.setImageResource(produit.getImageRes());
        title.setText(produit.getNom());
        chipTime.setText(produit.getDuree());
        chipPrice.setText(produit.getPrix() + " €");

        // Formater les ingrédients avec des puces
        String formattedIngredients = formatIngredients(produit.getIngredients());
        ingredients.setText(formattedIngredients);

        desc.setText(produit.getDescription());

        // Formater les étapes avec numérotation
        String formattedSteps = formatSteps(produit.getEtapes());
        steps.setText(formattedSteps);
    }

    private String formatIngredients(String ingredients) {
        if (ingredients == null) return "";
        String[] lines = ingredients.split("\n");
        StringBuilder formatted = new StringBuilder();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                formatted.append("• ").append(line.trim()).append("\n");
            }
        }
        return formatted.toString();
    }

    private String formatSteps(String steps) {
        if (steps == null) return "";
        StringBuilder formatted = new StringBuilder();
        String[] lines = steps.split("\n");
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                formatted.append(line.trim()).append("\n\n");
            }
        }
        return formatted.toString();
    }

    private void setupAnimations() {
        // Animation pour le scrolling smooth
        findViewById(R.id.steps).setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            // Smooth scrolling effect
        });
    }

    private void setupClickListeners() {
        // Animation pour la section ingrédients (collapsible)
        MaterialCardView ingredientsCard = findViewById(R.id.ingredientsCard);
        ImageView expandIcon = findViewById(R.id.expandIngredients);
        TextView ingredientsText = findViewById(R.id.ingredients);

        ingredientsCard.setOnClickListener(v -> {
            ingredientsExpanded = !ingredientsExpanded;

            // Animation de rotation
            ObjectAnimator rotation = ObjectAnimator.ofFloat(expandIcon, "rotation",
                    ingredientsExpanded ? 0f : 180f, ingredientsExpanded ? 180f : 0f);
            rotation.setDuration(300);
            rotation.start();

            // Animation d'expansion
            if (ingredientsExpanded) {
                ingredientsText.setVisibility(View.VISIBLE);
                ingredientsText.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .start();
            } else {
                ingredientsText.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .withEndAction(() -> ingredientsText.setVisibility(View.GONE))
                        .start();
            }
        });

        // Bouton de partage
        ExtendedFloatingActionButton fabShare = findViewById(R.id.fabShare);
        fabShare.setOnClickListener(v -> {
            shareRecipe();
        });

        // Animation au clic sur les chips
        Chip chipTime = findViewById(R.id.chipTime);
        Chip chipPrice = findViewById(R.id.chipPrice);

        chipTime.setOnClickListener(v -> {
            v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100)
                    .withEndAction(() -> v.animate().scaleX(1f).scaleY(1f).setDuration(100).start());
            // Ajouter une action si nécessaire
        });

        chipPrice.setOnClickListener(v -> {
            v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100)
                    .withEndAction(() -> v.animate().scaleX(1f).scaleY(1f).setDuration(100).start());
        });
    }

    private void shareRecipe() {
        String shareText = "🍕 " + produit.getNom() + "\n\n" +
                "⏱️ " + produit.getDuree() + "\n" +
                "💰 " + produit.getPrix() + " €\n\n" +
                "🥗 Ingrédients:\n" + produit.getIngredients() + "\n\n" +
                "👨‍🍳 Préparation:\n" + produit.getEtapes();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Recette de " + produit.getNom());
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Partager la recette"));
    }

    private void animateContent() {
        // Animation d'entrée pour le contenu
        View content = findViewById(R.id.ingredientsCard);
        content.setAlpha(0f);
        content.setTranslationY(50f);
        content.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        View descCard = findViewById(R.id.descCard);
        descCard.setAlpha(0f);
        descCard.setTranslationY(50f);
        descCard.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        View stepsCard = findViewById(R.id.stepsCard);
        stepsCard.setAlpha(0f);
        stepsCard.setTranslationY(50f);
        stepsCard.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setStartDelay(200)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}