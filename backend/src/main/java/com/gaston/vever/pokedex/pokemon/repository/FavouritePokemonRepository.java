package com.gaston.vever.pokedex.pokemon.repository;

import java.util.Set;
import lombok.NonNull;

public interface FavouritePokemonRepository {

  /**
   * Saves a pokemon as a user favourite.
   */
  void saveFavourite(@NonNull String userId, @NonNull String pokemonId);

  /**
   * Removes a pokemon from user favourites.
   */
  void removeFavourite(@NonNull String userId, @NonNull String pokemonId);

  /**
   * Returns all the pokemon ids from the users favourite list.
   */
  Set<String> findFavourites(@NonNull String userId);

}
