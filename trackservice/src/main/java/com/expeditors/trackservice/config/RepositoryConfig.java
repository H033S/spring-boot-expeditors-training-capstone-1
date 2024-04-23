package com.expeditors.trackservice.config;

import com.expeditors.trackservice.domain.Artist;
import com.expeditors.trackservice.domain.Track;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RepositoryConfig {

    @Bean
    public Map<Integer, Artist> getArtistMap(){
        return new ConcurrentHashMap<>();
    }

    @Bean
    public Map<Integer, Track> getTrackMap(){
        return new ConcurrentHashMap<>();
    }
}
