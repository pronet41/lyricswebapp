package com.example.lyricswebapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phrases")
public class PhraseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String phrase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private SongModel song;

    @Column(nullable = false)
    private int startIndex; // The index of the first word of the phrase in the song.

    @Column(nullable = false)
    private int length; // Length of the phrase (in words).

    // Getters and setters
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

    public SongModel getSong() {
        return song;
    }

    public void setSong(SongModel song) {
        this.song = song;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
