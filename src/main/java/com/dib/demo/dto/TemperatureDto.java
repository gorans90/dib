package com.dib.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDto {

    private int value;
    private String unit;

}
