/*
 * Copyright (c) 2025 Suraj
 *
 * Licensed under the MIT License. You may obtain a copy of the License at
 * https://opensource.org/licenses/MIT
 *
 * Author: Bhanupriya Rout
 *
 * This file is part of the Country Info API project.
 * Provides a dummy country response since the external API is unavailable.
 */
package com.example.util;

import com.example.model.CountryResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyCountryUtil {

    private static final Map<String, CountryResponse> dummyData = new HashMap<>();
    static {
        dummyData.put("US", new CountryResponse(
                "US", "United States of America", "Washington, D.C.", "Americas",
                List.of("USD"), List.of("English"), List.of("CAN", "MEX"),
                331000000L
        ));

        dummyData.put("IN", new CountryResponse(
                "IN", "India", "New Delhi", "Asia",
                List.of("INR"), List.of("Hindi", "English"), List.of("PAK", "CHN", "NPL", "BGD"),
                1380004385L
        ));

        dummyData.put("DE", new CountryResponse(
                "DE", "Germany", "Berlin", "Europe",
                List.of("EUR"), List.of("German"), List.of("FRA", "POL", "CHE"),
                83000000L
        ));
    }
    /**
     * Returns the dummy CountryResponse for a given country code.
     * @param code two-letter ISO country code (e.g., "US")
     * @return CountryResponse if exists; otherwise null
     */
    public static CountryResponse getDummyCountryByCode(String code) {
        if (code == null || !code.matches("^[A-Z]{2}$")) {
            return null;
        }
        return dummyData.get(code.toUpperCase());
    }
}
