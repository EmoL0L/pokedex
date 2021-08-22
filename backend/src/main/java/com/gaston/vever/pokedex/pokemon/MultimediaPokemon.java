package com.gaston.vever.pokedex.pokemon;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * {@link Pokemon} with website related fields and logic.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Value
public class MultimediaPokemon extends Pokemon {

  boolean favourite;

  @Builder(builderMethodName = "multimediaBuilder")
  private MultimediaPokemon(String id, String name,
      PokemonDimension weight, PokemonDimension height, String classification,
      List<String> types, List<String> resistant,
      List<Attack> attacks, List<String> weaknesses, Float fleeRate, Integer maxCP,
      List<Pokemon> evolutions,
      PokemonEvolutionRequirement evolutionRequirements, Integer maxHP, boolean favourite) {
    super(id, name, weight, height, classification, types, resistant, weaknesses, attacks, fleeRate,
        maxCP, evolutions, evolutionRequirements, maxHP);
    this.favourite = favourite;
  }


  /**
   * Returns sound url.
   */
  public String getSound() {
    //Lack of time to do it correctly, with time possibly I would have made a factory with sound
    // and image suppliers to lift some business decisions
    return "http://localhost:8080/sounds/" + this.getNumber() + ".mp3";
  }

  /**
   * Returns image url.
   */
  public String getImage() {
    return "https://img.pokemondb.net/artwork/" + this.getName().toLowerCase()
        //Copied from example
        .replaceAll("/[&\\\\/\\\\\\\\#,+()$~%.'\":*?<>{}]/g", "")
        .replace(' ', '-')
        + ".jpg";
  }
}
