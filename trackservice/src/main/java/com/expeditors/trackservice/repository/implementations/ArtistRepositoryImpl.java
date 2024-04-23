package com.expeditors.trackservice.repository.implementations;

import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.repository.ArtistRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ArtistRepositoryImpl
        extends AbstractBaseInMemoryRepository<Artist>
        implements ArtistRepository {

    public ArtistRepositoryImpl(Map<Integer, Artist> artistList) {
        super(artistList);
    }


    @Override
    public boolean updateEntity(Artist entity) {
        return super.updateEntity(entity);
    }

    @Override
    public boolean deleteEntity(int id) {
        return super.deleteEntity(id);
    }
}
