package com.gaston.vever.pokedex.pokemon.repository.impl;

import static com.gaston.vever.pokedex.pokemon.repository.impl.RepoTestUtils.repositoryWithBulbasaur;
import static org.assertj.core.api.Assertions.assertThat;

import com.gaston.vever.pokedex.pokemon.Pokemon;
import com.gaston.vever.pokedex.pokemon.repository.PokemonRepository;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InMemoryPokemonRepositoryTest {

  static PokemonRepository pokemonRepository;

  @BeforeAll
  static void setup() {
    pokemonRepository = repositoryWithBulbasaur();
  }

  @Test
  void givenPokemon001Exist_whenFindById_thenGetBulbasaur() {
    final Optional<Pokemon> byId = pokemonRepository.findById("001");

    //Could test more exhaustive but lack of time
    assertThat(byId).map(Pokemon::getName).containsSame("Bulbasaur");
  }

  @Test
  void givenPokemonZZZDoesNotExist_whenFindById_thenGetEmpty() {
    final Optional<Pokemon> byId = pokemonRepository.findById("ZZZ");

    assertThat(byId).isEmpty();
  }

  @Test
  void givenBulbasaurIsStored_whenFindAllSearchingByNameBulba_thenFind1() {
    final List<Pokemon> all = pokemonRepository.findAll(List.of(byName("bulba")));

    assertThat(all).hasSize(1);//For sake of simplicity validations won't be exhaustive.
  }

  @Test
  void givenNoPokemonNamedPepeIsStored_whenFindAllSearchingByNamePepe_thenFindNone() {
    final List<Pokemon> all = pokemonRepository.findAll(List.of(byName("pepe")));

    assertThat(all).isEmpty();
  }

  @Test
  void givenBulbasaurIsStored_whenFindAllSearchingByNameBulbaAndTypeGrass_thenFind1() {
    final List<Pokemon> all = pokemonRepository.findAll(
        List.of(byName("bulba"), byType("Grass")));

    assertThat(all).hasSize(1);
  }

  @Test
  void givenBulbasaurIsStored_whenFindAllSearchingByNameBulbaAndTypeDark_thenFind0() {
    final List<Pokemon> all = pokemonRepository.findAll(
        List.of(byName("bulba"), byType("Dark")));

    assertThat(all).isEmpty();
  }

  private static Predicate<Pokemon> byName(String name) {
    return pokemon -> pokemon.getName().toLowerCase().contains(name.toLowerCase());
  }

  private static Predicate<Pokemon> byType(String type) {
    return pokemon -> pokemon.isType(type);
  }
}