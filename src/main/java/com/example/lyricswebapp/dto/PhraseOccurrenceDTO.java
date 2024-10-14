package com.example.lyricswebapp.dto;

public class PhraseOccurrenceDTO {
    private String phrase;
    private Long occurrences;

    public PhraseOccurrenceDTO(String phrase, Long occurrences) {
        this.phrase = phrase;
        this.occurrences = occurrences;
    }

    public Long getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Long occurrences) {
        this.occurrences = occurrences;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}