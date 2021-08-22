package com.gaston.vever.pokedex.pokemon.controller;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Value;

/**
 * Small dto to send only the necessary information to display a pokemon.
 */
@Builder
@Value
class BasePokemonDto implements Serializable {

  String id;
  String name;
  String image;
  ArrayList<String> types;//ArrayList cuzz is serializable
  boolean favourite;
}
