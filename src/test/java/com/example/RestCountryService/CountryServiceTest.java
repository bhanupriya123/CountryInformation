package com.example.RestCountryService;

/*
 * Copyright (c) 2025 Suraj
 *
 * Author: Bhanupriya Rout
 *
 * Licensed under the MIT License. You may obtain a copy of the License at
 * https://opensource.org/licenses/MIT
 *
 * This file is part of the Country Info API project.
 *
 *
 */

import com.example.exception.CountryNotFoundException;
import com.example.model.CountryResponse;
import com.example.service.CountryService;
import com.example.util.DummyCountryUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountryServiceTest {

    @InjectMocks
    private CountryService countryService; // Service to be tested

    @Mock
    private DummyCountryUtil dummyCountryUtil; // Mock the utility class

    @Mock
    private RestTemplateBuilder restTemplateBuilder; // Mock RestTemplateBuilder

    @Mock
    private RestTemplate restTemplate; // Mock RestTemplate

    private CountryResponse mockCountryResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        countryService = new CountryService(restTemplateBuilder);

    }

    @Test
    public void testGetCountryDetails_Success() {
        // Given
        String countryCode = "US";
        CountryResponse mockResponse = new CountryResponse("US","United States of America");
        // Set other fields as needed

        when(restTemplate.getForObject(anyString(), eq(CountryResponse.class))).thenReturn(mockResponse);

        // Act
        CountryResponse response = countryService.getCountryByCode(countryCode);

        // Assert
        assertNotNull(response);
    }
}
