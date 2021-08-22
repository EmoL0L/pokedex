package com.gaston.vever.pokedex.pokemon.repository.impl;

import com.gaston.vever.pokedex.pokemon.Pokemon;
import java.util.Map;

class RepoTestUtils {

  public static InMemoryPokemonRepository repositoryWithBulbasaur() {
    return new InMemoryPokemonRepository(Map.of(bulbasaur().getId(), bulbasaur()));
  }

  public static Pokemon bulbasaur() {
    return Pokemon.builder()
        .id("001")
        .name("Bulbasaur")
        .type("Grass")
        .type("Poison")
        .build();
  }
}
