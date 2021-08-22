package com.gaston.vever.pokedex.pokemon.repository.impl;

import com.gaston.vever.pokedex.pokemon.Pokemon;
import com.gaston.vever.pokedex.pokemon.repository.PokemonRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class InMemoryPokemonRepository implements PokemonRepository {

  private final Map<String, Pokemon> pokemons;

  @Override
  public Optional<Pokemon> findById(String id) {
    return Optional.ofNullable(pokemons.get(id));
  }

  @Override
  public List<Pokemon> findAll(List<Predicate<Pokemon>> criteria) {
    return pokemons.values().stream()
        .filter(criteria.stream().reduce(pokemon -> true, Predicate::and))
        .collect(Collectors.toList());
  }
}
