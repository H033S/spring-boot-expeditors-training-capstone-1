package com.expeditors.trackservice.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static java.lang.StringTemplate.STR;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Track extends Entity {

    private String title;
    private String album;
    private LocalDate issueDate;
    private double durationInMinutes;
    private MediaType type;
    private List<Artist> artistList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;

        return Double.compare(durationInMinutes, track.durationInMinutes) == 0 &&
                Objects.equals(title, track.title) &&
                Objects.equals(album, track.album) &&
                Objects.equals(issueDate, track.issueDate) &&
                type.equals(track.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, album, issueDate, durationInMinutes, type);
    }

    @Override
    public String toString() {
        return STR."Track{title= \{title} }";
    }
}
