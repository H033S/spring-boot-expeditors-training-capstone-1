package com.expeditors.trackservice.domain;

public enum MediaType {
    OGG("Ogg"),
    MP3("Mp3"),
    FLAC("Flac"),
    WAV("Wav");

    private final String text;

    MediaType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
