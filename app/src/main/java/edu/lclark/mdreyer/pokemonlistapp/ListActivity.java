package edu.lclark.mdreyer.pokemonlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.mdreyer.pokemonlistapp.adapters.PokemonRecyclerAdapter;
import edu.lclark.mdreyer.pokemonlistapp.pokemon.Pokedex;
import edu.lclark.mdreyer.pokemonlistapp.pokemon.Pokemon;

public class ListActivity extends AppCompatActivity implements
        PokemonRecyclerAdapter.PokemonRowClickListener {


    private static final int POKEMON_CODE = 0;

    @Bind(R.id.pokemon_list)
    RecyclerView pokemonList;

    private Pokedex pokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        pokedex = new Pokedex();

        pokemonList.setLayoutManager(new LinearLayoutManager(this));
        PokemonRecyclerAdapter adapter = new PokemonRecyclerAdapter(pokedex.getPokemons(), this);
        pokemonList.setAdapter(adapter);

        pokemonList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onRowClick(Pokemon pokemon) {
        Intent intent = new Intent(ListActivity.this, PokemonDetailActivity.class);
        intent.putExtra(PokemonDetailActivity.POKEMON_EXTRA, pokemon);
        startActivityForResult(intent, POKEMON_CODE);
    }
}
