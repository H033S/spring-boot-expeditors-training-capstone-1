package com.expeditors.trackservice.domain;

import com.expeditors.trackservice.domain.Entity;
import com.expeditors.trackservice.domain.Track;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artist extends Entity {

    private String firstName;
    private String lastName;
    List<Track> trackList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return  Objects.equals(firstName, artist.firstName) &&
                Objects.equals(lastName, artist.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
