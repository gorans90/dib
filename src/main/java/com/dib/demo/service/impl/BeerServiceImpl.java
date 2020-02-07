package com.dib.demo.service.impl;

import com.dib.demo.dto.BeerDto;
import com.dib.demo.exception.NotFoundException;
import com.dib.demo.mapper.BeerMapper;
import com.dib.demo.model.Beer;
import com.dib.demo.repository.BeerRepository;
import com.dib.demo.service.BeerService;
import com.dib.demo.util.Endpoints;
import com.dib.demo.util.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link BeerService}
 */

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Value("${beer.api.url.base}")
    private String apiRootUrl;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    public void initialize() {
        List<Beer> beers = beerRepository.findAll();
        String url = StringUtils.join(apiRootUrl, Endpoints.RANDOM);
        List<Beer> beersToSave = new ArrayList<>();

        for (int i = beers.size(); i < 10; i++) {
            ResponseEntity<BeerDto[]> response = restTemplate.exchange(url, HttpMethod.GET, null, BeerDto[].class);
            for (BeerDto dto : response.getBody()) {
                beersToSave.add(BeerMapper.fromDto(dto));
            }
        }

        beerRepository.saveAll(beersToSave);
    }

    @Override
    public List<Beer> getAll() throws NotFoundException {
        List<Beer> beers = beerRepository.findAll();
        if (CollectionUtils.isEmpty(beers)) {
            throw new NotFoundException(ErrorCode.BEER_NOT_FOUND, "Beers not found in the database");
        }
        return beers;
    }

    @Override
    public Beer getById(Long id) throws NotFoundException {
        return beerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.BEER_NOT_FOUND, "Beer with id: " + id + " not found"));
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        beerRepository.deleteById(getById(id).getId());
    }
}
