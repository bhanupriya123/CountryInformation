/*
 * Copyright (c) 2025 Suraj
 *
 * Licensed under the MIT License. You may obtain a copy of the License at
 * https://opensource.org/licenses/MIT
 *
 * Author: Bhanupriya Rout
 *
 * This file is part of the Country Info API project.
 * Model CountryResponse.
 */
package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
    private String countryCode;
    private String name;
    private String capital;
    private String region;
    private List<String> currencies;
    private List<String> languages;
    private List<String> borders;
    private String sizeCategory;

    @JsonIgnore
    private long population;

    public CountryResponse(String countryCode, String name, String capital, String region,
                           List<String> currencies, List<String> languages, List<String> borders,
                           long population) {
        this.countryCode = countryCode;
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.currencies = currencies;
        this.languages = languages;
        this.borders = borders;
        this.population = population;
    }

    public CountryResponse(String countryCode, String name) {
        this.countryCode = countryCode;
        this.name = name;
    }


    public String getSizeCategory() {
        if (population < 1000000) {
            return "SMALL";
        } else if (population >= 1000000 && population <= 10000000) {
            return "MEDIUM";
        } else {
            return "LARGE";
        }
    }
}
