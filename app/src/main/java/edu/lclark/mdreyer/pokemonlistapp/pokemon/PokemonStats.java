package edu.lclark.mdreyer.pokemonlistapp.pokemon;

import java.io.Serializable;

/**
 * Created by Magisus on 2/22/2016.
 */
public class PokemonStats implements Serializable {
    private String baseExperience;
    private String hp;
    private String attack;
    private String specialAttack;
    private String defense;
    private String specialDefense;
    private String speed;

    public String getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(String baseExperience) {
        this.baseExperience = baseExperience;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(String specialAttack) {
        this.specialAttack = specialAttack;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(String specialDefense) {
        this.specialDefense = specialDefense;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
