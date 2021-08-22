package com.gaston.vever.pokedex.pokemon;

import static java.util.Objects.isNull;

import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.NonFinal;

//Although this documentation might be obvious/redundant, we have to suppose that the next programmer
// won't know anything about the domain (in this case Pokemon). That's why I like to explain what
// is the domain object about.

/**
 * Pokemons are fictional creatures that can be captured, combat between them and evolve to stronger
 * versions of themselves.
 */
@Builder
@NonFinal
@Value
public class Pokemon {

  /**
   * Ids are always incremental numeric.
   */
  String id;
  String name;
  PokemonDimension weight;
  PokemonDimension height;
  String classification;
  //Types could have been an enum but since types can be added on newer editions,
  // preferred to not have this kinds of constraints.
  //Another cool alternative would have been a class Type {List<Type> weakness, List<Type> resistant}
  // but too much effort.
  /**
   * Fire, Grass, Water, Ghost, Dragon, etc. pokemons are classified in types and depending of them
   * they are stronger and weaker against other types.
   */
  @Singular
  List<String> types;
  /**
   * Types the pokemon is resistant to.
   */
  List<String> resistant;
  /**
   * Types the pokemon is weak against.
   */
  List<String> weaknesses;
  List<Attack> attacks;
  /**
   * Chances the pokemon will run?
   */
  Float fleeRate;
  /**
   * Maximum combat points. The highter the cp, the more stats a pokemon has.
   */
  Integer maxCP;
  List<Pokemon> evolutions;
  PokemonEvolutionRequirement evolutionRequirements;
  /**
   * Max health points.
   */
  Integer maxHP;

  //Tell Don't Ask
  public boolean isType(String type) {
    return !isNull(types) && types.stream().anyMatch(type::equalsIgnoreCase);
  }

  /**
   * Returns the numeric version of the id.
   */
  public int getNumber() {
    return Integer.parseInt(id);
  }

  @Builder
  @Value
  public static class PokemonDimension {

    //With time a metric class would have been cool
    String minimum;
    String maximum;
  }

  @Builder
  @Value
  public static class PokemonEvolutionRequirement {

    String name;
    Integer amount;
  }

  //Not implemented =(
  @Builder
  @Value
  public static class Attack {

    AttackType attackType;
    String type;
    String name;
    Integer damage;

    public enum AttackType {
      FAST, SPECIAL
    }
  }
}
