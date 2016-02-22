package edu.lclark.mdreyer.pokemonlistapp.pokemon;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ntille on 2/3/16.
 */
public class Pokemon implements Serializable {


    // id,identifier,species_id,height,weight

    private String mName, mId, mSpeciesId, mHeight, mWeight;

    private PokemonStats stats;

    public Pokemon(String csvStr) {
        String[] split = csvStr.trim().split(",");

        mId = split[0];

        mName = split[1];
        // Capitalize name
        mName = mName.substring(0,1).toUpperCase() + mName.substring(1);

        mSpeciesId = split[2];
        mHeight = split[3];
        mWeight = split[4];
    }

    public String getImageUrl() {
        return "http://img.pokemondb.net/artwork/" + getName().toLowerCase() + ".jpg";
    }


    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public String getSpeciesId() {
        return mSpeciesId;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getWeight() {
        return mWeight;
    }

    public PokemonStats getStats() {
        return stats;
    }

    public void setStats(PokemonStats stats) {
        this.stats = stats;
    }
}
