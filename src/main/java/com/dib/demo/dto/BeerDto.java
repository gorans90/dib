package com.dib.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Transfer object class for {@link com.dib.demo.model.Beer}
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeerDto {

    private long id;
    private long externalId;
    private String name;
    private String description;
    private MethodDto method;
    private double mashTemp;

}
