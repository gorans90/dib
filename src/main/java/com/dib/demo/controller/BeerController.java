package com.dib.demo.controller;

import com.dib.demo.config.ApiResponseCodes;
import com.dib.demo.dto.BeerDto;
import com.dib.demo.exception.NotFoundException;
import com.dib.demo.mapper.BeerMapper;
import com.dib.demo.service.BeerService;
import com.dib.demo.util.Endpoints;
import com.dib.demo.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(Endpoints.BEER)
@Api(value = Endpoints.BEER)
@ApiResponseCodes
@Validated
public class BeerController {

    @Lazy
    @Autowired
    private BeerService beerService;

    @GetMapping(value = "/{beer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get one beer by id",
            notes = "Send GET request to get one beer by id",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET", response = BeerDto.class)
    @ApiResponseCodes
    public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable(value = "beer_id") Long beerId) throws NotFoundException {
        return ResponseEntity.ok(BeerMapper.toDto(beerService.getById(beerId)));
    }

    @GetMapping(value = Endpoints.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all beers",
            notes = "Send GET request to get all beers",
            httpMethod = "GET", response = List.class)
    @ApiResponseCodes
    public ResponseEntity<List<BeerDto>> getAllBeers() throws NotFoundException {
        return ResponseEntity.ok(BeerMapper.toDtoList(beerService.getAll()));
    }

    @DeleteMapping(value = Endpoints.DELETE + "/{id}")
    @ApiOperation(value = "Delete beer",
            notes = "Send DELETE request to delete beer",
            httpMethod = "DELETE", response = String.class)
    @ApiResponseCodes
    public ResponseEntity<String> deleteBeer(@NotNull @PathVariable(name = "id") Long id) throws NotFoundException {
        beerService.deleteById(id);
        return ResponseEntity.ok(JsonUtil.toJson("Beer has been successfully deleted"));
    }

    @GetMapping(value = Endpoints.INIT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Initialize beer data",
            notes = "Send GET request to initialize beer data",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET", response = String.class)
    @ApiResponseCodes
    public ResponseEntity<String> initialize() {
        return ResponseEntity.ok(JsonUtil.toJson("Data has been successfully initialized"));
    }
}
