package com.dib.demo.mapper;

import com.dib.demo.dto.BeerDto;
import com.dib.demo.model.Beer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for {@link Beer} and {@link BeerDto}
 */

public class BeerMapper {

    public static Beer fromDto(BeerDto beerDto) {
        return Beer.builder()
                .description(beerDto.getDescription())
                .name(beerDto.getName())
                .mashTemp(beerDto.getMethod().getMashTemp()
                        .stream().map(item -> item.getTemp().getValue()).collect(Collectors.toList()))
                .externalId(beerDto.getId())
                .build();
    }

    public static List<Beer> fromDtoList(List<BeerDto> beerDtos) {
        return beerDtos.stream().map(BeerMapper::fromDto).collect(Collectors.toList());
    }

    public static BeerDto toDto(Beer beer) {
        return BeerDto.builder()
                .id(beer.getId())
                .description(beer.getDescription())
                .name(beer.getName())
                .mashTemp(beer.getMashTemp().stream()
                        .mapToDouble(temp -> temp).average()
                        .orElse(0.0))
                .build();
    }

    public static List<BeerDto> toDtoList(List<Beer> beers) {
        return beers.stream().map(BeerMapper::toDto).collect(Collectors.toList());
    }
}
