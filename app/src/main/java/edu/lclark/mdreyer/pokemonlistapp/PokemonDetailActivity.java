package edu.lclark.mdreyer.pokemonlistapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.mdreyer.pokemonlistapp.pokemon.Pokemon;

public class PokemonDetailActivity extends AppCompatActivity {

    public static final String POKEMON_EXTRA = "POKEMON_EXTRA";

    private Pokemon pokemon;

    @Bind(R.id.id_detail_text)
    TextView idText;

    @Bind(R.id.height_weight_detail_text)
    TextView heightWeightText;

    @Bind(R.id.details_progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.pokemon_detail_image)
    ImageView pokemonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        ButterKnife.bind(this);

        pokemon = (Pokemon) getIntent().getSerializableExtra(POKEMON_EXTRA);

        setTitle(pokemon.getName());
        idText.setText(pokemon.getId());
        heightWeightText.setText(getString(R.string.height_weight_detail_format,
                pokemon.getHeight(), pokemon.getWeight()));

        Picasso.with(this).load(pokemon.getImageUrl()).fit().centerInside().into(pokemonImage);

        progressBar.setVisibility(View.GONE);

        getAdditionalDetails();
    }

    private void getAdditionalDetails() {

    }
}
