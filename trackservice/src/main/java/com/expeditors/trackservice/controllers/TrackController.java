package com.expeditors.trackservice.controllers;

import com.expeditors.trackservice.domain.MediaType;
import com.expeditors.trackservice.dto.ArtistResponse;
import com.expeditors.trackservice.dto.TrackResponse;
import com.expeditors.trackservice.service.PricingProvider;
import com.expeditors.trackservice.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackService trackService;
    private final PricingProvider pricingProvider;

    public TrackController(
            TrackService trackService,
            PricingProvider pricingProvider) {

        this.trackService = trackService;
        this.pricingProvider = pricingProvider;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTracks(){
        return ResponseEntity
                .ok()
                .body(
                        trackService.getAllEntities()
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }

    @GetMapping("/by/id/{trackId}")
    public ResponseEntity<?> getTrackById(
            @PathVariable int trackId){

       return trackService.getEntityById(trackId)
               .map(t -> ResponseEntity
                       .ok()
                       .body(TrackResponse.fromTrack(t, pricingProvider.getPrice())))
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by/type/{mediaType}")
    public ResponseEntity<?> getTrackByMediaType(
            @PathVariable MediaType mediaType){

        return ResponseEntity
                .ok()
                .body(
                        trackService.getTracksByMediaType(mediaType)
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }

    @GetMapping("/by/year/{year}")
    public ResponseEntity<?> getTrackByYear(
            @PathVariable int year){

        return ResponseEntity
                .ok()
                .body(
                        trackService.getTracksByYear(year)
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }

    @GetMapping("/{trackId}/artists")
    public ResponseEntity<?> getTrackByArtistId(
            @PathVariable int trackId){

        return ResponseEntity
                .ok()
                .body(
                        trackService.getArtistByTracks(trackId)
                                .stream()
                                .map(a -> ArtistResponse.fromArtist(a, pricingProvider.getPrice()))
                                .toList()
                );
    }

    @GetMapping("/by/duration/greaterthan/{duration}")
    public ResponseEntity<?> getTracksByDurationGreaterThan(
            @PathVariable double duration){

        return ResponseEntity
                .ok()
                .body(
                        trackService.getTracksByDurationGreaterThan(duration)
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }

    @GetMapping("/by/duration/lessthan/{duration}")
    public ResponseEntity<?> getTracksByDurationLessThan(
            @PathVariable double duration){

        return ResponseEntity
                .ok()
                .body(
                        trackService.getTracksByDurationLessThan(duration)
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }

    @GetMapping("/by/duration/equals/{duration}")
    public ResponseEntity<?> getTracksByDurationEqualsTo(
            @PathVariable double duration){

        return ResponseEntity
                .ok()
                .body(
                        trackService.getTracksByDurationEqualsTo(duration)
                                .stream()
                                .map(t -> TrackResponse.fromTrack(t, pricingProvider.getPrice()))
                                .toList()
                );
    }
}
