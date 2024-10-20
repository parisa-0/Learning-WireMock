package com.udacity.timezones.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TimeZoneServiceTest {
    //This line creates an instance of WireMockServer and configures it to run on port 8080.
    static WireMockServer wireMock = new WireMockServer(wireMockConfig().port(8080));

    //This line defines a static string variable serverPath that holds the URL of the WireMock server.
    private static String serverPath = "http://localhost:8080";

    private static String europePath = "/api/timezone/Europe/";

    //Should be executed once before any of the test methods in the class are run. Starts the WireMock server configured
    @BeforeAll
    static void setUp() {
        wireMock.start();
    }

    //Should be executed once after all the test methods in the class have run. Stops the WireMock server that was started in the @BeforeAll method.
    @AfterAll
    static void cleanup() {
        wireMock.stop();
    }

    private TimeZoneService timeZoneService;
/*
    Should be executed before each individual test.
    Reset the state of the WireMock server.
    This clears any previously registered stubs and recorded requests, ensuring a clean slate for each test.
 */
    @BeforeEach
    void init() {
        wireMock.resetAll();
        timeZoneService = new TimeZoneService(serverPath + europePath);
    }

    /*
    Unit test to simulate a service call by returning a resposne with status code 200
    and a body containing the expected string
     */

    @Test
    void getAvailableTimezoneText_timeApiReturnsStringList_returnsCountriesAsString() {
        wireMock.stubFor(
                get(urlEqualTo(serverPath + europePath))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withBody("[\"Amsterdam\", \"Andorra\", \"Astrakhan\", \"Athens\"]")
                        )
        );

        String expected = timeZoneService.getAvailableTimezoneText("Europe");
        
        assertTrue(expected.contains("Available timezones in Europe are Europe/Andorra,"));
    }
}
