/*
 * Copyright (c) 2025 Suraj
 *
 * Licensed under the MIT License. You may obtain a copy of the License at
 * https://opensource.org/licenses/MIT
 * Author: Bhanupriya Rout
 *
 * This file is part of the Country Info API project.
 * Service logic for providing CountryResponse.
 */
package com.example.service;

import com.example.model.CountryResponse;
import com.example.util.DummyCountryUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class CountryService {

    private final RestTemplate restTemplate;

    public CountryService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     *
     * Provides Country response on basis country code
     * @param code CountryCode
     * @return
     */
    public CountryResponse getCountryByCode(String code) {
        // Validate format
        if (code == null || !code.matches("^[A-Z]{2}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid country code format");
        }

        try {
            // External API call
            String url = "https://restcountries.com/v3.1/alpha/" + code;
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().isEmpty()) {
                Map<String, Object> apiData = response.getBody().get(0);
                return mapToCountryResponse(code, apiData);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found in API");
            }
        } catch (Exception e) {
            // 🧪 Fallback to dummy data
            CountryResponse dummy = DummyCountryUtil.getDummyCountryByCode(code);
            if (dummy != null) return dummy;

            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "External API failure and no fallback found");
        }
    }

    private CountryResponse mapToCountryResponse(String code, Map<String, Object> data) {
        // You can implement mapping logic here as per actual API structure
        // For brevity, return dummy for now
        return DummyCountryUtil.getDummyCountryByCode(code);
    }

    /**
     * Determines the size category based on the population.
     *
     * @param population the population of the country
     * @return the size category as a string ("SMALL", "MEDIUM", "LARGE")
     */
    private String determineSizeCategory(long population) {
        if (population < 1000000) {
            return "SMALL";
        } else if (population >= 1000000 && population <= 10000000) {
            return "MEDIUM";
        } else {
            return "LARGE";
        }
    }
}
