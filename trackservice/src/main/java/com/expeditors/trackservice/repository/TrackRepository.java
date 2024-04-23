package com.expeditors.trackservice.repository;

import com.expeditors.trackservice.domain.MediaType;
import com.expeditors.trackservice.domain.Track;

import java.util.List;

public interface TrackRepository
        extends BaseRepository<Track> {

    List<Track> getTracksByMediaType(MediaType mediaType);
    List<Track> getTracksByYear(int year);
    List<Track> getTracksByArtist(int artistId);
    List<Track> getTracksByDurationGreaterThan(double duration);
    List<Track> getTracksByDurationEqualsTo(double duration);
    List<Track> getTracksByDurationLessThan(double duration);
}