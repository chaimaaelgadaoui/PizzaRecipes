package com.example.pizzarecipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.ui.PizzaDetailActivity;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private final Context context;
    private final List<Produit> pizzas;

    public PizzaAdapter(Context context, List<Produit> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Produit p = pizzas.get(position);
        
        holder.img.setImageResource(p.getImageRes());
        holder.tvNom.setText(p.getNom());
        holder.tvPrix.setText(String.format("%.2f €", p.getPrix()));
        holder.tvMeta.setText(p.getDuree());

        // Animation d'entrée pour chaque carte
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PizzaDetailActivity.class);
            intent.putExtra("pizza_id", p.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvNom, tvPrix, tvMeta;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgPizza);
            tvNom = itemView.findViewById(R.id.tvNom);
            tvPrix = itemView.findViewById(R.id.tvPrix);
            tvMeta = itemView.findViewById(R.id.tvMeta);
        }
    }
}