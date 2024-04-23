package com.expeditors.trackservice.controllers;

import com.expeditors.trackservice.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackController {

    private TrackService trackService;

    @GetMapping("")
    public ResponseEntity<?> getAllTracks(){
        return ResponseEntity
                .ok()
                .body(trackService.getAllEntities());
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<?> getTrackById(
            @PathVariable int trackId){

        var optTrack = trackService.getEntityById(trackId);
        if(optTrack.isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(optTrack.get());
    }
}
