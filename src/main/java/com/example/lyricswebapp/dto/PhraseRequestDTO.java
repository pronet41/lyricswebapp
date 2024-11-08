package com.example.lyricswebapp.dto;

public class PhraseRequestDTO {
    private Long songId;
    private String phrase;

    // Getters and setters
    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
