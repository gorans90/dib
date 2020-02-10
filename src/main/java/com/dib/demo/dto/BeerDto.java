package com.dib.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BeerDto {

    private long id;
    private Long externalId;
    private String name;
    private String description;
    private MethodDto method;
    private double mashTemp;

}
