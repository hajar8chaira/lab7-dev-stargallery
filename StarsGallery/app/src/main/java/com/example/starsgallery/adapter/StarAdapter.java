package com.example.starsgallery.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarHolder> {
    private List<Star> stars;
    private Context ctx;

    public StarAdapter(Context ctx, List<Star> stars) {
        this.ctx = ctx;
        this.stars = stars;
    }

    @NonNull
    @Override
    public StarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.star_item, parent, false);
        return new StarHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StarHolder holder, int position) {
        final Star s = stars.get(position);

        // 1. Textes et Notes
        holder.name.setText(s.getName().toUpperCase());
        holder.rating.setRating(s.getRating());

        // 2. Affichage du numéro (1, 2, 3...)
        if (holder.index != null) {
            holder.index.setText(String.valueOf(position + 1));
        }

        // 3. Chargement d'image sécurisé avec Glide
        Glide.with(ctx)
                .load(s.getImg())
                .placeholder(R.drawable.star) // Montre votre étoile en attendant
                .error(R.drawable.star)       // Montre votre étoile si le lien échoue
                .circleCrop()                 // Force la forme circulaire
                .into(holder.img);

        // 4. Le Popup au clic sur la ligne
        holder.itemView.setOnClickListener(v -> {
            View popupView = LayoutInflater.from(ctx).inflate(R.layout.star_edit_item, null);
            final RatingBar barNote = popupView.findViewById(R.id.popup_rating);
            barNote.setRating(s.getRating());

            new AlertDialog.Builder(ctx)
                    .setTitle("Modifier la note")
                    .setView(popupView)
                    .setPositiveButton("Valider", (dialog, which) -> {
                        s.setRating(barNote.getRating());
                        notifyItemChanged(position); // Rafraîchit l'étoile jaune
                    })
                    .setNegativeButton("Annuler", null)
                    .show();
        });
    }


    @Override public int getItemCount() { return stars.size(); }

    public static class StarHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, index; // Vérifiez que 'index' est bien là
        RatingBar rating;
        public StarHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img_star_item);
            name = v.findViewById(R.id.nom_star);
            index = v.findViewById(R.id.index_star); // Vérifiez cet ID
            rating = v.findViewById(R.id.note_star);
        }
    }
}
