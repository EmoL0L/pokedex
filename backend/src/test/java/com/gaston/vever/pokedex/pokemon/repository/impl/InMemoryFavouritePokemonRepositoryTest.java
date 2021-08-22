package com.gaston.vever.pokedex.pokemon.repository.impl;

import static com.gaston.vever.pokedex.pokemon.repository.impl.RepoTestUtils.bulbasaur;
import static org.assertj.core.api.Assertions.assertThat;

import com.gaston.vever.pokedex.pokemon.repository.FavouritePokemonRepository;
import java.util.HashMap;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryFavouritePokemonRepositoryTest {

  static final String USER_ID = "1";
  static final String BULBASAUR_ID = bulbasaur().getId();

  FavouritePokemonRepository repository;

  @BeforeEach
  void setup() {
    this.repository = new InMemoryFavouritePokemonRepository(new HashMap<>());
  }

  @Test
  void givenNothingIsSaved_whenFindFavourites_thenNoFavourites() {
    assertThat(repository.findFavourites(USER_ID)).isEmpty();
  }

  @Test
  void givenSavedFavourite_whenFindFavourites_thenGetOneFavourite() {
    repository.saveFavourite(USER_ID, BULBASAUR_ID);

    final Set<String> favourites = repository.findFavourites(USER_ID);

    assertThat(favourites).hasSize(1);
  }


  @Test
  void givenIsSavedBulbasaur_andThenRemoved_whenFindFavourites_thenNoFavourites() {
    repository.saveFavourite(USER_ID, BULBASAUR_ID);
    repository.removeFavourite(USER_ID, BULBASAUR_ID);

    final Set<String> favourites = repository.findFavourites(USER_ID);

    assertThat(favourites).isEmpty();
  }


}