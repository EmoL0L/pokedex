package com.gaston.vever.pokedex.pokemon.controller;

import com.gaston.vever.pokedex.pokemon.Pokemon.PokemonDimension;
import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Builder
@Value
class MultimediaPokemonDto {

  String id;
  String name;
  PokemonDimension weight;
  PokemonDimension height;
  @Singular
  List<String> types;
  Integer maxCP;
  Integer maxHP;
  String image;
  String sound;
  boolean favourite;
  List<BasePokemonDto> evolutions;

}
