package com.gaston.vever.pokedex.pokemon.controller;

import com.gaston.vever.pokedex.pokemon.MultimediaPokemon;
import com.gaston.vever.pokedex.pokemon.repository.MultimediaPokemonRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pokedex/pokemon")
class PokedexController {

  private final MultimediaPokemonRepository multimediaPokemonRepository;
  private final DtoMapper mapper;

  @GetMapping("/{id}")
  public MultimediaPokemonDto getPokemon(@PathVariable String id) {
    log.info("Starts request get pokemon with id {}", id);
    return multimediaPokemonRepository.findById(User.ID, id)
        .map(mapper::toMultimediaPokemonDto)
        //If this was a bigger project I would possibly use an AdviceController.
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found"));
  }

  @GetMapping
  public List<BasePokemonDto> getPokemons(
      @RequestParam(required = false) Optional<String> name,
      @RequestParam(required = false) Optional<String> type,
      @RequestParam(required = false) Optional<Boolean> favourite) {
    log.info("Starts request find all pokemon");
    val criteria = new ArrayList<Predicate<MultimediaPokemon>>();

    name.ifPresent(s -> criteria.add(MultimediaPokemonRepository.byName(s)));
    type.ifPresent(s -> criteria.add(MultimediaPokemonRepository.byType(s)));
    favourite.ifPresent(s -> criteria.add(MultimediaPokemonRepository.byFavourite(s)));

    return multimediaPokemonRepository.findAll(User.ID, criteria)
        .stream()
        .map(mapper::toBasicDto)
        .sorted(Comparator.comparing(BasePokemonDto::getId))
        .collect(Collectors.toList());
  }


  @Mapper
  interface DtoMapper {

    BasePokemonDto toBasicDto(MultimediaPokemon pokemon);

    MultimediaPokemonDto toMultimediaPokemonDto(MultimediaPokemon pokemon);
  }
}
