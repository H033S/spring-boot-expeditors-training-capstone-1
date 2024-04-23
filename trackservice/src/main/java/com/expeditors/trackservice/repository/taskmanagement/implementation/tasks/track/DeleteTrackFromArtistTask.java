package com.expeditors.trackservice.repository.taskmanagement.implementation.tasks.track;

import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.domain.Track;
import com.expeditors.trackservice.repository.taskmanagement.Task;

import java.util.Optional;

public class DeleteTrackFromArtistTask implements Task {

    private final Artist artist;
    private final int trackId;
    private Optional<Track> trackRemoved;

    public DeleteTrackFromArtistTask(Artist artist, int trackId) {
        this.artist = artist;
        this.trackId = trackId;
    }

    @Override
    public boolean process() {
        trackRemoved = artist.getTrackList()
                .stream()
                .filter(t -> t.getId() == trackId)
                .findAny();

        if(trackRemoved.isEmpty()) return false;
        return artist.getTrackList().removeIf(t -> t.getId() == trackId);
    }

    @Override
    public boolean revert() {

        if(trackRemoved.isEmpty()){
            return false;
        }
        return artist.getTrackList().add(trackRemoved.get());
    }
}
