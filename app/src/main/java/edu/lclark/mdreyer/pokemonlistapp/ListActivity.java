package edu.lclark.mdreyer.pokemonlistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements
        PokemonRecyclerAdapter.PokemonRowClickListener {

    @Bind(R.id.pokemon_list)
    RecyclerView pokemonList;

    private Pokedex pokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        getActionBar().setIcon(getDrawable(R.drawable.pokeball));

        pokedex = new Pokedex();

        pokemonList.setLayoutManager(new LinearLayoutManager(this));
        PokemonRecyclerAdapter adapter = new PokemonRecyclerAdapter(pokedex.getPokemons(), this);
        pokemonList.setAdapter(adapter);

        pokemonList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onRowClick(Pokemon pokemon) {
        Toast.makeText(this, pokemon.getName(), Toast.LENGTH_SHORT).show();
    }
}
