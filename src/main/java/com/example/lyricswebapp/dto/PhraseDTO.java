package com.example.lyricswebapp.dto;

public class PhraseDTO {

    private Long id;
    private String phrase;

    public PhraseDTO() {}

    public PhraseDTO(Long id, String phrase) {
        this.id = id;
        this.phrase = phrase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}