package com.gaston.vever.pokedex.pokemon.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaston.vever.pokedex.pokemon.Pokemon;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
class InMemoryPokemonRepositoryConfig {

  /**
   * Loads from a given json all the pokemon.
   *
   * @param pokemonJson pokemon json resource
   * @param objectMapper jackson json deserializer
   * @param jsonMapper {@link JsonPokemon} to {@link Pokemon} mapper
   *
   * @return Map<Id, Pokemon> of all the pokemons from the json file.
   * @throws IOException when json not found or bad formatted.
   */
  @Bean
  public Map<String, Pokemon> loadedPokemon(
      @Value("${pokemon-json-location:classpath:pokemons.json}") Resource pokemonJson,
      ObjectMapper objectMapper,
      JsonMapper jsonMapper) throws IOException {

    JsonPokemon[] jsonPokemons = objectMapper.readValue(pokemonJson.getFile(), JsonPokemon[].class);

    //TODO -> fill evolutions correctly
    return Arrays.stream(jsonPokemons)
        .map(jsonMapper::toPokemon)
        .collect(Collectors.toMap(Pokemon::getId, Function.identity()));
  }


  @Mapper
  interface JsonMapper {

    Pokemon toPokemon(JsonPokemon pokemon);
  }

  //Jackson with some adjustments can build immutable objects through constructor but preferred to
  // show off some encapsulation and hexagonal
  @Data
  static class JsonPokemon {

    String id;
    String name;
    PokemonDimension weight;
    PokemonDimension height;
    String classification;
    List<String> types;
    List<String> resistant;
    List<String> weaknesses;
    Float fleeRate;
    Integer maxCP;
    List<JsonPokemon> evolutions;
    PokemonEvolutionRequirement evolutionRequirements;
    Integer maxHP;

    @Data
    static class PokemonDimension {

      String minimum;
      String maximum;
    }

    @Data
    static class PokemonEvolutionRequirement {

      String name;
      Integer amount;
    }

  }
}
