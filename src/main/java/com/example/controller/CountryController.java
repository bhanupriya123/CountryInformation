/*
 * Copyright (c) 2025 Suraj
 *
 * Licensed under the MIT License. You may obtain a copy of the License at
 * https://opensource.org/licenses/MIT
 *
 * Author: Bhanupriya Rout
 *
 * This file is part of the Country Info API project.
 */
package com.example.controller;

import com.example.exception.CountryNotFoundException;
import com.example.model.CountryResponse;
import com.example.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;



    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Returns the response for a specific country code.
     * @param code country code
     * @return CountryResponse
     */
    @GetMapping("/{code}")
    public ResponseEntity<CountryResponse> getCountry(@PathVariable String code) {
        CountryResponse response = countryService.getCountryByCode(code);
        if (response == null) {
            throw new CountryNotFoundException("Country with code " + code + " not found");
        }
        return ResponseEntity.ok(response);
    }
}
