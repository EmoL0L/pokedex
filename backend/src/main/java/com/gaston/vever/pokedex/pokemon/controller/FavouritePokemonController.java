package com.gaston.vever.pokedex.pokemon.controller;

import com.gaston.vever.pokedex.pokemon.repository.FavouritePokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pokedex/pokemon")
class FavouritePokemonController {

  private final FavouritePokemonRepository favouritePokemonRepository;

  @PostMapping("/favourite/{pokemonId}")
  public void addToFavourites(@PathVariable String pokemonId) {
    log.info("Starts request to add to favourites pokemon with id {}", pokemonId);
    //We assume userId comes from somewhere
    favouritePokemonRepository.saveFavourite(User.ID, pokemonId);
  }

  @DeleteMapping("/favourite/{pokemonId}")
  public void removeFromFavourites(@PathVariable String pokemonId) {
    log.info("Starts request to remove from favourites pokemon with id {}", pokemonId);
    favouritePokemonRepository.removeFavourite(User.ID, pokemonId);
  }

}
