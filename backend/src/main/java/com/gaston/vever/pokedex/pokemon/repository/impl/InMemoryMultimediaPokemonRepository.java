package com.gaston.vever.pokedex.pokemon.repository.impl;

import com.gaston.vever.pokedex.pokemon.MultimediaPokemon;
import com.gaston.vever.pokedex.pokemon.Pokemon;
import com.gaston.vever.pokedex.pokemon.repository.FavouritePokemonRepository;
import com.gaston.vever.pokedex.pokemon.repository.MultimediaPokemonRepository;
import com.gaston.vever.pokedex.pokemon.repository.PokemonRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class InMemoryMultimediaPokemonRepository implements MultimediaPokemonRepository {

  private final PokemonRepository pokemonRepository;
  private final FavouritePokemonRepository favouritePokemonRepository;
  private final PokemonMapper mapper;

  @Override
  public Optional<MultimediaPokemon> findById(String userId, String pokemonId) {
    //TODO Future would be cool
    final Set<String> favourites = favouritePokemonRepository.findFavourites(userId);

    return pokemonRepository.findById(pokemonId)
        .map(pokemon -> mapper.to(pokemon, favourites.contains(pokemonId)));
  }

  @Override
  public List<MultimediaPokemon> findAll(String userId,
      List<Predicate<MultimediaPokemon>> criteria) {
    //TODO Future would be cool
    final List<Pokemon> all = pokemonRepository.findAll(Collections.emptyList());
    final Set<String> favourites = favouritePokemonRepository.findFavourites(userId);

    return all.stream()
        .map(pokemon -> mapper.to(pokemon, favourites.contains(pokemon.getId())))
        .filter(criteria.stream().reduce(pokemon -> true, Predicate::and))
        .collect(Collectors.toList());
  }


  @Mapper
  interface PokemonMapper {

    MultimediaPokemon to(Pokemon pokemon, boolean favourite);
  }
}
