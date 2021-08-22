package com.gaston.vever.pokedex.pokemon.repository;

import com.gaston.vever.pokedex.pokemon.Pokemon;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface PokemonRepository {

  Optional<Pokemon> findById(String id);

  List<Pokemon> findAll(List<Predicate<Pokemon>> criteria);
}
