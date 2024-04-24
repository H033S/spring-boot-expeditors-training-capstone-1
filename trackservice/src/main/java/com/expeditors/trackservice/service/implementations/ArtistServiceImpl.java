package com.expeditors.trackservice.service.implementations;

import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.domain.Track;
import com.expeditors.trackservice.repository.ArtistRepository;
import com.expeditors.trackservice.service.AbstractBaseService;
import com.expeditors.trackservice.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArtistServiceImpl
        extends AbstractBaseService<Artist>
        implements ArtistService {

    private final ArtistRepository repository;

    public ArtistServiceImpl(ArtistRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Artist> getArtistByName(String firstName) {
        return repository.getArtistByName(firstName);
    }

    @Override
    public List<Track> getTracksByArtistId(int id) {
        return repository.getTracksByArtistId(id);
    }
}
