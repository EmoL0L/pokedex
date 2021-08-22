package com.gaston.vever.pokedex.pokemon.repository.impl;

import com.gaston.vever.pokedex.pokemon.repository.FavouritePokemonRepository;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class InMemoryFavouritePokemonRepository implements FavouritePokemonRepository {

  private final Map<String, Set<String>> favouritePokemons;

  @Override
  public void saveFavourite(@NonNull String userId, @NonNull String pokemonId) {
    favouritePokemons.computeIfAbsent(userId, id -> new HashSet<>())
        .add(pokemonId);
  }

  @Override
  public void removeFavourite(@NonNull String userId, @NonNull String pokemonId) {
    favouritePokemons.computeIfPresent(userId, (user, pokemons) -> {
      pokemons.remove(pokemonId);
      return pokemons;
    });
  }

  @Override
  public Set<String> findFavourites(@NonNull String userId) {
    return favouritePokemons.computeIfAbsent(userId, id -> new HashSet<>());
  }

}
