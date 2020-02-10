package com.dib.demo;

import com.dib.demo.base.ControllerTest;
import com.dib.demo.dto.BeerDto;
import com.dib.demo.util.Endpoints;
import com.dib.demo.util.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BeerControllerTest extends ControllerTest {

    @Test
    public void aInitTest() {
        final String url = StringUtils.join(Endpoints.BEER, Endpoints.INIT);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(authorizedHeadersDefault()), Void.class);
        Assert.assertEquals("Wrong response status", HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    public void getAllTest() {
        final String url = StringUtils.join(Endpoints.BEER, Endpoints.ALL);
        ResponseEntity<BeerDto[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(authorizedHeadersDefault()), BeerDto[].class);
        Assert.assertEquals("Wrong response status", HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals("Wrong number of item", 10, response.getBody().length);
    }

    @Test
    public void getByIdValidTest() {
        final Long beerId = 1L;
        final double expectedValue = 37.0;
        final String url = StringUtils.join(Endpoints.BEER, "/", beerId);
        ResponseEntity<BeerDto> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(authorizedHeadersDefault()), BeerDto.class);
        Assert.assertEquals("Wrong response status", HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals("Wrong name", "Jelen", response.getBody().getName());
        Assert.assertEquals(expectedValue, response.getBody().getMashTemp(), 0.0);
    }

    @Test
    public void getByIdInvalidTest() {
        final Long beerId = 100L;
        final String url = StringUtils.join(Endpoints.BEER, "/", beerId);
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(authorizedHeadersDefault()), ErrorResponse.class);
        Assert.assertEquals("Wrong response status", HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }

    @Test
    public void deleteByIdValidTest() {
        final Long beerId = 1L;
        final String url = StringUtils.join(Endpoints.BEER, Endpoints.DELETE, "/", beerId);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(authorizedHeadersDefault()), String.class);
        Assert.assertEquals("Wrong response status", HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals("Wrong message", "Beer has been successfully deleted", substringResponse(response.getBody()));
    }

    @Test
    public void deleteByIdInValidTest() {
        final Long beerId = 100L;
        final String url = StringUtils.join(Endpoints.BEER, Endpoints.DELETE, "/", beerId);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(authorizedHeadersDefault()), String.class);
        Assert.assertEquals("Wrong response status", HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }
}
