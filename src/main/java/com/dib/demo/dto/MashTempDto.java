package com.dib.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MashTempDto {

    private TemperatureDto temp;
    private int duration;

}
