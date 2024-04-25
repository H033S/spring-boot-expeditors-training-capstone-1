package com.expeditors.trackservice.dto;

import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.domain.MediaType;
import com.expeditors.trackservice.domain.Track;
import com.expeditors.trackservice.service.ArtistService;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrackRequest {

    private String title;
    private String album;
    private double durationInMinutes;
    private MediaType type;
    private LocalDate issueDate;
    private List<Integer> artistIds;


    public Track toTrack(
            Set<Artist> artistList,
            double price) {

        return Track.builder()
                .title(title)
                .album(album)
                .durationInMinutes(durationInMinutes)
                .type(type)
                .issueDate(issueDate)
                .artistList(artistList)
                .build();
    }
}
