package edu.lclark.mdreyer.pokemonlistapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.lclark.mdreyer.pokemonlistapp.pokemon.Pokemon;
import edu.lclark.mdreyer.pokemonlistapp.pokemon.PokemonStats;

public class PokemonDetailActivity extends AppCompatActivity implements NetworkAsyncTask
        .NetworkListener {

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

    @Bind(R.id.experience_text)
    TextView experienceText;

    @Bind(R.id.hp_text)
    TextView hpText;

    @Bind(R.id.speed_text)
    TextView speedText;

    @Bind(R.id.attack_text)
    TextView attackText;

    @Bind(R.id.special_attack_text)
    TextView specialAttackText;

    @Bind(R.id.defense_text)
    TextView defenseText;

    @Bind(R.id.special_defense_text)
    TextView specialDefenseText;

    private NetworkAsyncTask statFetchTask;

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

        if (pokemon.getStats() == null) {
            getAdditionalDetails();
        } else {
            setDetailsTexts();
        }
    }

    private void getAdditionalDetails() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService
                (CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(this, R.string.no_internet_error, Toast.LENGTH_LONG).show();
            Picasso.with(this).load(R.drawable.pokeball).fit().centerInside().into(pokemonImage);
        } else {
            statFetchTask = new NetworkAsyncTask(this);
            statFetchTask.execute(pokemon.getId());
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNetworkTaskComplete(PokemonStats stats) {
        pokemon.setStats(stats);
        progressBar.setVisibility(View.GONE);
        setDetailsTexts();
    }

    private void setDetailsTexts() {
        PokemonStats stats = pokemon.getStats();
        experienceText.setText(getString(R.string.base_xp, stats.getBaseExperience()));
        hpText.setText(getString(R.string.hp_detail, stats.getHp()));
        speedText.setText(getString(R.string.speed_detail, stats.getSpeed()));
        attackText.setText(getString(R.string.attack_detail, stats.getAttack()));
        specialAttackText.setText(getString(R.string.special_attack_detail,
                stats.getSpecialAttack()));
        defenseText.setText(getString(R.string.defense_detail, stats.getDefense()));
        specialDefenseText.setText(getString(R.string.special_defense_detail,
                stats.getSpecialDefense()));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(POKEMON_EXTRA, pokemon);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (statFetchTask != null && !statFetchTask.isCancelled()) {
            statFetchTask.cancel(true);
        }
    }
}
