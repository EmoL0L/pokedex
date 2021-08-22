package com.gaston.vever.pokedex.pokemon.repository;

import com.gaston.vever.pokedex.pokemon.MultimediaPokemon;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface MultimediaPokemonRepository {

  Optional<MultimediaPokemon> findById(String userId, String pokemonId);

  List<MultimediaPokemon> findAll(String userId, List<Predicate<MultimediaPokemon>> criteria);

  //I dont like this but had not much time. This isn't JPA friendly.
  static Predicate<MultimediaPokemon> byName(String name) {
    return pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase());
  }

  static Predicate<MultimediaPokemon> byType(String type) {
    return pokemon -> pokemon.isType(type);
  }

  static Predicate<MultimediaPokemon> byFavourite(boolean favourite) {
    return pokemon -> pokemon.isFavourite() == favourite;
  }
}
