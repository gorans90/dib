package com.dib.demo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for temperature measurement types
 */

@Getter
@AllArgsConstructor
public enum TemperatureTypes {

    CELSIUS("celsius");

    private String value;
}
