package edu.lclark.mdreyer.pokemonlistapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Magisus on 2/20/2016.
 */
public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.PokemonViewHolder> {

    public interface PokemonRowClickListener {
        void onRowClick(Pokemon pokemon);
    }

    private final List<Pokemon> allPokemon;
    private final PokemonRowClickListener listener;

    public PokemonRecyclerAdapter(List<Pokemon> allPokemon, PokemonRowClickListener listener) {
        this.allPokemon = allPokemon;
        this.listener = listener;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.recycler_row_pokemon, parent, false);
        return new PokemonViewHolder(row);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        final Pokemon pokemon = allPokemon.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.idNumber.setText(pokemon.getId());

        Context context = holder.fullView.getContext();
        holder.heightText.setText(
                context.getString(R.string.height_weight_format_string,
                                    pokemon.getHeight(),
                                    pokemon.getWeight()));

        holder.fullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRowClick(pokemon);
                }
            }
        });

        Picasso.with(context).load(pokemon.getImageUrl()).fit().
                centerInside().into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return allPokemon.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.pokemon_list_image)
        ImageView pokemonImage;
        @Bind(R.id.pokemon_name) TextView pokemonName;
        @Bind(R.id.height_weight_text) TextView heightText;
        @Bind(R.id.id_number_text) TextView idNumber;

        View fullView;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            fullView = itemView;
        }
    }
}
