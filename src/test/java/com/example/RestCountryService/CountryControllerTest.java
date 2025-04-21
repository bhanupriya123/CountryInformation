package com.example.RestCountryService;

/*
 * Copyright (c) 2025 Suraj
 *
 * Author: Suraj [you@example.com]
 * GitHub: https://github.com/yourusername
 *
 * Licensed under the MIT License. You may obtain a copy of the License at
 * https://opensource.org/licenses/MIT
 *
 * This file is part of the Country Info API project.
 *
 * This software is provided "as-is", without warranty of any kind.
 * See the License for the specific language governing permissions and limitations under the License.
 */

import com.example.controller.CountryController;
import com.example.exception.CountryNotFoundException;
import com.example.model.CountryResponse;
import com.example.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc; // To perform HTTP requests in the tests

    @Mock
    private CountryService countryService; // Mock the CountryService to test the controller independently

    private CountryResponse mockCountryResponse;

    @BeforeEach
    public void setUp() {
        mockCountryResponse = new CountryResponse("US", "United States of America", "Washington, D.C.", "Americas",
                List.of("USD"), List.of("English"), List.of("CAN", "MEX"), 83000000L);
    }

    @Test
    public void testGetCountryDetails_Success() throws Exception {
        // Given
        String countryCode = "US";
        when(countryService.getCountryByCode(countryCode)).thenReturn(mockCountryResponse);

        // When & Then
        mockMvc.perform(get("/countries/{code}", countryCode)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("US"))
                .andExpect(jsonPath("$.name").value("United States of America"))
                .andExpect(jsonPath("$.capital").value("Washington, D.C."))
                .andExpect(jsonPath("$.region").value("Americas"))
                .andExpect(jsonPath("$.currencies[0]").value("USD"))
                .andExpect(jsonPath("$.languages[0]").value("English"))
                .andExpect(jsonPath("$.borders[0]").value("CAN"))
                .andExpect(jsonPath("$.borders[1]").value("MEX"))
                .andExpect(jsonPath("$.sizeCategory").value("LARGE"));

        // Verify that the service was called exactly once with the correct countryCode
        verify(countryService, times(1)).getCountryByCode(countryCode);
    }
}
