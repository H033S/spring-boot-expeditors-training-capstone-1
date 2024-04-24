package com.expeditors.trackservice.controllers;

import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.dto.ArtistResponse;
import com.expeditors.trackservice.dto.TrackResponse;
import com.expeditors.trackservice.service.ArtistService;
import com.expeditors.trackservice.service.PricingProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final PricingProvider pricingProvider;

    public ArtistController(
            ArtistService artistService,
            PricingProvider pricingProvider) {

        this.artistService = artistService;
        this.pricingProvider = pricingProvider;
    }

    @GetMapping
    public ResponseEntity<?> getAllArtists(){

        return ResponseEntity
                .ok()
                .body(artistService.getAllEntities()
                        .stream()
                        .map(a -> ArtistResponse.fromArtist(a, pricingProvider.getPrice()))
                        .toList());
    }

    @GetMapping("/by/id/{artistId}")
    public ResponseEntity<?> getArtistById(
            @PathVariable int artistId){

        return artistService.getEntityById(artistId)
                .map(
                        a -> ResponseEntity
                                .ok()
                                .body(ArtistResponse.fromArtist(a, pricingProvider.getPrice())))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by/name/{artistName}")
    public ResponseEntity<?> getArtistById(
            @PathVariable String artistName){

        return ResponseEntity
                .ok()
                .body(
                        artistService.getArtistByName(artistName)
                                .stream()
                                .map(a -> ArtistResponse.fromArtist(a, pricingProvider.getPrice()))
                                .toList()
                );

    }

    @GetMapping("/{artistId}/tracks")
    public ResponseEntity<?> getTrackForArtist(
            @PathVariable int artistId ){

        return ResponseEntity
                .ok()
                .body(
                        artistService.getTracksByArtistId(artistId)
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }




}
