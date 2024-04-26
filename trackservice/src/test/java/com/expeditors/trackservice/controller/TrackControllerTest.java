package com.expeditors.trackservice.controller;

import com.expeditors.trackservice.controllers.TrackController;
import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.domain.MediaType;
import com.expeditors.trackservice.domain.Track;
import com.expeditors.trackservice.dto.TrackResponse;
import com.expeditors.trackservice.service.PricingProvider;
import com.expeditors.trackservice.service.TrackService;
import com.expeditors.trackservice.service.implementations.PricingProviderClient;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.github.tomakehurst.wiremock.client.WireMock;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TrackControllerTest {

    @Autowired
    TrackController trackController;
    @MockBean
    TrackService trackService;

    private LocalDate date1 = LocalDate.now().minusYears(1);
    private LocalDate date2 = LocalDate.now().minusYears(2);
    private LocalDate date3 = LocalDate.now().minusYears(3);

    private Track track1 = Track.builder().issueDate(date1).type(MediaType.WAV).album("A1").title("T1").durationInMinutes(2).artistList(new HashSet<>()).build();
    private Track track2 = Track.builder().issueDate(date1).type(MediaType.WAV).album("A2").title("T2").durationInMinutes(2).artistList(new HashSet<>()).build();
    private Track track3 = Track.builder().issueDate(date2).type(MediaType.MP3).album("A3").title("T3").durationInMinutes(3).artistList(new HashSet<>()).build();
    private Track track4 = Track.builder().issueDate(date3).type(MediaType.MP3).album("A4").title("T4").durationInMinutes(3).artistList(new HashSet<>()).build();

    private Artist artist1 = Artist.builder().firstName("F1").lastName("L1").trackList(new HashSet<>()).build();
    private Artist artist2 = Artist.builder().firstName("F2").lastName("L2").trackList(new HashSet<>()).build();
    private Artist artist3 = Artist.builder().firstName("F3").lastName("L3").trackList(new HashSet<>()).build();
    private Artist artist4 = Artist.builder().firstName("F4").lastName("L4").trackList(new HashSet<>()).build();


    @Test
    void getAllTracks() {
        var l1 = List.of(track1, track2);

        Mockito.doReturn(l1)
                .when(trackService).getAllEntities();

        ResponseEntity<?> response = trackController.getAllTracks();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Class<List<TrackResponse>> clazz = null;

        assertInstanceOf(List.class, response.getBody());
        assertThat((List<?>)response.getBody(), hasSize(2));
    }
}
